package main.java.pane.order;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.main.product.CustomerInfo;



public class PersonPane extends Pane {
    private CustomerInfo info;

    public PersonPane(CustomerInfo info){
        this.info=info;
        setId("customer");
        setPrefSize(200, 50);
        AddText(info.getFirstname()+" "+info.getLastname());
        AddText("ID: "+" "+String.valueOf(info.getId()),0,20);
        addClickable();
    }

    private void AddText(String name)
    {
        Text text = new Text(0, 20, name);
        getChildren().add(text);
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
