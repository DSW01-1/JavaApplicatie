package main.java.graphs;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.constant.Constants;

public class BaseGrid extends Canvas
{
	private GraphicsContext gc;
	private int tileAmount;
	private int tileSize;
	private int gridSize = Constants.gridSize;

	public BaseGrid(int tileAmount)
	{
	}

	/**
	 * Redraw the grid
	 */
	public void Redraw()
	{
		gc.clearRect(0, 0, getWidth(), getHeight());

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
}
