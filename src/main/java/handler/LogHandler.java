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
			PrintWriter writer = new PrintWriter(new FileOutputStream(new File(ErrorConstants.errorLogFile), true));

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
			// TODO
		}

	}

	// Write the next action done to the action log file to see what the user
	// has done
	public static void WriteToActionLogFile()
	{
		// TODO
	}

	// Shows the user a warning. The user might need to do a certain action to
	// avoid this warning
	public static void ShowWarning(String warning)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(warning);
	}

	public static boolean ShowWarning(String warning, String action)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Warning!");
		alert.setHeaderText(warning);

		ButtonType buttonTypeAction = new ButtonType(action);
		ButtonType buttonTypeConfirmation = new ButtonType(Language.getTranslation("btn.cancel"));

		alert.getButtonTypes().setAll(buttonTypeConfirmation, buttonTypeAction);

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == buttonTypeAction)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
