package main.java.pane;

import javafx.scene.layout.Pane;

public class Product extends Pane
{
	public Product(String name)
	{
		setId("product");
		setPrefSize(200, 50);
	}
}
