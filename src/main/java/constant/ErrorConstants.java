package main.java.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorConstants
{
	public static String actionLogFile = "";
	public static String errorLogFile = "";

	private static String GetDateFormat()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String GetErrorLogFile()
	{
		if ("".equals(errorLogFile))
		{
			errorLogFile = "Logs/[" + GetDateFormat() + "]ErrorLog.log";
		}
		return errorLogFile;
	}

	public static String GetActionLogFile()
	{
		if ("".equals(actionLogFile))
		{
			errorLogFile = "Logs/[" + GetDateFormat() + "]ActionLog.log";
		}
		return errorLogFile;
	}

	public static String ConnectionException = "08";

}
