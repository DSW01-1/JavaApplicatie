package main.java.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
			primaryStage.getIcons().add(new Image(Constants.ApplicationIcon));
			String generatedName;
			int nameID = ((int) Math.floor(Math.random() * (10 - 1)) + 1);
			switch (nameID){
				case 1: generatedName = "You underestimate the power of COFFEE!";
				break;
				case 2: generatedName = "Why i wear glasses? Well, because I don't C#.";
				break;
				case 3: generatedName = "Java: turning coffee into code.";
				break;
				case 4: generatedName = "What's a programmer's favorite bar? The Foo Bar.";
				break;
				case 5: generatedName = "[\"hip\",\"hip\"]";
				break;
				case 6: generatedName = "Lubarsky’s Law of Cybernetic Entomology: There’s always one more bug.";
				break;
				case 7: generatedName = "The worst part of \"Go to hell\" is the \"go to\".";
				break;
				case 8: generatedName = "Algorithm: a word used when programmers don't want to explain what they are doing.";
				break;
				case 9: generatedName = "Have you tried turning it off and on again?";
				break;
				case 10: generatedName = "Semicolon: hide and seek champion since forever.";
				break;
				default: generatedName = "Java applicatie";
			}
			primaryStage.setTitle(generatedName);

			SwitchPane(new MainMenu());

			primaryStage.setMaximized(true);
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
