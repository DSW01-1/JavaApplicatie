package main.java.database;

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

public class DatabaseConnection
{
	private static String username = "root";
	private static String password = "usbw";

	/**
	 * Create a connection with the database *
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
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/", connectionProps);
		}
		catch (ClassNotFoundException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Class not found");
		}
		catch (SQLException e)
		{
			if (e.getSQLState().startsWith(ErrorConstants.ConnectionException))
			{
				LogHandler.WriteErrorToLogFile(e, "Could not create a connection with the database");
			}
		}
		return conn;
	}

	/**
	 * Get all the available products for ordering from the database *
	 * 
	 * @return ArrayList<String> An array of product names
	 */
	public static ArrayList<String> GetAvailableProducts()
	{
		ArrayList<String> products = new ArrayList<String>();

		try
		{
			Connection conn = Connect();

			if (conn != null)
			{
				// Path of the product table
				String table = Constants.databaseName + "." + Constants.productTableName;
				PreparedStatement preparedStatement = conn.prepareStatement("select * from " + table);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next())
				{
					products.add(rs.getString("name"));
				}
			}
			else
			{
				LogHandler.ShowWarning(Language.getTranslation("warning.nodatabaseconnection"));
			}
		}
		catch (SQLException e)
		{
			LogHandler.WriteErrorToLogFile(e, "SQL Exception: " + e.getSQLState() + ", Check list for error");
		}
		catch (NullPointerException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Nullpointer, Connection may not exist");
		}

		return products;
	}
}
