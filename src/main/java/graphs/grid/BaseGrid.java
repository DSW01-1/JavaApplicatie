package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.constant.Constants;
import main.java.main.Vector2;

public abstract class BaseGrid extends Canvas
{
	protected ArrayList<GridTile> gridTileArray;
	protected GraphicsContext gc;
	protected int tileAmount;
	protected int tileSize;
	protected int gridSize;
	protected Vector2 robotPos;

	public BaseGrid(int tileAmount)
	{
		this.tileAmount = tileAmount;
		gc = getGraphicsContext2D();
		gridSize = Constants.drawnGridSize;
		setWidth(gridSize);
		setHeight(gridSize);
		setScaleY(-1);
		CreateTiles();
	}

	/**
	 * Add a mouse handler to add points on the grid
	 */
	protected abstract void AddMouseEventHandler();

	/**
	 * Redraw the grid
	 */
	public void Redraw()
	{
		gc.setLineWidth(1);
		gc.setStroke(Color.BLACK);

		for (int y = 0; y < tileAmount; y++)
		{
			for (int x = 0; x < tileAmount; x++)
			{
				// Draw the lines
				int x1 = (x * tileSize);
				int x2 = ((x + 1) * tileSize);

				int y1 = (y * tileSize);
				int y2 = ((y + 1) * tileSize);

				// x lines
				gc.strokeLine(x1, y1, x2, y1);
				gc.strokeLine(x1, y2, x2, y2);

				// y lines
				gc.strokeLine(x1, y1, x1, y2);
				gc.strokeLine(x2, y1, x2, y2);
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

	/**
	 * Get the gridTileArray
	 * 
	 * @return
	 */
	public ArrayList<GridTile> GetGridTileArray()
	{
		return gridTileArray;
	}

	/**
	 * Get a list of all the current selected tiles
	 * 
	 * @return
	 */
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

	/**
	 * Get the current robot position
	 * 
	 * @return
	 */
	public Vector2 GetRobotPos()
	{
		return robotPos;
	}

	/**
	 * Draw the current Robot position on the grid
	 * 
	 * @param coords
	 */
	protected void DrawRobotPos(Vector2 coords)
	{
		if (robotPos.getX() == coords.getX() && robotPos.getY() == coords.getY())
		{
			gc.setFill(Color.ANTIQUEWHITE);
			gc.fillRect(robotPos.getX() * tileSize, robotPos.getY() * tileSize, tileSize, tileSize);
		}
	}
}
