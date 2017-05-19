package main.java.algorithms.tsp;

import java.util.ArrayList;

import javafx.application.Platform;
import main.java.graphs.grid.DistanceGrid;
import main.java.graphs.grid.GridTile;
import main.java.main.Vector2;
import main.java.pane.simulation.TspSimulation;

public class TotalEnumeration extends Thread
{
	/* Variables */
	private ArrayList<GridTile> tileList;
	private ArrayList<ArrayList<Integer>> possibilities;

	private EnumPath shortestPath;
	private DistanceGrid dG;
	private TspSimulation simulation;

	private boolean logging = false;
	private double pathLength = 0;

	private int possiblePaths;
	private int factor = 0;
	private int progress = 0;
	private int state = 0; // 0 = inactive, 1 = suspended, 2 = active

	/* Controls */
	public synchronized void suspendII()
	{
		state = 1;
		notify();
	}

	public synchronized void resumeII()
	{
		state = 2;
		notify();
	}

	public synchronized void killII()
	{
		state = 0;
		notify();
		cleanKill();
	}

	/* Constructors incl overload */
	public TotalEnumeration(ArrayList<GridTile> tileList)
	{
		this.tileList = tileList;
		this.simulation = new TspSimulation();
		dG = new DistanceGrid(tileList);
	}

	public TotalEnumeration(TspSimulation simulation)
	{
		this.simulation = simulation;
		this.logging = true;
		this.tileList = new ArrayList<GridTile>();
		dG = new DistanceGrid(tileList);
	}

	public TotalEnumeration(ArrayList<GridTile> tileList, TspSimulation simulation)
	{
		this.tileList = tileList;
		this.simulation = simulation;
		logging = true;
		dG = new DistanceGrid(tileList);
	}

	/* Getters and/or Setters */
	public ArrayList<Vector2> getShortestPath()
	{
		return shortestPath.getTiles();
	}

	public void setTileList(ArrayList<GridTile> tileList)
	{
		this.tileList = tileList;
		dG = new DistanceGrid(tileList);
	}

	public int getPossiblePaths()
	{
		return possiblePaths;
	}

	public void showPermutations(int startindex, int[] input)
	{
		permute(input, 0);
	}

	public int showState()
	{
		return state;
	}

	/* THREAD RUN method */
	public void run()
	{
		// Algoritme voorbereiden
		state = 2;
		long startTime = System.nanoTime();
		if (logging)
		{
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					simulation.addConsoleItem("Starting thread", "ALERT");
				}
			});
		}

		// Structuur van het pad opzetten
		int[] tileIndexes = new int[tileList.size()];
		for (int i = 0; i < tileList.size(); i++)
		{
			tileIndexes[i] = i;
		}

		// Factor berekenen (aantal mogelijke paden)
		if (state != 0)
		{
			setFactor(tileList.size());
			if (logging)
			{
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						simulation.addConsoleItem(factor + " Possible paths", "INFO");
					}
				});
			}
			showPermutations(0, tileIndexes);
		}

		// Kortste pad zoeken
		ArrayList<Vector2> shortestPath = new ArrayList<Vector2>();
		if (state != 0)
		{
			shortestPath = this.getShortestPath();
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					simulation.addConsoleItem("Applying path to the grid..", "INFO");
				}
			});
			simulation.updatePath(shortestPath);
			if (logging)
			{
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						simulation.addConsoleItem("Finished configuring 'total enumeration' algorithm", "INFO");
					}
				});
			}
		}

		// Algoritme afronden
		long stopTime = System.nanoTime();
		long duration = (stopTime - startTime) / 100000;
		if (logging)
		{
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					String showDuration = (duration < 1) ? "duration: less then a ms" : "duration: " + duration + " ms";
					simulation.addConsoleItem(showDuration, "INFO");
				}
			});

		}

		// Progress bar resetten
		simulation.changeProgression(0);
		simulation.grid.setActive(false);
	}

	/* MISC */
	public void processShortestPath(int[] indexList)
	{

		double totalLength = 0;
		ArrayList<Vector2> tileCoordinates = new ArrayList<>();

		int lastIndex = 0;

		// loop through indexes
		for (int index : indexList)
		{

			// get difference
			double xyDiff = dG.distanceGrid[lastIndex][index];

			lastIndex = index;
			tileCoordinates.add(new Vector2(tileList.get(index).getXcoord(), tileList.get(index).getYcoord()));

			totalLength += xyDiff;
		}

		int lastCoorX = tileList.get(lastIndex).getXcoord();
		int lastCoorY = tileList.get(lastIndex).getYcoord();
		double xyDiff = Math.hypot(lastCoorX, lastCoorY);
		totalLength += xyDiff;

		tileCoordinates.add(new Vector2(lastCoorX, lastCoorY));

		if (totalLength < this.pathLength || this.pathLength == 0)
		{
			this.pathLength = totalLength;
			shortestPath = new EnumPath(this.pathLength, tileCoordinates);

		}

		if (logging)
		{
			double calc = (double) this.progress / factor;
			simulation.progression.setProgress(calc);
		}

	}

	private void permute(int[] a, int k)
	{

		synchronized (this)
		{
			while (state == 1)
			{
				try
				{
					wait();
					// The current thread will block until someone else calls
					// notify(),Then if _suspended is false, it keeps looping
					// the for loop
				}
				catch (Exception ex)
				{
					//TODO, gebruik geen System.out, en zeker geen "Shit failed". Gebruik LogHandler
					System.out.println("Shit failed");
				}

			}
		}
		if (state != 0)
		{
			if (k == a.length)
			{
				processShortestPath(a);
				this.progress++;
			}
			else
			{
				for (int i = k; i < a.length; i++)
				{

					int temp = a[k];
					a[k] = a[i];
					a[i] = temp;
					permute(a, k + 1);
					temp = a[k];
					a[k] = a[i];
					a[i] = temp;
				}
			}
		}

	}

	public void setFactor(int m)
	{
		for (int y = (m - 1); y > 0; y--)
		{
			m = m * y;
		}
		this.factor = m;
	}

	public void cleanKill()
	{
		simulation.addConsoleItem("The process has been killed", "ALERT");
		simulation.addConsoleItem("Removing evidence..", "INFO");

		tileList = new ArrayList<GridTile>();
		simulation.addConsoleItem("Evidence succesfully removed", "INFO");
	}
}
