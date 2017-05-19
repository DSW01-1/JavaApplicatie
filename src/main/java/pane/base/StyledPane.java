package main.java.pane.base;

import javafx.scene.layout.Pane;
import main.java.main.Vector2;

public class StyledPane extends Pane
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

	public void InitPane()
	{
	}

}
