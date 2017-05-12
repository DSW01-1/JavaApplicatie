package main.java.algorithms.bpp;
import main.java.graphs.Box;
import main.java.graphs.Product;
import main.java.pane.simulation.BppSimulation;

import java.util.ArrayList;


public class FirstFit {
    public ArrayList<Box> returnBoxes = new ArrayList();
    private boolean fitted = false;

    public int boxVolume;
    private BppSimulation simulation;

    public FirstFit(BppSimulation simulation){
        this.simulation = simulation;
    }
    public ArrayList<Box> executeFirstFit(ArrayList<Product> products, BppSimulation simulation) {
        returnBoxes.clear();
        this.simulation.addConsoleItem("Cleared returnboxes","DEBUG");
        this.simulation.addConsoleItem("Starting First Fit Algorithm.","INFO");
        this.simulation.addConsoleItem("Box size used: "+ boxVolume +". Starting with 0 boxes.","INFO");
        for (Product product : products) {
            for (Box currentBox : returnBoxes) {
                if (currentBox.checkFit(product.GetSize())&&fitted==false) {
                    currentBox.addProduct(product);
                    fitted = true;
                    this.simulation.addConsoleItem("Added product (size: "+product.GetSize()+")to current box.","DEBUG");

                }
            }
            if (fitted == false) {
                Box newBox = new Box(boxVolume);
                returnBoxes.add(newBox);
                newBox.addProduct(product);
                this.simulation.addConsoleItem("Could not add product(size: "+product.GetSize()+") to current box. Created new box. Total amount of boxes: "+returnBoxes.size() + ". Added product to new box.","DEBUG");

            }
            fitted = false;
        }
        this.simulation.addConsoleItem("Total amount of boxes needed:"+returnBoxes.size()+", filled with "+ products.size()+" products.","INFO");
        this.simulation.addConsoleItem("FINISHED.","INFO");
        return returnBoxes;
    }
}