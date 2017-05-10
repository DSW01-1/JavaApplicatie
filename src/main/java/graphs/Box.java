package main.java.graphs;

import java.util.ArrayList;

public class Box {

    public Box(int volume){
        this.volume = volume;
    }
    private ArrayList<Product> products = new ArrayList<Product>();
    private int volume;
    public boolean checkFit(int size){
        return size <= volume;
    }

    public void addProduct(Product product){
        //not necessary to check fit, done in algorithm
        this.products.add(product);
        this.volume = this.volume-product.GetSize();
    }

    public void clearBox(){
        this.products.clear();
    }
}