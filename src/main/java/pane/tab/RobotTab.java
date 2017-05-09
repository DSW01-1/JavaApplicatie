package main.java.pane.tab;

import javafx.scene.layout.GridPane;
import main.java.graphs.Grid;
import main.java.main.Language;
import main.java.main.ScreenProperties;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotTab extends StyledPane
{
	private Grid grid;

	public RobotTab()
	{
		grid = new Grid(false);
		getChildren().add(grid);

		CreateControlButtons();
	}

	private void CreateControlButtons()
	{
		GridPane gridPane = new GridPane();
		gridPane.setLayoutX(ScreenProperties.getScreenWidth() / 4 * 3);
		gridPane.setLayoutY(ScreenProperties.getScreenHeight() / 8);
		gridPane.setVgap(15);

		String[] buttonNameArray =
		{ "btn.start", "btn.pause", "btn.reset", "btn.hardEmpty", "btn.softEmpty" };

		Runnable[] codeArray = new Runnable[buttonNameArray.length];

		codeArray[0] = new Runnable()
		{
			public void run()
			{
				// TODO Auto-generated method stub
			}
		};

		codeArray[1] = new Runnable()
		{
			public void run()
			{
				// TODO Auto-generated method stub
			}
		};

		codeArray[2] = new Runnable()
		{
			public void run()
			{
				// TODO Auto-generated method stub
			}
		};

		codeArray[3] = new Runnable()
		{
			public void run()
			{
				// TODO Auto-generated method stub
			}
		};

		codeArray[4] = new Runnable()
		{
			public void run()
			{
				// TODO Auto-generated method stub
			}
		};

		for (int i = 0; i < 5; i++)
		{
			StyledButton styledButton = new StyledButton(Language.getTranslation(buttonNameArray[i]), codeArray[i]);
			gridPane.add(styledButton, 0, i);

		}

		getChildren().add(gridPane);

	}
}
