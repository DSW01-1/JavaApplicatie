package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.constant.Constants;
import main.java.database.DatabaseConnection;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.DatabaseManagePane;

public class ProductGrid extends BaseGrid
{
	ArrayList<Product> productArray;
	DatabaseManagePane databaseManage;

	public ProductGrid(int tileAmount, DatabaseManagePane databaseManage)
	{
		super(tileAmount);
		this.databaseManage = databaseManage;
		setLayoutX(ScreenProperties.getScreenWidth() / 2 - Constants.gridSize / 2);
		setLayoutY(15);

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
		for (Product product : productArray)
		{
			double x = (product.GetCoords().getX() + 1) * tileSize - tileSize + 15;
			double y = (product.GetCoords().getY() + 1) * tileSize - tileSize + 15;

			gc.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
			gc.fillRect(x, y, tileSize - 30, tileSize - 30);
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
