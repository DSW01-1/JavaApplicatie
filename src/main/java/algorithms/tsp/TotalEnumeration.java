package main.java.algorithms.tsp;
import main.java.graphs.GridTile;
import main.java.main.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TotalEnumeration
{
    private ArrayList<GridTile> tileList;
    private ArrayList<EnumPath> pathList = new ArrayList<>();
    private int possiblePaths;

    public TotalEnumeration(ArrayList<GridTile> tileList){
        this.tileList = tileList;

        // creating array of all tile indexes
        int[] tileIndexes = new int[tileList.size()];
        for(int i=0; i < tileList.size();i++){
            tileIndexes[i] = i;
        }


        // Fetching all possible paths
        ArrayList<ArrayList<Integer>> test = permute(tileIndexes);
        possiblePaths = test.size();

        // putting all paths in an arraylist
        int cnt = 1;
        for(ArrayList<Integer> subtest : test){
            addPath(subtest);
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

    private void addPath(ArrayList<Integer> Path){
        int totalLength = 0;
        ArrayList<Vector2> tileCoordinates = new ArrayList<>();

        int beginX = 0;
        int beginY = 0;

        for(int index : Path){
            double xDiff = (tileList.get(index).getXcoord() < beginX) ? beginX - tileList.get(index).getXcoord() : tileList.get(index).getXcoord() - beginX ;
            double yDiff = (tileList.get(index).getYcoord() < beginY) ? beginY - tileList.get(index).getYcoord() : tileList.get(index).getYcoord() - beginY ;

            beginX = tileList.get(index).getXcoord();
            beginY = tileList.get(index).getYcoord();

            tileCoordinates.add(new Vector2(beginX, beginY));

            double xyDiff = Math.hypot(xDiff, yDiff);
            totalLength += xyDiff;
        }

        double xyDiff = Math.hypot(beginX, beginY);
        totalLength += xyDiff;

        pathList.add(new EnumPath(totalLength, tileCoordinates));
    }

    public ArrayList<Vector2> getShortestPath(){
        int pathLength = 0;
        int finalIndex = 0;

        int index = 0;
        for(EnumPath currentPath : pathList){
            if(currentPath.getPathLength() < pathLength || pathLength == 0){
                pathLength = currentPath.getPathLength();
                finalIndex = index;
            }
            index++;
        }
        return pathList.get(finalIndex).getTiles();
    }
    public int getPossiblePaths(){
        return possiblePaths;
    }


}
