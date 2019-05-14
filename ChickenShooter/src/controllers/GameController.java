package controllers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.util.Duration;
import models.ChickenModel;
import models.GameModel;
import views.ChickenView;
import views.GameView;

public class GameController {
	private GameModel game;
	private GameView view;

	private final Integer BASIC_SPEED_IN_SECONDS;
	private final Integer SPAWNING_RATE;
	
	private Task task = new Task<Void>() {
		@Override
		public Void call() {
			while (true) {
				spawnChicken(ThreadLocalRandom.current().nextInt(1, 5));
			}

		}
	};

	public GameController(GameModel game, GameView view) {
		this.game = game;
		this.view = view;
		this.BASIC_SPEED_IN_SECONDS = this.game.getComplexity().speed;
		this.SPAWNING_RATE = this.game.getComplexity().spawningRate;
	}

	public void startGame() {

		Runnable task = new Runnable() {
			public void run() {
				runTask();
			}

		};

		Thread backgroundThread = new Thread(task);

		backgroundThread.setDaemon(true);

		backgroundThread.start();

	}

	private void runTask() {
		while (!game.gameIsOver()) {
			try {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						spawnChicken(ThreadLocalRandom.current().nextInt(1, 5));
					}
				});

				TimeUnit.MILLISECONDS.sleep(SPAWNING_RATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void spawnChicken(Integer health) {
		ChickenModel chicken = new ChickenModel(health);
		ChickenView ball = new ChickenView(chicken.getColor(), chicken.getSize());

		new ChickenController(chicken, ball).initController();

		ball.relocate(0, ThreadLocalRandom.current().nextDouble(10, view.getBounds().getMaxY() - 30));
		GameView.getCanvas().getChildren().add(ball);
		KeyValue flyingChicken = new KeyValue(ball.layoutXProperty(), view.getBounds().getMaxX() - ball.getRadius());

		Timeline line = new Timeline(new KeyFrame(Duration.seconds(health + BASIC_SPEED_IN_SECONDS), flyingChicken));
		line.setOnFinished(e -> {
			if (chicken.getHealth() == 0) {
				System.out.println("Dead one");
			} else {
				System.out.println("Alive one");
			}

			GameView.getCanvas().getChildren().remove(ball);
		});
		line.play();

	}

}
