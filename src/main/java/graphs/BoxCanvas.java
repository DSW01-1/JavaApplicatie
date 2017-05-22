package main.java.graphs;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.main.product.Box;
import main.java.main.product.Product;

public class BoxCanvas extends Canvas
{
	private GraphicsContext gc;
	private Box box;

	public BoxCanvas(Box box, int boxPaneSize)
	{
		super();
		this.box = box;
		gc = getGraphicsContext2D();
		setWidth(100);
		setHeight(boxPaneSize - 50);
		Redraw();
	}

	public void Redraw()
	{
		int fillAmount = 0;

		for (Product product : box.GetProductArray())
		{
			int relativeProductHeight = (int) ((getHeight() / box.GetTotalSpace()) * product.GetSizeInInt());
			int x = 0;
			int y = (int) (getHeight() - relativeProductHeight - fillAmount);
			int w = (int) getWidth();
			int h = relativeProductHeight;

			fillAmount += relativeProductHeight;

			Color color = Color.color(Math.random(), Math.random(), Math.random(), 1);

			gc.setFill(color);
			gc.fillRect(x, y, w, h);
		}

		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, getWidth(), getHeight());

	}
}
