package main.java.graphs;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

public class BoxCanvas extends Canvas {
    private GraphicsContext gc;
    private Box box;

    public BoxCanvas(Box box) {
        super();
        gc = getGraphicsContext2D();
        Redraw();
    }

    public void Redraw() {
        int fillAmount = 0;

        for (Product product : box.GetProductArray()) {
            gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            int x1 = (int) getWidth();
            int x2 = 0;
            int y1 = (int) (getHeight() - fillAmount);

            fillAmount += ((product.GetSize() / box.getSize()) * getHeight());
            int y2 = (int) (getHeight() - fillAmount);
            gc.fillRect(x1, y1, x2, y2);
        }

        gc.setFill(Color.BLACK);
        gc.strokeRect(0, 0, getWidth(), getHeight());
    }
}
