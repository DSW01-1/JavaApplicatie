package main.java.algorithms.tsp;

import main.java.main.Vector2;

import java.util.ArrayList;

/**
 * Created by aukev on 15-5-2017.
 */
public class EnumPath {

    private int pathLength;
    private ArrayList<Vector2> Coordinates;

    public EnumPath(int PathLength, ArrayList<Vector2> Coordinates){
        this.pathLength = PathLength;
        this.Coordinates = Coordinates;
    }

    public int getPathLength(){
        return pathLength;
    }
    public ArrayList<Vector2> getTiles() {
        return Coordinates;
    }

}
