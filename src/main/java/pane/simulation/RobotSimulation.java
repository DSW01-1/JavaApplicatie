package main.java.pane.simulation;

import main.java.constant.Constants;
import main.java.controller.ArduinoController;
import main.java.graphs.Grid;
import main.java.main.Main;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotSimulation extends StyledPane
{
	public RobotSimulation()
	{
		Grid grid = new Grid(5, false);
		grid.setLayoutX(100);
		grid.setLayoutY(100);
		getChildren().add(grid);

		// back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		new ArduinoController(grid);
	}
}
