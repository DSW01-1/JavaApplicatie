package main.java.database;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.handler.LogHandler;
import main.java.main.product.CustomerInfo;
import main.java.main.product.Order;

public class DatabaseOrder
{
	public static ArrayList<Order> GetAllOrders()
	{
		ArrayList<Order> orderList = new ArrayList<Order>();

		ResultSet rs = DatabaseConnection.GetDataFromDatabase("");

		try
		{
			while (rs.next())
			{
				CustomerInfo cusInfo = new CustomerInfo();
				cusInfo.setFirstname(rs.getString("Firstname"));
				cusInfo.setLastname(rs.getString("Lastname"));
				cusInfo.setAddress(rs.getString("Street"));
				cusInfo.setZipcode(rs.getString("Zipcode"));
				cusInfo.setCity(rs.getString("City"));

				Order order = new Order();
				order.setCustomerinfo(cusInfo);
				order.setDate(rs.getString("Orderdate"));
				order.setOrderid(rs.getString("Orderid"));

				Array array = rs.getArray("product");
				String[] productArray = (String[]) array.getArray();
				ArrayList<String> products = new ArrayList<String>();

				for (String product : productArray)
				{
					products.add(product);
				}
				order.setProductnumber(products);
				orderList.add(order);
			}
		}
		catch (SQLException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Could not retrieve order info from datebees");
		}

		return orderList;
	}
}
