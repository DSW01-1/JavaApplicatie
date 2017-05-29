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

	@Override
	public void InitPane()
	{
		getChildren().add(new BackToMainMenuButton());

		Vector2 personPos = new Vector2(ScreenProperties.getScreenWidth() / 3 - 100, 240);
		Vector2 orderPos = new Vector2(ScreenProperties.getScreenWidth() / 2 - 100, 240);
		Vector2 productPos = new Vector2((ScreenProperties.getScreenWidth() / 3) * 2 - 100, 240);
		Vector2 headerPos = new Vector2(800, 20);

		StyledLabel headerLabel = new StyledLabel("lbl.history", headerPos);
		StyledLabel personLabel = new StyledLabel("lbl.persons", personPos);
		StyledLabel orderLabel = new StyledLabel("lbl.orders", orderPos);
		StyledLabel productLabel = new StyledLabel("lbl.products", productPos);
		getChildren().addAll(personLabel, orderLabel, productLabel, headerLabel);

		createPersonVBox();
		createEmptyOrderVBox();
		createEmptyProductVBox();
	}

	private void createPersonVBox()
	{
		ArrayList<CustomerInfo> customers = DatabaseOrder.getAllCustomers();
		VBox vbox = new VBox();

		Vector2 pos = new Vector2(ScreenProperties.getScreenWidth() / 3 - 100, 270);
		Vector2 size = new Vector2(200, 500);
		for (CustomerInfo info : customers)
		{
			vbox.getChildren().add(new PersonPane(info, this));
		}
		if(vbox.getChildren().size()==0){
			TextArea text = new TextArea(Language.getTranslation("warning.nopersons"));
			text.setWrapText(true);
			text.setMinWidth(200);
			text.setPrefHeight(30);
			text.setEditable(false);
			vbox.getChildren().add(text);
		}
		getChildren().add(new StyledScrollPane(vbox, pos, size));
	}

	public void createOrderVBox(int Customerid)
	{
		VBox vboxOrder = new VBox();
		List<Integer> orders;
		orders = DatabaseOrder.getOrdersFromId(Customerid);
		for (Integer ordernr : orders)
		{
			vboxOrder.getChildren().add(new OrderPane(ordernr, this));
		}

		Vector2 pos = new Vector2(ScreenProperties.getScreenWidth() / 2 - 100, 270);
		Vector2 size = new Vector2(200, 500);
		getChildren().add(new StyledScrollPane(vboxOrder, pos, size));
	}

	public void createProductVBox(int Ordernr)
	{
		VBox vboxProduct = new VBox();
		List<Integer> products;
		products = DatabaseOrder.getProductsIDFromOrder(Ordernr);
		for (Integer productid : products)
		{
			vboxProduct.getChildren().add(new ProductPaneHistory(DatabaseOrder.getProductByID(productid), this));
		}
		Vector2 pos = new Vector2((ScreenProperties.getScreenWidth() / 3) * 2 - 100, 270);
		Vector2 size = new Vector2(200, 500);
		getChildren().add(new StyledScrollPane(vboxProduct, pos, size));
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
	private void createEmptyProductVBox()
	{
		VBox vboxProduct = new VBox();
		Vector2 pos = new Vector2(((ScreenProperties.getScreenWidth() / 3) * 2) - 100, 270);
		Vector2 size = new Vector2(200, 500);
		getChildren().add(new StyledScrollPane(vboxProduct, pos, size));
	}
}
