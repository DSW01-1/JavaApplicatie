package main.java.controller;

import main.java.constant.ArduinoConstants;
import main.java.graphs.grid.BaseGrid;
import main.java.handler.ArduinoCommunicationHandler;
import main.java.main.Command;
import main.java.main.Vector2;
import main.java.pane.simulation.ConsolePane;

public class ArduinoController
{
	private ArduinoCommunicationHandler comHandler;
	private BaseGrid grid;
	private ConsolePane console;

	public void AddConsole(ConsolePane console)
	{
		this.console = console;
	}

	public boolean EstablishConnection()
	{
		comHandler = new ArduinoCommunicationHandler(this);
		return comHandler.EstablishConnection();
	}

	public void HandleInput(String input)
	{
		Command command = null;

		if (input.contains("<") && input.contains(">"))
		{
			command = Command.SortCommand(input);
		}

		if (console != null)
		{
			console.addConsoleItem(input, "IN");
		}

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

	public boolean HandleOutput(Command command)
	{
		String cmdString = "<" + command.getCommand();

		if (command.getExtraInfo() != "")
		{
			cmdString = cmdString.concat("[" + command.getExtraInfo() + "]");
		}
		cmdString = cmdString.concat(">");

		if (console != null)
		{
			console.addConsoleItem(cmdString, "OUT");
		}

		return comHandler.WriteToArduino(cmdString);
	}
}
