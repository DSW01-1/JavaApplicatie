package main.java.pane.base;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import main.java.main.Vector2;

public class StyledChoiceBox extends ChoiceBox<String>
{

	public StyledChoiceBox(String[] options)
	{
		super(FXCollections.observableArrayList(options));
	}

	public StyledChoiceBox(String[] options, Vector2 pos)
	{
		this(options);
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
	}

	public StyledChoiceBox(String[] options, Vector2 pos, Vector2 size)
	{
		this(options, pos);
		setPrefSize(size.getX(), size.getY());
	}
}
