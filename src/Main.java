public class Main {
	
	public static final String eloSpacer = " | ";
	public static final double K = 32;
	
	public static int[] startingElos, elos;
	
	public static void main(String[] args) {
		DataManager.checkForDB();
		MainWindow.main(null);
		
	}
	
//	static void resetElos() {
//		elos = new int[]{startingElos[0],startingElos[1]};
//	}
//	
//	// Calculates expected score. 
//	// a win = 1, lose = 0; draw = .5. 
//	// This gives what the computer would expect 
//	// the average would be after infinitely many games
//	static double eScore(int e1, int e2) {
//		double denom = 1 + Math.pow(10, (e2-e1)/400.0);
//		return 1/denom;
//	}
//	
//	// This uses the expected score, compares it to the
//	// resulting score and updates elo accordingly
//	// the "k" value used it taken from the International Chess Club
//	static int updateElo(int e1, int e2, double score) {
//		double k = 32.0;
//		double eScore = eScore(e1, e2);
//		return (int) (e1 + k * (score - eScore));	
//	}
//	
//	static int[] twoZero(int[] elos) {
//		elos = updateElos(elos, 1);
//		elos = updateElos(elos, 1);
//		return elos;
//	}
//	static int[] twoOne(int[] elos) {
//		elos = updateElos(elos, 1);
//		elos = updateElos(elos, 0);
//		elos = updateElos(elos, 1);
//		return elos;
//	}
//	
//	
//	// if score = 0, the overall winner lost this round.
//	// if score = 1, the overall winner won.
//	static int[] updateElos(int[] elos, double score) {
//		int temp = updateElo(elos[0], elos[1], score);
//		elos[1] = updateElo(elos[1], elos[0], 1-score);
//		elos[0] = temp;
//		return elos;
//	}
//	
//	static int[] threeZero(int[] elos) {
//		elos = updateElos(elos, 1);
//		elos = updateElos(elos, 1);
//		elos = updateElos(elos, 1);
//		return elos;
//	}
//	
//	static int[] threeOne(int[] elos) {
//		elos = updateElos(elos, 1);		
//		elos = updateElos(elos, 1);		
//		elos = updateElos(elos, 0);		
//		elos = updateElos(elos, 1);
//		return elos;
//	}
//	
//	static int[] threeTwo(int[] elos) {
//		elos = updateElos(elos, 1);		
//		elos = updateElos(elos, 1);		
//		elos = updateElos(elos, 0);
//		elos = updateElos(elos, 0);
//		elos = updateElos(elos, 1);
//		return elos;
//	}

}
