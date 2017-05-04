package pane;

import javafx.scene.control.Button;
import main.Vector2;

public class StyledButton extends Button
{

	public StyledButton(String name)
	{
		super(name);
		setId("button");
	}

	public StyledButton(String name, Vector2 coord, Vector2 size)
	{
		this(name);

		setPrefSize(size.getX(), size.getY());
		
		setLayoutX(coord.getX());
		setLayoutY(coord.getY());
	}
}
