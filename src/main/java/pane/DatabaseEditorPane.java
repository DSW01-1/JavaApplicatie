package main.java.pane;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledLabel;
import main.java.pane.base.StyledPane;

public class DatabaseEditorPane extends StyledPane
{
	private static int paneHeight = ScreenProperties.getScreenHeight() / 2;
	private Product product;

	public DatabaseEditorPane(Product product)
	{
		super(new Vector2(ScreenProperties.getScreenWidth() - 500, paneHeight - (paneHeight / 3) * 2));

		AddName();
		AddCoord();
		AddSize();
		AddConfirmButton();
	}

	private void AddName()
	{
		StyledLabel nameLabel = new StyledLabel("lbl.name", new Vector2(0, 0));
		getChildren().add(nameLabel);

		TextField nameField = new TextField(product.GetName());
		nameField.setLayoutX(100);
		nameField.setLayoutY(0);
		getChildren().add(nameField);
	}

	private void AddCoord()
	{
		StyledLabel coordNameLabel = new StyledLabel("lbl.coord", new Vector2(0, 50));
		getChildren().add(coordNameLabel);

		Label coordLabel = new Label(product.GetCoords().getX() + " : " + product.GetCoords().getY());
		coordLabel.setLayoutX(100);
		coordLabel.setLayoutY(50);
		getChildren().add(coordLabel);

		StyledButton coordButton = new StyledButton("btn.changeCoord", new Vector2(80, 50));
		getChildren().add(coordButton);
	}

	private void AddSize()
	{
		ChoiceBox<String> sizeChoiceBox = new ChoiceBox<String>();
		sizeChoiceBox.setLayoutX(100);
		sizeChoiceBox.setLayoutY(100);
		getChildren().add(sizeChoiceBox);
	}

	private String[] GetChoiceOptions(int theOptions)
	{
		String[] options = new String[theOptions];

		for (int i = 1; i <= theOptions; i++)
		{
			options[i - 1] = Integer.toString(i);
		}
		return options;
	}

	private void AddConfirmButton()
	{
		String buttonName = product.GetId() == 0 ? "btn.addProduct" : "btn.confirm";

		StyledButton confirmButton = new StyledButton(buttonName, new Vector2(280, 200));
		getChildren().add(confirmButton);
	}
}
