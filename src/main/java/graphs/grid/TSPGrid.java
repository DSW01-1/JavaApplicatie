package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.constant.Constants;
import main.java.main.Vector2;

public class TSPGrid extends BaseGrid
{
	private Color lineColor = Constants.standardLineColor;
	private Color tileColor = Constants.standardTileColor;
	private ArrayList<Vector2> pathList;
	private boolean isActive = false;
	private boolean isInteractive;
	private Vector2 robotPos;

	public TSPGrid(int tileAmount, boolean isInteractive)
	{
		super(tileAmount);
		this.tileAmount = tileAmount;
		this.isInteractive = isInteractive;

		AddMouseEventHandler();
		Redraw();
	}

	public void chgLineColor(Color color)
	{
		this.lineColor = color;
	}

	/**
	 * Add a mouse handler to add points on the grid
	 */
	protected void AddMouseEventHandler()
	{
		addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
		{
			int x = (int) Math.ceil(event.getX() / (gridSize / tileAmount));
			int y = (int) Math.ceil(event.getY() / (gridSize / tileAmount));

			if (isInteractive)
			{
				for (GridTile tile : gridTileArray)
				{
					if (tile.getXcoord() == x && tile.getYcoord() == y)
					{
						tile.SetSelected(!tile.IsSelected());
						pathList = null;
					}
				}
				Redraw();
			}
		});
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
	 * Redraw the grid
	 */
	@Override
	public void Redraw()
	{
		gc.clearRect(0, 0, getWidth(), getHeight());
		gc.setLineWidth(1);

		for (int y = tileAmount; y >= 0; y--)
		{
			for (int x = 0; x <= tileAmount; x++)
			{
				// Draw the Robot position if possible
				if (robotPos != null)
				{
					DrawRobotPos(new Vector2(x, y));
				}
			}
		}

		// Draw the points
		if (pathList != null)
		{
			DrawPathLines(pathList);
		}
		for (GridTile tile : gridTileArray)
		{
			if (tile.IsSelected())
			{
				DrawTileIfSelected(new Vector2(tile.getXcoord(), tile.getYcoord()));
			}
		}

		super.Redraw();
	}

	public void Redraw(ArrayList<Vector2> customArray)
	{
		customArray.add(0, new Vector2(1, 1));
		customArray.add(customArray.size(), new Vector2(1, 1));
		pathList = customArray;
		Redraw();
	}

	/**
	 * Draw the current Robot position on the grid
	 * 
	 * @param coords
	 */
	private void DrawRobotPos(Vector2 coords)
	{
		if (robotPos.getX() == coords.getX() && robotPos.getY() == coords.getY())
		{
			gc.setFill(Color.ANTIQUEWHITE);
			gc.fillRect(robotPos.getX() * tileSize, robotPos.getY() * tileSize, tileSize, tileSize);
		}
	}

	/**
	 * Draw circles on the tiles that are selected
	 * 
	 * @param tilePos
	 */
	public void DrawTileIfSelected(Vector2 tilePos)
	{
		double x = tilePos.getX() * tileSize - tileSize + 25;
		double y = tilePos.getY() * tileSize - tileSize + 25;

		gc.setFill(tileColor);
		gc.fillRoundRect(x, y, tileSize - 50, tileSize - 50, 8, 8);
	}

	/**
	 * Draw lines between the given points
	 * 
	 * @param points
	 */
	public void DrawPathLines(ArrayList<Vector2> points)
	{
		gc.setLineWidth(5);
		gc.setStroke(lineColor);

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
	 * Get the current robot position
	 * 
	 * @return
	 */
	public Vector2 GetRobotPos()
	{
		return robotPos;
	}

	public void SetGridColor(String color)
	{
		switch (color)
		{
		case "black":
			tileColor = Color.BLACK;
			lineColor = Color.GRAY;
			break;
		case "red":
			tileColor = Color.DARKRED;
			lineColor = Color.RED;
			break;
		case "blue":
			tileColor = Color.DARKBLUE;
			lineColor = Color.BLUE;
			break;
		case "green":
			tileColor = Color.DARKGREEN;
			lineColor = Color.GREEN;
			break;
		default:
			tileColor = Color.BLACK;
			lineColor = Color.GRAY;
			break;
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

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean active)
	{
		isActive = active;
	}
}
