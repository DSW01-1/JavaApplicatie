package main.java.database;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.constant.Constants;
import main.java.handler.LogHandler;
import main.java.main.Vector2;
import main.java.main.product.CustomerInfo;
import main.java.main.product.Order;
import main.java.main.product.Product;

public class DatabaseOrder
{
	// method to get all orders in the database
	public static ArrayList<Order> GetAllOrders()
	{
		ArrayList<Order> orderList = new ArrayList<Order>();
		String statement = ("select Firstname,Lastname,Street,Zipcode,City,o.Orderdate,o.Orderid,p.Productid,\n"
				+ "p.Productname,Productsize,xcoord,ycoord  \n"
				+ "From customers c join orders o on c.Customerid=o.Customerid \n"
				+ "join receipts r on r.Orderid = o.Orderid\n" + "join products p on p.Productid = r.Productid ");
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

	// method to add order to the database.
	public static void addOrderToDatabase(Order order)
	{
		// setting all tables as a String
		String customerTable = Constants.databaseName + "." + Constants.customerTableName;
		String orderTable = Constants.databaseName + "." + Constants.orderTableName;
		String receiptTable = Constants.databaseName + "." + Constants.receiptTableName;
		// create connection
		Connection conn = DatabaseConnection.Connect();
		// setting statements
		String customerStatement = "INSERT INTO " + customerTable
				+ "(Firstname,Lastname, Address,Zipcode,City) VALUES (?, ?, ?, ?,?);";
		String orderStatement = "INSERT INTO " + orderTable + "(Customerid, Orderdate) VALUES (?,?);";
		String receiptStatement = "INSERT INTO " + receiptTable + "(Orderid,Productid) VALUES (?,?);";

		try
		{
			// preparing statements
			PreparedStatement preparedStatementReceipt = conn.prepareStatement(receiptStatement);
			// setting parameters
			preparedStatementReceipt.setString(1, String.valueOf(getLastReceiptIndex() + 1));
			for (String productnr : order.getProducts())
			{
				// for each product add a new row with the same orderid
				preparedStatementReceipt.setString(2, productnr);
				preparedStatementReceipt.executeUpdate();
			}

			// insert order into order database
			PreparedStatement preparedStatementOrder = conn.prepareStatement(orderStatement);
			preparedStatementOrder.setString(1, String.valueOf(getLastCustomerId() + 1));
			preparedStatementOrder.setString(2, order.getDate());

			// insert curtomer in customer database
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
			LogHandler.WriteErrorToLogFile(e, "Could not add order to database");
		}
	}

	// get last receipt index number
	public static int getLastReceiptIndex()
	{
		int toReturn = 0;
		String receiptTable = Constants.databaseName + "." + Constants.receiptTableName;
		String orderid = "select * from " + receiptTable + " order by orderid desc limit 1;";
		Connection conn = DatabaseConnection.Connect();
		try
		{
			PreparedStatement preparedStatementReceipt = conn.prepareStatement(orderid);
			ResultSet rs = preparedStatementReceipt.executeQuery();
			while (rs.next())
			{
				toReturn = rs.getInt("Orderid");
			}
			conn.close();
		}
		catch (SQLException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Could not get last receiptIndex info");
		}
		catch(Exception e){
			LogHandler.ShowWarning("Could not get last receiptIndex info.");
		}
		return toReturn;
	}

	// gets highest customer id. Useful when creating a new customer
	public static int getLastCustomerId()
	{
		int toReturn = 0;
		try
		{
			String customerTable = Constants.databaseName + "." + Constants.customerTableName;
			String customerid = "select * from " + customerTable + " order by customerid desc limit 1;";
			Connection conn = DatabaseConnection.Connect();
			PreparedStatement preparedStatementReceipt = conn.prepareStatement(customerid);
			ResultSet rs = preparedStatementReceipt.executeQuery();
			while (rs.next())
			{
				toReturn = rs.getInt("Customerid");
			}
			conn.close();
		}
		catch (SQLException e)
		{
			LogHandler.ShowWarning("Kon geen verbinding maken met de database.");
		}
		catch(Exception e){
			LogHandler.ShowWarning("Kon geen verbinding maken met de database.");
		}
		return toReturn;
	}

	// returns all customers in database
	public static ArrayList<CustomerInfo> getAllCustomers()
	{
		String customerTable = Constants.databaseName + "." + Constants.customerTableName;
		String statement = ("select * from " + customerTable);
		ArrayList<CustomerInfo> customers = new ArrayList<CustomerInfo>();
		try
		{
			Connection conn = DatabaseConnection.Connect();
			PreparedStatement preparedCustomers = conn.prepareStatement(statement);
			ResultSet rs = preparedCustomers.executeQuery();
			while (rs.next())
			{
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
		catch (SQLException e)
		{
			LogHandler.ShowWarning("Er kon klanten niet ophalen uit de database.");
		}
		catch(Exception e){
			LogHandler.ShowWarning("Er kon klanten niet ophalen uit de database.");
		}
		return customers;
	}

	// returns all order from one person
	public static List<Integer> getOrdersFromId(int id)
	{
		List<Integer> OrderList = new ArrayList<Integer>();
		String orderTable = Constants.databaseName + "." + Constants.orderTableName;
		String statement = "Select * from " + orderTable + " where Customerid = ?";
		Connection conn = DatabaseConnection.Connect();
		try
		{
			PreparedStatement preparedOrderes = conn.prepareStatement(statement);
			preparedOrderes.setInt(1, id);
			ResultSet rs = preparedOrderes.executeQuery();
			while (rs.next())
			{
				OrderList.add(rs.getInt("Customerid"));
			}
			conn.close();
		}
		catch (SQLException e)
		{
			LogHandler.ShowWarning("Kon order niet ophalen");
		}
		catch(Exception e){
			LogHandler.ShowWarning("Kon order niet ophalen");
		}
		return OrderList;
	}

	// gets all products from an order
	public static List<Integer> getProductsIDFromOrder(int orderid)
	{
		ArrayList<Integer> productList = new ArrayList<Integer>();
		String receiptTable = Constants.databaseName + "." + Constants.receiptTableName;
		String statement = "Select * from " + receiptTable + " where orderid= ?";
		try
		{
			Connection conn = DatabaseConnection.Connect();
			PreparedStatement preparedReceipt = conn.prepareStatement(statement);
			preparedReceipt.setInt(1, orderid);
			ResultSet rs = preparedReceipt.executeQuery();
			while (rs.next())
			{
				int productId = rs.getInt("productid");
				productList.add(productId);
			}
		}
		catch (SQLException e)
		{
			LogHandler.ShowWarning("Kon producten niet ophalen");
		}
		catch(Exception e){
			LogHandler.ShowWarning("Kon producten niet ophalen.");
		}
		return productList;
	}

	// get product information by id
	public static Product getProductByID(int productid)
	{
		Product product = new Product(0, "No product", new Vector2(0, 0), 0);
		String productTable = Constants.databaseName + "." + Constants.productTableName;
		String statement = "Select * from " + productTable + " where productid = ?";
		try
		{
			Connection conn = DatabaseConnection.Connect();
			PreparedStatement preparedProduct = conn.prepareStatement(statement);
			preparedProduct.setInt(1, productid);
			ResultSet rs = preparedProduct.executeQuery();
			while (rs.next())
			{
				product = new Product(productid, rs.getString("Productname"),
						new Vector2(rs.getInt("xcoord"), rs.getInt("ycoord")), rs.getInt("Productsize"));
			}
		}
		catch (SQLException e)
		{
			LogHandler.ShowWarning("Er kon geen productinformatie opgehaald worden");
		}
		catch(Exception e){
			LogHandler.ShowWarning("Er kon geen productinformatie opgehaald worden.");
		}
		return product;
	}
}
