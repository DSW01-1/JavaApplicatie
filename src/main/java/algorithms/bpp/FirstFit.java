package main.java.algorithms.bpp;

import java.util.ArrayList;

import main.java.algorithms.BPPAlgorithm;
import main.java.main.product.Box;
import main.java.main.product.Product;

public class FirstFit extends BPPAlgorithm
{
	public ArrayList<Box> getBoxes(ArrayList<Product> products, int boxVolume)
	{
		boolean doesFit = false;

		// clear returnboxes so it starts clean.
		returnBoxes.clear();
		// walk through all products
		for (Product product : products)
		{
			// check for each box if it fits
			for (Box currentBox : returnBoxes)
			{
				// if it fits and has not fitted already put it in currentBox
				if (currentBox.checkFit((int) product.GetSize()) && !doesFit)
				{
					currentBox.addProduct(product);
					doesFit = true;
				}
			}
			// if product didn't fit anywhere create new box
			if (!doesFit)
			{
				Box newBox = new Box(boxVolume);
				returnBoxes.add(newBox);
				newBox.addProduct(product);
			}
			doesFit = false;
		}
		return returnBoxes;

	}
}