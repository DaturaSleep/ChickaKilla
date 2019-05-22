package main;

import models.GameModel;
import models.UserModel;
import other.GameComplexity;
import other.ScoreBoard;
import views.GameView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import controllers.GameController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
	static GameModel game;
	static GameView view;
	static Stage stage;
	static GameController gC;

	@Override
	public void start(Stage stage) {
		Main.stage = stage;

		showMenu();

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		loadUsers();

		launch();

		saveUsers();
	}

	public static void loadUsers() throws ClassNotFoundException, IOException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/res/usermodel.out"));
		ArrayList<UserModel> restoredUsers = new ArrayList<>();

		boolean cont = true;
		try {
			while (cont) {
				System.out.println("1");
				UserModel obj = (UserModel) objectInputStream.readObject();
				if (obj != null)
					restoredUsers.add(obj);
				else
					cont = false;
			}
		} catch (Exception e) {

		}

		objectInputStream.close();

		UserModel.setExtent(restoredUsers);
	}

	public static void saveUsers() throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/res/usermodel.out"));
		for (UserModel user : UserModel.getExtent()) {
			objectOutputStream.writeObject(user);
		}
		objectOutputStream.close();
	}

	public static void startGame() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Select");
		alert.setHeaderText("Choose Difficulty");

		ButtonType easy = new ButtonType("Easy");
		ButtonType medium = new ButtonType("Middle");
		ButtonType hard = new ButtonType("High");
		ButtonType insane = new ButtonType("Insane");

		// Remove default ButtonTypes
		alert.getButtonTypes().clear();

		alert.getButtonTypes().addAll(easy, medium, hard, insane);

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == easy) {
			game = new GameModel(GameComplexity.EASY);
		} else if (option.get() == medium) {
			game = new GameModel(GameComplexity.MEDIUM);
		} else if (option.get() == hard) {
			game = new GameModel(GameComplexity.HARD);
		} else if (option.get() == insane) {
			game = new GameModel(GameComplexity.INSANE);
		}
		GameView.refresh();
		view = new GameView();
		gC = new GameController(game, view);
		stage.setTitle("Chick shooter");
		stage.setScene(view);
		stage.show();
		gC.startGame();

	}

	public static void exitToMenu() {
		stage.close();
		try {
			saveUsers();
		} catch (IOException e) {

			e.printStackTrace();
		}
		showMenu();
	}

	public static void exit() {
		stage.close();
		try {
			saveUsers();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void showMenu() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Menu");
		alert.setHeaderText("Choose option");

		ButtonType start = new ButtonType("Start");
		ButtonType highscore = new ButtonType("Highscore");
		ButtonType exit = new ButtonType("Exit");

		// Remove default ButtonTypes
		
		alert.getButtonTypes().clear();

		alert.getButtonTypes().addAll(start, highscore, exit);

		// option != null.
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == start) {
			startGame();

		} else if (option.get() == highscore) {
			new ScoreBoard();
		} else {
			exit();
		}
	}
}
