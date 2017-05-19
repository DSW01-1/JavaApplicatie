package main.java.graphs;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.main.ScreenProperties;
import main.java.main.product.Box;
import main.java.main.product.Product;

public class BoxCanvas extends Canvas
{
	private GraphicsContext gc;
	private Box box;

	public BoxCanvas(Box box)
	{
		super();
		this.box = box;
		gc = getGraphicsContext2D();
		setWidth(100);
		setHeight(ScreenProperties.getScreenHeight() / 3);
		Redraw();
	}

	public void Redraw()
	{
		int fillAmount = 0;

		for (Product product : box.GetProductArray())
		{
			int relativeProductHeight = (int) ((product.GetSize() / box.GetVolume())
					* (ScreenProperties.getScreenHeight() / 3));
			int x = 0;
			int y = (int) (getHeight() - relativeProductHeight - fillAmount);
			int w = (int) getWidth();
			int h = relativeProductHeight;

			System.out.println("Height: " + relativeProductHeight);
			System.out.println(fillAmount);

			fillAmount += ((product.GetSize() / box.GetVolume()) * (ScreenProperties.getScreenHeight() / 3));

			gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
			gc.fillRect(x, y, w, h);
		}

		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, getWidth(), getHeight());
	}
}
