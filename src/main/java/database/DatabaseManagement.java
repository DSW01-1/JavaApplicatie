package main.java.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.constant.Constants;
import main.java.handler.LogHandler;
import main.java.main.product.Product;

public class DatabaseManagement
{

	public static void UpdateProduct(Product product)
	{
		// Path of the product table
		String table = Constants.databaseName + "." + Constants.productTableName;
		Connection conn = DatabaseConnection.Connect();

		String statement = "UPDATE " + table + " SET Productname = ?, Productsize = ? WHERE xcoord = ? AND ycoord = ?;";

		try
		{
			PreparedStatement preparedStatement = conn.prepareStatement(statement);

			preparedStatement.setString(1, product.GetName());
			preparedStatement.setInt(2, (int) product.GetSize());
			preparedStatement.setInt(3, (int) product.GetCoords().getX());
			preparedStatement.setInt(4, (int) product.GetCoords().getY());

			preparedStatement.executeUpdate();
			conn.close();
		}
		catch (SQLException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Could not updata Product info");
		}
	}

	public static void CreateNewProduct(Product product)
	{
		// Path of the product table
		String table = Constants.databaseName + "." + Constants.productTableName;
		Connection conn = DatabaseConnection.Connect();

		String statement = "INSERT INTO " + table + "(Productname, xcoord, ycoord,Productsize) VALUES (?, ?, ?, ?);";

		try
		{
			PreparedStatement preparedStatement = conn.prepareStatement(statement);

			preparedStatement.setString(1, product.GetName());
			preparedStatement.setInt(2, (int) product.GetCoords().getX());
			preparedStatement.setInt(3, (int) product.GetCoords().getY());
			preparedStatement.setInt(4, (int) product.GetSize());

			preparedStatement.executeUpdate();
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void CreateListOfNewProducts(ArrayList<Product> products)
	{
		for (Product product : products)
		{
			CreateNewProduct(product);
		}
	}
}