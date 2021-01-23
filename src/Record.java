//Alex D'Elia
//251021780
//adelia3
//Oct. 17/19

public class Record {
	private String config;
	private int score;
	
	//Record constructor
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}
	
	//gets the configuration of the Record
	public String getConfig() {
		return config;
	}
	
	//gets the score of the Record
	public int getScore() {
		return score;
	}
}
