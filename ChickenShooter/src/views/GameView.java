package views;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameView extends Scene {
	private static Pane canvas;
	private static Label score;
	private static Label actualScore;
	private static Label life;
	private static Label actualLife;
	private static Label blankSpacing;
	private static HBox hbox;

	private static BorderPane border = new BorderPane();

	public GameView() {
		super(border, 800, 600);

	}

	public static void initView() {
		canvas = new Pane();
		score = new Label("Score: ");
		actualScore = new Label("0");
		life = new Label("Lifes: ");
		actualLife = new Label("");
		blankSpacing = new Label("           ");
		hbox = new HBox();
		canvas.setStyle("-fx-background-color:white");
		border.setCenter(canvas);
		hbox.setSpacing(5);
		hbox.getChildren().addAll(score, actualScore, blankSpacing, life, actualLife);
		border.setBottom(hbox);
	}

	public static void refresh() {
		border = new BorderPane();
	}

	public static Pane getCanvas() {
		return canvas;
	}

	public static void setCanvas(Pane canvas) {
		GameView.canvas = canvas;
	}

	public Bounds getBounds() {
		return canvas.getBoundsInParent();
	}

	public static Label getActualScore() {
		return actualScore;
	}

	public static void setActualScore(String actualScore) {
		GameView.actualScore.setText(actualScore);
	}

	public static Label getActualLife() {
		return actualLife;
	}

	public static void setActualLife(String actualLife) {
		GameView.actualLife.setText(actualLife);
	}

}
