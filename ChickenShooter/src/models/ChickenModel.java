package models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class ChickenModel {
	private Integer health;
	private Color color;
	private Double size;

	// static final Map of all possible health-colors
	private static final Map<Integer, Color> HEALTH_COLOR;
	static {
		Map<Integer, Color> wrtMap = new HashMap<>();
		wrtMap.put(0, null);
		wrtMap.put(1, Color.BLUE);
		wrtMap.put(2, Color.BLUEVIOLET);
		wrtMap.put(3, Color.BROWN);
		wrtMap.put(4, Color.GOLD);
		wrtMap.put(5,Color.ORANGERED);
		HEALTH_COLOR = Collections.unmodifiableMap(wrtMap);
	}
	private static final Integer MAX_HEALTH = 5;
	private static final Integer MIN_HEALTH = 0;
	public final static Double BASIC_SIZE = 30.0;

	public ChickenModel(Integer health) {
		this.setHealth(health);
		this.setSize(health);
		System.out.println(size);
	}

	public void decreaseHealthByOne() {
		if (this.health == null) {
			throw new NullPointerException(String.format("Chicken is already dead, calm down!!"));
		} else if (this.health - 1 == MIN_HEALTH) {
			destroyChicken();
		} else {
			setHealth(this.health - 1);
			setSize(this.health);
		}
	}

	private void destroyChicken() {
		this.color = null;
		this.health = 0;
		System.out.println("You have killed a chicken, how dare you?");
	}

	public boolean isGoingToDieInNextTurn() {
		if (this.health - 1 == 0) {
			return true;
		} else
			return false;
	}

	public Integer getHealth() {
		return this.health;
	}

	public Double getSize() {
		return this.size;
	}

	private void setSize(Integer health) {
		this.size = BASIC_SIZE - health*4;

	}

	private void setHealth(Integer health) {
		if (health == null) {
			throw new IllegalArgumentException("Health can't be null");
		} else if (health > MAX_HEALTH) {
			throw new IllegalArgumentException(String.format("Health is too big max is: %d", MAX_HEALTH));
		} else if (health < MIN_HEALTH) {
			throw new IllegalArgumentException(String.format("Health is too small min is: %d", MIN_HEALTH));
		} else {
			this.health = health;
			setColor(health);
			setSize(health);
		}
	}

	public Color getColor() {
		return this.color;
	}

	private void setColor(Integer health) {
		if (health == null) {
			throw new IllegalArgumentException("Health can't be null");
		} else if (health > MAX_HEALTH) {
			throw new IllegalArgumentException(String.format("Health is too big max is: %d", MAX_HEALTH));
		} else if (health < MIN_HEALTH) {
			throw new IllegalArgumentException(String.format("Health is too small min is: %d", MIN_HEALTH));
		} else {
			this.color = HEALTH_COLOR.get(health);
		}
	}
}
