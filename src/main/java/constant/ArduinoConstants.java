package main.java.constant;

public class ArduinoConstants
{
	public static final String comPort = "COM3";

	// Commands to Send
	public static final String cmdMoveXAxis = "cmd.movexaxis";
	public static final String cmdMoveYAxis = "cmd.moveyaxis";
	public static final String cmdMoveToCoord = "cmd.movecoord";
	public static final String cmdGetPackage = "cmd.getpackage";
	public static final String cmdUnloadPackage = "cmd.unloadpackage";
	public static final String cmdDumpPackage = "cmd.dumpPackage";

	public static final String cmdStart = "cmd.start";
	public static final String cmdPauze = "cmd.pauze";
	public static final String cmdReset = "cmd.reset";

	// Commands to receive
	public static final String cmdMoveRobot = "cmd.moveRobot";
}
