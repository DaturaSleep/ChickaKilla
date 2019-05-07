package views;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameView extends Scene {
	private static Pane canvas = new Pane();
	private Bounds bounds = canvas.getBoundsInLocal();

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
