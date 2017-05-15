package main.java.pane;

import javafx.scene.shape.Rectangle;
import main.java.pane.base.StyledPane;

public class Boxpane extends StyledPane
{

	public Boxpane(int size)
	{
		super();

		setLayoutX(200);
		setLayoutY(400);
		setPrefSize(200, 400);

		Rectangle boxShape = new Rectangle();
		boxShape.setHeight(size);
		boxShape.setWidth(50);
		boxShape.setVisible(true);
		getChildren().add(boxShape);
	}

}
