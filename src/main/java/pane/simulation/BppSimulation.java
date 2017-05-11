package main.java.pane.simulation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import main.java.algorithms.bpp.NextFit;
import main.java.graphs.Product;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.Vector2;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.simulation.TspSimulation;

import java.awt.*;
import java.awt.Label;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BppSimulation extends StyledPane
{
	private TextField productSizes;
	public static BppSimulation simulation;

	public ListView<String> consoleList = new ListView<String>();
	private ArrayList<Product> products = new ArrayList<Product>();

	public BppSimulation() {
		NextFit nextFit = new NextFit(this);
		consoleList.setLayoutX(15);
		consoleList.setLayoutY(570);
		consoleList.setPrefWidth(825);
		consoleList.setPrefHeight(250);
		getChildren().add(consoleList);
		simulation = this;
		// back to main menu
		StyledButton orderHistoryButton = new StyledButton(Language.getTranslation("btn.backToMainMenu"),
				new Vector2(15, 15), new Vector2(200, 50));
		orderHistoryButton.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(orderHistoryButton);
		//text field for product sizes
		TextField productSizes = new TextField("");
		productSizes.setLayoutX(1025);
		productSizes.setLayoutY(750);
		productSizes.setPrefHeight(30);
		productSizes.setPrefWidth(150);
		getChildren().add(productSizes);
		//textfield for box size
		TextField boxSize = new TextField("");
		boxSize.setLayoutX(1025);
		boxSize.setLayoutY(650);
		boxSize.setPrefHeight(30);
		boxSize.setPrefWidth(150);
		getChildren().add(boxSize);
		//labels for both text fields
		StyledLabel productSizesLabel = new StyledLabel("Product size:",new Vector2(880,750),20);
		getChildren().add(productSizesLabel);
		StyledLabel boxSizeLabel = new StyledLabel("Box size:",new Vector2(923,650),20);
		getChildren().add(boxSizeLabel);
		//button for confirming box size
		StyledButton confirmBoxSize = new StyledButton("Confirm",new Vector2(1185,650));
		confirmBoxSize.setOnAction(event ->{
			nextFit.boxVolume = (Integer.parseInt(boxSize.getText()));
			boxSize.setText("");
		});
		getChildren().add(confirmBoxSize);
		//button for confirming product size
		StyledButton confirmProductSize = new StyledButton("Confirm",new Vector2(1185,750));
		confirmProductSize.setOnAction(event -> {
			products.add(new Product(Integer.parseInt(productSizes.getText())));
			productSizes.setText("");
		});
		getChildren().add(confirmProductSize);

		//start algorithm
		StyledButton startNextFit = new StyledButton("Start Next Fit",new Vector2(15,200));
		startNextFit.setOnAction(event -> {
			nextFit.executeNextFit(products,this);
			products.clear();
		});
		getChildren().add(startNextFit);
	}
	public void addConsoleItem(String Message, String msgType)
	{
		consoleList.getItems().add(consoleList.getItems().size(), String.format("[%s] %s", msgType, Message));
	}
	//getters for textfields
}
