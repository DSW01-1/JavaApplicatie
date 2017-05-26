package main.java.pane.tab;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import main.java.algorithms.tsp.ScissorEdge;
import main.java.constant.Constants;
import main.java.graphs.grid.MainAppGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.BackToMainMenuButton;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotTspTab extends StyledPane
{
	private MainAppGrid grid;

	public RobotTspTab()
	{
		CreateGrid();
		CreateControlButtons();
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
		{ "btn.start", "btn.pause", "btn.reset", "btn.hardEmpty", "btn.softEmpty" };

		Runnable[] codeArray = new Runnable[buttonNameArray.length];

		codeArray[0] = () -> System.out.println("");
		codeArray[1] = () -> System.out.println("");
		codeArray[2] = () -> System.out.println("");
		codeArray[3] = () -> System.out.println("");
		codeArray[4] = () -> System.out.println("");

		for (int i = 0; i < 5; i++)
		{
			StyledButton styledButton = new StyledButton(buttonNameArray[i], codeArray[i]);
			gridPane.add(styledButton, 0, i);
		}
	}

	/**
	 * Setup the robot pos and calculate the shortestPath
	 * 
	 * @param products
	 */
	public void Setup(ArrayList<Product> products)
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

		grid.Redraw(shortestPath, products);
	}
}
