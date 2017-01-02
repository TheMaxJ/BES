
public class Player {
	int elo;
	int newElo;
	String name;
	
	public Player(String s) {
		String[] split = s.split(Main.eloSpacer);
		name = split[0];
		
		elo = Integer.valueOf(split[2]);
	}
	
	public int getElo() {
		return this.elo;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setElo(int elo) {
		this.elo = elo;
	}
	
	public void updateElo(Player opp, double score) {
		double eScore = 1/(1 + Math.pow(10, (opp.getElo()-elo)/400.0));
		newElo = (int) (elo + Main.K * (score - eScore));	
	}
	
	public void pushElo() {
		elo = newElo;
	}
	
	public String toString() {
		return name + Main.eloSpacer + elo;
	}
}
