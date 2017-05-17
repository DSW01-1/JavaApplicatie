package main.java.pane.simulation;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import main.java.algorithms.bpp.BruteForce;
import main.java.algorithms.bpp.DecreasingFirstFit;
import main.java.algorithms.bpp.FirstFit;
import main.java.algorithms.bpp.NextFit;
import main.java.constant.Constants;
import main.java.graphs.Product;
import main.java.main.Main;
import main.java.main.Vector2;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.graphs.BoxPane;
import main.java.main.ScreenProperties;
import main.java.pane.ConsolePane;
import main.java.pane.SimulationControls;

public class BppSimulation extends StyledPane
{
	private TextField productSizes;
	public static BppSimulation simulation;

	public ListView<String> consoleList = new ListView<String>();
	private ArrayList<Product> products = new ArrayList<Product>();
	ConsolePane consolePane;
	BoxPane boxPane;

	public BppSimulation()
	{
		super();
		AddControls();
		AddConsolePane();
		AddInputFields();
		AddBoxPane();
	}

	private void AddInputFields()
	{
		TextField boxSizeInput = new TextField();
		boxSizeInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		boxSizeInput.setLayoutY(ScreenProperties.getScreenHeight() - 200);
		boxSizeInput.setPrefSize(40, 30);
		getChildren().add(boxSizeInput);

		TextField productsInput = new TextField();
		productsInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		productsInput.setLayoutY(ScreenProperties.getScreenHeight() - 150);
		productsInput.setPrefSize(ScreenProperties.getScreenWidth() / 3, 30);
		getChildren().add(productsInput);
	}

	public void AddControls()
	{
		String[] algorithmNames =
		{ "btn.firstFit", "btn.nextFit", "btn.totalEnumeration", "btn.ownAlgorithm" };

		//getChildren().add(new SimulationControls(algorithmNames, this));
	}

	private void AddConsolePane()
	{
		consolePane = new ConsolePane();
		getChildren().add(consolePane);
	}

	public void AddBoxPane()
	{
		boxPane = new BoxPane();
		getChildren().add(boxPane);
	}

	public void addConsoleItem(String string, String string2)
	{
		//productGen productGen = new productGen(200000,products);
		consoleList.setLayoutX(15);
		consoleList.setLayoutY(570);
		consoleList.setPrefWidth(825);
		consoleList.setPrefHeight(250);
		getChildren().add(consoleList);
		simulation = this;

		// back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		// text field for product sizes
		TextField productSizes = new TextField("");
		productSizes.setLayoutX(1025);
		productSizes.setLayoutY(750);
		productSizes.setPrefHeight(30);
		productSizes.setPrefWidth(150);
		getChildren().add(productSizes);
		// textfield for box size
		TextField boxSize = new TextField("");
		boxSize.setLayoutX(1025);
		boxSize.setLayoutY(650);
		boxSize.setPrefHeight(30);
		boxSize.setPrefWidth(150);
		getChildren().add(boxSize);
		// labels for both text fields
		StyledLabel productSizesLabel = new StyledLabel("lbl.productSize", new Vector2(850, 750), 20);
		getChildren().add(productSizesLabel);
		StyledLabel boxSizeLabel = new StyledLabel("lbl.boxSize", new Vector2(870, 650), 20);
		getChildren().add(boxSizeLabel);
		// button for confirming box size
		NextFit nextFit = new NextFit(this);
		FirstFit firstFit = new FirstFit(this);
		BruteForce bruteForce = new BruteForce(this);
		DecreasingFirstFit decFirstFit = new DecreasingFirstFit(this);
		StyledButton confirmBoxSize = new StyledButton("btn.confirm", new Vector2(1200, 650));
		confirmBoxSize.setOnAction(event ->
		{
			firstFit.boxVolume = (Integer.parseInt(boxSize.getText()));
			nextFit.boxVolume = (Integer.parseInt(boxSize.getText()));
			bruteForce.boxVolume = (Integer.parseInt(boxSize.getText()));
			decFirstFit.boxVolume = (Integer.parseInt(boxSize.getText()));
			boxSize.setText("");
		});

		getChildren().add(confirmBoxSize);
		// button for confirming product size
		boxSize.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()== KeyCode.ENTER){
					firstFit.boxVolume = (Integer.parseInt(boxSize.getText()));
					nextFit.boxVolume = (Integer.parseInt(boxSize.getText()));
					bruteForce.boxVolume = (Integer.parseInt(boxSize.getText()));
					decFirstFit.boxVolume = (Integer.parseInt(boxSize.getText()));
					boxSize.setText("");
				}
			}
		});
		StyledButton confirmProductSize = new StyledButton("btn.confirm", new Vector2(1200, 750));
		confirmProductSize.setOnAction((ActionEvent event) ->
		{
			String productString = productSizes.getText();
			String[] productsToAdd = productString.split(" ");
			for (String aProductsToAdd : productsToAdd) {
				products.add(new Product(Integer.parseInt(aProductsToAdd)));
			}
			productSizes.setText("");
		});

		getChildren().add(confirmProductSize);
		productSizes.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode()== KeyCode.ENTER){
                String productString = productSizes.getText();
                String[] productsToAdd = productString.split(" ");
				for (String ProductsToAdd : productsToAdd) {
					products.add(new Product((Integer.parseInt(ProductsToAdd))));
				}
                productSizes.setText("");
            }
        });
		// start algorithm
		StyledButton startNextFit = new StyledButton("btn.startNextFit", new Vector2(15, 200));
		startNextFit.setOnAction(event ->
		{
			nextFit.executeNextFit(products);
			products.clear();
			nextFit.returnBoxes.clear();
		});
		StyledButton startFirstFit = new StyledButton("btn.startFirstFit", new Vector2(15, 300));
		startFirstFit.setOnAction(event ->
		{
			firstFit.executeFirstFit(products);
			products.clear();
			firstFit.returnBoxes.clear();
		});
		StyledButton startBruteForce = new StyledButton("btn.startBruteForce", new Vector2(100, 300));
		startBruteForce.setOnAction(event ->
		{
			bruteForce.executeBruteForce(products);
			products.clear();
		});
		StyledButton startDecFirstFit = new StyledButton("btn.startDecFirstFit", new Vector2(100, 200));
		startDecFirstFit.setOnAction(event ->
		{
			decFirstFit.executeDecreasingFirstFit(products);
			products.clear();
		});
		getChildren().add(startNextFit);
		getChildren().add(startFirstFit);
		getChildren().add(startBruteForce);
		getChildren().add(startDecFirstFit);
	}
	//public void addConsoleItem(String Message, String msgType)
	//{
	//	consoleList.getItems().add(consoleList.getItems().size(), String.format("[%s] %s", msgType, Message));
	//}
	// getters for textfields
}
