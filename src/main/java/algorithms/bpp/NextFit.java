package main.java.algorithms.bpp;

import java.util.ArrayList;

import main.java.algorithms.BPPAlgorithm;
import main.java.main.product.Box;
import main.java.main.product.Product;

public class NextFit extends BPPAlgorithm
{
	/**
	 * Print all products in the list
	 * 
	 * @param products
	 * @return
	 */
	public static String printAllProducts(ArrayList<Product> products)
	{
		String productData = "";
		for (Product product : products)
		{
			productData = ("Size: " + product.GetSize() + ". ID: " + product.GetId() + ". Coords" + product.GetCoords()
					+ ". Name: " + product.GetName() + ".");
		}
		return productData;
	}

	@Override
	public ArrayList<Box> getBoxes(ArrayList<Product> products, int boxVolume)
	{
		Box currentBox = new Box(0);
		// clear returnBoxes arraylist so no leftovers of previous calculations
		// remain.
		returnBoxes.clear();
		// loop through all products asked for
		for (Product product : products)
		{
			// check if product fits in current box
			if (currentBox.checkFit((int) product.GetSize()))
			{
				currentBox.addProduct(product);
			}
			else
			{
				// else create new box for currentBox and add product then
				currentBox = new Box(boxVolume);
				currentBox.addProduct(product);
				returnBoxes.add(currentBox);
			}
		}
		// set currentBox size 0 again so a new one immediately gets initialized
		// and added next time at execution
		currentBox = new Box(0);

		return returnBoxes;
	}
}
