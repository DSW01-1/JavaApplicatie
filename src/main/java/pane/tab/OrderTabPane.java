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
	private ArrayList<Product> products;
	private StyledPane[] tabArray;
	private Order order;

	public OrderTabPane(String[] name, StyledPane[] tabArray)
	{
		super(name, tabArray);
		this.tabArray = tabArray;
	}

	public OrderTabPane(String[] name, StyledPane[] tabArray, Order order)
	{
		this(name, tabArray);
		this.order = order;
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
			if (order == null)
			{
				order = JsonHandler.GetNewestOrder();
				DatabaseOrder.addOrderToDatabase(order);
			}

			ArrayList<String> productArray = order.getProducts();
			products = new ArrayList<Product>();

			for (String product : productArray)
			{
				products.add(DatabaseConnection.GetProductInfo(Integer.parseInt(product)));
			}

			((RobotTab) tabArray[0]).Setup(products);
			((BPPTab) tabArray[1]).Setup(products);
		}
		catch (NullPointerException ex)
		{
			LogHandler.WriteErrorToLogFile(ex, "Database is not on, could not retrieve info");
		}
	}

}
