package main.java.graphs.grid;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import main.java.constant.Constants;
import main.java.database.DatabaseConnection;
import main.java.graphs.Product;
import main.java.main.ScreenProperties;

public class ProductGrid extends BaseGrid
{
	ArrayList<Product> productArray;

	public ProductGrid(int tileAmount)
	{
		super(tileAmount);
		setLayoutX(ScreenProperties.getScreenWidth() / 2 - Constants.gridSize / 2);
		setLayoutY(15);

		productArray = new ArrayList<Product>();
	}

	@Override
	public void Redraw()
	{
		for (Product product : productArray)
		{
			System.out.println(product.GetName());

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
	}

}
