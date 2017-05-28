package main.java.pane.tab;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import main.java.algorithms.tsp.ScissorEdge;
import main.java.constant.ArduinoConstants;
import main.java.constant.Constants;
import main.java.controller.ArduinoController;
import main.java.graphs.StatusCanvas;
import main.java.graphs.grid.MainAppGrid;
import main.java.handler.LogHandler;
import main.java.main.Command;
import main.java.main.ConnectionStatus;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.BackToMainMenuButton;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;
import main.java.pane.simulation.ConsolePane;

public class RobotTspTab extends StyledPane
{
	private MainAppGrid grid;
	private ArduinoController controller;
	public StatusCanvas statusCanvas;
	private ConsolePane consolePane;

	public RobotTspTab()
	{
		CreateGrid();
		CreateControlButtons();
		CreateStatusCanvas();
	}

	private void CreateStatusCanvas()
	{
		statusCanvas = new StatusCanvas(new Vector2(ScreenProperties.getScreenWidth() - 150, 15), 60);
		getChildren().add(statusCanvas);
	}

	/**
	 * Create and setup the grid
	 */
	private void CreateGrid()
	{
		grid = new MainAppGrid();
		grid.setLayoutX(ScreenProperties.getScreenWidth() / 2 - Constants.drawnGridSize / 2);
		grid.setLayoutY(15);
		getChildren().add(grid);

		Vector2 pos = new Vector2(ScreenProperties.getScreenWidth() / 2 - Constants.drawnGridSize / 2,
				Constants.drawnGridSize + 20);

		StyledButton togglePathButton = new StyledButton("btn.toggleTspPath", pos);
		getChildren().add(togglePathButton);
	}

	/**
	 * Create the control buttons
	 */
	private void CreateControlButtons()
	{
		getChildren().add(new BackToMainMenuButton());

		GridPane gridPane = new GridPane();
		gridPane.setLayoutX(ScreenProperties.getScreenWidth() / 4 * 3);
		gridPane.setLayoutY(ScreenProperties.getScreenHeight() / 8);
		gridPane.setVgap(15);
		getChildren().add(gridPane);

		String[] buttonNameArray =
		{ "btn.start", "btn.pause", "btn.reset" };

		Runnable[] codeArray = new Runnable[buttonNameArray.length];

		String[] commandArray =
		{ ArduinoConstants.cmdStart, ArduinoConstants.cmdPause, ArduinoConstants.cmdReset };

		for (int i = 0; i < buttonNameArray.length; i++)
		{
			final int j = i;
			StyledButton styledButton = new StyledButton(buttonNameArray[i], codeArray[i]);
			styledButton.setOnAction(event ->
			{
				boolean didOutput = controller.HandleOutput(new Command(commandArray[j]));
				statusCanvas.SetStatus(didOutput ? ConnectionStatus.ACTIVE : ConnectionStatus.INACTIVE);
			});
			gridPane.add(styledButton, 0, i);
		}
	}

	/**
	 * Setup the robot pos and calculate the shortestPath
	 * 
	 * @param products
	 */
	public void Setup(ArduinoController controller, ArrayList<Product> products)
	{
		this.controller = controller;
		CreateConsolePane();
		CalculateAndDrawPath(products);

	}

	private void CreateConsolePane()
	{
		consolePane = new ConsolePane();
		getChildren().add(consolePane);
		controller.AddConsole(consolePane);
	}

	/**
	 * Calculate and draw the TSP path
	 * 
	 * @param products
	 */
	void CalculateAndDrawPath(ArrayList<Product> products)
	{
		ArrayList<Vector2> pointsOne = new ArrayList<Vector2>();
		ArrayList<Vector2> pointsTwo = new ArrayList<Vector2>();

		for (Product product : products)
		{
			pointsOne.add(product.GetCoords());
			pointsTwo.add(product.GetCoords());
		}

		ScissorEdge scissorEdge = new ScissorEdge(Constants.baseWareHouseSize);
		ArrayList<Vector2> shortestPathOne = scissorEdge.GetShortestPath(pointsOne);

		scissorEdge.currentIndex = 1;
		ArrayList<Vector2> shortestPathTwo = scissorEdge.GetShortestPath(pointsTwo);

		ArrayList<Vector2> shortestPath = scissorEdge.CalculatePathLength(shortestPathOne) < scissorEdge
				.CalculatePathLength(shortestPathTwo) ? shortestPathOne : shortestPathTwo;

		// Redraw the visual path
		grid.Redraw(shortestPath, products);

		try
		{
			Thread.sleep(4000);

			// Send the path to the arduino
			String coords = "";

			for (int i = 0; i < shortestPath.size(); i++)
			{
				coords = coords.concat(shortestPath.get(i).getX() + "!" + shortestPath.get(i).getY());
				if (i < shortestPath.size() - 1)
				{
					coords = coords.concat("@");
				}
			}

			Command commandToSend = new Command(ArduinoConstants.cmdDoCycle, coords);
			controller.HandleOutput(commandToSend);

		}
		catch (InterruptedException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Sleep got interrupted");
		}
	}
}
