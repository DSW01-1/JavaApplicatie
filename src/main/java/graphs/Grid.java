package main.java.graphs;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import main.java.constant.Constants;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;

public class Grid extends GridPane
{
	private GridTile[] gridTileArray;

	public Grid(final boolean isInteractive)
	{
		int gridSize = Constants.gridSize;
		gridTileArray = new GridTile[(int) Math.pow(gridSize, 2)];

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
							gc.fillOval(10, 10, tileSize - 20, tileSize - 20);
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
			}
		}
	}

	public GridTile[] GetGridTileArray()
	{
		return gridTileArray;
	}

	public void SetGridTileArray(GridTile[] gridTileArray)
	{
		this.gridTileArray = gridTileArray;
	}
}