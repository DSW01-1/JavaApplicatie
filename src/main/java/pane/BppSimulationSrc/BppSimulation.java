package main.java.pane.BppSimulationSrc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;
/**
 * Created by Jordi Smit on 5/9/2017.
 */
public class BppSimulation extends StyledPane {

    public BppSimulation(){
        //back to main menu
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
        //box size field
        TextField boxSize = new TextField();
        getChildren().add(boxSize);
    }


}
