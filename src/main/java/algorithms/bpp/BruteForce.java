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

	public ArrayList<Box> executeBruteForce(ArrayList<Product> products) {
		ArrayList<Product> productList = products;
		leastBoxAmount=1000;
		for (int i = 1; i <= products.size(); i++) {
			for (Product product : productList) {
				for (Box currentBox : latestBoxes) {
					if (currentBox.checkFit(product.GetSize()) && fitted == false) {
						currentBox.addProduct(product);
						fitted = true;
					}
				}
				if (!fitted) {
					Box currentBox = new Box(10);
					currentBox.addProduct(product);
					latestBoxes.add(currentBox);
				}
				fitted = false;
			}
			if (latestBoxes.size() < leastBoxAmount) {
				System.out.println(latestBoxes.size());
				System.out.println(leastBoxAmount>latestBoxes.size());
				leastBoxAmount = latestBoxes.size();
				returnBoxes = latestBoxes;
			}
			latestBoxes.clear();
			Collections.rotate(productList, 1);
		}
		System.out.println(returnBoxes);
		System.out.println(returnBoxes.size() + " box(es) needed");

		return returnBoxes;
	}
}
