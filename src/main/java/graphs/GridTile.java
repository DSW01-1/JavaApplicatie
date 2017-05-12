package main.java.graphs;

import main.java.main.Vector2;

public class GridTile
{
	private Vector2 pos;
	private boolean isSelected;

	public GridTile(Vector2 pos)
	{
		this.pos = pos;
		isSelected = false;
	}
	
	public int getXcoord()
	{
		return (int) pos.getX();
	}
	
	public int getYcoord()
	{
		return (int) pos.getY();
	}

	public boolean IsSelected()
	{
		return isSelected;
	}
	
	public void SetSelected(boolean selected)
	{
		isSelected = selected;
	}
}
