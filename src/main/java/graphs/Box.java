package main.java.graphs;

public class Box {

    private int volume = 10;
    int[] contents = new int[10];
    //x is location in contents array
    int x = 0;

    public boolean checkFit(int size){
        return size < volume;
    }
    public void addProduct(int size){
        if(checkFit(size)) {
            this.contents[x] = size;
            this.volume -= size;
            x++;
        }
        else{
            //printmessage not possible
        }
    }
}
