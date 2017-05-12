package main.java.graphs;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import main.java.constant.Constants;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;

public class Grid extends Canvas
{
	private ArrayList<GridTile> gridTileArray;
	private GraphicsContext gc;
	private int tileSize;
	private int tileAmount;
	private int gridSize = Constants.gridSize;

	public Grid(int tileAmount, boolean isInteractive)
	{
		super();
		gc = getGraphicsContext2D();
		setWidth(gridSize);
		setHeight(gridSize);
		setScaleY(-1);

		this.tileAmount = tileAmount;
		Redraw();

		gridTileArray = new ArrayList<GridTile>();

		tileSize = (ScreenProperties.getScreenHeight() / 2) / gridSize;

		for (int y = 0; y < gridSize; y++)
		{
			for (int x = 0; x < gridSize; x++)
			{
				final GridTile tile = new GridTile(new Vector2(x, y));
				gridTileArray.add(tile);
			}
		}

		addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
		{
			int x = (int) Math.ceil(event.getX() / (gridSize / tileAmount));
			int y = (int) Math.ceil(event.getY() / (gridSize / tileAmount));

			Vector2 mouseCoord = new Vector2(x, y);

			System.out.println(x + ";" + y);
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
		for (int y = 0; y <= tileAmount; y++)
		{
			for (int x = 0; x <= tileAmount; x++)
			{
				gc.strokeLine(0, y * gridSize / tileAmount, gridSize, y * gridSize / tileAmount);
				gc.strokeLine(x * gridSize / tileAmount, 0, x * gridSize / tileAmount, gridSize);
			}
		}
	}

	public void SetTile(Vector2 tilePos, boolean selected)
	{
		for (GridTile tile : gridTileArray)
		{
			if (tile.getXcoord() == tilePos.getX() && tile.getYcoord() == tilePos.getY())
			{
				tile.SetSelected(selected);
				if (selected)
				{
					gc.fillOval(tilePos.getX() * tileSize, tilePos.getY() * tileSize, tileSize / 2, tileSize / 2);
				}
			}
		}
	}
}
