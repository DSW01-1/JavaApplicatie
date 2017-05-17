package main.java.graphs;

import main.java.main.Vector2;

public class Product implements Comparable<Object>
{
	private int id;
	private String name;
	private Vector2 coords;
	private int size;

	public Product(int size)
	{
		this.size = size;
	}

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

	public float GetSize()
	{
		return size;
	}

	@Override
	public int compareTo(Object o)
	{
		int returnedInt = 0;
		Product compared = (Product) o;
		if (this.size < compared.size)
		{
			returnedInt = -1;
		}
		if (this.size == compared.size)
		{
			returnedInt = 0;
		}
		if (this.size > compared.size)
		{
			returnedInt = 1;
		}
		return returnedInt;
	}
}
