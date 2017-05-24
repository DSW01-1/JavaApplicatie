package main.java.pane.base;

import main.java.constant.Constants;
import main.java.main.Main;
import main.java.pane.MainMenu;

public class BackToMainMenuButton extends StyledButton
{

	public BackToMainMenuButton()
	{
		super("btn.backToMainMenu", Constants.backTMMBP, Constants.backTMMBS);
		setOnAction(event ->
		{
			Main.SwitchPane(new MainMenu());
		});
	}
}
