package main.java.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
		String statement = ("select Firstname,Lastname,Street,Zipcode,City,o.Orderdate,o.Orderid,p.Productid,\n" +
				"p.Productname,Productsize,xcoord,ycoord  \n" +
				"From customers c join orders o on c.Customerid=o.Customerid \n" +
				"join receipts r on r.Orderid = o.Orderid\n" +
				"join products p on p.Productid = r.Productid ");
		Connection conn = DatabaseConnection.Connect();
		try
		{
			PreparedStatement preparedStatement = conn.prepareStatement(statement);
			ResultSet rs = preparedStatement.executeQuery();
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
		String orderStatement = "INSERT INTO "+ orderTable+ "(Customerid, Orderdate) VALUES (?,?);";
		String receiptStatement = "INSERT INTO "+ receiptTable + "(Orderid,Productid) VALUES (?,?);";

		try
		{
			PreparedStatement preparedStatementReceipt = conn.prepareStatement(receiptStatement);
			preparedStatementReceipt.setString(1, String.valueOf(getLastReceiptIndex()+1));
			for(String productnr: order.getProductnumber()){
				preparedStatementReceipt.setString(2,productnr);
				preparedStatementReceipt.executeUpdate();
			}

			PreparedStatement preparedStatementOrder = conn.prepareStatement(orderStatement);
			preparedStatementOrder.setString(1, String.valueOf(getLastCustomerId()+1));
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
		String receiptTable = Constants.databaseName + "." + Constants.receiptTableName;
		String orderid = "select * from "+receiptTable+" order by orderid desc limit 1;";
		Connection conn = DatabaseConnection.Connect();
		try {
			PreparedStatement preparedStatementReceipt = conn.prepareStatement(orderid);
			ResultSet rs =preparedStatementReceipt.executeQuery();
			while(rs.next()) {
				toReturn = rs.getInt("Orderid");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	public static int getLastCustomerId() {
		int toReturn =0;
		String customerTable = Constants.databaseName + "." + Constants.customerTableName;
		String customerid = "select * from "+customerTable+" order by customerid desc limit 1;";
		Connection conn = DatabaseConnection.Connect();
		try {
			PreparedStatement preparedStatementReceipt = conn.prepareStatement(customerid);
			ResultSet rs =preparedStatementReceipt.executeQuery();
			while(rs.next()) {
				toReturn = rs.getInt("Customerid");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	public static ArrayList<CustomerInfo> getAllCustomers(){
		String customerTable = Constants.databaseName + "." + Constants.customerTableName;
		String statement = ("select * from "+ customerTable);
		ArrayList<CustomerInfo> customers = new ArrayList();
		Connection conn = DatabaseConnection.Connect();
		try{
			PreparedStatement preparedCustomers = conn.prepareStatement(statement);
			ResultSet rs =preparedCustomers.executeQuery();
			while(rs.next()){
				CustomerInfo cusInfo = new CustomerInfo();
				cusInfo.setId(rs.getInt("Customerid"));
				cusInfo.setFirstname(rs.getString("Firstname"));
				cusInfo.setLastname(rs.getString("Lastname"));
				cusInfo.setAddress(rs.getString("Address"));
				cusInfo.setZipcode(rs.getString("Zipcode"));
				cusInfo.setCity(rs.getString("City"));
				customers.add(cusInfo);
			}
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return customers;
	}

	public static List<Integer> getOrdersFromId(int id){
		List<Integer> OrderList=null;
		String orderTable = Constants.databaseName+ "."+Constants.orderTableName;
		String statement = "Select * from "+orderTable+" where Customerid = "+id+";";
		Connection conn = DatabaseConnection.Connect();
		try{
			PreparedStatement preparedOrderes = conn.prepareStatement(statement);
			ResultSet rs = preparedOrderes.executeQuery();
			while(rs.next()){
				OrderList.add(rs.getInt("Orderid"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return OrderList;
	}
}
