package main.java.pane.base;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import main.java.main.Vector2;

public class StyledScrollPane extends ScrollPane
{

	public StyledScrollPane(Node node, Vector2 size, Vector2 pos)
	{
		super(node);
		setId("styledScrollPane");
		setHbarPolicy(ScrollBarPolicy.NEVER);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		setPrefSize(size.getX(), size.getY());
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
	}

}
