package main.java.graphs;

import javafx.scene.canvas.Canvas;
import main.java.main.Vector2;

public class GridTile extends Canvas
{
	public Vector2 coords;

	public GridTile(Vector2 coords)
	{
		this.coords = coords;
	}
}
