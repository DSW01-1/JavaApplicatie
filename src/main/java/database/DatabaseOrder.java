package main.java.database;

import java.sql.*;
import java.util.ArrayList;

import main.java.constant.Constants;
import main.java.handler.LogHandler;
import main.java.main.product.CustomerInfo;
import main.java.main.product.Order;
import main.java.main.product.Product;

public class DatabaseOrder
{
	public static ArrayList<Order> GetAllOrders()
	{
		ArrayList<Order> orderList = new ArrayList<Order>();

		ResultSet rs = DatabaseConnection.GetDataFromDatabase("select Firstname,Lastname,Street,Zipcode,City,o.Orderdate,o.Orderid,p.Productid,\n" +
				"p.Productname,Productsize,xcoord,ycoord  \n" +
				"From customers c join orders o on c.Customerid=o.Customerid \n" +
				"join receipts r on r.Orderid = o.Orderid\n" +
				"join products p on p.Productid = r.Productid ");

		try
		{
			while (rs.next())
			{
				CustomerInfo cusInfo = new CustomerInfo();
				cusInfo.setFirstname(rs.getString("Firstname"));
				cusInfo.setLastname(rs.getString("Lastname"));
				cusInfo.setAddress(rs.getString("Address"));
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

	public static void addOrderToDatabase(Order order){
		String customerTable = Constants.databaseName + "." + Constants.customerTableName;
		String orderTable = Constants.databaseName + "." + Constants.orderTableName;
		String receiptTable = Constants.databaseName + "." + Constants.receiptTableName;
		Connection conn = DatabaseConnection.Connect();
		String customerStatement = "INSERT INTO " + customerTable + "(Firstname,Lastname, Address,Zipcode,City) VALUES (?, ?, ?, ?,?);";
		String orderStatement = "INSERT INTO "+ orderTable+ "(Customerid,Orderdate) VALUES (?,?);";
		String receiptStatement = "INSERT INTO "+ receiptTable + "(Orderid,Productid) VALUES (?,?);";

		try
		{
			for(String productnr: order.getProductnumber()){
				PreparedStatement preparedStatementReceipt = conn.prepareStatement(receiptStatement);
				preparedStatementReceipt.setString(1, String.valueOf(getLastReceiptIndex()+1));
				preparedStatementReceipt.setString(2,productnr);
				preparedStatementReceipt.executeUpdate();
			}

			PreparedStatement preparedStatementOrder = conn.prepareStatement(orderStatement);
			preparedStatementOrder.setInt(1, order.getCustomerinfo().getId());
			preparedStatementOrder.setString(2, order.getDate());

			PreparedStatement preparedStatementCustomer = conn.prepareStatement(customerStatement);
			preparedStatementCustomer.setString(1, order.getCustomerinfo().getFirstname());
			preparedStatementCustomer.setString(2, order.getCustomerinfo().getLastname());
			preparedStatementCustomer.setString(3, order.getCustomerinfo().getAddress());
			preparedStatementCustomer.setString(4, order.getCustomerinfo().getZipcode());
			preparedStatementCustomer.setString(5, order.getCustomerinfo().getCity());

			preparedStatementOrder.executeUpdate();
			preparedStatementCustomer.executeUpdate();
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static int getLastReceiptIndex() {
		int toReturn =0;
		ResultSet rs = DatabaseConnection.GetDataFromDatabase("select * from receipts order by orderid desc limit 1;");
		try {
			while(rs.first()){
				toReturn = rs.getInt("Orderid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
}
