package main.java.pane;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import main.java.main.Vector2;

public class StyledButton extends Button
{

	public StyledButton(String name)
	{
		super(name);
		setId("button");
		setCursor(Cursor.HAND);
	}

	public StyledButton(String name, Vector2 coord)
	{
		this(name);
		setLayoutX(coord.getX());
		setLayoutY(coord.getY());
	}

	public StyledButton(String name, Vector2 coord, Vector2 size)
	{
		this(name, coord);
		setPrefSize(size.getX(), size.getY());
	}

}