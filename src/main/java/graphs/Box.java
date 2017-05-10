package main.java.graphs;

import java.util.ArrayList;

public class Box {

    private int volume = 10;
    private ArrayList<Product> products= new ArrayList();
    //x is location in contents array
    int x = 0;

    public boolean checkFit(int size){
        return size < volume;
    }

    public void addProduct(Product product){
        //geen check of hij past omdat dat al in het algoritme gebeurt.
        this.products.add(product);
        this.volume -= product.GetSize();
    }

    public Box returnBox(){
        return this;
    }
}
