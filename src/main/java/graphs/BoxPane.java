package main.java.graphs;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class BoxPane extends StyledPane
{

	public BoxPane()
	{
		super();
		AddScrollPane();
	}

	private void AddScrollPane()
	{

		int scrnWidth = ScreenProperties.getScreenWidth();
		int scrnHeight = ScreenProperties.getScreenHeight();

		HBox hBox = new HBox();
		hBox.setPrefHeight(scrnHeight / 3);
		// ArrayList<Box> boxArray = new ArrayList<Box>();

		Product[] productArray =
		{ new Product(2), new Product(2), new Product(4) };

		hBox.getChildren().add(new BoxCanvas(new Box(productArray)));

		StyledScrollPane scrollPane = new StyledScrollPane(hBox, new Vector2(scrnHeight / 3, scrnHeight / 3),
				new Vector2(scrnWidth / 2 - scrnHeight / 3, 15));

		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		getChildren().add(scrollPane);
	}
}
