package main.java.pane.tab;

import javafx.scene.layout.GridPane;
import main.java.graphs.TSPGrid;
import main.java.main.ScreenProperties;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotTab extends StyledPane
{
	public RobotTab()
	{
		getChildren().add(new TSPGrid(5, false));

		CreateControlButtons();
	}

	private void CreateControlButtons()
	{
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
