package main.java.pane.simulation;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;
import main.java.algorithms.tsp.HungarianAssignment;
import main.java.algorithms.tsp.NearestNeighbour;
import main.java.algorithms.tsp.TotalEnumeration;
import main.java.algorithms.tsp.TwoWayEdgeNearestNeighbour;
import main.java.constant.Constants;
import main.java.graphs.grid.GridTile;
import main.java.graphs.grid.TSPGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.ConsolePane;
import main.java.pane.SimulationControls;
import main.java.pane.base.StyledChoiceBox;

public class TspSimulation extends BaseSimulation
{
	public TSPGrid grid;
	private boolean isInteractive = true;
	public ConsolePane consolePane;
	public ProgressBar progression;
	private TotalEnumeration algorithm = new TotalEnumeration(this);

	public TspSimulation()
	{
		super();
		getStyleClass().add("background");
		setPrefHeight(ScreenProperties.getScreenHeight() - 92);
		AddControls();
		AddGrid(5);
		AddConsolePane();
		AddProgressBar();
	}

	private void AddConsolePane()
	{
		consolePane = new ConsolePane(15, 320);
		getChildren().add(consolePane);
	}

	private void AddProgressBar()
	{
		this.progression = new ProgressBar(0);
		progression.setLayoutX(15);
		progression.setLayoutY(285);
		progression.setPrefWidth(ScreenProperties.getScreenWidth() / 4);
		progression.setPrefHeight(30);
		progression.setProgress(0);
		getChildren().add(progression);
	}

	/**
	 *
	 */
	public void AddControls()
	{
		String[] algorithmNames =
		{ "btn.nearestNeighbour", "btn.hungarianAssignment", "btn.totalEnumeration", "btn.ownAlgorithm" };

		getChildren().add(new SimulationControls(algorithmNames, this));

		addChoicebox();

	}

	/**
	 *
	 * @param size
	 */
	public void AddGrid(int size)
	{
		grid = new TSPGrid(size, isInteractive);

		grid.setLayoutX((ScreenProperties.getScreenWidth() / 2) - Constants.gridSize / 2);
		grid.setLayoutY(15);
		getChildren().add(grid);
	}

	/**
	 *
	 * @param newSize
	 */
	public void UpdateGrid(int newSize)
	{
		getChildren().remove(grid);
		AddGrid(newSize);
	}

	public void updatePath(ArrayList<Vector2> shortestPath)
	{
		grid.Redraw(shortestPath);
	}

	/**
	 *
	 * @param interactive
	 */
	public void SetInteractive(boolean interactive)
	{
		isInteractive = interactive;
	}

	// Nearest Noughbour
	@Override
	public void ExecuteAlgorithmOne()
	{
		if (grid.isActive())
		{
			addConsoleItem("Algorithm blocked, thread is already running", "ERROR");
		}
		else
		{
			consolePane.getItems().clear();
			grid.setActive(true);
			ArrayList<GridTile> activeTiles = grid.getSelectedTiles();

			addConsoleItem("Starting Algorithm 'nearest neighbour'", "INFO");
			addConsoleItem("Searching for Coordinates", "INFO");

			// ArrayList<GridTile> activeTiles = newGrid.getSelectedTiles();
			if (activeTiles.size() > 0)
			{
				addConsoleItem(String.format("%s coordinates found, starting algorithm", activeTiles.size()), "ALERT");
				long startTime = System.nanoTime();
				NearestNeighbour algoritme = new NearestNeighbour(activeTiles);
				ArrayList<Vector2> shortestPath = algoritme.getShortestPath();
				long stopTime = System.nanoTime();
				addConsoleItem("Kortste pad gevonden", "INFO");
				long duration = (stopTime - startTime) / 100000;
				String showDuration = (duration < 1) ? "duration: less then a ms" : "duration: " + duration + " ms";
				addConsoleItem(showDuration, "INFO");
				grid.Redraw(shortestPath);
			}
			else
			{
				addConsoleItem(String.format("%s coordinates found, algorithm has been cancelled", activeTiles.size()),
						"ERROR");
			}
			grid.setActive(false);
		}

	}

	// Hungarian Assignment
	@Override
	public void ExecuteAlgorithmTwo()
	{
		ArrayList<GridTile> activeTiles = grid.getSelectedTiles();

		addConsoleItem("Starting Algorithm 'Hungarian Assignment'", "DEBUG");
		addConsoleItem("Searching for Coordinates", "DEBUG");

		// ArrayList<GridTile> activeTiles = newGrid.getSelectedTiles();
		addConsoleItem(String.format("%s coordinates found, starting algorithm", activeTiles.size()), "DEBUG");

		@SuppressWarnings("unused")
		long startTime = System.nanoTime();

		HungarianAssignment algoritme = new HungarianAssignment(activeTiles);
		ArrayList<Vector2> vector = algoritme.runHungarian();
		grid.Redraw(vector);
		for (Vector2 vect : vector)
		{
			System.out.println(String.format("x: %s y: %s   ", vect.getX(), vect.getY()));
		}
	}

	// Brute Force
	@Override
	public void ExecuteAlgorithmThree()
	{
		if (algorithm.showState() == 1)
		{
			algorithm.resumeII();
			addConsoleItem("Resuming thread", "ALERT");
		}
		else
		{
			if (grid.isActive())
			{
				addConsoleItem("Algorithm blocked, thread is already running", "ERROR");
			}
			else
			{
				grid.setActive(true);
				ArrayList<GridTile> activeTiles = grid.getSelectedTiles();
				consolePane.getItems().clear();
				addConsoleItem("Starting Algorithm 'Total Enumeration'", "INFO");
				addConsoleItem("Searching for Coordinates", "INFO");

				if (activeTiles.size() > 0)
				{
					algorithm = new TotalEnumeration(activeTiles, this);
					algorithm.start();

					if (algorithm.getState() == Thread.State.TERMINATED)
					{
						addConsoleItem("Process has stopped unexpectly", "ERROR");

					}
				}
				else
				{
					addConsoleItem(
							String.format("%s coordinates found, algorithm has been cancelled", activeTiles.size()),
							"ERROR");
					grid.setActive(false);
				}
			}
		}
	}

	// Own Algorithm
	@Override
	public void ExecuteAlgorithmFour()
	{
		
	}

	@Override
	public void pauseAlgorithm()
	{
		algorithm.suspendII();
		// algorithm.pauseThread();
		addConsoleItem("Thread has been paused", "ALERT");

	}

	@Override
	public void stopAlgorithm()
	{
		addConsoleItem("Attempting to murder Thread 'algorithm' in his sleep.", "INFO");

		algorithm.killII();
	}

	public void addConsoleItem(String message, String msgType)
	{
		consolePane.getItems().add(consolePane.getItems().size(), String.format("[%s] %s", msgType, message));
	}

	private void addChoicebox()
	{
		String[] colorOptions =
		{ "black", "Red", "Green", "blue" };

		StyledChoiceBox cb = new StyledChoiceBox(colorOptions, new Vector2(215, 250), new Vector2(280, 25));
		cb.getSelectionModel().selectFirst();

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
		{
			public void changed(ObservableValue<? extends Number> ov, Number value, Number new_value)
			{
				switch (new_value.intValue())
				{
				case 0:
					grid.SetGridColor("black");
					break;
				case 1:
					grid.SetGridColor("red");
					break;
				case 2:
					grid.SetGridColor("green");
					break;
				case 3:
					grid.SetGridColor("blue");
					break;
				}

			}
		});

		getChildren().add(cb);
	}

	public void changeProgression(int progress)
	{
		progression.setProgress(progress);
	}

}
