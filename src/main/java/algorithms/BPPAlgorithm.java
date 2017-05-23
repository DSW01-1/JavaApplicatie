package main.java.algorithms;

import main.java.main.product.Box;
import main.java.main.product.Product;

import java.util.ArrayList;

public abstract class BPPAlgorithm {
    public abstract ArrayList<Box> getBoxes(ArrayList<Product> products);
    public ArrayList<Box> returnBoxes;
}
