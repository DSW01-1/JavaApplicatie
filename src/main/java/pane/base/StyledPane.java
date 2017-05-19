package main.java.pane.base;

import javafx.scene.layout.Pane;
import main.java.main.Vector2;

public abstract class StyledPane extends Pane
{

	public StyledPane()
	{
		super();
	}

	public StyledPane(Vector2 pos)
	{
		this();
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
	}

	public StyledPane(Vector2 pos, Vector2 size)
	{
		this(pos);
		setPrefSize(size.getX(), size.getY());
	}

	public abstract void InitPane();
}