package main.java.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.java.main.Language;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;



/**
 * Created by aukevanoost on 9-5-2017.
 */

public class TspSimulation extends StyledPane {

    public TspSimulation(){

        // back to menu
        StyledButton orderHistoryButton = new StyledButton("Go back to menu",
                new Vector2(15,15), new Vector2(200, 50));
        orderHistoryButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent arg0)
            {
                //TODO
            }
        });
        getChildren().add(orderHistoryButton);

        
    }
}
