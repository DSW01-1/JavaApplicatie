package main.java.graphs;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class StatusCanvas extends Canvas
{
	private GraphicsContext gc;
	private Status currentStatus = Status.INACTIVE;
	private int canvasSize = 70;

	private int rotationPoint = 360;
	private int counter = 0;
	private int external = 0;

	public StatusCanvas()
	{
		setLayoutX(100);
		setLayoutY(300);
		setWidth(canvasSize * 4);
		setHeight(canvasSize * 4);

		gc = getGraphicsContext2D();

		DrawCircle();

		setOnMousePressed(event ->
		{
			rotationPoint = 360;
			counter = 0;
			external = 0;

			Timer timer = new Timer();
			TimerTask task = new TimerTask()
			{
				@Override
				public void run()
				{
					rotationPoint -= 4;

					if (rotationPoint == 0)
					{
						rotationPoint = 360;
					}
					counter++;

					if (counter >= 200 && counter <= 220)
					{
						external += 2;
					}

					if (counter >= 220)
					{
						timer.cancel();
					}

					DrawCircle();
				}
			};
			timer.schedule(task, new Date(), 20);
		});
	}

	public void DrawCircle()
	{
		gc.clearRect(0, 0, canvasSize, canvasSize);
		gc.setFill(Color.CORNFLOWERBLUE);
		gc.setLineWidth(2);
		gc.strokeArc(1 + (external / 2), 1 + (external / 2), (canvasSize - 2) - external, (canvasSize - 2) - external,
				rotationPoint, 90, ArcType.OPEN);

		switch (currentStatus)
		{
		case ACTIVE:
			gc.setFill(Color.LIGHTGREEN);
			break;
		case PENDING:
			gc.setFill(Color.CORNFLOWERBLUE);
			break;
		case INACTIVE:
			gc.setFill(Color.INDIANRED);
			break;
		default:
			gc.setFill(Color.RED);
			break;
		}

		int oLW = 13;
		gc.fillOval(oLW / 2, oLW / 2, canvasSize - oLW, canvasSize - oLW);
	}
}

enum Status
{
	ACTIVE, PENDING, INACTIVE
}