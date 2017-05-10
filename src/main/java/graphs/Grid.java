package main.java.graphs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import main.java.constant.Constants;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;

import java.util.ArrayList;

public class Grid extends GridPane
{
	private ArrayList<GridTile> gridTileArray;

	public Grid(final boolean isInteractive)
	{
		int gridSize = Constants.gridSize;
		gridTileArray = new ArrayList<GridTile>();

		setGridLinesVisible(true);

		final int tileSize = (ScreenProperties.getScreenHeight() / 2) / gridSize;

		for (int y = 0; y < gridSize; y++)
		{
			for (int x = 0; x < gridSize; x++)
			{
				final GridTile tile = new GridTile(new Vector2(x, y));

				tile.setWidth(tileSize);
				tile.setHeight(tileSize);
				tile.setOnMouseClicked(event ->
				{
					if (isInteractive)
					{
						GraphicsContext gc = tile.getGraphicsContext2D();
						if (!tile.IsSelected())
						{
							gc.fillOval(tileSize / 3, tileSize / 3, tileSize / 3, tileSize / 3);
							tile.SetSelected(true);
						}
						else
						{
							gc.clearRect(0, 0, tileSize, tileSize);
							tile.SetSelected(false);
						}
					}
				});
				add(tile, x, gridSize - 1 - y);
				gridTileArray.add(tile);

			}
		}
	}

	public ArrayList<GridTile> GetGridTileArray()
	{
		return gridTileArray;
	}

	public ArrayList<GridTile> getSelectedTiles()
	{
		ArrayList<GridTile> selectedTiles = new ArrayList<>();

		for (GridTile currentTile : this.gridTileArray)
		{
			if (currentTile.IsSelected())
			{
				selectedTiles.add(currentTile);
			}
		}

		return selectedTiles;
	}
}
