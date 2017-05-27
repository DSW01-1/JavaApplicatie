package main.java.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.constant.Constants;
import main.java.handler.LogHandler;
import main.java.main.Vector2;
import main.java.main.product.Product;

public class DatabaseManagement
{
	/**
	 * Create a list of products in the database
	 * 
	 * @param products
	 */
	public static void CreateListOfNewProducts(ArrayList<Product> products)
	{
		for (Product product : products)
		{
			CreateNewProduct(product);
		}
	}

	/**
	 * Create new product in the database
	 * 
	 * @param product
	 */
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
			LogHandler.WriteErrorToLogFile(e, "Could not create new product");
		}
	}

	/**
	 * Update an existing product in the database
	 * 
	 * @param product
	 */
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
			LogHandler.WriteErrorToLogFile(e, "Could not update Product info");
		}
	}

	/**
	 * Delete product in the database
	 */
	public static void DeleteProduct(Vector2 coords)
	{
		// Path of the product table
		String table = Constants.databaseName + "." + Constants.productTableName;
		Connection conn = DatabaseConnection.Connect();

		String statement = "DELETE FROM " + table + " WHERE xcoord = ? AND ycoord = ?;";

		try
		{
			PreparedStatement preparedStatement = conn.prepareStatement(statement);

			preparedStatement.setInt(1, coords.getX());
			preparedStatement.setInt(2, coords.getY());

			preparedStatement.executeUpdate();
			conn.close();
		}
		catch (SQLException e)
		{
			LogHandler.WriteErrorToLogFile(e, "Could not delete product");
		}
	}
}