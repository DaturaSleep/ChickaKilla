package views;

import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameView extends Scene {
	private static Pane canvas = new Pane();
	private Bounds bounds = canvas.getBoundsInLocal();
	private Label scoreLabel = new Label(); 
			
	public GameView() {
		super(canvas, 800, 600);
		
	}

	public static Pane getCanvas() {
		return canvas;
	}

	public static void setCanvas(Pane canvas) {
		GameView.canvas = canvas;
	}

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

}
