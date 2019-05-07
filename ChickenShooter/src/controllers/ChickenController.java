package controllers;

import models.ChickenModel;
import views.ChickenView;

public class ChickenController {
	private ChickenModel chicken;
	private ChickenView view;

	public ChickenController(ChickenModel chicken, ChickenView view) {
		this.chicken = chicken;
		this.view = view;
	}

	public void initController() {
		view.setOnMouseClicked(e -> harmChicken());
	}

	public void harmChicken() {
		chicken.decreaseHealthByOne();
		view.setFill(chicken.getColor());
		view.setRadius(chicken.getSize());
	}
}
