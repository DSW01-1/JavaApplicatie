package main.java.graphs.grid;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.main.Vector2;

public class RobotGrid extends PathGrid
{
	private GridTile selectedTile;

	public RobotGrid()
	{
		super(5);
		AddMouseEventHandler();
	}

	@Override
	protected void AddMouseEventHandler()
	{
		addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
		{
			int x = (int) Math.ceil(event.getX() / (gridSize / tileAmount));
			int y = (int) Math.ceil(event.getY() / (gridSize / tileAmount));

			for (GridTile tile : gridTileArray)
			{
				if (tile.getXcoord() == x && tile.getYcoord() == y)
				{
					if (selectedTile != null)
					{
						if (selectedTile != tile)
						{
							tile.SetSelected(true);
							selectedTile.SetSelected(false);
							selectedTile = tile;
						}
						else
						{
							selectedTile.SetSelected(!selectedTile.IsSelected());
						}
					}
					else
					{
						selectedTile = tile;
						selectedTile.SetSelected(true);
					}
				}
			}
			Redraw();
		});
	}

	@Override
	public void Redraw()
	{
		gc.clearRect(0, 0, getWidth(), getHeight());
		gc.setLineWidth(1);

		for (GridTile tile : gridTileArray)
		{
			// Draw the Robot position if possible
			if (robotPos.Compare(tile.GetPos()))
			{
				DrawRobotPos(robotPos);
			}

			// Draw a tile if it is selected
			if (tile.IsSelected())
			{
				DrawTileIfSelected();
			}
		}
		super.Redraw();
	}

	private void DrawTileIfSelected()
	{
		double miniGrid = tileSize / 6;
		double x = selectedTile.GetPos().getX() * tileSize - (miniGrid * 5);
		double y = selectedTile.GetPos().getY() * tileSize - (miniGrid * 5);

		gc.setFill(Color.DODGERBLUE);
		gc.fillRoundRect(x, y, miniGrid * 4, miniGrid * 4, 8, 8);
	}

	@Override
	public void SetRobotPos(Vector2 vector2)
	{
		super.SetRobotPos(vector2);
		Redraw();
	}

	/**
	 * Get the current selected tile
	 * 
	 * @return GridTile
	 */
	public GridTile GetSelectedTile()
	{
		return selectedTile;
	}
}
