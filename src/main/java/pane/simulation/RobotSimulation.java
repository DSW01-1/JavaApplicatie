package main.java.pane.simulation;

import main.java.controller.ArduinoController;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotSimulation extends StyledPane
{

	ArduinoController controller;
	private boolean isLightOn;

	public RobotSimulation()
	{
		CreateControlButtons();

		controller = new ArduinoController();
	}

	private void CreateControlButtons()
	{
		
		// StyledButton lightsOnButton = new StyledButton("Toggle Lights", new
		// Vector2(100, 100));
		// lightsOnButton.setOnAction(event ->
		// {
		// if(isLightOn)
		// {
		// controller.HandleOutput("<cmd.lightsOn>");
		// }
		// else
		// {
		// controller.HandleOutput("<cmd.lightsOff>");
		// }
		// isLightOn = !isLightOn;
		// });
		// getChildren().add(lightsOnButton);
	}
}
