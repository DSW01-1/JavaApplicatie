package main.java.pane.tab;

import main.java.graphs.Grid;
import main.java.pane.base.StyledPane;

public class RobotTab extends StyledPane
{
	public RobotTab()
	{
		getChildren().add(new Grid());
	}
}
