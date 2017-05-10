package main.java.algorithms;
import main.java.graphs.Box;
import main.java.graphs.Product;
import java.util.ArrayList;


public class FirstFit {
    private int boxAmount;
    private Box currentBox = new Box();
    public FirstFit(ArrayList<Product> producten){
        for(Product product: producten){
            if(currentBox.checkFit(product.getSize())){

            }
        }
    }
}