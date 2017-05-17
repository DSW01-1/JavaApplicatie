package main.java.pane.simulation;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.algorithms.bpp.BruteForce;
import main.java.algorithms.bpp.DecreasingFirstFit;
import main.java.algorithms.bpp.FirstFit;
import main.java.algorithms.bpp.NextFit;
import main.java.graphs.Product;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.pane.ConsolePane;
import main.java.pane.SimulationControls;
import main.java.pane.base.StyledLabel;

import java.util.ArrayList;

public class BppSimulation extends BaseSimulation {
    private static int boxVolume;
    private ConsolePane consolePane;
    private ListView<String> consoleList = new ListView<String>();
    private ArrayList<Product> products = new ArrayList<Product>();

    public BppSimulation() {
        super();
        AddControls();
        AddConsolePane();
        AddInputFields();
    }
    //create algorithms
    NextFit nextFit = new NextFit(this);
    FirstFit firstFit = new FirstFit(this);
    BruteForce bruteForce = new BruteForce(this);
    DecreasingFirstFit decreasingFirstFit = new DecreasingFirstFit(this);

    @Override
    public void ExecuteAlgorithmOne() {

        consolePane.getItems().clear();
        addConsoleItem("Starting Algorithm 'Next Fit'","DEBUG");
        nextFit.executeNextFit(products);
        nextFit.boxVolume = boxVolume;
        System.out.print("Test succeeded");
    }
    @Override
    public void ExecuteAlgorithmTwo() {
        consolePane.getItems().clear();

        firstFit.executeFirstFit(products);
    }
    @Override
    public void ExecuteAlgorithmThree() {
        consolePane.getItems().clear();
        bruteForce.executeBruteForce(products);
    }

    @Override
    public void ExecuteAlgorithmFour(){
        consolePane.getItems().clear();
        decreasingFirstFit.executeDecreasingFirstFit(products);
    }
    public void AddControls() {
        String[] algorithmNames =
                {"btn.nextFit", "btn.firstFit", "btn.bruteForce", "btn.ownAlgorithm"};

        getChildren().add(new SimulationControls(algorithmNames, this));
    }
    private void AddInputFields() {
        TextField boxSizeInput = new TextField();
        boxSizeInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
        boxSizeInput.setLayoutY(ScreenProperties.getScreenHeight() - 200);
        boxSizeInput.setPrefSize(40, 30);
        getChildren().add(boxSizeInput);
        boxSizeInput.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                nextFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
                firstFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
                bruteForce.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
                decreasingFirstFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
                boxSizeInput.setText("");
            }

        });

        //products input
        TextField productsInput = new TextField();
        productsInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
        productsInput.setLayoutY(ScreenProperties.getScreenHeight() - 150);
        productsInput.setPrefSize(ScreenProperties.getScreenWidth() / 3, 30);
        getChildren().add(productsInput);
        productsInput.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ENTER){
                String productsString = (productsInput.getText());
                String[] productsToAdd = productsString.split(" ");
                for (int i = 0; i < productsToAdd.length; i++){
                    products.add(new Product(Integer.parseInt(productsToAdd[i])));
                }
                productsInput.setText("");
            }
        });
    }

    private void AddConsolePane() {
        consolePane = new ConsolePane();
        getChildren().add(consolePane);
    }

    public void addConsoleItem(String Message, String msgType) {
        consolePane.getItems().add(String.format("[%s] %s", msgType, Message));
    }
    // getters for textfields
}
