package main.java.algorithms.bpp;

import java.util.ArrayList;

import main.java.main.product.Box;
import main.java.main.product.Product;
import main.java.pane.simulation.BppSimulation;

public class FirstFit
{
	public ArrayList<Box> returnBoxes = new ArrayList<>();
	private boolean doesFit = false;

	public int boxVolume;

	public ArrayList<Box> executeFirstFit(ArrayList<Product> products)
	{
		Long startTime = System.nanoTime();
		returnBoxes.clear();
		for (Product product : products)
		{
			for (Box currentBox : returnBoxes)
			{
				if (currentBox.checkFit((int) product.GetSize()) && !doesFit)
				{
					currentBox.addProduct(product);
					doesFit = true;
				}
			}
			if (!doesFit)
			{
				Box newBox = new Box(boxVolume);
				returnBoxes.add(newBox);
				newBox.addProduct(product);
			}
			doesFit = false;
		}
		Long endtime = System.nanoTime();
		Long duration = (endtime - startTime) / 1000000;
		endtime = null;
		startTime = null;
		duration = null;
		System.gc();
		return returnBoxes;

	}
}