package main.java.handler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import main.java.constant.ArduinoConstants;
import main.java.controller.ArduinoController;

public class ArduinoCommunicationHandler implements SerialPortEventListener
{
	SerialPort serialPort;
	private BufferedReader input;
	private OutputStream output;
	private ArduinoController controller;

	public ArduinoCommunicationHandler(ArduinoController controller)
	{
		this.controller = controller;
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			public void run()
			{
				close();
			}
		}, "Shutdown-thread"));
	}

	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	/**
	 * Connect to the port the arduino is connected to
	 * @param portName the portname the arduino is connected to
	 * @return the port the arduino is connected to
	 */
	public CommPortIdentifier GetArduinoPort()
	{
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//Loop through every port
		while (portEnum.hasMoreElements())
		{
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

			if (currPortId.getName().equals(ArduinoConstants.comPort))
			{
				portId = currPortId;
				break;
			}
		}

		return portId;
	}

	/**
	 * Setup a connection with the arduino, set the input and output
	 */
	public void EstablishConnection()
	{

		CommPortIdentifier portId = GetArduinoPort();

		try
		{
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		}
		catch (Exception e)
		{
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port. This will prevent
	 * port locking on platforms like Linux.
	 */
	public synchronized void close()
	{
		if (serialPort != null)
		{
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent)
	{
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
		{
			try
			{
				String inputLine = input.readLine();
				controller.HandleInput(inputLine);
			}
			catch (Exception e)
			{
				//System.err.println(e.toString());
			}
		}
	}

	public synchronized void WriteToArduino(String data)
	{
		try
		{
			output.write(data.getBytes());
		}
		catch (Exception e)
		{
			LogHandler.WriteErrorToLogFile(e, "Could not write to Arduino");
		}
	}
}
