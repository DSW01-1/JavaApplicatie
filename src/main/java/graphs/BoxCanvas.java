package main.java.graphs;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.java.constant.ColorConstants;
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
		ArrayList<Product> products = box.GetProductArray();
		Color color;

		for (int i = 0; i < products.size(); i++)
		{
			int relativeProductHeight = (int) ((getHeight() / box.GetTotalSpace()) * products.get(i).GetSizeInInt());
			int x = 0;
			int y = (int) (getHeight() - relativeProductHeight - fillAmount);
			int w = (int) getWidth();
			int h = relativeProductHeight;

			fillAmount += relativeProductHeight;

			// Rainbow all the way
			if (products.size() == 7)
			{
				color = ColorConstants.GetRainbowColor(6 - i);
			}
			else
			{
				color = Color.rgb((int) Math.floor(Math.random() * (256 - 128)) + 128,
						(int) Math.floor(Math.random() * (256 - 128)) + 128,
						(int) Math.floor(Math.random() * (256 - 128)) + 128);
			}

			// Draw the box
			gc.setFill(color);
			gc.fillRect(x, y, w, h);

			// Draw the number
			String fontName = "largeFont";
			gc.setFont(new Font(fontName, 24));
			gc.setFill(Color.BLACK);
			gc.fillText(String.valueOf(products.get(i).GetSizeInInt()), (w / 2) - 8, y + relativeProductHeight / 2);
		}

		gc.setFill(Color.BLACK);
		gc.strokeRect(0, 0, getWidth(), getHeight());

	}
}
