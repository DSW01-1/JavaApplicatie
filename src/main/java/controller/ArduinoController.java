package main.java.controller;

import main.java.handler.ArduinoCommunicationHandler;

public class ArduinoController
{
	private ArduinoCommunicationHandler comHandler;

	public ArduinoController()
	{
		comHandler = new ArduinoCommunicationHandler(this);
		comHandler.EstablishConnection();
	}
	
	public void HandleInput(String input)
	{
		
	}
	
	public void HandleOutput(String command)
	{
		comHandler.WriteToArduino(command);
	}
}
