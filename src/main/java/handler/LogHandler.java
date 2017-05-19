package main.java.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import main.java.constant.ErrorConstants;
import main.java.main.Language;

public class LogHandler
{

	// Writes an error message to the log file so that developers can fix the
	// problem
	public static void WriteErrorToLogFile(Exception e, String message)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		Date date = new Date();

		try
		{
			File file = new File("Logs");
			if (!file.exists())
			{
				file.mkdirs();
			}

			PrintWriter writer = new PrintWriter(
					new FileOutputStream(new File(ErrorConstants.GetErrorLogFile()), true));

			writer.println("=============================================================================");
			writer.println(dateFormat.format(date));
			writer.println(message);
			writer.println("");

			for (StackTraceElement ste : e.getStackTrace())
			{
				if (ste.getClassName().startsWith("main.java."))
				{
					writer.println(
							ste.getClassName() + ", " + ste.getMethodName() + " on line :" + ste.getLineNumber());
				}
			}
			writer.close();
		}
		catch (IOException ex)
		{
			LogHandler.WriteErrorToLogFile(ex, "IOException");
		}
	}

	// Write the next action done to the action log file to see what the user
	// has done
	public static void WriteToActionLogFile()
	{
		// TODO
	}

	/**
	 * Shows a general warning
	 * 
	 * @param warning
	 */
	public static void ShowWarning(String warning)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(warning);
	}

	/**
	 * Shows a waning screen with two buttons, cancel and another provided one
	 * 
	 * @param warning
	 *            The warning text
	 * @param action
	 *            The text for the other button
	 * @return boolean, true if the user pressed the action button
	 */
	public static boolean ShowWarning(String warning, String action)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(warning);

		ButtonType buttonTypeAction = new ButtonType(action);
		ButtonType buttonTypeConfirmation = new ButtonType(Language.getTranslation("btn.cancel"));

		alert.getButtonTypes().setAll(buttonTypeConfirmation, buttonTypeAction);

		Optional<ButtonType> result = alert.showAndWait();

		return result.get() == buttonTypeAction;
	}
}
