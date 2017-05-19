package main.java.main.product;

import main.java.main.Vector2;

public class Product implements Comparable<Object>
{
	private int id;
	private String name;
	private Vector2 coords;
	private int size;

	/**
	 * General constructor
	 * 
	 * @param id
	 * @param name
	 * @param coords
	 * @param size
	 */
	public Product(int id, String name, Vector2 coords, int size)
	{
		this(name, coords, size);
		this.id = id;
	}

	/**
	 * Constructor needed to create new products in the database, id is set by
	 * database
	 * 
	 * @param name
	 * @param coords
	 * @param size
	 */
	public Product(String name, Vector2 coords, int size)
	{
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

	public int GetSizeInInt()
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

	public boolean CheckCoords(Vector2 tileCoord)
	{
		return GetCoords().getX() + 1 == tileCoord.getX() && GetCoords().getY() + 1 == tileCoord.getY() ? true : false;
	}
}
