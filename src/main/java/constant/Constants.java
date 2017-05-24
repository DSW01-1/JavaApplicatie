package main.java.constant;

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
}