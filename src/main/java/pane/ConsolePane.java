package main.java.pane;

import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import main.java.main.ScreenProperties;

public class ConsolePane extends ListView<String>
{
	public ConsolePane()
	{
		super();



		setLayoutX(15);
		setLayoutY(570);
		setPrefWidth(ScreenProperties.getScreenWidth() / 4);
		setPrefHeight(ScreenProperties.getScreenHeight() / 3);
	}

	public ConsolePane(int x, int y)
	{
		super();



		setLayoutX(x);
		setLayoutY(y);
		setPrefWidth(ScreenProperties.getScreenWidth() / 4);
		setPrefHeight(ScreenProperties.getScreenHeight() / 3);
	}

	public ConsolePane(int x, int y, int width, int height)
	{
		super();



		setLayoutX(x);
		setLayoutY(y);
		setPrefWidth(width);
		setPrefHeight(height);
	}
}
