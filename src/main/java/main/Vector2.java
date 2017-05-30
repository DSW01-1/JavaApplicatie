package main.java.main;

public class Vector2
{
	private int x, y;

	/**
	 * Vector2 is a two dimensional coordinate
	 * 
	 * @param x
	 * @param y
	 */
	public Vector2(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the x point in space
	 * 
	 * @return int
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Set the x point in space
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * Get the y point in space
	 * 
	 * @return int
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Set the y point in space
	 * 
	 * @param y
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public String toString()
	{
		return getX() + "." + getY();
	}

	/**
	 * Compare the current vector2 to another one
	 * 
	 * @param vector
	 * @return true if the same coordinate
	 */
	public boolean Compare(Vector2 vector)
	{
		return getX() == vector.getX() && getY() == vector.getY() ? true : false;
	}

}
