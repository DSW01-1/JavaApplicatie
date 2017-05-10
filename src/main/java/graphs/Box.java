package main.java.graphs;

/**
 * Created by Jordi Smit on 5/9/2017.
 */
public class Box {

    private int volume = 10;
    int[] contents = new int[10];

    public boolean checkFit(int size){
        return size < volume;
    }
}
