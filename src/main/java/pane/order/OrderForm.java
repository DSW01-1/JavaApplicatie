package main.java.pane.order;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import main.java.constant.Constants;
import main.java.handler.JsonHandler;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.product.Order;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.tab.BPPTab;
import main.java.pane.tab.OrderTabPane;
import main.java.pane.tab.RobotTspTab;

public class OrderForm extends GridPane
{
	private TextField[] formItems;
	private VBox rightColumnVBox, leftColumnVBox;
	private final NewOrder newOrder;
	private String[] labelArray =
	{ "form.firstname", "form.lastname", "form.address", "form.zipcode", "form.city" };
	private TextField[] textFields = new TextField[labelArray.length];
	private boolean isOrderLoaded = false;
	private Order order;

	/**
	 * The order form for the New Order
	 * 
	 * @param newOrder
	 */
	public OrderForm(NewOrder newOrder)
	{
		super();
		this.newOrder = newOrder;
		rightColumnVBox = newOrder.rightColumnVBox;
		leftColumnVBox = newOrder.leftColumnVBox;

		setLayoutX(ScreenProperties.getScreenWidth() / 4 * 2.5f);
		setLayoutY(300);
		setHgap(15);
		setVgap(15);

		AddLabels();
		AddButtons();
	}

	/**
	 * Add the labels
	 */
	public void AddLabels()
	{
		formItems = new TextField[labelArray.length];

		for (int i = 0; i < labelArray.length; i++)
		{
			add(new StyledLabel(labelArray[i]), 0, i);
			TextField textField = new TextField();
			textFields[i] = textField;
			add(textField, 1, i);
			formItems[i] = textField;
		}
	}

	/**
	 * Add the buttons
	 */
	public void AddButtons()
	{
		Button button = new Button("Skip");
		button.setLayoutX(200);
		button.setOnAction(event ->
		{
			SwitchToMainApp();
		});
		add(button, 2, labelArray.length);

		// Load Button
		StyledButton loadOrderButton = new StyledButton("btn.loadOrder");
		loadOrderButton.setOnAction(event ->
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialDirectory(new File(Constants.jsonOrderDirectory));
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Json Files", "*.json"));
			File selectedFile = fileChooser.showOpenDialog(loadOrderButton.getScene().getWindow());
			if (selectedFile != null)
			{
				isOrderLoaded = true;
				order = JsonHandler.FileToJson(selectedFile);

				SwitchToMainApp();
			}
		});
		add(loadOrderButton, 0, labelArray.length);

		// The orderbutton, can't be pressed if no items are in the rightcolumn
		StyledButton orderButton = new StyledButton("btn.order");
		orderButton.setOnAction(event ->
		{
			int proceedAmount = 0;

			for (int i = 0; i < textFields.length; i++)
			{
				if (textFields[i].getText().isEmpty())
				{
					textFields[i].setStyle("-fx-border-color: #FF4943");
				}
				else
				{
					textFields[i].setStyle("-fx-border-color: white");
					proceedAmount++;
				}
			}

			if (rightColumnVBox.getChildren().size() > 0)
			{
				rightColumnVBox.getParent().setStyle("-fx-border-color: white");
				proceedAmount++;
			}
			else
			{
				rightColumnVBox.getParent().setStyle("-fx-border-color: #FF4943");
			}

			if (proceedAmount == 6)
			{
				ProcessForm();
			}
		});
		add(orderButton, 1, labelArray.length);
	}

	/**
	 * Process the form, create a JsonFile for the order
	 */
	private void ProcessForm()
	{
		String[] userData = new String[formItems.length];

		for (int i = 0; i < userData.length; i++)
		{
			userData[i] = formItems[i].getText();
		}

		ArrayList<String> productID = new ArrayList<String>();

		for (int i = 0; i < rightColumnVBox.getChildren().size(); i++)
		{
			ProductPane product = (ProductPane) rightColumnVBox.getChildren().get(i);
			productID.add(Integer.toString(product.GetProduct().GetId()));
		}
		order = JsonHandler.DataToOrder(userData, productID);
		JsonHandler.SaveOrderToJSON(order);

		SwitchToMainApp();
	}

	private void SwitchToMainApp()
	{
		StyledPane[] tabArray =
		{ new RobotTspTab(), new BPPTab() };

		String[] nameArray =
		{ "Robot & TSP", "BPP" };

		OrderTabPane orderTabPane = isOrderLoaded ? new OrderTabPane(nameArray, tabArray, order)
				: new OrderTabPane(nameArray, tabArray);

		Main.SwitchPane(orderTabPane);
	}

}
