package main.java.algorithms;

import main.java.main.product.Box;
import main.java.main.product.Product;

import java.util.ArrayList;

public abstract class BPPAlgorithm
{
	public ArrayList<Box> returnBoxes = new ArrayList<Box>();

	/**
	 * Gets the least amount of boxes
	 * 
	 * @param products
	 * @return
	 */
	public abstract ArrayList<Box> getBoxes(ArrayList<Product> products);

}
