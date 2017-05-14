package main.java.controller;

import main.java.constant.ArduinoConstants;
import main.java.graphs.Grid;
import main.java.handler.ArduinoCommunicationHandler;
import main.java.main.Vector2;

public class ArduinoController
{
	private ArduinoCommunicationHandler comHandler;
	private Grid grid;

	public ArduinoController(Grid grid)
	{
		comHandler = new ArduinoCommunicationHandler(this);
		comHandler.EstablishConnection();
		this.grid = grid;
	}

	public void HandleInput(String input)
	{
		switch (input)
		{
		case ArduinoConstants.cmdMoveLeft:
			grid.SetRobotPos(new Vector2(-1, 0));
			break;
		case ArduinoConstants.cmdMoveRight:
			grid.SetRobotPos(new Vector2(1, 0));
			break;
		case ArduinoConstants.cmdMoveUp:
			grid.SetRobotPos(new Vector2(0, 1));
			break;
		case ArduinoConstants.cmdMoveDown:
			grid.SetRobotPos(new Vector2(0, -1));
			break;
		default:
			break;
		}
	}

	public void HandleOutput(String command)
	{
		comHandler.WriteToArduino(command);
	}
}
