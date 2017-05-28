package main.java.pane.simulation;

import javafx.scene.control.ListView;
import main.java.main.ScreenProperties;

public class ConsolePane extends ListView<String>
{
	public ConsolePane()
	{
		this(15, 570);
	}

	public ConsolePane(int x, int y)
	{
		this(x, y, ScreenProperties.getScreenWidth() / 4, ScreenProperties.getScreenHeight() / 3);
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
