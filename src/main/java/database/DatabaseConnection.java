package main.java.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import main.java.constant.Constants;
import main.java.constant.ErrorConstants;
import main.java.handler.LogHandler;
import main.java.main.Language;
import main.java.main.Vector2;
import main.java.main.product.Product;

public class DatabaseConnection
{
	private static String username = "root";
	private static String password = "usbw";

	/**
	 * Create a connection with the database
	 * 
	 * @return Connection
	 */
	public static Connection Connect()
	{
		// Setup the username and password
		Properties connectionProps = new Properties();
		connectionProps.put("user", username);
		connectionProps.put("password", password);
		Connection conn = null;

		try
		{
			// Try to create the connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/", connectionProps);
		}
		catch (SQLException e)
		{
			// If the error code is the connection error code
			if (e.getSQLState().startsWith(ErrorConstants.ConnectionException))
			{
				LogHandler.WriteErrorToLogFile(e, "Could not create a connection with the database");

				// Show a warning that the application could not connect to the
				// database,
				// if the user clicks on [Start Database] return true
				boolean doAction = LogHandler.ShowWarning(Language.getTranslation("warning.nodatabaseconnection"),
						Language.getTranslation("btn.openDatabaseApplication"));

				if (doAction)
				{
					try
					{
						System.out.println(Constants.usbWebServer);

						// Try to open the webserver
						String cmd = Constants.usbWebServer;
						Runtime.getRuntime().exec(cmd);

						// Wait for 6 seconds, hopefully enough time for the
						// webserver to start mysql
						Thread.sleep(6000);

						// Retry to make a connection
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/", connectionProps);
						conn.setAutoCommit(false);
					}
					catch (IOException ex)
					{
						// Could not open the application, maybe the exe file is
						// not there
						LogHandler.ShowWarning(Language.getTranslation("warning.couldnotopenserver"));
						LogHandler.WriteErrorToLogFile(ex, "Could not run external application");

					}
					catch (InterruptedException ex)
					{
						// For some reason the 6 second wait was interrupted
						LogHandler.WriteErrorToLogFile(ex, "Thread sleep was interrupted");
					}
					catch (SQLException ex)
					{
						if (ex.getSQLState().startsWith(ErrorConstants.ConnectionException))
						{
							// Second attempt, break off and give up
							LogHandler.WriteErrorToLogFile(ex,
									"Second attempt to connect to database, failed. Returning null");
							return null;
						}
					}
				}
			}
		}
		return conn;
	}

	/**
	 * Get all the available products for ordering from the database
	 * 
	 * @return ArrayList<String> An array of product names
	 */
	public static ArrayList<Product> GetAvailableProducts()
	{
		ArrayList<Product> products = new ArrayList<Product>();

		try
		{
			// Path of the product table
			String table = Constants.databaseName + "." + Constants.productTableName;
			ResultSet rs = GetDataFromDatabase("select * from " + table);

			if (rs != null)
			{
				while (rs.next())
				{
					products.add(new Product(rs.getInt("Productid"), rs.getString("Productname"),
							new Vector2(rs.getInt("xcoord"), rs.getInt("ycoord")), rs.getInt("Productsize")));
				}
			}
		}
		catch (SQLException e)
		{
			if (e.getSQLState().startsWith("42"))
			{
				LogHandler.WriteErrorToLogFile(e, "SQL Exception: Table does not exist");
			}
			else
			{
				LogHandler.WriteErrorToLogFile(e, "SQL Exception: " + e.getSQLState() + ", Check list for error");
			}
		}
		return products;
	}

	/**
	 * Get all data from the given statement
	 * 
	 * @param statement
	 * @return
	 */
	public static ResultSet GetDataFromDatabase(String statement)
	{
		Connection conn = Connect();
		ResultSet rs = null;

		if (conn != null)
		{

			try
			{
				PreparedStatement preparedStatement = conn.prepareStatement(statement);
				rs = preparedStatement.executeQuery();
			}
			catch (SQLException e)
			{
				LogHandler.WriteErrorToLogFile(e, "Could not retrieve data from database");
			}
		}
		return rs;
	}

	public static ResultSet GetDataFromDatabase(PreparedStatement statement)
	{
		Connection conn = Connect();
		ResultSet rs = null;

		if (conn != null)
		{
			try
			{
				rs = statement.executeQuery();
			}
			catch (SQLException e)
			{
				LogHandler.WriteErrorToLogFile(e, "Could not retrieve data from database");
			}
		}
		return rs;
	}

	public static Product GetProductInfo(int productId)
	{
		Product product = null;

		try
		{
			String table = Constants.databaseName + "." + Constants.productTableName;
			String statement = "select * from " + table + " where Productid = ?";

			PreparedStatement preparedStatement = Connect().prepareStatement(statement);
			preparedStatement.setInt(1, productId);

			ResultSet rs = GetDataFromDatabase(preparedStatement);

			while (rs.next())
			{
				product = new Product(productId, rs.getString("Productname"),
						new Vector2(rs.getInt("xcoord"), rs.getInt("ycoord")), rs.getInt("Productsize"));
			}
		}
		catch (SQLException ex)
		{
			LogHandler.WriteErrorToLogFile(ex, "Could not retreive product info");
		}

		return product;
	}
}
