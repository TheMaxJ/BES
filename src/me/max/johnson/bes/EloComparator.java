package me.max.johnson.bes;
import java.util.Comparator;

public class EloComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		Player p1 = new Player(o1);
		Player p2 = new Player(o2);	
		
		if (p1.getElo() > p2.getElo()) {
			return -1;
		}
			
		return 1;
	}

}
