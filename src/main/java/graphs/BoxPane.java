package main.java.graphs;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import main.java.constant.Constants;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class BoxPane extends StyledPane
{
	private int boxPaneSize = Constants.gridSize;
	private StyledScrollPane scrollPane;
	private HBox hBox;

	public BoxPane()
	{
		super();
		AddScrollPane();

	}

	private void AddScrollPane()
	{
		hBox = new HBox();
		hBox.setPrefHeight(boxPaneSize);

		scrollPane = new StyledScrollPane(hBox, new Vector2(boxPaneSize, boxPaneSize),
				new Vector2((ScreenProperties.getScreenWidth() / 2) - boxPaneSize / 2, 15));

		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		getChildren().add(scrollPane);
	}

	public void AddBoxList(ArrayList<Box> boxArrayList)
	{
		hBox.getChildren().removeAll();

		for (Box box : boxArrayList)
		{
			hBox.getChildren().add(new BoxCanvas(box));
		}

	}
}
