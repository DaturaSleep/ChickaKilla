package models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserModel implements Comparable<UserModel>,Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer highScore = 0;

	private final Integer NAME_MAX_SIZE = 25;
	private final String NAME_ALLOWED_SYMBOLS = "[a-zA-Z]+";
	
	private static ArrayList<UserModel> extent = new ArrayList<>();
	
	public UserModel(String name) {
		this.setName(name);
		getExtent().add(this);
	}

	public void setHighScore(Integer highScore) {
		if (this.highScore < 0) {
			throw new IllegalArgumentException("Highscore can't be less than 0");
		} else if (this.highScore == null) {
			throw new IllegalArgumentException("Highscore can't be null");
		} else {
			this.highScore = highScore;
		}
	}

	public String getName() {
		return this.name;
	}

	public void addScore(Integer addedValue) {
		this.highScore = this.highScore + addedValue;
	}

	public Integer getScore() {
		return this.highScore;
	}

	public void setName(String name) {
		if (!name.matches(NAME_ALLOWED_SYMBOLS)) {
			throw new IllegalArgumentException(
					String.format("The name consist illegal chars, allowed chars: %s", NAME_ALLOWED_SYMBOLS));
		} else if (name.length() > NAME_MAX_SIZE) {
			throw new IllegalArgumentException(String.format("The name is too long max chars is: %d", NAME_MAX_SIZE));
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("Name can't be empty");
		} else {
			this.name = name;
		}
	}

	public static ArrayList<UserModel> getExtent() {
		return extent;
	}

	public static void setExtent(ArrayList<UserModel> extent) {
		UserModel.extent = extent;
	}

	

	

	@Override
	public int compareTo(UserModel otherUser) {
		if(otherUser.highScore == this.highScore) {
			return 0;
		}else if(otherUser.highScore<this.highScore) {
			return -1;
		}else  {
			return 1;
		}
		
	}
}
