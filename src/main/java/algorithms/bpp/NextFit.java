package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;
public class NextFit {

    private ArrayList<Box> returnBoxes = new ArrayList<Box>();
    private Box firstBox = new Box(10);
    private boolean fitted = false;
    private Box currentBox = firstBox;
    public ArrayList<Box> executeNextFit(ArrayList<Product> products){
        returnBoxes.add(firstBox);
        for (Product product : products){
                if(currentBox.checkFit(product.GetSize())&& !fitted){
                    currentBox.addProduct(product);
                    fitted = true;
            }
            if(!fitted){
                Box i = new Box(10);
                returnBoxes.add(i);
                i.addProduct(product);
            }
            fitted = false;
        }
        return returnBoxes;
    }



}
