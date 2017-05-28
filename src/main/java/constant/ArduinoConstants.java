package main.java.constant;

public class ArduinoConstants
{
	public static final String comPort = "COM3";

	// Commands to Send
	public static final String cmdSendTest = "cmdSendTest";

	public static final String cmdDoCycle = "cmdDoCycle";
	public static final String cmdMoveXAxis = "cmdMoveXAxis";
	public static final String cmdMoveYAxis = "cmdMoveYAxis";
	public static final String cmdMoveToCoord = "cmdMoveCoord";
	public static final String cmdGetPackage = "cmdGetPackage";
	public static final String cmdUnloadPackage = "cmdUnloadPackage";
	public static final String cmdDumpPackage = "cmdDumpPackage";

	public static final String cmdStart = "cmdStart";
	public static final String cmdPause = "cmdPauze";
	public static final String cmdReset = "cmdReset";

	// Commands to receive
	public static final String cmdMoveRobot = "cmdMoveRobot";
}
