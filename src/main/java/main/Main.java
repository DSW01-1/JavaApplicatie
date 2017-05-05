package main.java.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.pane.MainMenu;

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

			primaryStage.setMaximized(true);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static void SwitchPane(Pane pane)
	{
		Scene scene = new Scene(pane, ScreenProperties.getScreenWidth(), ScreenProperties.getScreenHeight());
		scene.getStylesheets().add(Constants.styleSheetLocation + "application.css");

		primaryStage.setScene(scene);
	}
}
