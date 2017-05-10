package main.java.graphs;

import java.util.ArrayList;

public class Box {

    private int volume = 10;
    private ArrayList<Product> products= new ArrayList<Product>();

    public boolean checkFit(int size){
        return size < volume;
    }

    public void addProduct(Product product){
        //not necessary to check fit, done in algorithm
        this.products.add(product);
        this.volume -= product.GetSize();
    }

    public Box returnBox(){
        return this;
    }
}
