package main.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import main.java.main.Constants;

public class DatabaseConnection
{
	private static String username = "root";
	private static String password = "usbw";

	public static Connection Connect()
	{
		Properties connectionProps = new Properties();
		connectionProps.put("user", username);
		connectionProps.put("password", password);
		Connection conn = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/", connectionProps);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public static ArrayList<String> GetAvailableProducts()
	{
		ArrayList<String> products = new ArrayList<String>();
		
		try
		{
			String table = Constants.databaseName + "." + Constants.productTableName;
			PreparedStatement preparedStatement = Connect().prepareStatement("select * from " + table);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next())
			{
				products.add(rs.getString("name"));
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}
}
