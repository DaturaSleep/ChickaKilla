package other;

public enum GameComplexity {
	EASY(8,1500,1),
	MEDIUM(6,1000,2),
	HARD(5,750,3),
	INSANE(3,500,4);
	
	public Integer speed;
	public Integer spawningRate;
	public Integer scoreMultiplyer;
	
	private GameComplexity (Integer speed,Integer spawningRate,Integer scoreMultiplyer) {
		this.scoreMultiplyer = scoreMultiplyer;
		this.speed = speed;
		this.spawningRate = spawningRate;
	}
}
