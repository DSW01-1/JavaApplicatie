package main.java.pane.simulation;

import java.util.HashMap;
import java.util.Map;

import main.java.constant.ArduinoConstants;
import main.java.constant.Constants;
import main.java.controller.ArduinoController;
import main.java.graphs.StatusCanvas;
import main.java.graphs.grid.RobotGrid;
import main.java.main.Command;
import main.java.main.ConnectionStatus;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.BackToMainMenuButton;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotSimulation extends StyledPane
{

	private RobotGrid grid;
	private ArduinoController controller;
	private StatusCanvas statusCanvas;
	private ConsolePane consolePane;

	public RobotSimulation()
	{
		super();
		InitPane();
	}

	@Override
	public void InitPane()
	{
		super.InitPane();
		getStyleClass().add("background");
		getChildren().add(new BackToMainMenuButton());
		CreateGrid();
		CreateButtons();
		AddConsolePane();

		controller.AddConsole(consolePane);

		statusCanvas = new StatusCanvas(new Vector2(ScreenProperties.getScreenWidth() - 150, 25), 70);
		getChildren().add(statusCanvas);
	}

	private void CreateButtons()
	{
		controller = new ArduinoController();

		controller.SetGrid(grid);

		Map<String, String> cmdButtonMap = new HashMap<String, String>();

		String[] buttonNames =
		{ "btn.connectArduino", "btn.sendTest", "btn.moveXAxis", "btn.moveYAxis", "btn.moveToCoord", "btn.getPackageAt",
				"btn.unloadPackage", "btn.getLocation" };

		String[] cmdArray =
		{ ArduinoConstants.cmdMoveXAxis, ArduinoConstants.cmdMoveYAxis, ArduinoConstants.cmdUnloadPackage,
				ArduinoConstants.cmdSendTest };

		cmdButtonMap.put(buttonNames[2], cmdArray[0]);
		cmdButtonMap.put(buttonNames[3], cmdArray[1]);
		cmdButtonMap.put(buttonNames[6], cmdArray[2]);
		cmdButtonMap.put(buttonNames[1], cmdArray[3]);

		for (int i = 0; i < buttonNames.length; i++)
		{
			final int j = i;
			Vector2 pos = new Vector2(Constants.backTMMBP.getX(), 120 + (i * 50));
			StyledButton button = new StyledButton(buttonNames[i], pos);
			button.setOnAction(event ->
			{
				String coordString = "";

				if (grid.GetSelectedTile() != null)
				{
					Vector2 coordPos = grid.GetSelectedTile().GetPos();
					coordString = coordPos.getX() + "!" + coordPos.getY();
				}

				switch (buttonNames[j])
				{
				case "btn.connectArduino":
					statusCanvas.StartAnimation();
					boolean established = controller.EstablishConnection();

					ConnectionStatus status = established ? ConnectionStatus.ACTIVE : ConnectionStatus.INACTIVE;
					statusCanvas.SetStatus(status);

					if (established == false)
					{
						consolePane.addConsoleItem("Connection could not be established", "ERROR");
					}

					break;
				case "btn.moveToCoord":
					controller.HandleOutput(new Command(ArduinoConstants.cmdMoveToCoord, coordString));
					break;
				case "btn.getPackageAt":
					controller.HandleOutput(new Command(ArduinoConstants.cmdGetPackage, coordString));
					break;
				case "btn.getLocation":
					controller.HandleOutput(new Command(ArduinoConstants.cmdGetLocation));
					break;
				default:
					controller.HandleOutput(new Command(cmdButtonMap.get(buttonNames[j])));
					break;
				}
			});
			getChildren().add(button);
		}
	}

	private void CreateGrid()
	{
		grid = new RobotGrid();

		grid.setLayoutX(ScreenProperties.getScreenWidth() / 2 - Constants.drawnGridSize / 2);
		grid.setLayoutY(10);
		getChildren().add(grid);
		grid.Redraw();
	}

	private void AddConsolePane()
	{
		consolePane = new ConsolePane(15,
				ScreenProperties.getScreenHeight() - (ScreenProperties.getScreenHeight() / 3) - 100);
		getChildren().add(consolePane);
	}
}
