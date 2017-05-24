package main.java.pane.database;

import java.util.ArrayList;
import java.util.Random;

import main.java.constant.Constants;
import main.java.constant.NameConstants;
import main.java.database.DatabaseConnection;
import main.java.database.DatabaseManagement;
import main.java.graphs.grid.ProductGrid;
import main.java.main.Main;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class DatabaseManagePane extends StyledPane
{
	private DatabaseEditorPane editorPane;
	private ProductGrid grid;

	/**
	 * The main database manager
	 */
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

	/**
	 * Add the product grid
	 */
	private void AddProductGrid()
	{
		grid = new ProductGrid(Constants.baseWareHouseSize, this);

		grid.UpdateProductArray();
		getChildren().add(grid);
	}

	/**
	 * Add the product editor, is added when clicking on a product
	 * 
	 * @param product
	 */
	public void AddProductEditor(Product product)
	{
		// Delete the editor pane if it exists
		if (editorPane != null)
		{
			getChildren().remove(editorPane);
		}

		// Create a new Editor Pane
		editorPane = new DatabaseEditorPane(product, grid);
		getChildren().add(editorPane);
	}

	/**
	 * Create the control buttons
	 */
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

			// For every product in the database
			for (Product product : productList)
			{
				// Add the product to the corresponding coord in the array
				productArray[(int) product.GetCoords().getX() - 1][(int) product.GetCoords().getY() - 1] = product;
			}

			// For every possible coordinate on the grid
			for (int y = 0; y < baseWareHouseSize; y++)
			{
				for (int x = 0; x < baseWareHouseSize; x++)
				{
					// If there isn't a product already
					if (productArray[x][y] == null)
					{
						// Create a new product with a random name and a random
						// size
						Random r = new Random();
						Product product = new Product(NameConstants.GetRandomProductName(), new Vector2(x + 1, y + 1),
								r.nextInt(10));
						newProductArray.add(product);
					}
				}
				grid.Redraw();
			}

			// Push all the created products to the database
			DatabaseManagement.CreateListOfNewProducts(newProductArray);
		});
		getChildren().add(randomFill);
	}
}
