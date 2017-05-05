package main.java.main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class ScreenProperties
{
	private static GraphicsDevice gd;

	public static int getScreenWidth()
	{
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getWidth();
	}

	public static int getScreenHeight()
	{
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getHeight();
	}
}
