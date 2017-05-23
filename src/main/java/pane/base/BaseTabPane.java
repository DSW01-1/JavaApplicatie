package main.java.pane.base;

public class BaseTabPane extends StyledPane
{
	private StyledPane[] tabArray;
	private String[] name;

	public BaseTabPane(String name[], StyledPane[] tabArray)
	{
		super();
		this.name = name;
		this.tabArray = tabArray;

	}

	@Override
	public void InitPane()
	{
		CreateTabPane();
	}

	/**
	 * Create the tabpane
	 */
	protected void CreateTabPane()
	{
		StyledTabPane tabPane = new StyledTabPane();

		for (int i = 0; i < tabArray.length; i++)
		{
			StyledTab tab = new StyledTab(name[i]);
			tab.setContent(tabArray[i]);
			tabPane.getTabs().add(tab);
		}

		getChildren().add(tabPane);
	}
}
