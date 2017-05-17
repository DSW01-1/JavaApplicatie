package main.java.algorithms.tsp;

import main.java.main.Vector2;

import java.util.ArrayList;

/**
 * Created by aukev on 15-5-2017.
 */
public class EnumPath {

    private double pathLength;
    private ArrayList<Vector2> Coordinates;

    public EnumPath(double PathLength, ArrayList<Vector2> Coordinates){
        this.pathLength = PathLength;
        this.Coordinates = Coordinates;
    }

    public double getPathLength(){
        return pathLength;
    }
    public ArrayList<Vector2> getTiles() {
        return Coordinates;
    }

}
