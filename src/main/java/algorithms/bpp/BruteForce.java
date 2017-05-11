package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;
import java.util.Collections;

public class BruteForce
{
	//returnBoxes is de arrayList die boxen returned met de producten.
	//latestBoxes is de laatste gesorteerde arraylist
	private ArrayList<Box> returnBoxes = new ArrayList<Box>();
	private ArrayList<Box> latestBoxes = new ArrayList<Box>();
	private Box currentBox = new Box(10);
	private boolean fitted;
	private int x=1;
	public ArrayList<Box> executeBruteForce(ArrayList<Product> products) {
		while(x<=products.size()) {
			latestBoxes.clear();
			System.out.println("Loop nr: "+x);
			for (Product product : products) {
				for (Box currentBox : latestBoxes) {
					if (currentBox.checkFit(product.GetSize())&&fitted==false) {
						System.out.println("Product fitted");
						currentBox.addProduct(product);
						fitted = true;
					}
				}
				if (fitted == false) {
					Box newBox = new Box(10);
					latestBoxes.add(newBox);
					newBox.addProduct(product);
					System.out.println("New box named: "+newBox+" Product fitted");
				}
				fitted = false;
			}
			if (latestBoxes.size() < returnBoxes.size()||returnBoxes.size()==0) {
				System.out.println("New least box size which is: "+latestBoxes.size());
				returnBoxes = latestBoxes;
			}
			Collections.rotate(products, 1);
			System.out.println("Collections rotated");
			System.out.println("Product order: "+products);
			x++;
			System.out.println();
		}
		System.out.println("Boxes"+returnBoxes);
		System.out.println(returnBoxes.size() + " box(es) needed");
		return returnBoxes;
	}
}
