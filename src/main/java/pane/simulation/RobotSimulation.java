package main.java.pane.simulation;

import javafx.scene.control.ColorPicker;
import main.java.constant.Constants;
import main.java.controller.ArduinoController;
import main.java.graphs.grid.TSPGrid;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;

public class RobotSimulation extends BaseSimulation
{
	public RobotSimulation()
	{
		super();
		
		TSPGrid grid = new TSPGrid(5, false);
		grid.setLayoutX(ScreenProperties.getScreenWidth() - Constants.gridSize - 10);
		grid.setLayoutY(10);
		getChildren().add(grid);

		StyledButton connectArduino = new StyledButton("btn.connectArduino",
				new Vector2(Constants.backTMMBP.getX(), 90));

		connectArduino.setOnAction(event ->
		{
			new ArduinoController(grid);
		});
		getChildren().add(connectArduino);

		ColorPicker colorPicker = new ColorPicker();
		grid.chgLineColor(colorPicker.getValue());
		getChildren().add(colorPicker);
	}
}
