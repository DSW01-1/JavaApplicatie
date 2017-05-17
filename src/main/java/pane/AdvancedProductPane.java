package main.java.pane;

import javafx.scene.control.Label;
import main.java.graphs.Product;
import main.java.main.Vector2;
import main.java.pane.base.StyledPane;

public class AdvancedProductPane extends StyledPane
{
	private Product product;

	public AdvancedProductPane(Product product, Vector2 size)
	{
		super();
		this.product = product;
		setPrefSize(size.getX(), size.getY());
		AddInfoLabels();
	}

	private void AddInfoLabels()
	{
		Label label = new Label(product.GetName());
		getChildren().add(label);
	}
}
