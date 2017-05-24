package main.java.pane.tab;

import java.util.ArrayList;

import main.java.database.DatabaseConnection;
import main.java.database.DatabaseOrder;
import main.java.handler.JsonHandler;
import main.java.handler.LogHandler;
import main.java.main.product.Order;
import main.java.main.product.Product;
import main.java.pane.base.BaseTabPane;
import main.java.pane.base.StyledPane;

public class OrderTabPane extends BaseTabPane
{
	StyledPane[] tabArray;

	public OrderTabPane(String[] name, StyledPane[] tabArray)
	{
		super(name, tabArray);
		this.tabArray = tabArray;
	}

	@Override
	public void InitPane()
	{
		CreateTabPane();
		LoadOrderFromJson();
	}

	private void LoadOrderFromJson()
	{
		try
		{
		Order order = JsonHandler.GetNewestOrder();

		ArrayList<String> productArray = order.getProducts();
		ArrayList<Product> products = new ArrayList<Product>();

		for (String product : productArray)
		{
		 products.add(DatabaseConnection.GetProductInfo(Integer.parseInt(product)));
		}

		((RobotTab) tabArray[0]).Setup(products);
		((BPPTab) tabArray[1]).Setup(products);

		DatabaseOrder.addOrderToDatabase(order);
		}
		catch(NullPointerException ex)
		{
			LogHandler.WriteErrorToLogFile(ex, "Database is not on, could not retrieve info");
		}
	}

}
