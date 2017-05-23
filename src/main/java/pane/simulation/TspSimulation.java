package main.java.pane.simulation;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;
import main.java.algorithms.tsp.HungarianAssignment;
import main.java.algorithms.tsp.NearestNeighbour;
import main.java.algorithms.tsp.ScissorEdge;
import main.java.algorithms.tsp.TotalEnumeration;
import main.java.constant.Constants;
import main.java.graphs.grid.GridTile;
import main.java.graphs.grid.TSPGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledChoiceBox;

public class TspSimulation extends BaseSimulation
{
	public TSPGrid grid;
	private boolean isInteractive = true;
	public ConsolePane consolePane;
	public ProgressBar progression;
	private TotalEnumeration algorithm = new TotalEnumeration(this);
	private SimulationControls simulationControls;

	public TspSimulation()
	{
		super();
		getStyleClass().add("background");
		setPrefHeight(ScreenProperties.getScreenHeight() - 92);
		AddControls();

		// Add a default grid
		AddGrid(Constants.baseWareHouseSize);
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

		simulationControls = new SimulationControls(algorithmNames, this);

		getChildren().add(simulationControls);

		addColorChoicebox();
		addGridSizeChoicebox();
	}

	/**
	 *
	 * @param size
	 */
	public void AddGrid(int size)
	{
		if (grid != null)
		{
			getChildren().remove(grid);
		}

		grid = new TSPGrid(size, isInteractive);

		grid.setLayoutX((ScreenProperties.getScreenWidth() / 2) - Constants.drawnGridSize / 2);
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

			if (activeTiles.size() > 0)
			{
				addConsoleItem(String.format("%s coordinates found, starting algorithm", activeTiles.size()), "ALERT");

				long startTime = System.nanoTime();
				NearestNeighbour algoritme = new NearestNeighbour(activeTiles);
				ArrayList<Vector2> shortestPath = algoritme.getShortestPath();

				long stopTime = System.nanoTime();
				addConsoleItem("Kortste pad gevonden", "INFO");

				float duration = (stopTime - startTime) / 100000f;
				addConsoleItem("duration: " + duration + " ms", "INFO");
				addConsoleItem("Total path distance: " + CalculatePathLength(shortestPath), "INFO");

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

	// Own Algorithm, ScissorEdge
	@Override
	public void ExecuteAlgorithmFour()
	{
		consolePane.getItems().clear();
		ArrayList<GridTile> activeTiles = grid.getSelectedTiles();

		if (activeTiles.size() > 0)
		{
			ScissorEdge twoWayLeft = new ScissorEdge(grid.GetTileAmount(), 0);
			ScissorEdge twoWayRight = new ScissorEdge(grid.GetTileAmount(), 1);

			ArrayList<Vector2> pointList1 = new ArrayList<Vector2>();
			ArrayList<Vector2> pointList2 = new ArrayList<Vector2>();

			for (GridTile tile : activeTiles)
			{
				pointList1.add(tile.GetPos());
				pointList2.add(tile.GetPos());
			}

			// Try Left
			long startTime = System.nanoTime();
			ArrayList<Vector2> shortestPathLeft = twoWayLeft.GetShortestPath(pointList1);
			long stopTime = System.nanoTime();

			float durationRight = (stopTime - startTime) / 100000f;
			addConsoleItem("duration left: " + durationRight + " ms", "INFO");
			addConsoleItem("Total path distance left: " + CalculatePathLength(shortestPathLeft), "INFO");

			// Try Right
			startTime = System.nanoTime();
			ArrayList<Vector2> shortestPathRight = twoWayRight.GetShortestPath(pointList2);
			stopTime = System.nanoTime();

			float durationLeft = (stopTime - startTime) / 100000f;
			addConsoleItem("duration right: " + durationLeft + " ms", "INFO");
			addConsoleItem("Total path distance right: " + CalculatePathLength(shortestPathRight), "INFO");

			addConsoleItem("Total duration: " + (durationLeft + durationRight) + " ms", "INFO");

			ArrayList<Vector2> shortestPath = CalculatePathLength(shortestPathLeft) < CalculatePathLength(
					shortestPathRight) ? shortestPathLeft : shortestPathRight;

			grid.SetAlgorithm(twoWayLeft);
			grid.Redraw(shortestPath);
			grid.SetAlgorithm(null);
		}
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

	public void clearField()
	{
		for (GridTile tile : grid.getSelectedTiles())
		{
			tile.SetSelected(false);
		}
		grid.resetPathList();
		grid.Redraw();
	}

	public void addConsoleItem(String message, String msgType)
	{
		consolePane.getItems().add(consolePane.getItems().size(), String.format("[%s] %s", msgType, message));
	}

	private void addColorChoicebox()
	{
		String[] colorOptions =
		{ "black", "Red", "Green", "blue" };

		StyledChoiceBox cb = new StyledChoiceBox(colorOptions, new Vector2(340, 250), new Vector2(100, 25));
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
				default:
					grid.SetGridColor("black");
					break;
				}
			}
		});
		getChildren().add(cb);
	}

	private void addGridSizeChoicebox()
	{
		String[] numberOptions = new String[18];

		for (int i = 0; i < numberOptions.length; i++)
		{
			numberOptions[i] = Integer.toString(i + 3);
		}

		StyledChoiceBox choiceBox = new StyledChoiceBox(numberOptions, new Vector2(445, 250), new Vector2(50, 25));
		choiceBox.getSelectionModel().selectFirst();

		choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
		{
			public void changed(ObservableValue<? extends Number> ov, Number value, Number new_value)
			{
				AddGrid(Integer.parseInt(choiceBox.getItems().get((Integer) new_value)));
			}
		});
		getChildren().add(choiceBox);
		choiceBox.getSelectionModel().select(2);
	}

	public void changeProgression(int progress)
	{
		progression.setProgress(progress);
	}

	public static double CalculatePathLength(ArrayList<Vector2> shortestPath)
	{
		// Calculate the total path distance
		double totalPathDistance = 0;

		for (int i = 0; i < shortestPath.size() - 1; i++)
		{
			double x = (shortestPath.get(i).getX() - shortestPath.get(i + 1).getX());
			double y = (shortestPath.get(i).getY() - shortestPath.get(i + 1).getY());

			totalPathDistance += Math.sqrt(x * x + y * y);
		}

		return totalPathDistance;
	}

}
