package main.java.algorithms;

import java.util.ArrayList;

import main.java.main.product.Box;
import main.java.main.product.Product;

public abstract class BPPAlgorithm
{
	public static ArrayList<Box> returnBoxes = new ArrayList<Box>();

	/**
	 * Gets the least amount of boxes
	 * 
	 * @param products
	 * @return
	 */
	public ArrayList<Box> getBoxes(ArrayList<Product> products, int boxSize)
	{
		return returnBoxes;
	}
}
