package main.java.graphs;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import main.java.constant.Constants;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;

public class Grid extends GridPane
{

	public Grid()
	{
		setGridLinesVisible(true);

		int gridSize = Constants.gridSize;
		int tileSize = (ScreenProperties.getScreenHeight() / 2) / gridSize;

		for (int y = 0; y < gridSize; y++)
		{
			for (int x = 0; x < gridSize; x++)
			{
				final GridTile tile = new GridTile(new Vector2(x, y));
				tile.setWidth(tileSize);
				tile.setHeight(tileSize);
				tile.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
					public void handle(MouseEvent event)
					{
						System.out.println("X=" + tile.coords.getX() + ", Y=" + tile.coords.getY());
					}
				});
				add(tile, x, gridSize - 1 - y);
			}
		}

		addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent e)
			{
				for (Node node : getChildren())
				{

					if (node instanceof Label)
					{
						if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY()))
						{
							System.out.println("Node: " + node + " at " + GridPane.getRowIndex(node) + "/"
									+ GridPane.getColumnIndex(node));
						}
					}
				}
			}
		});
	}
}
