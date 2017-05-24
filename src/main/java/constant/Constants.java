package main.java.constant;

import javafx.scene.paint.Color;
import main.java.main.Vector2;

public final class Constants
{
	// Resources
	public static final String styleSheetLocation = "main/resources/stylesheets/";
	public static final String localizationLocation = "main/resources/localization/";
	public static final String jsonOrderDirectory = "JsonOrders";
	public static final String usbWebServer = System.getenv("ProgramFiles(X86)") + "/usbwebserver/usbwebserver.exe";

	public static final String databaseName = "magazijn";
	public static final String productTableName = "products";
	public static final String orderTableName = "orders";
	public static final String customerTableName = "customers";
	public static final String receiptTableName = "receipts";

	public static final String iconLocation = "main/resources/icons/";
	public static final String shoppingCartImage = iconLocation + "shoppingcart.png";
	public static final String xImage = iconLocation + "x.png";
	public static final String ApplicationIcon = iconLocation + "ApplicatieLogo.png";

	// Size of the grid
	public static final int drawnGridSize = (int) (665);
	public static final int baseWareHouseSize = 5;
	public static final int baseBoxSize = 10;

	// Back to main menu position and size
	public static final Vector2 backTMMBP = new Vector2(15, 15);
	public static final Vector2 backTMMBS = new Vector2(250, 50);

	// Standards Colors
	public static final Color standardLineColor = Color.BLACK;
	public static final Color standardTileColor = Color.GRAY;
	public static final Color standardProductColor = Color.TAN;
	public static final Color standardBlzitColor = Color.GREENYELLOW;
	public static final Color standardSelectColor = Color.AQUAMARINE;

	// Rainbow Colors
	public static final String[] rainbowColors =
	{ "#fea3aa", "#f8b88b", "#faf884", "#baed91", "#b2cefe", "#f2a2e8", "#e292f8" };

	public static Color GetRainbowColor(int i)
	{
		double r = Integer.valueOf(rainbowColors[i].substring(1, 3), 16);
		double g = Integer.valueOf(rainbowColors[i].substring(3, 5), 16);
		double b = Integer.valueOf(rainbowColors[i].substring(5, 7), 16);
		return new Color(r / 255, g / 255, b / 255, 1);
	}

	public static boolean GetBlzitNumber(double pathLength)
	{
		String value = String.valueOf(pathLength);

		return (value.contains("420") || value.contains("42.0") || value.contains("4.20"));
	}

}