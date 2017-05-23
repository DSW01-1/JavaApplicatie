package main.java.algorithms.tsp;

import java.util.ArrayList;

import javafx.application.Platform;
import main.java.graphs.grid.DistanceGrid;
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

	float estInSec = 0;
	float estTenSec = 0;
	long estStart = 0;


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
			this.estStart = System.nanoTime();
			showPermutations(0, tileIndexes);
		}

		// Calculating shortest path
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

		// Finishing algorithm
		long stopTime = System.nanoTime();
		float duration = (stopTime - startTime) / 100000f;
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

		// ETA
		if(this.progress == 0){
			long estStop = System.nanoTime();
			float duration = (estStop - estStart) / 100000f;
			estimateTimeLeft(duration);
		}else if(progress%200==0){
			calcEstTime(this.progress);
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

	public void estimateTimeLeft(float duration){
		this.estTenSec = duration;
		float calc = (factor - 1);
		float estTime = (calc * duration) / 1000000f;
		this.estInSec = estTime;
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				simulation.lblProgSec.setText(String.format("%s sec",Math.round(estTime)));
			}
		});

	}

	public void calcEstTime(int index){
		float calc = (factor - (index + 1));
		float estTime = (calc * estTenSec) / 1000000f;
		this.estInSec = estTime;
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				simulation.lblProgSec.setText(String.format("%s sec",Math.round(estTime)));
			}
		});	}
}
