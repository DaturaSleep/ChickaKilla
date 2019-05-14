package main;

import models.GameModel;
import other.GameComplexity;
import views.GameView;
import controllers.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	static GameModel game = new GameModel(GameComplexity.EASY);
	static GameView view = new GameView();
	
	static GameController gC = new GameController(game, view);
	
	@Override
	public void start(Stage stage) {
		
		
		
		
		stage.setTitle("Chick shooter");
		stage.setScene(view);
		stage.show();
		
		gC.startGame();

	}

	public static void main(String[] args) {
		launch();
		
	}
}
