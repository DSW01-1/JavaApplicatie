package main.java.graphs;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.constant.Constants;
import main.java.main.Vector2;

public class Grid extends Canvas
{
	private ArrayList<GridTile> gridTileArray;
	private GraphicsContext gc;
	private int tileAmount;
	private int tileSize;
	private int gridSize = Constants.gridSize;
	private Vector2 robotPos;

	public Grid(int tileAmount, boolean isInteractive)
	{
		super();
		gc = getGraphicsContext2D();
		setWidth(gridSize);
		setHeight(gridSize);
		setScaleY(-1);

		this.tileAmount = tileAmount;

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

		Redraw();

		addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
		{
			int x = (int) Math.ceil(event.getX() / (gridSize / tileAmount));
			int y = (int) Math.ceil(event.getY() / (gridSize / tileAmount));

			for (GridTile tile : gridTileArray)
			{
				if (tile.getXcoord() == x && tile.getYcoord() == y)
				{
					tile.SetSelected(!tile.IsSelected());
				}
			}

			Redraw();
		});
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

	public void Redraw()
	{
		gc.setLineWidth(1);
		gc.clearRect(0, 0, getWidth(), getHeight());
		for (int y = tileAmount; y >= 0; y--)
		{
			for (int x = 0; x <= tileAmount; x++)
			{
				if (robotPos != null)
				{
					if (robotPos.getX() == x && robotPos.getY() == robotPos.getY())
					{
						gc.setFill(Color.ANTIQUEWHITE);
						gc.fillRect(robotPos.getX() * tileSize, robotPos.getY() * tileSize, tileSize, tileSize);
					}
				}

				gc.setStroke(Color.BLACK);
				gc.strokeLine(0, y * tileSize, gridSize, y * tileSize);
				gc.strokeLine(x * tileSize, 0, x * tileSize, gridSize);

			}
		}

		ArrayList<Vector2> points = new ArrayList<Vector2>();

		for (GridTile tile : gridTileArray)
		{
			if (tile.IsSelected())
			{
				DrawTileIfSelected(new Vector2(tile.getXcoord(), tile.getYcoord()));
				points.add(tile.GetPos());
			}
		}

		DrawPathLines(points);
	}

	/**
	 * Draw circles on the tiles that are selected
	 * 
	 * @param tilePos
	 */
	public void DrawTileIfSelected(Vector2 tilePos)
	{
		double x = tilePos.getX() * tileSize - tileSize + (tileSize / 3);
		double y = tilePos.getY() * tileSize - tileSize + (tileSize / 3);

		gc.fillOval(x, y, tileSize / 3, tileSize / 3);
	}

	/**
	 * Draw the paths
	 * 
	 * @param points
	 */
	public void DrawPathLines(ArrayList<Vector2> points)
	{
		gc.setLineWidth(5);
		gc.setStroke(Color.INDIANRED);

		for (int i = 0; i < points.size() - 1; i++)
		{
			double x1 = points.get(i).getX() * tileSize - tileSize + (tileSize / 2);
			double x2 = points.get(i + 1).getX() * tileSize - tileSize + (tileSize / 2);

			double y1 = points.get(i).getY() * tileSize - tileSize + (tileSize / 2);
			double y2 = points.get(i + 1).getY() * tileSize - tileSize + (tileSize / 2);

			gc.strokeLine(x1, y1, x2, y2);
		}
	}

	/**
	 * Set the current Position of the robot
	 * 
	 * @param pos
	 */
	public void SetRobotPos(Vector2 transPos)
	{
		if (robotPos.getX() + transPos.getX() >= 0 && robotPos.getX() + transPos.getX() < tileAmount)
		{
			robotPos.setX((int) (robotPos.getX() + transPos.getX()));
		}

		/*
		 * if (robotPos.getY() > transPos.getY()) { robotPos.setY((int)
		 * (robotPos.getY() + transPos.getY())); }
		 */

		Redraw();
	}

	public Vector2 GetRobotPos()
	{
		return robotPos;
	}
}
