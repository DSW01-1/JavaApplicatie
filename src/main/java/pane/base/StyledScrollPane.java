package main.java.pane.base;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import main.java.main.Vector2;

public class StyledScrollPane extends ScrollPane
{

	public StyledScrollPane(Node node, Vector2 pos, Vector2 size)
	{
		super(node);
		setId("styledScrollPane");
		setHbarPolicy(ScrollBarPolicy.NEVER);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		setPrefSize(size.getX(), size.getY());
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
	}

	public StyledScrollPane(Node node, Vector2 pos, Vector2 size, boolean isVertical)
	{
		super();
		if (isVertical)
		{
			setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
			setVbarPolicy(ScrollBarPolicy.NEVER);
		}
		else
		{
			setHbarPolicy(ScrollBarPolicy.NEVER);
			setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		}

		setId("styledScrollPane");
		setPrefSize(size.getX(), size.getY());
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
	}

}
