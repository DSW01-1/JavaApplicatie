package main.java.pane.bppSimulationSrc;
import javafx.scene.shape.Rectangle;
import main.java.pane.base.StyledPane;

public class Boxpane extends StyledPane {
    private int size;

    public Boxpane(int size) {
        super();
        this.size = size;

        setLayoutX(200);
        setLayoutY(400);
        setPrefSize(200, 400);

        Rectangle boxShape = new Rectangle();
        boxShape.setHeight(size);
        boxShape.setWidth(50);
        boxShape.setVisible(true);
        getChildren().add(boxShape);
    }


}
