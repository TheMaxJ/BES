package me.max.johnson.bes;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class EventHandler {
	
	@SuppressWarnings("unchecked")
	public static void listRightClicked(MouseEvent event) {
		JList<String> list = (JList<String>) event.getComponent();
		int row = list.locationToIndex(event.getPoint());
        list.setSelectedIndex(row);  
	}
	
	public static void saveClicked() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < MainWindow.getPlayerList().size(); i++) {
			players.add(new Player(MainWindow.getPlayerList().getElementAt(i)));
		}
		DataManager.savePlayers(players);
	}
	
	public static void newMatchClicked() {
		if (MainWindow.getPlayerList().isEmpty()) return;
		String name1 = "";
		String name2 = "";
		boolean cont = false;
		do {
			name1 = JOptionPane.showInputDialog("First player's name?");
			if (name1 == null) return;
			cont = MainWindow.contains(name1);
			if (!cont) {
				JOptionPane.showMessageDialog(null, "Could not find that player name.");
			}
		} while (!cont);
		cont = false;
		do {
			if (MainWindow.getPlayerList().isEmpty()) return;
			name2 = JOptionPane.showInputDialog("Second player's name?");
			if (name2 == null) return;
			cont = MainWindow.contains(name2);
			if (!cont) {
				JOptionPane.showMessageDialog(null, "Could not find that player name.");
			}
		} while (!cont);
		
		Player p1 = MainWindow.getPlayer(name1);
		Player p2 = MainWindow.getPlayer(name2);
		
		String outcomes[] = {name1, name2, "Draw"};
		
		int i = JOptionPane.showOptionDialog(null, "Who won?", "Outcome", JOptionPane.DEFAULT_OPTION, 0, null, outcomes, outcomes[0]);
		double score = .5;
		if (i == 0) {
			score = 1;
		} else if (i == 1) {
			score = 0;
		} else if (i == -1) {
			return;
		}
		System.out.println(i);
		p1.updateElo(p2, score);
		p2.updateElo(p1, 1-score);
		MainWindow.removePlayer(p1);
		MainWindow.removePlayer(p2);
		p1.pushElo();
		p2.pushElo();
		MainWindow.addPlayer(p1);
		MainWindow.addPlayer(p2);
	}
	
	public static void addPlayerClicked() {
		String newPlayer = JOptionPane.showInputDialog("New player name");
		MainWindow.getPlayerList().addElement(newPlayer + Main.eloSpacer + "1000");
	}
}
