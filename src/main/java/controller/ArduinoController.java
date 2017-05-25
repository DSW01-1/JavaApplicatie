package main.java.controller;

import main.java.constant.ArduinoConstants;
import main.java.graphs.grid.BaseGrid;
import main.java.handler.ArduinoCommunicationHandler;
import main.java.main.Command;
import main.java.main.Vector2;

public class ArduinoController
{
	private ArduinoCommunicationHandler comHandler;
	private BaseGrid grid;

	public ArduinoController()
	{
	}

	public void EstablishConnection()
	{
		comHandler = new ArduinoCommunicationHandler(this);
		comHandler.EstablishConnection();
	}

	public void HandleInput(String input)
	{
		Command command = Command.SortCommand(input);

		switch (command.getCommand())
		{
		case ArduinoConstants.cmdMoveRobot:
			String[] posArray = command.getExtraInfo().split("\\!");
			Vector2 pos = new Vector2(Integer.parseInt(posArray[0]), Integer.parseInt(posArray[1]));
			grid.SetRobotPos(pos);
			break;
		default:
			break;
		}
	}

	public void HandleOutput(Command command)
	{
		String cmdString = "<" + command.getCommand();

		if (command.getExtraInfo() != "")
		{
			cmdString.concat(command.getExtraInfo());
		}

		cmdString.concat(">");

		System.out.println(cmdString);

		comHandler.WriteToArduino(cmdString);
	}
}
