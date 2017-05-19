package main.java.pane;

import main.java.constant.Constants;
import main.java.graphs.grid.ProductGrid;
import main.java.main.Main;
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
}
