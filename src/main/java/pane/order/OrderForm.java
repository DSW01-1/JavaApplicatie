package main.java.pane.order;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.handler.JsonHandler;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.pane.base.BaseTabPane;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;
import main.java.pane.tab.BPPTab;
import main.java.pane.tab.RobotTab;

public class OrderForm extends GridPane
{
	private TextField[] formItems;
	private VBox rightColumnVBox;
	private String[] labelArray =
	{ "form.firstname", "form.lastname", "form.address", "form.zipcode", "form.city" };
	private TextField[] textFields = new TextField[labelArray.length];

	public OrderForm(VBox rightColumnVBox)
	{
		super();
		this.rightColumnVBox = rightColumnVBox;
		setLayoutX(ScreenProperties.getScreenWidth() / 4 * 2.5f);
		setLayoutY(300);
		setHgap(15);
		setVgap(15);

		AddLabels();
		AddButton();
	}

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

	public void AddButton()
	{
		// The order button, can't be pressed if no items are in the rightcolumn
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

		JsonHandler.SaveOrderToJSON(userData, productID);

		StyledPane[] tabArray =
		{ new RobotTab(), new BPPTab() };

		String[] nameArray =
		{ "Robot & TSP", "BPP" };

		Main.SwitchPane(new OrderTabPane(nameArray, tabArray));
	}

}
