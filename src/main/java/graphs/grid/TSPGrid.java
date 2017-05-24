package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.algorithms.ITspAlgorithm;
import main.java.main.Vector2;

public class TSPGrid extends PathGrid
{
	private boolean isActive = false;
	private boolean isInteractive;

	/**
	 * The TSP Grid, used for drawing lines between set points
	 * 
	 * @param tileAmount
	 * @param isInteractive
	 */
	public TSPGrid(int tileAmount, boolean isInteractive)
	{
		super(tileAmount);
		this.tileAmount = tileAmount;
		this.isInteractive = isInteractive;

		AddMouseEventHandler();
		Redraw();
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public ArrayList<Vector2> getPathList()
	{
		return pathList;
	}

	/**
	 * TODO
	 */
	public void resetPathList()
	{
		pathList = null;
	}

	/**
	 * Change the color of the line being drawn
	 * 
	 * @param color
	 */
	public void chgLineColor(Color color)
	{
		this.lineColor = color;
	}

	@Override
	protected void AddMouseEventHandler()
	{
		addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
		{
			int x = (int) Math.ceil(event.getX() / (gridSize / tileAmount));
			int y = (int) Math.ceil(event.getY() / (gridSize / tileAmount));

			// If the user is allowed to click
			if (isInteractive)
			{
				// For every tile in the grid
				for (GridTile tile : gridTileArray)
				{
					if (tile.getXcoord() == x && tile.getYcoord() == y)
					{
						// Select the clicked tile, tile will be put into an
						// ArrayList
						tile.SetSelected(!tile.IsSelected());
						pathList = null;
					}
				}
				// Redraw, will draw the newly selected tile on the grid
				Redraw();
			}
		});
	}

	@Override
	public void Redraw()
	{
		gc.clearRect(0, 0, getWidth(), getHeight());
		gc.setLineWidth(1);

		// Draw the points
		if (pathList != null)
		{
			DrawPathLines(pathList);
		}
		for (GridTile tile : gridTileArray)
		{
			// Draw a tile if it is selected
			if (tile.IsSelected())
			{
				DrawTile(new Vector2(tile.getXcoord(), tile.getYcoord()));
			}
		}
		super.Redraw();
	}

	/**
	 * Redraw the grid with a given set of points
	 * 
	 * @param customArray
	 */
	public void Redraw(ArrayList<Vector2> customArray, double pathLength)
	{
		this.pathLength = pathLength;
		pathList = customArray;

		Redraw();
	}

	/**
	 * Set the interacitvity of the grid, used by the robot simulation after
	 * setting a path
	 * 
	 * @param isInteractive
	 */
	public void SetInteractivity(boolean isInteractive)
	{
		this.isInteractive = isInteractive;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isActive()
	{
		return isActive;
	}

	/**
	 * 
	 * @param active
	 */
	public void setActive(boolean active)
	{
		isActive = active;
	}

	/**
	 * 
	 * @return
	 */
	public int GetTileAmount()
	{
		return tileAmount;
	}

	/**
	 * 
	 * @param usedAlgorithm
	 */
	public void SetAlgorithm(ITspAlgorithm usedAlgorithm)
	{
		this.usedAlgorithm = usedAlgorithm;
	}
}
