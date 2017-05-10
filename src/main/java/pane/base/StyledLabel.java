package main.java.pane.base;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import main.java.main.Vector2;

public class StyledLabel extends Label
{

	public StyledLabel(String name, Vector2 pos)
	{
		super(name);
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
		setFont(Font.font("Century Gothic", 20));
	}
}
