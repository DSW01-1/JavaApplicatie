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
import main.java.main.product.Box;
import main.java.main.product.Product;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.algorithms.BPPAlgorithm;

public class BppSimulation extends BaseSimulation
{
	private int boxVolume;
	private ConsolePane consolePane;
	private ArrayList<Product> products = new ArrayList<Product>();
	private BoxPane boxPane;

	public BppSimulation()
	{
		super();

		// add all controls and javaFX panes
		AddControls();
		AddConsolePane();
		AddInputFields();
		getStyleClass().add("background");
		AddBoxPane();
	}

	// set algorithm options for the radio buttons from AddControls()
	@Override
	public void ExecuteAlgorithmOne()
	{
		runAlgorithm(new NextFit());
	}

	@Override
	public void ExecuteAlgorithmTwo()
	{
		runAlgorithm(new FirstFit());
	}

	@Override
	public void ExecuteAlgorithmThree()
	{
		runAlgorithm(new BruteForce());
	}

	@Override
	public void ExecuteAlgorithmFour()
	{
		runAlgorithm(new DecreasingFirstFit());
	}

	public void AddControls()
	{
		String[] algorithmNames =
		{ "btn.nextFit", "btn.firstFit", "btn.bruteForce", "btn.ownAlgorithm" };

		getChildren().add(new SimulationControls(algorithmNames, this));
	}

	private void AddInputFields()
	{
		// make a new label for box size input
		StyledLabel boxSizeInputLabel = new StyledLabel("lbl.boxSize",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) - 125,
						ScreenProperties.getScreenHeight() - 192),
				16);
		getChildren().add(boxSizeInputLabel);

		// make a new textfield for the box size input
		TextField boxSizeInput = new TextField();
		boxSizeInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		boxSizeInput.setLayoutY(ScreenProperties.getScreenHeight() - 200);
		boxSizeInput.setPrefSize(40, 30);
		getChildren().add(boxSizeInput);
		boxSizeInput.setOnKeyPressed(event ->
		{
			// if the enter button is pressed, do...
			if (event.getCode() == KeyCode.ENTER)
			{
				boxVolume = (Integer.parseInt(boxSizeInput.getText()));
				boxSizeInput.setText("");
			}
		});

		// create a button to confirm the box size input
		StyledButton boxSizeInputButton = new StyledButton("btn.confirmBox",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) + 100,
						ScreenProperties.getScreenHeight() - 200));
		getChildren().add(boxSizeInputButton);
		boxSizeInputButton.setOnAction(event ->
		{
			boxVolume = (Integer.parseInt(boxSizeInput.getText()));
			boxSizeInput.setText("");
		});

		// products input
		StyledLabel productsInputLabel = new StyledLabel("lbl.productSize",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) - 145,
						ScreenProperties.getScreenHeight() - 148),
				16);
		getChildren().add(productsInputLabel);

		// product generation, for easy testing of large amounts of products
		TextField randomProductInput = new TextField();
		randomProductInput.setLayoutX(
				(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2) + 500);
		randomProductInput.setLayoutY(ScreenProperties.getScreenHeight() - 200);
		randomProductInput.setPrefSize(40, 30);
		getChildren().add(randomProductInput);
		randomProductInput.setOnKeyPressed(event ->
		{
			// if the enter key is pressed, do...
			if (event.getCode() == KeyCode.ENTER)
			{
				// if the box volume is not 0 (has been set), do...
				if (boxVolume != 0)
				{
					int amount = (Integer.parseInt(randomProductInput.getText()));
					for (int k = 0; k < amount; k++)
					{
						// generate a new product with a random size between 1
						// and boxVolume (inclusive)
						products.add(new Product(1, "1", new Vector2(1, 1),
								((int) Math.floor(Math.random() * (boxVolume - 1)) + 1)));
					}
					printAllProducts(products);
					randomProductInput.setText("");
				}
				else
				{
					addConsoleItem("Please enter a box size first!", "ERROR");
				}
			}
		});
		// Create a new textfield to input products manually
		TextField productsInput = new TextField();
		productsInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		productsInput.setLayoutY(ScreenProperties.getScreenHeight() - 150);
		productsInput.setPrefSize(ScreenProperties.getScreenWidth() / 3, 30);
		getChildren().add(productsInput);

		// create a button to confirm the product size input
		StyledButton productsInputButton = new StyledButton("btn.confirmProduct",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) + 200,
						ScreenProperties.getScreenHeight() - 200));
		getChildren().add(productsInputButton);
		// check if the enter key is pressed while in the ProductsInput
		// textfield...
		productsInput.setOnKeyPressed((KeyEvent event) ->
		{
			if (event.getCode() == KeyCode.ENTER)
			{
				if (products.size() > 0)
				{
					products.clear();
				}
				// if the box volume is set
				if (boxVolume != 0)
				{
					String productsString = (productsInput.getText());
					// if the string contains a letter or other non-numerical
					// characters except a single space...
					if (productsString.matches("[0-9 *]+"))
					{
						// split the string into a list and add them to the
						// product array
						String[] productsToAdd = productsString.split(" ");
						for (int i = 0; i < productsToAdd.length; i++)
						{
							products.add(new Product(i, "i", new Vector2(0, 0), (Integer.parseInt(productsToAdd[i]))));
						}
						productsInput.setText("");
					}
					else
					{
						// if there were non-numercial characters in the
						// productSize input, warn the user
						addConsoleItem("Only numerical and spaces are accepted.", "ERROR");
					}
				}
				else
				{
					// if the box volume has not been set, warn the user
					addConsoleItem("Please enter a box size first.", "ERROR");
				}
			}
		});
		// this does the exact same as the above, but instead of when the enter
		// key is pressed, it executes when the productsInputButton is pressed.
		productsInputButton.setOnAction(event ->
		{
			if (boxVolume != 0)
			{
				String productsString = (productsInput.getText());
				if (productsString.matches("[0-9 *]+"))
				{
					String[] productsToAdd = productsString.split(" ");
					for (int i = 0; i < productsToAdd.length; i++)
					{
						products.add(new Product(i, "i", new Vector2(0, 0), (Integer.parseInt(productsToAdd[i]))));
					}
					productsInput.setText("");
				}
				else
				{
					addConsoleItem("Only numerical and spaces are accepted.", "ERROR");
				}
			}
			else
			{
				addConsoleItem("Please enter a box size first.", "ERROR");
			}
		});
	}

	public void AddBoxPane()
	{
		// creates a new pane for the boxes to be drawn in
		boxPane = new BoxPane();
		getChildren().add(boxPane);
	}

	public String printAllProducts(ArrayList<Product> products)
	{
		// prints all products currently in the products array
		String productGegevens;
		for (Product product : products)
		{
			productGegevens = ("Size: " + product.GetSize() + ". ID: " + product.GetId() + ". Coords"
					+ product.GetCoords() + ". Name: " + product.GetName() + ".");
			this.addConsoleItem(productGegevens, "DEBUG");
		}
		return "";
	}

	public boolean testInputs()
	{
		boolean isTrue = (boxVolume == 0 || products.size() == 0) ? false : true;

		if (!isTrue)
		{
			addConsoleItem("Please enter a box size and products!", "ERROR");
		}

		return isTrue;
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

	public void runAlgorithm(BPPAlgorithm algorithmToRun)
	{
		// start the algorithm
		if (testInputs())
		{
			System.out.println("Going!");

			addConsoleItem("Starting.", "INFO");
			// start of algorithm
			long startTime = System.nanoTime();
			ArrayList<Box> returnBoxes = algorithmToRun.getBoxes(products, boxVolume);
			// end of algorithm
			long endTime = System.nanoTime();
			// time needed to execute algorithm = end time - start time. Divided
			// by 1000000 because Nanotime is in nanoseconds.
			long duration = (endTime - startTime) / 1000000;
			addConsoleItem("Duration (milliseconds): " + duration, "INFO");
			boxPane.AddBoxList(returnBoxes);
		}
	}

}
