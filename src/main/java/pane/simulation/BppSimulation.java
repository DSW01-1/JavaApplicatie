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
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;

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
		getStyleClass().add("background");
		AddBoxPane();
	}

	// create algorithms
	NextFit nextFit = new NextFit();
	FirstFit firstFit = new FirstFit();
	BruteForce bruteForce = new BruteForce();
	DecreasingFirstFit decreasingFirstFit = new DecreasingFirstFit();

	@Override
	public void ExecuteAlgorithmOne()
	{
		if (testInputs())
		{
			consolePane.getItems().clear();
			nextFit.getBoxes(products);
			products.clear();
			addConsoleItem("Boxes needed: " + nextFit.returnBoxes.size(), "DEBUG");
			boxPane.AddBoxList(nextFit.returnBoxes);
		}
	}

	@Override
	public void ExecuteAlgorithmTwo()
	{
		if (testInputs())
		{
			consolePane.getItems().clear();
			firstFit.getBoxes(products);
			products.clear();
			addConsoleItem("Boxes needed: " + firstFit.returnBoxes.size(), "DEBUG");
			boxPane.AddBoxList(firstFit.returnBoxes);
		}
	}

	@Override
	public void ExecuteAlgorithmThree()
	{
		if (testInputs())
		{
			consolePane.getItems().clear();
			bruteForce.getBoxes(products);
			products.clear();
			addConsoleItem("Boxes needed: " + bruteForce.returnBoxes.size(), "DEBUG");
			boxPane.AddBoxList(bruteForce.returnBoxes);
		}
		consolePane.getItems().clear();
		bruteForce.getBoxes(products);
		products.clear();
	}

	@Override
	public void ExecuteAlgorithmFour()
	{
		if (testInputs())
		{
			consolePane.getItems().clear();
			decreasingFirstFit.getBoxes(products);
			products.clear();
			addConsoleItem("Boxes needed: " + decreasingFirstFit.returnBoxes.size(), "DEBUG");
			boxPane.AddBoxList(decreasingFirstFit.returnBoxes);
		}
	}

	public void AddControls()
	{
		String[] algorithmNames =
		{ "btn.nextFit", "btn.firstFit", "btn.bruteForce", "btn.ownAlgorithm" };

		getChildren().add(new SimulationControls(algorithmNames, this));
	}

	private void AddInputFields()
	{
		StyledLabel boxSizeInputLabel = new StyledLabel("lbl.boxSize",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) - 125,
						ScreenProperties.getScreenHeight() - 192),
				16);
		getChildren().add(boxSizeInputLabel);

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
				boxVolume = (Integer.parseInt(boxSizeInput.getText()));
				boxSizeInput.setText("");
			}
		});

		StyledButton boxSizeInputButton = new StyledButton("btn.confirmBox",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) + 100,
						ScreenProperties.getScreenHeight() - 200));
		getChildren().add(boxSizeInputButton);
		boxSizeInputButton.setOnAction(event ->
		{
			nextFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
			firstFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
			bruteForce.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
			decreasingFirstFit.boxVolume = (Integer.parseInt(boxSizeInput.getText()));
			boxVolume = (Integer.parseInt(boxSizeInput.getText()));
			boxSizeInput.setText("");
		});

		// products input
		StyledLabel productsInputLabel = new StyledLabel("lbl.productSize",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) - 145,
						ScreenProperties.getScreenHeight() - 148),
				16);
		getChildren().add(productsInputLabel);

		TextField productsInput = new TextField();
		productsInput.setLayoutX(ScreenProperties.getScreenWidth() / 2 - (ScreenProperties.getScreenWidth() / 3) / 2);
		productsInput.setLayoutY(ScreenProperties.getScreenHeight() - 150);
		productsInput.setPrefSize(ScreenProperties.getScreenWidth() / 3, 30);
		getChildren().add(productsInput);

		StyledButton productsInputButton = new StyledButton("btn.confirmProduct",
				new Vector2((ScreenProperties.getScreenWidth() / 2) - (ScreenProperties.getScreenWidth() / 3 / 2) + 200,
						ScreenProperties.getScreenHeight() - 200));
		getChildren().add(productsInputButton);
		productsInput.setOnKeyPressed((KeyEvent event) ->
		{
			if (event.getCode() == KeyCode.ENTER)
			{
				if (!(boxVolume == 0))
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
						// TODO Geen System.out
						System.out.println("Input error");
					}
				}
				else
				{
					addConsoleItem("Please enter a box size first.", "ERROR");
					// TODO Geen System.out
					System.out.println("Box size error");
				}
			}
		});
		productsInputButton.setOnAction(event ->
		{
			if (!(boxVolume == 0))
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
					// TODO Geen System.out
					System.out.println("Input error");
				}
			}
			else
			{
				addConsoleItem("Please enter a box size first.", "ERROR");
				// TODO Geen System.out
				System.out.println("Box size error");
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

	public boolean testInputs()
	{
		if (boxVolume == 0 || products.size() == 0)
		{
			addConsoleItem("Please enter a box size and products!", "ERROR");
			return false;
		}
		else
		{
			return true;
		}
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
