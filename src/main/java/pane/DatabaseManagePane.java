package main.java.pane;

import java.util.ArrayList;
import java.util.Random;

import main.java.constant.Constants;
import main.java.constant.NameConstant;
import main.java.database.DatabaseConnection;
import main.java.database.DatabaseManagement;
import main.java.graphs.grid.ProductGrid;
import main.java.main.Main;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class DatabaseManagePane extends StyledPane
{
	int newXValue, newYValue = 0;
	DatabaseEditorPane editorPane;
	ProductGrid grid;

	public DatabaseManagePane()
	{
		super();

		// Back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		AddProductGrid();
		CreateButtons();
	}

	private void AddProductGrid()
	{
		grid = new ProductGrid(Constants.baseWareHouseSize, this);

		grid.UpdateProductArray();
		getChildren().add(grid);
	}

	public void AddProductEditor(Product product)
	{
		if (editorPane != null)
		{
			getChildren().remove(editorPane);
		}

		editorPane = new DatabaseEditorPane(product, grid);
		getChildren().add(editorPane);
	}

	private void CreateButtons()
	{
		Vector2 pos = new Vector2(110, 200);
		Vector2 size = new Vector2(150, 50);
		StyledButton randomFill = new StyledButton("btn.randomFill", pos, size);
		randomFill.setOnAction(event ->
		{
			int baseWareHouseSize = Constants.baseWareHouseSize;

			ArrayList<Product> newProductArray = new ArrayList<Product>();
			Product[][] productArray = new Product[baseWareHouseSize][baseWareHouseSize];

			ArrayList<Product> productList = DatabaseConnection.GetAvailableProducts();

			for (Product product : productList)
			{
				productArray[(int) product.GetCoords().getX()][(int) product.GetCoords().getY()] = product;
			}

			for (int y = 0; y < baseWareHouseSize; y++)
			{
				for (int x = 0; x < baseWareHouseSize; x++)
				{
					if (productArray[x][y] == null)
					{
						Random r = new Random();
						Product product = new Product(NameConstant.GetRandomName(), new Vector2(x + 1, y + 1), r.nextInt(10));
						newProductArray.add(product);
					}
				}
				grid.Redraw();
			}
			DatabaseManagement.CreateListOfNewProducts(newProductArray);
		});
		getChildren().add(randomFill);
	}
}
