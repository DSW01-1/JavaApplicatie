package main.java.graphs;

import java.util.ArrayList;

public class Box {

    private int volume;

    public Box(int volume){
        this.volume = volume;
    }
    private ArrayList<Product> products = new ArrayList<Product>();

    public boolean checkFit(int size){
        return size < volume;
    }

    public void addProduct(Product product){
        //geen check of hij past omdat dat al in het algoritme gebeurt.
        this.products.add(product);
        this.volume -= product.GetSize();
    }

}