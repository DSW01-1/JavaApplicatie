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

	/**
	 * Add the console to the controller if available, will be used to log
	 * 
	 * @param console
	 */
	public void AddConsole(ConsolePane console)
	{
		this.console = console;
	}

	/**
	 * Try to get a connection with the Arduino
	 * 
	 * @return true if connected, false if not
	 */
	public boolean EstablishConnection()
	{
		comHandler = new ArduinoCommunicationHandler(this);
		return comHandler.EstablishConnection();
	}

	/**
	 * Set the grid if needed
	 * 
	 * @param grid
	 */
	public void SetGrid(BaseGrid grid)
	{
		this.grid = grid;
	}

	/**
	 * Handle the input gotten from the arduino
	 * 
	 * @param input
	 */
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
			Vector2 pos = new Vector2(Integer.parseInt(posArray[0]) - 1, Integer.parseInt(posArray[1]));
			
			System.out.println(pos.toString());
			
			grid.SetRobotPos(pos);
			break;
		default:
			break;
		}
	}

	/**
	 * Handle the output to the arduino
	 * 
	 * @param command
	 * @return true if succeeded
	 */
	public boolean HandleOutput(Command command)
	{
		if (comHandler != null)
		{
			String cmdString = "<" + command.getCommand();

			if (!("".equals(command.getExtraInfo())))
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
		else
		{
			return false;
		}

	}
}
