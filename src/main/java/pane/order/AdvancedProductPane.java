package main.java.pane.order;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import main.java.main.Vector2;
import main.java.main.product.Product;
import main.java.pane.base.StyledPane;

public class AdvancedProductPane extends StyledPane
{
	private Product product;
	private Vector2 size;

	public AdvancedProductPane(Product product, Vector2 size)
	{
		super();
		this.product = product;
		setId("product");
		this.size = size;
		setPrefSize(size.getX(), size.getY());
		
		AddInfoLabels();
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

}
