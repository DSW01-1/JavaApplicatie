package main.java.pane.order;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class OrderPane extends Pane{
    private int ordernr;
    private OrderHistory parentScript;
    public OrderPane(int ordernr, OrderHistory parentScript){
        this.parentScript=parentScript;
        this.ordernr=ordernr;
        AddText("Ordernummer: "+ordernr,0,0);
        setId("customer");
        setPrefSize(200, 30);
        addClickable();
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
        clickPane.setPrefSize(200,30);
        clickPane.setCursor(Cursor.HAND);
        getChildren().add(clickPane);
        clickPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                parentScript.createProductVBox(ordernr);
            }
        });
    }
}
