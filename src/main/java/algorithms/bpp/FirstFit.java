package main.java.algorithms.bpp;

import java.util.ArrayList;

import main.java.algorithms.BPPAlgorithm;
import main.java.main.product.Box;
import main.java.main.product.Product;
import main.java.pane.simulation.BppSimulation;

public class FirstFit extends BPPAlgorithm
{
	public ArrayList<Box> returnBoxes = new ArrayList<>();
	private boolean doesFit = false;

	public int boxVolume;

	//method that sorts the products, returns arraylist with sorted boxes containing products
	public ArrayList<Box> getBoxes(ArrayList<Product> products)
	{
		Long startTime = System.nanoTime();
		//clear returnboxes so it starts clean.
		returnBoxes.clear();
		//walk through all products
		for (Product product : products)
		{
			//check for each box if it fits
			for (Box currentBox : returnBoxes)
			{
				//if it fits and has not fitted already put it in currentBox
				if (currentBox.checkFit((int) product.GetSize()) && !doesFit)
				{
					currentBox.addProduct(product);
					doesFit = true;
				}
			}
			//if product didn't fit anywhere create new box
			if (!doesFit)
			{
				Box newBox = new Box(boxVolume);
				returnBoxes.add(newBox);
				newBox.addProduct(product);
			}
			doesFit = false;
		}
		Long endtime = System.nanoTime();
		//time to measure efficiency
		Long duration = (endtime - startTime) / 1000000;
		endtime = null;
		startTime = null;
		duration = null;
		System.gc();
		return returnBoxes;

	}
}