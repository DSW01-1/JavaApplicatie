package main.java.pane;

import javafx.scene.layout.HBox;
import main.java.main.Vector2;
import main.java.pane.base.StyledPane;
import main.java.pane.base.StyledScrollPane;

public class DatabaseManagePane extends StyledPane
{
	public DatabaseManagePane()
	{
		super();

		AddScrollPane();
	}

	public void AddScrollPane()
	{
		HBox hbox = new HBox();

		Vector2 pos = new Vector2(2, 2);
		Vector2 size = new Vector2(2, 2);
		
		StyledScrollPane scrollPane = new StyledScrollPane(hbox, pos, size );
	}
}
