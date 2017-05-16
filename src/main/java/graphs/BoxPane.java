package main.java.graphs;

import javafx.scene.layout.HBox;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

import java.util.ArrayList;

public class BoxPane extends StyledPane{

    public BoxPane(){
        super();

    }

    private void AddScrollPane(){
        HBox hBox = new HBox();
        ArrayList<Box> boxArray = new ArrayList<Box>();
        boxArray.add(new Box(10));
       // StyledScrollPane scrollPane = new StyledScrollPane();
    }
}
