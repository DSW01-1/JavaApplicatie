package main.java.main.product;

import main.java.main.Vector2;

public class Product
{
	private int id;
	private String name;
	private Vector2 coords;
	private int size;

	public Product(int id, String name, Vector2 coords, int size)
	{
		this.id = id;
		this.name = name;
		this.coords = coords;
		this.size = size;
	}
	
	public String GetName()
	{
		return name;
	}

	public int GetId()
	{
		return id;
	}

	public Vector2 GetCoords()
	{
		return coords;
	}
	
	public int GetSize()
	{
		return size;
	}
}
