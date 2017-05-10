package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;

import java.util.ArrayList;
import java.util.Collections;

public class DecreasingFirstFit {
    private ArrayList<Box> returnBoxes = new ArrayList<Box>();
    private boolean fitted;

    public ArrayList<Box> executeDecreasingFirstFit(ArrayList<Product> products){
        Collections.sort(products);
        Collections.reverse(products);
        for (Product product : products) {
            for (Box currentBox : returnBoxes) {
                if (currentBox.checkFit(product.GetSize())&&fitted==false) {
                    currentBox.addProduct(product);
                    fitted = true;
                }
            }
            if (fitted == false) {
                Box newBox = new Box(10);
                returnBoxes.add(newBox);
                newBox.addProduct(product);
            }
            fitted = false;
        }
        return returnBoxes;
    }
}
