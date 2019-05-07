package models;

public class UserModel {
	private String name;
	private Integer highScore = 0;

	private final Integer NAME_MAX_SIZE = 25;
	private final String NAME_ALLOWED_SYMBOLS = "[a-zA-Z]+";

	public UserModel(String name) {
		this.setName(name);
	}

	public void setHighScore(Integer highScore) {
		if(this.highScore < 0) {
			throw new IllegalArgumentException("Highscore can't be less than 0");
		}else if(this.highScore == null) {
			throw new IllegalArgumentException("Highscore can't be null");
		}else {
			this.highScore = highScore;
		}
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		if(!name.matches(NAME_ALLOWED_SYMBOLS)) {
			throw new IllegalArgumentException(String.format("The name consist illegal chars, allowed chars: %s", NAME_ALLOWED_SYMBOLS));
		}
		else if (name.length() > NAME_MAX_SIZE) {
			throw new IllegalArgumentException(String.format("The name is too long max chars is: %d", NAME_MAX_SIZE));
		} else if (name.isEmpty()) {
			throw new IllegalArgumentException("Name can't be empty");
		} else {
			this.name = name;
		}
	}
}
