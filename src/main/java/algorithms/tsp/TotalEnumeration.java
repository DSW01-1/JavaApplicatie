package main.java.algorithms.tsp;
import main.java.graphs.GridTile;
import java.util.ArrayList;

public class TotalEnumeration
{
    private ArrayList<GridTile> tileList;
    public TotalEnumeration(ArrayList<GridTile> tileList){
        this.tileList = tileList;

        findPermutations();
    }

    private void findPermutations(){
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < tileList.size(); i++) {
            
            GridTile current = tileList.get(i);
            ArrayList<Integer> indexList = new ArrayList<>();

            for (int j = i +1; j < tileList.size(); j++) {
                //current = current + letterArray.get(j);
                indexList.add(j);
            }
            System.out.println(indexList);

        }
    }


}
