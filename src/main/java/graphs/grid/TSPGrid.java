package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.algorithms.ITspAlgorithm;
import main.java.algorithms.tsp.ScissorEdge;
import main.java.constant.Constants;
import main.java.main.Vector2;

public class TSPGrid extends BaseGrid
{
	private ArrayList<Vector2> pathList;
	private boolean isActive = false;
	private boolean isInteractive;
	private ITspAlgorithm usedAlgorithm;
	private double pathLength;
	public int tileAmount;

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
			// Draw the Robot position if possible
			if (robotPos != null)
			{
				DrawRobotPos(new Vector2(tile.getXcoord(), tile.getYcoord()));
			}

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
	 * Draw lines between the given points
	 * 
	 * @param points
	 */
	public void DrawPathLines(ArrayList<Vector2> points)
	{
		gc.setLineWidth(5);
		gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : lineColor);

		if (usedAlgorithm instanceof ScissorEdge)
		{
			gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : Color.RED);
			for (int i = 0; i <= ((points.size() - 2) / 2); i++)
			{
				double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
				gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
			}

			gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : Color.PURPLE);
			double[] pathCoords1 = GetPathLine(points.get((points.size() / 2) - 1), points.get((points.size() / 2)));
			gc.strokeLine(pathCoords1[0], pathCoords1[2], pathCoords1[1], pathCoords1[3]);

			gc.setStroke(Constants.GetBlzitNumber(pathLength) ? Constants.standardBlzitColor : Color.BLUE);
			for (int i = (points.size() / 2); i < points.size() - 1; i++)
			{
				double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
				gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
			}
		}
		else
		{
			for (int i = 0; i < points.size() - 1; i++)
			{
				double[] pathCoords = GetPathLine(points.get(i), points.get(i + 1));
				gc.strokeLine(pathCoords[0], pathCoords[2], pathCoords[1], pathCoords[3]);
			}
		}
	}

	/**
	 * TODO
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 */
	private double[] GetPathLine(Vector2 point1, Vector2 point2)
	{
		double[] line = new double[4];
		line[0] = (point1.getX() * tileSize) - tileSize + (tileSize / 2);
		line[1] = (point2.getX() * tileSize) - tileSize + (tileSize / 2);

		line[2] = (point1.getY() * tileSize) - tileSize + (tileSize / 2);
		line[3] = (point2.getY() * tileSize) - tileSize + (tileSize / 2);
		return line;
	}

	/**
	 * Set the current Position of the robot
	 * 
	 * @param transPos
	 */
	public void SetRobotPos(Vector2 transPos)
	{
		if (robotPos == null)
		{
			robotPos = new Vector2(0, 0);
		}

		if (robotPos.getX() + transPos.getX() >= 0 && robotPos.getX() + transPos.getX() < tileAmount)
		{
			robotPos.setX((int) (robotPos.getX() + transPos.getX()));
		}

		if (robotPos.getY() > transPos.getY())
		{
			robotPos.setY((int) (robotPos.getY() + transPos.getY()));
		}

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
