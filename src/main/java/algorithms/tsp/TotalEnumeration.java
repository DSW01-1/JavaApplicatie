package main.java.algorithms.tsp;
import javafx.application.Platform;
import main.java.graphs.GridTile;
import main.java.main.Vector2;
import main.java.pane.simulation.TspSimulation;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TotalEnumeration extends Thread
{

    private ArrayList<GridTile> tileList;
    private ArrayList<EnumPath> pathList = new ArrayList<>();
    private EnumPath shortestPath;

    ArrayList<ArrayList<Integer>> possibilities;
    private int possiblePaths;
    private TspSimulation simulation;
    private boolean logging = false;



    public TotalEnumeration(ArrayList<GridTile> tileList){
        this.tileList = tileList;
        this.simulation = new TspSimulation();
    }
    public TotalEnumeration(ArrayList<GridTile> tileList, TspSimulation simulation){
        this.tileList = tileList;
        this.simulation = simulation;
        logging = true;
    }

    public void run(){

        long startTime = System.nanoTime();
        if(logging){
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    simulation.addConsoleItem("Starting thread","DEBUG");
                }
            });
        }

        // creating array of all tile indexes
        int[] tileIndexes = new int[tileList.size()];
        for(int i=0; i < tileList.size();i++){
            tileIndexes[i] = i;
        }


        // Fetching all possible paths
        this.possibilities = permute(tileIndexes);
        possiblePaths = possibilities.size();

        if(logging){
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    simulation.addConsoleItem("Possible paths generated", "DEBUG");
                    simulation.addConsoleItem("There are " + possiblePaths + " paths", "DEBUG");
                }
            });
        }

        this.processShortestPath();
        if(logging){
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    simulation.addConsoleItem("Shortest path has been calculated", "DEBUG");
                }
            });
        }

        ArrayList<Vector2> shortestPath = this.getShortestPath();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                simulation.addConsoleItem("Applying path to the grid..", "DEBUG");
            }
        });

        simulation.updatePath(shortestPath);

        if(logging){
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    simulation.addConsoleItem("Finished configuring 'total enumeration' algorithm", "DEBUG");
                }
            });
        }

        long stopTime = System.nanoTime();
        long duration = (stopTime - startTime) / 100000;
        if(logging){
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    String showDuration = (duration < 1) ? "duration: less then a ms" : "duration: " + duration + " ms, moving to the next step";
                    simulation.addConsoleItem(showDuration, "INFO");
                }
            });

        }


    }

    public ArrayList<Vector2> getShortestPath(){
        return shortestPath.getTiles();
    }

    public void processShortestPath(){

        int cnt = 0;
        int pathLength = 0;

        for(ArrayList<Integer> path : this.possibilities){
            int totalLength = 0;
            ArrayList<Vector2> tileCoordinates = new ArrayList<>();

            int beginX = 0;
            int beginY = 0;


            // loop through indexes
            for(int index : path){

                // get difference

                double xDiff = (tileList.get(index).getXcoord() < beginX) ? beginX - tileList.get(index).getXcoord() : tileList.get(index).getXcoord() - beginX ;
                double yDiff = (tileList.get(index).getYcoord() < beginY) ? beginY - tileList.get(index).getYcoord() : tileList.get(index).getYcoord() - beginY ;
                double xyDiff = Math.hypot(xDiff, yDiff);

                beginX = tileList.get(index).getXcoord();
                beginY = tileList.get(index).getYcoord();

                tileCoordinates.add(new Vector2(beginX, beginY));

                totalLength += xyDiff;
            }
            //pathList.add(new EnumPath(totalLength, tileCoordinates));


            if(totalLength < pathLength || pathLength == 0){
                pathLength = totalLength;
                shortestPath = new EnumPath(pathLength, tileCoordinates);

            }

            if(logging){
                double calc = (double) cnt / this.possibilities.size();
                simulation.progression.setProgress(calc);
            }
            cnt++;
        }


    }

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        //start from an empty list
        result.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();

            for (ArrayList<Integer> l : result) {
                for (int j = 0; j < l.size()+1; j++) {
                    l.add(j, num[i]);

                    ArrayList<Integer> temp = new ArrayList<Integer>(l);
                    current.add(temp);

                    l.remove(j);
                }


            }

            result = new ArrayList<ArrayList<Integer>>(current);
        }

        return result;
    }

    public int getPossiblePaths(){
        return possiblePaths;
    }


}
