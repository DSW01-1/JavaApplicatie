package main.java.constant;

import javafx.scene.paint.Color;

public class ColorConstants
{
	// Standards Colors
	public static final Color standardLineColor = Color.BLACK;
	public static final Color standardTileColor = Color.GRAY;
	public static final Color standardProductColor = Color.TAN;
	public static final Color standardBlzitColor = Color.FORESTGREEN;
	public static final Color standardSelectColor = Color.LIGHTBLUE;
	public static final Color robotColor = Color.CORNFLOWERBLUE;

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

	/**
	 * Check if the given number contains the numbers | 4 2 0 | in that order
	 * 
	 * @param number
	 *            The number to compare
	 * @return true if the number contains | 4 2 0 |
	 */
	public static boolean GetBlzitNumber(double number)
	{
		String value = String.valueOf(number);
		return (value.contains("420") || value.contains("42.0") || value.contains("4.20"));
	}

	public static boolean GetNootianNumber(double number)
	{
		String value = String.valueOf(number);
		return (value.contains("1.2151520") || value.contains("12.151520") || value.contains("121.51520"));
	}
}
