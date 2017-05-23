package main.java.pane.tab;

import javafx.scene.layout.GridPane;
import main.java.constant.Constants;
import main.java.graphs.grid.TSPGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.BackToMainMenuButton;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotTab extends StyledPane
{
	TSPGrid grid;

	public RobotTab()
	{
		CreateGrid();
		CreateControlButtons();
	}

	private void CreateGrid()
	{
		grid = new TSPGrid(Constants.baseWareHouseSize, false);
		grid.setLayoutX(ScreenProperties.getScreenWidth() / 2 - Constants.drawnGridSize / 2);
		grid.setLayoutY(15);
		getChildren().add(grid);

		Vector2 pos = new Vector2(ScreenProperties.getScreenWidth() / 2 - Constants.drawnGridSize / 2,
				Constants.drawnGridSize + 20);

		StyledButton togglePathButton = new StyledButton("btn.toggleTspPath", pos);
		getChildren().add(togglePathButton);
	}

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
}
