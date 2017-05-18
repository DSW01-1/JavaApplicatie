package main.java.graphs;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.constant.Constants;
import main.java.main.Vector2;

public class BaseGrid extends Canvas
{
	protected ArrayList<GridTile> gridTileArray;
	protected GraphicsContext gc;
	protected int tileAmount;
	protected int tileSize;
	protected int gridSize = Constants.gridSize;

	public BaseGrid(int tileAmount)
	{
		gc = getGraphicsContext2D();
		this.tileAmount = tileAmount;

		CreateTiles();
	}

	/**
	 * Redraw the grid
	 */
	public void Redraw()
	{
		gc.setLineWidth(1);

		for (int y = tileAmount; y >= 0; y--)
		{
			for (int x = 0; x <= tileAmount; x++)
			{
				// Draw the lines
				gc.setStroke(Color.BLACK);
				gc.strokeLine(0, y * tileSize, gridSize, y * tileSize);
				gc.strokeLine(x * tileSize, 0, x * tileSize, gridSize);
			}
		}
	}

	/**
	 * Create the tiles
	 */
	private void CreateTiles()
	{
		gridTileArray = new ArrayList<GridTile>();
		tileSize = gridSize / tileAmount;

		for (int y = 0; y < gridSize; y++)
		{
			for (int x = 0; x < gridSize; x++)
			{
				final GridTile tile = new GridTile(new Vector2(x, y));
				gridTileArray.add(tile);
			}
		}
	}
}