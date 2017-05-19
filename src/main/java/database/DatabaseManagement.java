package main.java.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.constant.Constants;
import main.java.main.Vector2;

public class DatabaseManagement
{

	public void CreateNewProduct(Vector2 coord)
	{
		// Path of the product table
		String table = Constants.databaseName + "." + Constants.productTableName;
		Connection conn = DatabaseConnection.Connect();

		String statement = "INSERT INTO " + table + "(name, xcoord, ycoord, size) VALUES (" + "" + coord.getX()
				+ coord.getY() + "0" + ");";

		try
		{
			PreparedStatement preparedStatement = conn.prepareStatement(statement);
			preparedStatement.executeQuery();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}