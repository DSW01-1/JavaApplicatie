package main.java.pane.order;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import main.java.database.DatabaseOrder;
import main.java.main.Language;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.CustomerInfo;
import main.java.pane.base.BackToMainMenuButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class OrderHistory extends StyledPane
{
	public OrderHistory()
	{
		super();
	}

	// create initializefunction
	@Override
	public void InitPane()
	{
		// terug naar menu knop
		getChildren().add(new BackToMainMenuButton());

		// posities van lijsten
		Vector2 personPos = new Vector2(ScreenProperties.getScreenWidth() / 3 - 100, 240);
		Vector2 orderPos = new Vector2(ScreenProperties.getScreenWidth() / 2 - 100, 240);
		Vector2 productPos = new Vector2((ScreenProperties.getScreenWidth() / 3) * 2 - 100, 240);
		Vector2 headerPos = new Vector2(800, 20);

		// alle labels
		StyledLabel headerLabel = new StyledLabel("lbl.history", headerPos);
		StyledLabel personLabel = new StyledLabel("lbl.persons", personPos);
		StyledLabel orderLabel = new StyledLabel("lbl.orders", orderPos);
		StyledLabel productLabel = new StyledLabel("lbl.products", productPos);
		getChildren().addAll(personLabel, orderLabel, productLabel, headerLabel);

		// eerste instanties maken
		createPersonVBox();
		createEmptyOrderVBox();
		createEmptyProductVBox();
	}

	// create Person list
	private void createPersonVBox()
	{
		// get customers to add to list
		ArrayList<CustomerInfo> customers = DatabaseOrder.getAllCustomers();
		VBox vbox = new VBox();

		Vector2 pos = new Vector2(ScreenProperties.getScreenWidth() / 3 - 100, 270);
		Vector2 size = new Vector2(200, 500);
		// add all customers to list
		for (CustomerInfo info : customers)
		{
			vbox.getChildren().add(new PersonPane(info, this));
		}
		// if there are no persons add this message
		if (vbox.getChildren().size() == 0)
		{
			TextArea text = new TextArea(Language.getTranslation("warning.nopersons"));
			text.setWrapText(true);
			text.setMinWidth(200);
			text.setPrefHeight(30);
			text.setEditable(false);
			vbox.getChildren().add(text);
		}
		getChildren().add(new StyledScrollPane(vbox, pos, size));
	}

	// create Order list
	public void createOrderVBox(int Customerid)
	{
		VBox vboxOrder = new VBox();
		List<Integer> orders;
		// get all order ID's from the database
		orders = DatabaseOrder.getOrdersFromId(Customerid);
		// add all orders to list
		for (Integer ordernr : orders)
		{
			vboxOrder.getChildren().add(new OrderPane(ordernr, this));
		}

		Vector2 pos = new Vector2(ScreenProperties.getScreenWidth() / 2 - 100, 270);
		Vector2 size = new Vector2(200, 500);
		getChildren().add(new StyledScrollPane(vboxOrder, pos, size));
		// display message if there are no orders
		if (vboxOrder.getChildren().size() == 0)
		{
			TextArea text = new TextArea(Language.getTranslation("warning.noorders"));
			text.setWrapText(true);
			text.setMinWidth(200);
			text.setPrefHeight(30);
			text.setEditable(false);
			vboxOrder.getChildren().add(text);
		}
	}

	/**
	 * Create the procuct box
	 * 
	 * @param Ordernr
	 */
	public void createProductVBox(int Ordernr)
	{
		VBox vboxProduct = new VBox();
		List<Integer> products;
		// get products from database from the order.
		products = DatabaseOrder.getProductsIDFromOrder(Ordernr);
		// add all products to the list
		for (Integer productid : products)
		{
			vboxProduct.getChildren().add(new ProductPaneHistory(DatabaseOrder.getProductByID(productid)));
		}
		Vector2 pos = new Vector2((ScreenProperties.getScreenWidth() / 3) * 2 - 100, 270);
		Vector2 size = new Vector2(200, 500);
		getChildren().add(new StyledScrollPane(vboxProduct, pos, size));
		// display message if there are no products
		if (vboxProduct.getChildren().size() == 0)
		{
			TextArea text = new TextArea(Language.getTranslation("warning.noproducts"));
			text.setWrapText(true);
			text.setMinWidth(200);
			text.setPrefHeight(30);
			text.setEditable(false);
			vboxProduct.getChildren().add(text);
		}
	}

	// ik weet dat dit beter kan :P
	private void createEmptyOrderVBox()
	{
		VBox vboxOrder = new VBox();
		Vector2 pos = new Vector2(ScreenProperties.getScreenWidth() / 2 - 100, 270);
		Vector2 size = new Vector2(200, 500);
		getChildren().add(new StyledScrollPane(vboxOrder, pos, size));
	}

	// ik weet dat dit beter kan :P
	// creeërt lege lijst voor eerste scherm
	private void createEmptyProductVBox()
	{
		VBox vboxProduct = new VBox();
		Vector2 pos = new Vector2(((ScreenProperties.getScreenWidth() / 3) * 2) - 100, 270);
		Vector2 size = new Vector2(200, 500);
		getChildren().add(new StyledScrollPane(vboxProduct, pos, size));
	}
}
