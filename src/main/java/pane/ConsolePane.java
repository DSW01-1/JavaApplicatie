package main.java.pane;

import javafx.scene.control.ListView;
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
}
