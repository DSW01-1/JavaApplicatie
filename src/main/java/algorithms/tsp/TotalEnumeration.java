package main.java.algorithms.tsp;

import java.util.ArrayList;

import javafx.application.Platform;
import main.java.graphs.DistanceGrid;
import main.java.graphs.grid.GridTile;
import main.java.main.Vector2;
import main.java.pane.simulation.TspSimulation;

public class TotalEnumeration extends Thread
{
	// Variables
	private ArrayList<GridTile> tileList;
	ArrayList<ArrayList<Integer>> possibilities;

	private EnumPath shortestPath;
	private DistanceGrid dG;
	private TspSimulation simulation;

	private boolean logging = false;
	private double pathLength = 0;

	private int possiblePaths;
	private int factor = 0;
	private int progress = 0;
	private int state = 0; // 0 = inactive, 1 = suspended, 2 = active

	private int frameCounter = 0;

	// Algorithm controls
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


	// Constructors
	public TotalEnumeration(ArrayList<GridTile> tileList)
	{
		this.tileList = tileList;
		this.simulation = new TspSimulation();
		dG = new DistanceGrid(tileList);
	}

	public TotalEnumeration(TspSimulation simulation)
	{
		this(new ArrayList<GridTile>(), simulation);
	}

	public TotalEnumeration(ArrayList<GridTile> tileList, TspSimulation simulation)
	{
		this.tileList = tileList;
		this.simulation = simulation;
		logging = true;
		dG = new DistanceGrid(tileList);
	}


	// getters and/or setters
	public ArrayList<Vector2> getShortestPath()
	{
		return shortestPath.getTiles();
	}
	public void setTileList(ArrayList<GridTile> tileList) {
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


	// Methods
	public int showState()
	{
		return state;
	}


	// Main thread
	public void run()
	{
		// preparing algorithm
		state = 2;
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

		// setting up path structure
		int[] tileIndexes = new int[tileList.size()];
		for (int i = 0; i < tileList.size(); i++)
		{
			tileIndexes[i] = i;
		}

		// Calculate amount of "possible" paths

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
		}


		/* ================= ALGORITHM =============================== */
		long startTime = System.nanoTime();

		// Calculating shortest path
		if(state != 0){
			showPermutations(0, tileIndexes);
		}
		/* ================= ALGORITHM =============================== */


		// Applying shortest path
		ArrayList<Vector2> shortestPath = new ArrayList<Vector2>();
		if (state != 0)
		{
			shortestPath = this.getShortestPath();
			simulation.updatePath(shortestPath);
		}


		// Finishing algorithm
		long stopTime = System.nanoTime();
		float duration = ( (stopTime - startTime) / 1000000f) / 1000f; // naar sec
		ArrayList<Vector2> pathLength = shortestPath;

		if (logging)
		{
			Platform.runLater(new Runnable()
			{
				@Override
				public void run()
				{
					simulation.addConsoleItem("duration: " + duration + " ms", "INFO");
					simulation.addConsoleItem("Total path distance: " + TspSimulation.CalculatePathLength(pathLength),
							"INFO");
				}
			});

		}

		// Progress bar resetten
		simulation.changeProgression(0);
		simulation.grid.setActive(false);
	}


	// Misc
	/* ====================================| Process shortest path |=========================================== */
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

		if(simulation.isFancy.isSelected()){
			EnumPath curPath = new EnumPath(this.pathLength, tileCoordinates);

			if(frameCounter >= 50000){
				simulation.updatePath(curPath.getTiles());
				frameCounter = 0;
			}else{
				frameCounter++;
			}
		}

		if (logging)
		{
			double calc = (double) this.progress / factor;
			simulation.progression.setProgress(calc);
		}
	}


	void permute(int[] a, int k)
	{
		synchronized (this)
		{
			while (state == 1)
			{
				try
				{
					wait();
				}
				catch (Exception ex)
				{
					System.out.println("Something went wrong");
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
