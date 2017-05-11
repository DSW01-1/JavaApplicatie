package main.java.database;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				cusInfo.setFirstname(rs.getString("firstname"));
				cusInfo.setLastname(rs.getString("lastname"));
				cusInfo.setAddress(rs.getString("address"));
				cusInfo.setZipcode(rs.getString("zipcode"));
				cusInfo.setCity(rs.getString("city"));

				Order order = new Order();
				order.setCustomerinfo(cusInfo);
				order.setDate(rs.getString("date"));
				order.setOrderid(rs.getString("orderid"));

				Array array = rs.getArray("products");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderList;
	}
}
