package main.java.pane.simulation;

import main.java.constant.Constants;
import main.java.controller.ArduinoController;
import main.java.graphs.grid.TSPGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;

public class RobotSimulation extends BaseSimulation
{

	private TSPGrid grid;

	public RobotSimulation()
	{
		super();
		InitPane();
	}

	@Override
	public void InitPane()
	{
		super.InitPane();

		CreateGrid();
		CreateButtons();

	}

	private void CreateButtons()
	{
		String[] buttonNames =
		{ "btn.connectArduino", "btn.moveXAxis", "btn.moveYAxis", "btn.moveToCoord", "btn.getPackageAt",
				"btn.unloadPackage", "btn.dumpPackage", "btn.getLocation" };

		Runnable[] codeArray = new Runnable[buttonNames.length];

		codeArray[0] = () ->
		{
			new ArduinoController(grid);
		};

		for (int i = 0; i < buttonNames.length; i++)
		{
			final int j = i;
			Vector2 pos = new Vector2(Constants.backTMMBP.getX(), 120 + (i * 50));
			StyledButton button = new StyledButton(buttonNames[i], pos);
			button.setOnAction(event -> codeArray[j].run());
			getChildren().add(button);
		}

	}

	private void CreateGrid()
	{
		grid = new TSPGrid(5, false);
		grid.setLayoutX(ScreenProperties.getScreenWidth() - Constants.drawnGridSize - 10);
		grid.setLayoutY(10);
		getChildren().add(grid);
	}
}
