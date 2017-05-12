package main.java.graphs;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import main.java.constant.Constants;
import main.java.main.Vector2;

public class Grid extends Canvas
{
	private ArrayList<GridTile> gridTileArray;
	private GraphicsContext gc;
	private int tileAmount;
	private int tileSize;
	private int gridSize = Constants.gridSize;

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
		for (int y = 0; y <= tileAmount; y++)
		{
			for (int x = 0; x <= tileAmount; x++)
			{
				gc.strokeLine(0, y * tileSize, gridSize, y * tileSize);
				gc.strokeLine(x * tileSize, 0, x * tileSize, gridSize);
			}
		}

		for (GridTile tile : gridTileArray)
		{
			if (tile.IsSelected())
			{
				DrawTileIfSelected(new Vector2(tile.getXcoord(), tile.getYcoord()));
			}
		}
	}

	public void DrawTileIfSelected(Vector2 tilePos)
	{

		gc.fillOval(tilePos.getX() * tileSize - tileSize + (tileSize / 3),
				tilePos.getY() * tileSize - tileSize + (tileSize / 3), tileSize / 3, tileSize / 3);

	}
}
