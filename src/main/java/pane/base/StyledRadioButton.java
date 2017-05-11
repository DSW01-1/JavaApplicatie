package main.java.pane.base;

import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;
import main.java.main.Vector2;

public class StyledRadioButton extends RadioButton
{

	public StyledRadioButton(String name, Vector2 pos)
	{
		super(name);
		setFont(Font.font("Century Gothic", 14));
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
	}
}
