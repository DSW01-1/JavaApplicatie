package main.java.graphs;

import java.util.ArrayList;

public class Box {

<<<<<<< HEAD
    private int volume;

    public Box(int volume){
        this.volume = volume;
    }
    private ArrayList<Product> products = new ArrayList<Product>();
=======
    private int volume = 10;
    private ArrayList<Product> products= new ArrayList<Product>();
>>>>>>> 31be7e23fd1aba432d311f153d54d5bfa2d01c29

    public boolean checkFit(int size){
        return size < volume;
    }

    public void addProduct(Product product){
        //not necessary to check fit, done in algorithm
        this.products.add(product);
        this.volume -= product.GetSize();
    }

}