package other;

public enum GameComplexity {
	EASY(8,1500),
	MEDIUM(6,1000),
	HARD(5,750),
	INSANE(3,500);
	
	public Integer speed;
	public Integer spawningRate;
	
	private GameComplexity (Integer speed,Integer spawningRate) {
		this.speed = speed;
		this.spawningRate = spawningRate;
	}
}
