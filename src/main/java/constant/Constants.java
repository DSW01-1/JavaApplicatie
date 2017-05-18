package main.java.constant;

import javafx.scene.paint.Color;
import main.java.main.Vector2;

public final class Constants
{
	// Resources
	public static String styleSheetLocation = "main/resources/stylesheets/";
	public static String localizationLocation = "main/resources/localization/";
	public static String jsonOrderDirectory = "JsonOrders";

	public static String databaseName = "javaapplicatie";
	public static String productTableName = "product";
	public static String orderTableName = "order";

	public static String iconLocation = "main/resources/icons/";
	public static String shoppingCartImage = iconLocation + "shoppingcart.png";
	public static String xImage = iconLocation + "x.png";

	// Size of the grid
	public static int gridSize = (int) (665);

	// Back to main menu position and size
	public static Vector2 backTMMBP = new Vector2(15, 15);
	public static Vector2 backTMMBS = new Vector2(250, 50);

	// Standards Colors
	public static Color standardLineColor = Color.INDIANRED;

	public static int baseWareHouseSize = 5;
	public static int baseBoxSize = 10;

}