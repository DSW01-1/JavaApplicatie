package main.java.pane.base;

import javafx.scene.control.TabPane;
import main.java.main.ScreenProperties;

public class StyledTabPane extends TabPane
{
	
	public StyledTabPane()
	{
		super();
		setId("styledTabPane");
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		setPrefWidth(ScreenProperties.getScreenWidth());
	}
	
	public void AddTab(StyledTab tab)
	{
		getTabs().add(tab);
	}
}
