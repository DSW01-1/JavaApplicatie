package main.java.pane.simulation;

import main.java.constant.Constants;
import main.java.controller.ArduinoController;
import main.java.graphs.Grid;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class RobotSimulation extends StyledPane
{
	public RobotSimulation()
	{
		Grid grid = new Grid(5, false);
		grid.setLayoutX(ScreenProperties.getScreenWidth() - Constants.gridSize - 10);
		grid.setLayoutY(10);
		getChildren().add(grid);

		// back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		StyledButton connectArduino = new StyledButton("btn.connectArduino",
				new Vector2(Constants.backTMMBP.getX(), 90));
		
		connectArduino.setOnAction(event ->
		{
			new ArduinoController(grid);
		});
		getChildren().add(connectArduino);
	}
}
