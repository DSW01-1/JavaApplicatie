package main.java.pane.simulation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import main.java.algorithms.bpp.NextFit;
import main.java.graphs.Product;
import main.java.main.Language;
import main.java.main.Main;
import main.java.main.Vector2;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;

import java.util.ArrayList;

public class BppSimulation extends StyledPane
{
	private TextField boxSize;
	private ArrayList<Product> products = new ArrayList<Product>();

	public BppSimulation()
	{
		// back to main menu
		StyledButton orderHistoryButton = new StyledButton(Language.getTranslation("btn.backToMainMenu"),
				new Vector2(15, 15), new Vector2(200, 50));
		orderHistoryButton.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(orderHistoryButton);

		// textfield to enter product size
		TextField productSize = new TextField();
		getChildren().add(productSize);

		// button to confirm adding product, and check if int.
		StyledButton confirmProduct = new StyledButton("Confirm product", new Vector2(200, 50), new Vector2(250, 100));
		confirmProduct.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				products.add(new Product(Integer.parseInt(productSize.getText())));
				productSize.setText("");
			}
		});
		getChildren().add(confirmProduct);

		NextFit nextFit = new NextFit();
		StyledButton startNextFit = new StyledButton("Start Next Fit", new Vector2(500, 500), new Vector2(750, 750));
		startNextFit.setOnAction(event ->
		{
			nextFit.executeNextFit(products);
			products.clear();
			nextFit.returnBoxes.clear();
		});
		getChildren().add(startNextFit);
	}

}
