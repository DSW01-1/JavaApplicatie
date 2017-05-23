package main.java.graphs;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
		Color color;
		for (Product product : box.GetProductArray())
		{
			int relativeProductHeight = (int) ((getHeight() / box.GetTotalSpace()) * product.GetSizeInInt());
			int x = 0;
			int y = (int) (getHeight() - relativeProductHeight - fillAmount);
			int w = (int) getWidth();
			int h = relativeProductHeight;

			fillAmount += relativeProductHeight;
			color = Color.rgb((int) Math.floor(Math.random() * (256 - 128)) + 128, (int) Math.floor(Math.random() * (256 - 128)) + 128, (int) Math.floor(Math.random() * (256 - 128)) + 128);
			gc.setFill(color);
			gc.fillRect(x, y, w, h);
			String fontName = "largeFont";
			gc.setFont(new Font(fontName,24));
			gc.setFill(Color.BLACK);
			gc.fillText(String.valueOf(product.GetSizeInInt()),(w/2)-8,y+relativeProductHeight/2);
		}

		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, getWidth(), getHeight());

	}
}
