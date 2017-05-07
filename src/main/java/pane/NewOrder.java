package main.java.pane;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.database.DatabaseConnection;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledScrollPane;

public class NewOrder extends Pane
{
	private VBox rightColumnVBox, leftColumnVBox;

	public NewOrder()
	{
		super();
		setId("background");

		StyledButton btnBackToMainMenu = new StyledButton(Language.getTranslation("btn.backToMainMenu"),
				new Vector2(10, 10));
		btnBackToMainMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent aEvent)
			{
				Main.SwitchPane(new MainMenu());
			}
		});
		getChildren().add(btnBackToMainMenu);

		CreateLeftColumn();
		CreateRightColumn();

		StyledButton orderButton = new StyledButton(Language.getTranslation("btn.order"), new Vector2(
				ScreenProperties.getScreenWidth() / 4 * 3 - 200, ScreenProperties.getScreenHeight() / 3 + 210));
		getChildren().add(orderButton);
	}

	/**
	 * Create the left column and adds all available products from the database
	 * to it
	 */
	private void CreateLeftColumn()
	{
		leftColumnVBox = new VBox();

		if (leftColumnVBox.getChildren().size() > 0)
		{
			leftColumnVBox.getChildren().removeAll();
		}

		leftColumnVBox.setMaxWidth(200);

		ArrayList<String> productList = DatabaseConnection.GetAvailableProducts();

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

		getChildren().add(new StyledScrollPane(leftColumnVBox, new Vector2(200, 400),
				new Vector2(ScreenProperties.getScreenWidth() / 4, ScreenProperties.getScreenHeight() / 3 - 200)));
	}

	/**
	 * Creates the right column
	 */
	private void CreateRightColumn()
	{
		rightColumnVBox = new VBox();
		rightColumnVBox.setMaxWidth(200);

		getChildren().add(new StyledScrollPane(rightColumnVBox, new Vector2(200, 400), new Vector2(
				ScreenProperties.getScreenWidth() / 4 * 3 - 200, ScreenProperties.getScreenHeight() / 3 - 200)));
	}

	/**
	 * Moves a product from one column to the other
	 * 
	 * @param fromSide
	 *            the side where the products originates from
	 * @param product
	 *            the product object
	 */
	public void MoveProduct(String fromSide, ProductPane product)
	{
		if (fromSide.equals("right"))
		{
			rightColumnVBox.getChildren().remove(product);
			leftColumnVBox.getChildren().add(new ProductPane(product.name, "left", this));
		}
		else
		{
			rightColumnVBox.getChildren().add(new ProductPane(product.name, "right", this));
			leftColumnVBox.getChildren().remove(product);
		}
	}
}
