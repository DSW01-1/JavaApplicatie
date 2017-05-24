package main.java.pane.order;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.database.DatabaseOrder;

import java.util.List;

public class OrderPane extends Pane{
    int customerId;
    OrderHistory parentScript;
    public OrderPane(int customerId, OrderHistory parentScript){
        this.parentScript=parentScript;
        this.customerId=customerId;
        List<Integer> Orders = DatabaseOrder.getOrdersFromId(customerId);
        setId("customer");
        setPrefSize(200, 50);
    }

    private void AddText(String name,int x, int y)
    {
        Text text = new Text(0, 20, name);
        text.setLayoutX(x);
        text.setLayoutY(y);
        getChildren().add(text);
    }

    private void addClickable(){
        Pane clickPane = new Pane();
        clickPane.setPrefSize(200,50);
        clickPane.setCursor(Cursor.HAND);
        getChildren().add(clickPane);
        clickPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

            }
        });
    }
}
