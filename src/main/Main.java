package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pane.MainMenu;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			MainMenu mainMenu = new MainMenu();
			Scene scene = new Scene(mainMenu, ScreenProperties.getScreenWidth(), ScreenProperties.getScreenHeight());
			scene.getStylesheets().add("css/application.css");

			primaryStage.setMaximized(true);
			primaryStage.setScene(scene);
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
}
