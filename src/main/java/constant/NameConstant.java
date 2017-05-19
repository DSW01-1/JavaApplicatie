package main.java.constant;

import java.util.Random;

public class NameConstant
{
	private static String[] nameArray = {
			"ToothBrush", "Pepsi", "Cola", "Car", "Plane", "Boat", "Website", "Box"
	};

	public static String GetRandomName()
	{
		Random rand = new Random();
		return nameArray[rand.nextInt(nameArray.length)];
	}
}
