package main.java.pane;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import main.java.graphs.Product;
import main.java.main.Vector2;
import main.java.pane.base.StyledPane;

public class AdvancedProductPane extends StyledPane
{
	private Product product;
	private Vector2 size;

	public AdvancedProductPane(Product product, Vector2 size, boolean isEditable)
	{
		this(size);
		this.product = product;

		if (isEditable)
		{
			AddEditableInfoLabels(product);
		}
		else
		{
			AddInfoLabels();
		}
	}

	public AdvancedProductPane(Product product, Vector2 size)
	{
		this(product, size, false);
		this.product = product;

	}

	public AdvancedProductPane(Vector2 size)
	{
		super();
		setId("product");
		this.size = size;
		setPrefSize(size.getX(), size.getY());
	}

	private void AddInfoLabels()
	{
		Label nameLabel = new Label(product.GetName());
		nameLabel.setFont(new Font("Arial", 24));
		nameLabel.setLayoutY(size.getY() / 2);
		nameLabel.setLayoutX(5);
		getChildren().add(nameLabel);

		Label coordLabel = new Label("X: " + product.GetCoords().getX() + ", Y: " + product.GetCoords().getY());
		coordLabel.setFont(new Font("Arial", 18));
		coordLabel.setLayoutY(size.getY() / 2 + 6);
		coordLabel.setLayoutX(120);
		getChildren().add(coordLabel);

		Label sizeLabel = new Label("Size: " + product.GetSize());
		sizeLabel.setFont(new Font("Arial", 18));
		sizeLabel.setLayoutY(size.getY() / 2 + 6);
		sizeLabel.setLayoutX(300);
		getChildren().add(sizeLabel);
	}

	private void AddEditableInfoLabels(Product product)
	{
		TextField changeName = new TextField();
		changeName.setLayoutY(size.getY() / 2);
		changeName.setLayoutX(5);
		changeName.setPrefSize(100, 30);
		getChildren().add(changeName);

		Label labelX = new Label("X:");
		labelX.setLayoutX(120);
		labelX.setLayoutY(size.getY() / 2);
		getChildren().add(labelX);

		TextField changeXCoord = new TextField();
		changeXCoord.setLayoutY(size.getY() / 2);
		changeXCoord.setLayoutX(140);
		changeXCoord.setPrefSize(30, 30);
		getChildren().add(changeXCoord);

		Label labelY = new Label("Y:");
		labelY.setLayoutX(190);
		labelY.setLayoutY(size.getY() / 2);
		getChildren().add(labelY);

		TextField changeYCoord = new TextField();
		changeYCoord.setLayoutY(size.getY() / 2);
		changeYCoord.setLayoutX(210);
		changeYCoord.setPrefSize(30, 30);
		getChildren().add(changeYCoord);

		Label labelSize = new Label("Size:");
		labelSize.setLayoutX(260);
		labelSize.setLayoutY(size.getY() / 2);
		getChildren().add(labelSize);

		TextField changeSize = new TextField();
		changeSize.setLayoutY(size.getY() / 2);
		changeSize.setPrefSize(40, 30);
		changeSize.setLayoutX(300);

		getChildren().add(changeSize);

	}
}
