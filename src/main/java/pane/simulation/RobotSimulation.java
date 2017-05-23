package main.java.pane.simulation;

import java.util.HashMap;
import java.util.Map;

import main.java.constant.ArduinoConstants;
import main.java.constant.Constants;
import main.java.controller.ArduinoController;
import main.java.graphs.grid.RobotGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;

public class RobotSimulation extends BaseSimulation
{

	private RobotGrid grid;
	private ArduinoController controller;

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
		controller = new ArduinoController(grid);

		Map<String, String> cmdButtonMap = new HashMap<String, String>();

		String[] buttonNames =
		{ "btn.connectArduino", "btn.moveXAxis", "btn.moveYAxis", "btn.moveToCoord", "btn.getPackageAt",
				"btn.unloadPackage", "btn.dumpPackage", "btn.getLocation" };

		String[] cmdArray =
		{ ArduinoConstants.cmdMoveXAxis, ArduinoConstants.cmdMoveYAxis, ArduinoConstants.cmdUnloadPackage,
				ArduinoConstants.cmdDumpPackage };

		cmdButtonMap.put(buttonNames[1], cmdArray[0]);
		cmdButtonMap.put(buttonNames[2], cmdArray[1]);
		cmdButtonMap.put(buttonNames[5], cmdArray[2]);
		cmdButtonMap.put(buttonNames[6], cmdArray[3]);

		for (int i = 0; i < buttonNames.length; i++)
		{
			final int j = i;
			Vector2 pos = new Vector2(Constants.backTMMBP.getX(), 120 + (i * 50));
			StyledButton button = new StyledButton(buttonNames[i], pos);
			button.setOnAction(event ->
			{
				switch (buttonNames[j])
				{
				case "btn.connectArduino":
					controller.EstablishConnection();
					break;
				case "btn.moveToCoord":
					// TODO
					break;
				case "btn.getPackageAt":
					// TODO
				default:
					OutToArduino(cmdButtonMap.get(buttonNames[j]));
					break;
				}
			});
			getChildren().add(button);
		}
	}

	private void CreateGrid()
	{
		grid = new RobotGrid();
		grid.setLayoutX(ScreenProperties.getScreenWidth() - Constants.drawnGridSize - 10);
		grid.setLayoutY(10);
		getChildren().add(grid);
	}

	private void OutToArduino(String command)
	{
		System.out.println(command);
		// controller.HandleOutput(command);
	}
}
