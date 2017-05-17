package main.java.pane.simulation;

import java.util.ArrayList;

import javafx.scene.control.ProgressBar;
import main.java.algorithms.tsp.NearestNeighbour;
import main.java.algorithms.tsp.TotalEnumeration;
import main.java.constant.Constants;
import main.java.graphs.Grid;
import main.java.graphs.GridTile;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.ConsolePane;
import main.java.pane.SimulationControls;

public class TspSimulation extends BaseSimulation
{
	private Grid grid;
	private boolean isInteractive = true;
	public ConsolePane consolePane;
	public ProgressBar progression;

	public TspSimulation()
	{
		super();

		AddControls();
		AddGrid(5);
		AddConsolePane();
		AddProgressBar();
	}

	private void AddConsolePane()
	{
		consolePane = new ConsolePane();
		getChildren().add(consolePane);
	}

	private void AddProgressBar()
	{
		this.progression = new ProgressBar(0);
		progression.setLayoutX(15);
		progression.setLayoutY(550);
		progression.setPrefWidth(ScreenProperties.getScreenWidth() / 4);
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
	}

	/**
	 * 
	 * @param size
	 */
	public void AddGrid(int size)
	{
		grid = new Grid(size, isInteractive);

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
		ArrayList<GridTile> activeTiles = grid.getSelectedTiles();

		addConsoleItem("Starting Algorithm 'nearest neighbour'", "DEBUG");
		addConsoleItem("Searching for Coordinates", "DEBUG");

		// ArrayList<GridTile> activeTiles = newGrid.getSelectedTiles();
		if (activeTiles.size() > 0)
		{
			addConsoleItem(String.format("%s coordinates found, starting algorithm", activeTiles.size()), "DEBUG");
			long startTime = System.nanoTime();
			NearestNeighbour algoritme = new NearestNeighbour(activeTiles);
			ArrayList<Vector2> shortestPath = algoritme.getShortestPath();
			long stopTime = System.nanoTime();
			addConsoleItem("==========| Kortste pad gevonden |==========", "INFO");
			String coordinates = "Coordinates: ";
			for (Vector2 coordinate : shortestPath)
			{
				coordinates += String.format("(%s, %s) ", coordinate.getX(), coordinate.getY());
				addConsoleItem(String.format("Tile: x=%s, y=%s", coordinate.getX(), coordinate.getY()), "INFO");
			}
			addConsoleItem(coordinates, "INFO");
			long duration = (stopTime - startTime) / 100000;
			String showDuration = (duration < 1) ? "duration: less then a ms" : "duration: " + duration + " ms";
			addConsoleItem(showDuration, "INFO");
			grid.Redraw(shortestPath);
		}
		else
		{
			addConsoleItem(String.format("%s coordinates found, algorithm has been cancelled", activeTiles.size()),
					"DEBUG");
		}
	}

	// Hungarian Assignment
	@Override
	public void ExecuteAlgorithmTwo()
	{

	}

	// Brute Force
	@Override
	public void ExecuteAlgorithmThree()
	{
		ArrayList<GridTile> activeTiles = grid.getSelectedTiles();

		addConsoleItem("Starting Algorithm 'Total Enumeration'", "DEBUG");
		addConsoleItem("Searching for Coordinates", "DEBUG");

		if (activeTiles.size() > 0)
		{
			TotalEnumeration algoritme = new TotalEnumeration(activeTiles, this);
			algoritme.start();

			if (algoritme.getState() == Thread.State.TERMINATED)
			{
				addConsoleItem("Process has stopped unexpectly", "DEBUG");

			}
		}
		else
		{
			addConsoleItem(String.format("%s coordinates found, algorithm has been cancelled", activeTiles.size()),
					"DEBUG");
		}
	}

	// Own Algorithm
	@Override
	public void ExecuteAlgorithmFour()
	{

	}

	public void addConsoleItem(String message, String msgType)
	{
		consolePane.getItems().add(consolePane.getItems().size(), String.format("[%s] %s", msgType, message));
	}

	public void changeProgression(int progress)
	{
		progression.setProgress(progress);
	}

}
