package main.java.pane.order;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import main.java.constant.Constants;
import main.java.database.DatabaseOrder;
import main.java.main.Main;
import main.java.main.ScreenProperties;
import main.java.main.Vector2;
import main.java.main.product.CustomerInfo;
import main.java.main.product.Order;
import main.java.pane.MainMenu;
import main.java.pane.base.StyledButton;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class OrderHistory extends StyledPane
{
	public OrderHistory()
	{
		super();
	}

	@Override
	public void InitPane()
	{
		ArrayList <CustomerInfo> customers = DatabaseOrder.getAllCustomers();
		// Back to menu button
		StyledButton goBackToMenu = new StyledButton("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		goBackToMenu.setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
		getChildren().add(goBackToMenu);

		VBox vbox = new VBox();
		vbox.maxWidth(ScreenProperties.getScreenWidth()/4);
		vbox.maxHeight(400);
		vbox.setLayoutX(160);
		vbox.setLayoutY(270);
		vbox.setStyle("-fx-border-width: 2;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;");
		for(CustomerInfo info:customers){
			vbox.getChildren().add(new PersonPane(info));
		}
		getChildren().add(vbox);

		VBox vboxOrder = new VBox();
		vboxOrder.maxWidth(ScreenProperties.getScreenWidth()/4);
		vboxOrder.maxHeight(400);
		vboxOrder.setLayoutX(700);
		vboxOrder.setLayoutY(270);
		vboxOrder.setStyle("-fx-border-width: 2;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;");
		getChildren().add(vboxOrder);
	}

	private void createPersonPane(){

	}

	public void getOrders(int customerId){

	}



}
