package main.java.pane.simulation;

import main.java.controller.ArduinoController;
import main.java.graphs.Grid;
import main.java.pane.base.StyledPane;

public class RobotSimulation extends StyledPane
{

	ArduinoController controller;

	public RobotSimulation()
	{
		Grid grid = new Grid(5, false);
		grid.setLayoutX(100);
		grid.setLayoutY(100);
		getChildren().add(grid);

		controller = new ArduinoController(grid);
	}
}
