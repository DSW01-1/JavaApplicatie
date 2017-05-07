package main.java.pane;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.constant.Constants;

public class ProductPane extends Pane
{
	private NewOrder parentScript;
	private ProductPane pane;
	public String name;
	
	public ProductPane(String name, String columnSide, NewOrder parentScript)
	{
		this.name = name;
		this.parentScript = parentScript;
		pane = this;		
		
		setId("product");
		setPrefSize(200, 50);

		AddText(name);

		if (columnSide.equals("left"))
		{
			AddShoppingCart();
		}
		else
		{
			AddRemoveButton();
		}

	}

	private void AddRemoveButton()
	{
		ImageView image = new ImageView();
		image.setImage(new Image(Constants.xImage));
		image.setFitWidth(30);
		image.setPreserveRatio(true);
		image.setLayoutX(160);
		image.setLayoutY(5);
		getChildren().add(image);

		Pane clickPane = new Pane();
		clickPane.setPrefSize(50, 45);
		clickPane.setLayoutX(150);
		clickPane.setLayoutY(0);
		clickPane.setCursor(Cursor.HAND);
		clickPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0);");
		getChildren().add(clickPane);

		clickPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				parentScript.MoveProduct("right", pane);
			}
		});		
	}

	private void AddText(String name)
	{
		Text text = new Text(0, 20, name);
		getChildren().add(text);
	}

	private void AddShoppingCart()
	{
		ImageView image = new ImageView();
		image.setImage(new Image(Constants.shoppingCartImage));
		image.setFitWidth(30);
		image.setPreserveRatio(true);
		image.setLayoutX(160);
		image.setLayoutY(5);
		getChildren().add(image);

		Pane clickPane = new Pane();
		clickPane.setPrefSize(50, 45);
		clickPane.setLayoutX(150);
		clickPane.setLayoutY(0);
		clickPane.setCursor(Cursor.HAND);
		clickPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0);");
		getChildren().add(clickPane);

		clickPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				parentScript.MoveProduct("left", pane);
			}
		});
	}
}
