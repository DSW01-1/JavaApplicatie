package main.java.pane.simulation;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.algorithms.bpp.BruteForce;
import main.java.algorithms.bpp.DecreasingFirstFit;
import main.java.algorithms.bpp.FirstFit;
import main.java.algorithms.bpp.NextFit;
import main.java.graphs.BoxPane;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.ConsolePane;
import main.java.pane.SimulationControls;

public class BppSimulation extends BaseSimulation
{
	private static int boxVolume;
	private ConsolePane consolePane;
	private ArrayList<Product> products = new ArrayList<Product>();
	private BoxPane boxPane;

	public BppSimulation()
	{
		super();

		AddControls();
		AddConsolePane();
		AddInputFields();
		AddBoxPane();
	}

	// create algorithms
	NextFit nextFit = new NextFit(this);
	FirstFit firstFit = new FirstFit(this);
	BruteForce bruteForce = new BruteForce(this);
	DecreasingFirstFit decreasingFirstFit = new DecreasingFirstFit(this);

	@Override
	public void ExecuteAlgorithmOne()
	{
		consolePane.getItems().clear();
		addConsoleItem("Starting Algorithm 'Next Fit'", "DEBUG");
		nextFit.executeNextFit(products);
		nextFit.boxVolume = boxVolume;
		System.out.print("Test succeeded");
		products.clear();
	}

	@Override
	public void ExecuteAlgorithmTwo()
	{
		consolePane.getItems().clear();

		firstFit.executeFirstFit(products);
		products.clear();
	}

	@Override
	public void ExecuteAlgorithmThree()
	{
		consolePane.getItems().clear();
		bruteForce.executeBruteForce(products);
		products.clear();
	}

	@Override
	public void ExecuteAlgorithmFour()
	{
		consolePane.getItems().clear();
		decreasingFirstFit.executeDecreasingFirstFit(products);
		products.clear();
	}

	public void AddControls()
	{
		String[] algorithmNames =
		{ "btn.nextFit", "btn.firstFit", "btn.bruteForce", "btn.ownAlgorithm" };

		getChildren().add(new SimulationControls(algorithmNames, this));
	}

	private void AddInputFields()
	{
		TextField boxSizeInput = new TextField();
		boxSizeInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		boxSizeInput.setLayoutY(ScreenProperties.getScreenHeight() - 200);
		boxSizeInput.setPrefSize(40, 30);
		getChildren().add(boxSizeInput);
		boxSizeInput.setOnKeyPressed(event ->
		{
			if (event.getCode() == KeyCode.ENTER)
			{
				nextFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
				firstFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
				bruteForce.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
				decreasingFirstFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
				boxSizeInput.setText("");
			}
		});

		// products input
		TextField productsInput = new TextField();
		productsInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		productsInput.setLayoutY(ScreenProperties.getScreenHeight() - 150);
		productsInput.setPrefSize(ScreenProperties.getScreenWidth() / 3, 30);
		getChildren().add(productsInput);
		productsInput.setOnKeyPressed((KeyEvent event) ->
		{
			if (event.getCode() == KeyCode.ENTER) {
				if (boxVolume != 0)
				{
					String productsString = (productsInput.getText());
					if (productsString.matches("[0-9 *]+")) {
						String[] productsToAdd = productsString.split(" ");
						for (int i = 0; i < productsToAdd.length; i++) {
							products.add(new Product(i, "i", new Vector2(0, 0), (Integer.parseInt(productsToAdd[i]))));
						}
					} else {
						addConsoleItem("Only numerical and spaces are accepted.", "ERROR");
					}
					productsInput.setText("");
				}else{
					addConsoleItem("Please enter a box size first.","ERROR");
				}
			}
		});
	}

	public void AddBoxPane()
	{
		boxPane = new BoxPane();
		getChildren().add(boxPane);
	}

	public String printAllProducts(ArrayList<Product> products)
	{
		String productGegevens = new String();
		for (Product product : products)
		{
			productGegevens = ("Size: " + product.GetSize() + ". ID: " + product.GetId() + ". Coords"
					+ product.GetCoords() + ". Name: " + product.GetName() + ".");
			this.addConsoleItem(productGegevens, "DEBUG");
		}
		return "";
	}

	private void AddConsolePane()
	{
		consolePane = new ConsolePane();
		getChildren().add(consolePane);
	}

	public void addConsoleItem(String Message, String msgType)
	{
		consolePane.getItems().add(String.format("[%s] %s", msgType, Message));
	}

}
