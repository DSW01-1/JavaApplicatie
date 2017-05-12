package main.java.pane;

import java.util.ArrayList;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.constant.Constants;
import main.java.database.DatabaseConnection;
import main.java.graphs.Product;
import main.java.handler.JsonHandler;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;
import main.java.pane.tab.BPPTab;
import main.java.pane.tab.RobotTab;
import main.java.pane.tab.TSPTab;

public class NewOrder extends StyledPane
{
	private VBox rightColumnVBox, leftColumnVBox;
	private TextField[] formItems;

	@Override
	public void InitPane()
	{
		// Back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		// Create the columns
		CreateLeftColumn();
		CreateRightColumn();
		CreateForm();

		AddProductsToLeftColumn();
	}

	private void CreateForm()
	{
		GridPane form = new GridPane();
		form.setLayoutX(ScreenProperties.getScreenWidth() / 4 * 2.5f);
		form.setLayoutY(300);
		form.setHgap(15);
		form.setVgap(15);

		String[] labelArray =
		{ "form.firstname", "form.lastname", "form.address", "form.zipcode", "form.city" };

		formItems = new TextField[labelArray.length];

		for (int i = 0; i < labelArray.length; i++)
		{
			form.add(new StyledLabel(labelArray[i]), 0, i);
			TextField textField = new TextField();

			form.add(textField, 1, i);
			formItems[i] = textField;
		}

		// The order button, can't be pressed if no items are in the right
		// column
		StyledButton orderButton = new StyledButton("btn.order");
		orderButton.setOnAction(event ->
		{
			String[] userData = new String[formItems.length];

			for (int i = 0; i < userData.length; i++)
			{
				userData[i] = formItems[i].getText();
			}

			ArrayList<String> productID = new ArrayList<String>();

			for (int i = 0; i < rightColumnVBox.getChildren().size(); i++)
			{
				ProductPane product = (ProductPane) rightColumnVBox.getChildren().get(i);
				productID.add(Integer.toString(product.GetProduct().GetId()));
			}

			JsonHandler.SaveOrderToJSON(userData, productID);

			StyledPane[] tabArray =
			{ new RobotTab(), new TSPTab(), new BPPTab() };

			String[] nameArray =
			{ "Robot", "TSP", "BPP" };

			Main.SwitchPane(new BaseTabPane(nameArray, tabArray));
		});
		form.add(orderButton, 1, labelArray.length);
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

		getChildren().add(new StyledScrollPane(leftColumnVBox, new Vector2(200, 400),
				new Vector2(100, ScreenProperties.getScreenHeight() / 3 - 200)));
	}

	/**
	 * Add products from the database to the left column
	 */
	private void AddProductsToLeftColumn()
	{
		// Get all products available
		ArrayList<Product> productList = DatabaseConnection.GetAvailableProducts();

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
			text.setMaxWidth(200);
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

		getChildren().add(new StyledScrollPane(rightColumnVBox, new Vector2(200, 400),
				new Vector2(350, ScreenProperties.getScreenHeight() / 3 - 200)));
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
		if (fromSide.equals("right"))
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
