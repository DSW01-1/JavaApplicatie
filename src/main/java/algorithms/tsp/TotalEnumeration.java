package main.java.algorithms.tsp;
import javafx.application.Platform;
import main.java.graphs.DistanceGrid;
import main.java.graphs.Grid;
import main.java.graphs.GridTile;
import main.java.main.Vector2;
import main.java.pane.simulation.TspSimulation;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TotalEnumeration extends Thread
{
    /* Variables */
    private ArrayList<GridTile> tileList;
    private ArrayList<EnumPath> pathList = new ArrayList<>();
    private EnumPath shortestPath;
    private DistanceGrid dG;

    ArrayList<ArrayList<Integer>> possibilities;
    private int possiblePaths;
    private TspSimulation simulation;
    private boolean logging = false;
    private double pathLength = 0;

    private int factor = 0;
    private int progress = 0;

    private boolean suspended = false;

    public synchronized void suspendII() { suspended = true; notify(); }
    public synchronized void resumeII() { suspended = false; notify(); }
    public synchronized void killII(){ suspended = false; stop(); }
    /* Constructors incl overload */
    public TotalEnumeration(ArrayList<GridTile> tileList){
        this.tileList = tileList;
        this.simulation = new TspSimulation();
        dG = new DistanceGrid(tileList);
    }
    public TotalEnumeration(TspSimulation simulation){
        this.simulation = simulation;
        this.logging = true;
        this.tileList = new ArrayList<GridTile>();
        dG = new DistanceGrid(tileList);
    }
    public TotalEnumeration(ArrayList<GridTile> tileList, TspSimulation simulation){
        this.tileList = tileList;
        this.simulation = simulation;
        logging = true;
        dG = new DistanceGrid(tileList);
    }


    /* Getters and/or Setters */
    public ArrayList<Vector2> getShortestPath(){
        return shortestPath.getTiles();
    }
    public void setTileList(ArrayList<GridTile> tileList){
        this.tileList = tileList;
        dG = new DistanceGrid(tileList);
    }
    public int getPossiblePaths(){
        return possiblePaths;
    }
    public void showPermutations(int startindex, int[] input) {
        permute(input,0);
    }
    public boolean isSuspended(){ return suspended;}

    /* THREAD RUN method */
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

        // calc factor
        setFactor(tileList.size());
        if(logging){
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    simulation.addConsoleItem(factor + " Possible paths","DEBUG");
                }
            });
        }

        showPermutations(0,tileIndexes);

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

/*
    public void pauseThread(){
        this.suspended = true;
    }
    public void resumeThread(){
        this.suspended = false;
        notify();
   }
*/

    /* MISC */
    public void processShortestPath(int[] indexList){

        double totalLength = 0;
        ArrayList<Vector2> tileCoordinates = new ArrayList<>();

        int lastIndex=0;


        // loop through indexes
        for(int index : indexList){

            // get difference
            double xyDiff = dG.distanceGrid[lastIndex][index];

            lastIndex = index;
            tileCoordinates.add(new Vector2(tileList.get(index).getXcoord(), tileList.get(index).getYcoord()));

            totalLength += xyDiff;
        }
        int lastCoorX=tileList.get(lastIndex).getXcoord();
        int lastCoorY=tileList.get(lastIndex).getYcoord();
        double xyDiff = Math.hypot(lastCoorX, lastCoorY);
        totalLength += xyDiff;

        tileCoordinates.add(new Vector2(lastCoorX, lastCoorY));

        if(totalLength < this.pathLength || this.pathLength == 0){
            this.pathLength = totalLength;
            shortestPath = new EnumPath(this.pathLength, tileCoordinates);


        }

        if(logging){
            double calc = (double) this.progress / factor;
            simulation.progression.setProgress(calc);
        }

    }



    void permute(int []a,int k ) {

        synchronized(this) {
            while (suspended) {
                try{
                    wait(); // The current thread will block until some else calls notify()
                    // Then if _suspended is false, it keeps looping the for
                }catch (Exception ex){
                    System.out.println("Shit failed");
                }

            }
        }
        if (k == a.length) {
            processShortestPath(a);

            this.progress++;
        } else {
            for (int i = k; i < a.length; i++) {

                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                permute(a, k + 1);
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }

    public void setFactor(int m) {
        for(int y=(m-1) ; y > 0 ; y--) {
            m = m * y;
        }
        this.factor = m;
    }


}
