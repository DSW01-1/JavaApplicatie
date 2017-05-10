package main.java.algorithms.bpp;

import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;
import java.util.Collections;

public class BruteForce {
    private ArrayList<Box> returnBoxes=new ArrayList<Box>();
    private ArrayList<Box> latestBoxes= new ArrayList<Box>();
    private ArrayList<Product> productList = new ArrayList<Product>();
    private int leastBoxAmount;
    private Box firstBox = new Box(10);
    private boolean fitted;
    public ArrayList<Box> executeBruteForce(ArrayList<Product> products){
        productList=products;
        latestBoxes.add(firstBox);
        for(int i=0;i<=products.size();i++) {
            for (Product product : productList) {
                for (Box currentBox : latestBoxes) {
                    if (currentBox.checkFit(product.GetSize())) {
                        currentBox.addProduct(product);
                        fitted = true;
                    }
                }
                if (!fitted) {
                    Box newBox = new Box(10);
                    latestBoxes.add(newBox);
                    newBox.addProduct(product);
                }
                fitted = false;
            }
            if (latestBoxes.size() < leastBoxAmount) {
                leastBoxAmount=latestBoxes.size();
                returnBoxes = latestBoxes;
            }
            Collections.rotate(productList,1);
        }
        return returnBoxes;
    }
}
