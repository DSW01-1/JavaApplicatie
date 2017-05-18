package main.java.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.constant.Constants;
import main.java.handler.LogHandler;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledPane;

public class Main extends Application
{
	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			Main.primaryStage = primaryStage;

			SwitchPane(new MainMenu());

			//primaryStage.setMaximized(true);
			primaryStage.show();

			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Class not found");
			LogHandler.ShowWarning(Language.getTranslation("warning.nojdbcclass"));
		}
		catch (Exception e)
		{
			LogHandler.WriteErrorToLogFile(e, "Exception");
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static void SwitchPane(StyledPane pane)
	{
		Scene scene = new Scene(pane, ScreenProperties.getScreenWidth(), ScreenProperties.getScreenHeight());
		scene.getStylesheets().add(Constants.styleSheetLocation + "application.css");

		primaryStage.setScene(scene);
		pane.InitPane();

	}
}
