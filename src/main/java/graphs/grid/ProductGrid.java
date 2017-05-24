package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.constant.ColorConstants;
import main.java.constant.Constants;
import main.java.database.DatabaseConnection;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.database.DatabaseManagePane;

public class ProductGrid extends BaseGrid
{
	private ArrayList<Product> productArray;
	private DatabaseManagePane databaseManage;
	private GridTile selectedTile;

	public ProductGrid(int tileAmount, DatabaseManagePane databaseManage)
	{
		super(tileAmount);
		this.databaseManage = databaseManage;
		setLayoutX(ScreenProperties.getScreenWidth() / 2 - Constants.drawnGridSize / 2);
		setLayoutY(50);

		productArray = new ArrayList<Product>();
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

					Redraw();
					databaseManage.AddProductEditor(TileHasProduct(new Vector2(x, y)));
				}
			}
		});
	}

	private Product TileHasProduct(Vector2 tileCoord)
	{
		Product returnProduct = null;

		for (Product product : productArray)
		{
			if (product.CheckCoords(tileCoord) && returnProduct == null)
			{
				returnProduct = product;
			}
		}

		if (returnProduct == null)
		{
			returnProduct = new Product("", tileCoord, 0);
		}

		return returnProduct;
	}

	@Override
	public void Redraw()
	{
		gc.clearRect(0, 0, gridSize, gridSize);
		if (selectedTile != null)
		{
			gc.setStroke(ColorConstants.standardSelectColor);
			gc.setLineWidth(10);
			gc.strokeRect((selectedTile.getXcoord() - 1) * tileSize + 5, (selectedTile.getYcoord() - 1) * tileSize + 5,
					tileSize - 10, tileSize - 10);
		}

		for (Product product : productArray)
		{
			double x = product.GetCoords().getX() * tileSize - tileSize + 15;
			double y = product.GetCoords().getY() * tileSize - tileSize + 15;

			gc.setFill(new Color(1 - (product.GetSize() / 10), 1 - (product.GetSize() / 10), 1, 1));
			gc.fillRoundRect(x, y, tileSize - 30, tileSize - 30, 8, 8);
		}

		super.Redraw();
	}

	public void UpdateProductArray()
	{
		productArray.removeAll(productArray);
		productArray = DatabaseConnection.GetAvailableProducts();
		Redraw();
	}

}
