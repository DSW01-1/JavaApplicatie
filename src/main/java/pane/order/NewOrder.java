package main.java.pane.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import main.java.database.DatabaseConnection;
import main.java.main.Language;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.BackToMainMenuButton;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class NewOrder extends StyledPane
{
	protected VBox rightColumnVBox;
	protected VBox leftColumnVBox;
	private Vector2 size = new Vector2(300, 600);

	@Override
	public void InitPane()
	{
		getChildren().add(new BackToMainMenuButton());
		getStyleClass().add("background");

		// Create the columns
		CreateLeftColumn();
		CreateRightColumn();
		CreateForm();

		AddProductsToLeftColumn();
	}

	private void CreateForm()
	{
		OrderForm form = new OrderForm(this);
		getChildren().add(form);
	}

	/**
	 * Create the left column and adds all available products from the database
	 * to it
	 */
	private void CreateLeftColumn()
	{
		leftColumnVBox = new VBox();

		// Delete the current column if it already exists
		if (leftColumnVBox.getChildren().size() > 0)
		{
			leftColumnVBox.getChildren().removeAll();
		}

		// The max width of the column
		leftColumnVBox.setMaxWidth(200);

		Vector2 pos = new Vector2(100, ScreenProperties.getScreenHeight() / 3 - 200);

		getChildren().add(new StyledScrollPane(leftColumnVBox, pos, size));
	}

	/**
	 * Add products from the database to the left column
	 */
	private void AddProductsToLeftColumn()
	{
		// Get all products available
		ArrayList<Product> productList = DatabaseConnection.GetAvailableProducts();

		long seed = System.nanoTime();
		Collections.shuffle(productList, new Random(seed));

		// If there are products in the database, add them to the list
		if (productList.size() > 0)
		{
			for (int i = 0; i < productList.size(); i++)
			{
				leftColumnVBox.getChildren().add(new ProductPane(productList.get(i), "left", this));
			}
		}
		// Else, there might not be a connection or there are actually no
		// products in the database
		else
		{
			TextArea text = new TextArea(Language.getTranslation("warning.nodatabaseconnection"));
			text.setWrapText(true);
			text.setMinWidth(298);
			text.setPrefHeight(598);
			text.setEditable(false);

			leftColumnVBox.getChildren().add(text);
		}
	}

	/**
	 * Creates the right column
	 */
	private void CreateRightColumn()
	{
		rightColumnVBox = new VBox();
		rightColumnVBox.setMaxWidth(200);

		Vector2 pos = new Vector2(500, ScreenProperties.getScreenHeight() / 3 - 200);

		getChildren().add(new StyledScrollPane(rightColumnVBox, pos, size));
	}

	/**
	 * Moves a product from one column to the other
	 * 
	 * @param fromSide
	 *            the side where the products originates from
	 * @param product
	 *            the product object
	 */
	public void MoveProduct(String fromSide, ProductPane productPane)
	{
		if ("right".equals(fromSide))
		{
			rightColumnVBox.getChildren().remove(productPane);
			leftColumnVBox.getChildren().add(new ProductPane(productPane.GetProduct(), "left", this));
		}
		else
		{
			rightColumnVBox.getChildren().add(new ProductPane(productPane.GetProduct(), "right", this));
			leftColumnVBox.getChildren().remove(productPane);
		}
	}
}
