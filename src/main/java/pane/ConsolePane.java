package main.java.pane;

import javafx.scene.control.ListView;

public class ConsolePane extends ListView<String>
{
	public ConsolePane()
	{
		super();
		setLayoutX(15);
		setLayoutY(570);
		setPrefWidth(825);
		setPrefHeight(250);
	}

	public void addConsoleItem(String Message, String msgType)
	{
		getItems().add(getItems().size(), String.format("[%s] %s", msgType, Message));
	}

}
