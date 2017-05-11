package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;
import java.util.Collections;

public class BruteForce
{
	private ArrayList<Box> returnBoxes = new ArrayList<Box>();
	private ArrayList<Box> latestBoxes = new ArrayList<Box>();
	private int leastBoxAmount = 1000;
	private boolean fitted;

	public ArrayList<Box> executeBruteForce(ArrayList<Product> products)
	{
		ArrayList<Product> productList = products;
		for (int i = 0; i <= products.size(); i++)
		{
			for (Product product : productList)
			{
				for (Box currentBox : latestBoxes)
				{
					if (currentBox.checkFit(product.GetSize()) && fitted == false)
					{
						currentBox.addProduct(product);
						fitted = true;
					}
				}
				if (!fitted)
				{
					Box newBox = new Box(10);
					latestBoxes.add(newBox);
					newBox.addProduct(product);
				}
				fitted = false;
			}
			if (leastBoxAmount == 0)
			{
				leastBoxAmount = latestBoxes.size();
				System.out.println(leastBoxAmount + "hier washet 0");
				returnBoxes = latestBoxes;
			}
			else if (latestBoxes.size() < leastBoxAmount)
			{
				System.out.println(latestBoxes.size());
				leastBoxAmount = latestBoxes.size();
				returnBoxes = latestBoxes;
			}
			Collections.rotate(productList, 1);
		}
		System.out.println(returnBoxes.size() + " box(es) needed");
		return returnBoxes;
	}

	public void clearAlgorithmCache()
	{
		returnBoxes.clear();
		leastBoxAmount = 0;
		latestBoxes.clear();
	}
}
