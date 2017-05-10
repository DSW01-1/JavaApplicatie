package main.java.algorithms.bpp;
import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;


public class FirstFit {
    private ArrayList<Box> returnBoxen = new ArrayList();
    private Box firstBox = new Box(10);
    private boolean fitted = false;

    public ArrayList<Box> executeFirstFit(ArrayList<Product> products) {
        returnBoxen.add(firstBox);
        for (Product product : products) {
            for (Box currentBox : returnBoxen) {
                if (currentBox.checkFit(product.GetSize())&&fitted==false) {
                    currentBox.addProduct(product);
                    fitted = true;
                }
            }
            if (fitted == false) {
                Box newBox = new Box(10);
                returnBoxen.add(newBox);
                newBox.addProduct(product);
            }
            fitted = false;
        }
        return returnBoxen;
    }
}