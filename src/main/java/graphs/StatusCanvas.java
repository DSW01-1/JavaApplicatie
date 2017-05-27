package main.java.graphs;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.main.ConnectionStatus;
import main.java.main.Vector2;

public class StatusCanvas extends Canvas
{
	private GraphicsContext gc;
	private ConnectionStatus currentStatus = ConnectionStatus.INACTIVE;
	private ConnectionStatus newStatus;
	private int canvasSize;

	private int rotationPoint = 360;

	public StatusCanvas(Vector2 pos, int canvasSize)
	{
		super();
		this.canvasSize = canvasSize;
		setLayoutX(pos.getX());
		setLayoutY(pos.getY());
		setWidth(canvasSize * 4);
		setHeight(canvasSize * 4);

		gc = getGraphicsContext2D();

		DrawCircle();
	}

	public void StartAnimation()
	{
		currentStatus = ConnectionStatus.PENDING;
		rotationPoint = 360;
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

				// Time to turn the timer off
				if (newStatus == ConnectionStatus.ACTIVE || newStatus == ConnectionStatus.INACTIVE)
				{
					currentStatus = newStatus;
					timer.cancel();
				}
				DrawCircle();
			}
		};
		timer.schedule(task, new Date(), 20);
	}

	public void DrawCircle()
	{
		gc.clearRect(0, 0, canvasSize, canvasSize);
		gc.setFill(Color.CORNFLOWERBLUE);

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

	public void SetStatus(ConnectionStatus status)
	{
		newStatus = status;
	}
}