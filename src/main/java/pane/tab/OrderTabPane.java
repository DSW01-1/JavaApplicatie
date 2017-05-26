package main.java.pane.tab;

import java.util.ArrayList;

import main.java.controller.ArduinoController;
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
	private ArduinoController controller;

	public OrderTabPane(String[] name, StyledPane[] tabArray)
	{
		super(name, tabArray);
		this.tabArray = tabArray;

		controller = new ArduinoController();
		controller.EstablishConnection();
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

	/**
	 * Load the order from json, except when loading an order from file
	 */
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
			

			((RobotTspTab) tabArray[0]).Setup(controller, products);
			((BPPTab) tabArray[1]).Setup(controller, products);
		}
		catch (NullPointerException ex)
		{
			LogHandler.WriteErrorToLogFile(ex, "Database is not on, could not retrieve info");
		}
	}
}
