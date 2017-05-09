package main.java.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import main.java.main.Main;
import main.java.main.Vector2;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

public class TspSimulation extends StyledPane {

    public TspSimulation(){

        int xPoint = 15;
        int yPoint = 15;

        // back to menu button
        StyledButton goBackToMenu = new StyledButton("Go back to menu", new Vector2(15,15), new Vector2(250, 50));
        goBackToMenu.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent aEvent)
            {
                Main.SwitchPane(new MainMenu());
            }
        });
        getChildren().add(goBackToMenu);



        // RADIO BUTTONS
        Label lblChooseAlgorithm = new Label("Algoritmes");
        lblChooseAlgorithm.setLayoutX(15);
        lblChooseAlgorithm.setLayoutY(105);
        lblChooseAlgorithm.setFont(Font.font ("Century Gothic", 20));
        getChildren().add(lblChooseAlgorithm);


        // RADIOBUTTONS
        RadioButton chkAlgorithm1 = new RadioButton("Nearest Neighbour");
        chkAlgorithm1.setLayoutX(15);
        chkAlgorithm1.setLayoutY(140);
        chkAlgorithm1.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(chkAlgorithm1);

        RadioButton chkAlgorithm2 = new RadioButton("Multiple Fragment");
        chkAlgorithm2.setLayoutX(15);
        chkAlgorithm2.setLayoutY(165);
        chkAlgorithm2.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(chkAlgorithm2);

        RadioButton chkAlgorithm3 = new RadioButton("Volledige Enumeratie");
        chkAlgorithm3.setLayoutX(15);
        chkAlgorithm3.setLayoutY(190);
        chkAlgorithm3.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(chkAlgorithm3);

        RadioButton chkAlgorithm4 = new RadioButton("Eigen Algoritme");
        chkAlgorithm4.setLayoutX(15);
        chkAlgorithm4.setLayoutY(215);
        chkAlgorithm4.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(chkAlgorithm4);

        ToggleGroup radioGroup = new ToggleGroup();
        chkAlgorithm1.setToggleGroup(radioGroup);
        chkAlgorithm2.setToggleGroup(radioGroup);
        chkAlgorithm3.setToggleGroup(radioGroup);
        chkAlgorithm4.setToggleGroup(radioGroup);



        // START + STOP BUTTON
        StyledButton startButton = new StyledButton("Play",
                new Vector2(15,245), new Vector2(115, 30));
        startButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent arg0)
            {

            }
        });
        getChildren().add(startButton);

        StyledButton stopButton = new StyledButton("Stop",
                new Vector2(150,245), new Vector2(115, 30));
        stopButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent arg0)
            {

            }
        });
        getChildren().add(stopButton);



        // PROGRESS BAR
        ProgressBar progression = new ProgressBar(0.6);
        progression.setLayoutX(15);
        progression.setLayoutY(295);
        progression.setPrefWidth(250);
        getChildren().add(progression);



        // RESULTS
        Label lblResults = new Label("Resultaten");
        lblResults.setLayoutX(15);
        lblResults.setLayoutY(340);
        lblResults.setFont(Font.font ("Century Gothic", 20));
        getChildren().add(lblResults);

        Rectangle rectResults = new Rectangle(15,370,250,130);
        rectResults.setFill(Color.TRANSPARENT);
        rectResults.setStroke(Color.BLACK);
        rectResults.setArcHeight(6);
        rectResults.setArcWidth(6);
        getChildren().add(rectResults);



        // RESULT LABELS
        int[] resultY = new int[]{380,410,440,470};
        String[] resultNames = new String[]{"Nearest Neighbour","Multiple Fragment","Volledige Enumeratie", "Eigen Algoritme"};

        for (int cnt = 1; cnt < 5; cnt++) {
            Label lblResAlg = new Label(resultNames[cnt - 1]);
            lblResAlg.setLayoutX(23);
            lblResAlg.setLayoutY(resultY[cnt - 1]);
            lblResAlg.setFont(Font.font("Century Gothic", 14));
            getChildren().add(lblResAlg);
        }



        // RESULTS IN MS
        Label lblResAlg1 = new Label("1 ms ");
        lblResAlg1.setLayoutX(210);
        lblResAlg1.setLayoutY(380);
        lblResAlg1.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(lblResAlg1);

        Label lblResAlg2 = new Label("3 ms ");
        lblResAlg2.setLayoutX(210);
        lblResAlg2.setLayoutY(410);
        lblResAlg2.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(lblResAlg2);

        Label lblResAlg3 = new Label("2.5 ms ");
        lblResAlg3.setLayoutX(210);
        lblResAlg3.setLayoutY(440);
        lblResAlg3.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(lblResAlg3);

        Label lblResAlg4 = new Label("10 ms");
        lblResAlg4.setLayoutX(210);
        lblResAlg4.setLayoutY(470);
        lblResAlg4.setFont(Font.font ("Century Gothic", 14));
        getChildren().add(lblResAlg4);




    }
}
