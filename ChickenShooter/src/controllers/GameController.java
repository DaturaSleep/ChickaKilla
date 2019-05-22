package controllers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.Main;
import models.ChickenModel;
import models.GameModel;
import models.UserModel;
import views.ChickenView;
import views.CloudView;
import views.GameView;

public class GameController {
	private GameModel game;
	private GameView view;
	private UserModel user;

	private final Integer BASIC_SPEED_IN_SECONDS;
	private final Integer SPAWNING_RATE;

	public GameController(GameModel game, GameView view) {
		this.game = game;
		this.view = view;
		GameView.initView();
		GameView.setActualLife(game.getPlayerHp() + "");
		GameView.setActualScore("0");
		this.user = new UserModel("Guest");
		this.BASIC_SPEED_IN_SECONDS = this.game.getComplexity().speed;
		this.SPAWNING_RATE = this.game.getComplexity().spawningRate;
	}

	public void startGame() {

		Runnable gameTask = new Runnable() {
			public void run() {
				gameRunningTask();
			}

		};

		Runnable scoreTask = new Runnable() {
			public void run() {
				scoreCounterTask();
			}

		};

		Thread backgroundThread1 = new Thread(gameTask);

		backgroundThread1.setDaemon(true);

		Thread backgroundThread2 = new Thread(scoreTask);

		backgroundThread2.setDaemon(true);

		backgroundThread1.start();
		backgroundThread2.start();

	}

	private void scoreCounterTask() {
		while (!game.gameIsOver()) {
			try {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						user.addScore(1 * game.getComplexity().scoreMultiplyer);
						GameView.setActualScore(user.getScore() + "");
					}
				});

				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void gameRunningTask() {
		while (!game.gameIsOver()) {
			try {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if(ThreadLocalRandom.current().nextInt(0, 3) == 1) {
							
							spawnCloud();
						}
						spawnChicken(ThreadLocalRandom.current().nextInt(1, 5));
					}
				});

				TimeUnit.MILLISECONDS.sleep(SPAWNING_RATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String name = JOptionPane.showInputDialog("Game Over!! Enter your name", "");
		user.setName(name);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Main.exitToMenu();
			}
		});
		
	}
	
	public void spawnCloud() {
		Integer height = ThreadLocalRandom.current().nextInt(70, 120);
		Integer width = ThreadLocalRandom.current().nextInt(140, 180);
		CloudView cloud = new CloudView(width,height,Color.DARKBLUE);
		
		cloud.relocate(0, ThreadLocalRandom.current().nextDouble(10, view.getBounds().getMaxY() - 80));
		GameView.getCanvas().getChildren().add(cloud);
		KeyValue flyingCloud = new KeyValue(cloud.layoutXProperty(), view.getBounds().getMaxX() - cloud.getX());
		
		Timeline line = new Timeline(new KeyFrame(Duration.seconds(BASIC_SPEED_IN_SECONDS), flyingCloud));
		line.setOnFinished(e -> {
		

			GameView.getCanvas().getChildren().remove(cloud);
		});
		line.play();
	}
	
	
	public void spawnChicken(Integer health) {
		ChickenModel chicken = new ChickenModel(health);
		ChickenView ball = new ChickenView(chicken.getColor(), chicken.getSize());

		new ChickenController(chicken, ball).initController();

		ball.relocate(0, ThreadLocalRandom.current().nextDouble(10, view.getBounds().getMaxY() - 80));
		GameView.getCanvas().getChildren().add(ball);
		KeyValue flyingChicken = new KeyValue(ball.layoutXProperty(), view.getBounds().getMaxX() - ball.getRadius());

		Timeline line = new Timeline(new KeyFrame(Duration.seconds(health + BASIC_SPEED_IN_SECONDS), flyingChicken));
		line.setOnFinished(e -> {
			if (chicken.getHealth() == 0) {
				System.out.println("Dead one");

			} else {
				System.out.println("Alive one");
				game.decreasePlayerHpByOne();
				GameView.setActualLife(game.getPlayerHp() + "");
			}

			GameView.getCanvas().getChildren().remove(ball);
		});
		line.play();

	}

}
