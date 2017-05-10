package main.java.graphs;

import javafx.scene.canvas.Canvas;
import main.java.main.Vector2;

public class GridTile extends Canvas
{
	private Vector2 coords;
	private boolean isSelected;

	public GridTile(Vector2 coords)
	{
		this.coords = coords;
		SetSelected(false);
	}

	public Vector2 GetCoords()
	{
		return coords;
	}

	public double getXcoord() {
		double xCoord = coords.getX();

		return xCoord;
	}
	public double getYcoord() {
		double yCoord = coords.getY();

		return yCoord;
	}

	public boolean IsSelected()
	{
		return isSelected;
	}

	public void SetSelected(boolean select)
	{
		isSelected = select;
	}
}
