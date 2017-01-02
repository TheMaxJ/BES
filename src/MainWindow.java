import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainWindow {

	private JFrame frame;
	private static DefaultListModel<String> playerList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerList = new DefaultListModel<String>();

		JList<String> list = new JList<String>(playerList);
		ListPopup popup = new ListPopup();
		popup.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				doPop(e);
		    }

		    private void doPop(MouseEvent e){

				
		    }
		});
		//list.setComponentPopupMenu(new ListPopup());
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			        list.setSelectedIndex(list.locationToIndex(e.getPoint())); //select the item
			        popup.show(list, e.getX(), e.getY()); //and show the menu
			    }
			}
			
			@Override
			public void mouseClicked(MouseEvent event) {
				
				if(SwingUtilities.isRightMouseButton(event)) {
					rightClickList(event);
				}
			}
		});
		
		List<Player> loaded = DataManager.loadPlayers();
		for (Player player : loaded) {
			addPlayer(player);
		}
		frame.getContentPane().add(list, BorderLayout.WEST);
		
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addPlayerPressed();
			}
		});
		frame.getContentPane().add(btnAddPlayer, BorderLayout.EAST);
		
		JButton btnNewMatch = new JButton("New Match");
		btnNewMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newMatchPressed();
			}
		});
		frame.getContentPane().add(btnNewMatch, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				savePressed();
			}
		});
		frame.getContentPane().add(btnSave, BorderLayout.NORTH);

		updateLists();
		
	}
	
	public static boolean contains(String query) {
		for (int i = 0; i < playerList.size(); i++) {
			String name = playerList.getElementAt(i).split(Main.eloSpacer)[0];
			if (name.equalsIgnoreCase(query)) {
				return true;
			}
		}
		return false;
	}
	
	public static Player getPlayer(String query) {
		for (int i = 0; i < playerList.size(); i++) {
			String name = playerList.getElementAt(i).split(Main.eloSpacer)[0];
			if (name.equalsIgnoreCase(query)) {
				return new Player(playerList.getElementAt(i));
			}
		}
		return null;
	}
	
	public static void removePlayer(Player player) {
		playerList.removeElement(player.toString());
	}
	
	public void updateLists() {
		List<String> alphabetical = new ArrayList<String>();
		for (int i = 0; i < playerList.size(); i++) {
			alphabetical.add(playerList.getElementAt(i));
		}
		
		Collections.sort(alphabetical, String.CASE_INSENSITIVE_ORDER);
		
		playerList.clear();
		
		for (String s : alphabetical) {
			playerList.addElement(s);
		}

	}
	
	public void addPlayerPressed() {
		String newPlayer = JOptionPane.showInputDialog("New player name");
		playerList.addElement(newPlayer + Main.eloSpacer + "1000");
		updateLists();
	}
	
	public void newMatchPressed() {
		if (playerList.isEmpty()) return;
		String name1 = "";
		String name2 = "";
		boolean cont = false;
		do {
			name1 = JOptionPane.showInputDialog("First player's name?");
			cont = contains(name1);
			if (!cont) {
				JOptionPane.showMessageDialog(null, "Could not find that player name.");
			}
		} while (!cont);
		cont = false;
		do {
			if (playerList.isEmpty()) return;
			name2 = JOptionPane.showInputDialog("Second player's name?");
			cont = contains(name2);
			if (!cont) {
				JOptionPane.showMessageDialog(null, "Could not find that player name.");
			}
		} while (!cont);
		
		Player p1 = getPlayer(name1);
		Player p2 = getPlayer(name2);
		
		String outcomes[] = {name1, name2, "Draw"};
		
		int i = JOptionPane.showOptionDialog(null, "Who won?", "Outcome", JOptionPane.DEFAULT_OPTION, 0, null, outcomes, outcomes[0]);
		double score = .5;
		if (i == 0) {
			score = 1;
		} else if (i == 1) {
			score = 0;
		}
		p1.updateElo(p2, score);
		p2.updateElo(p1, 1-score);
		removePlayer(p1);
		removePlayer(p2);
		p1.pushElo();
		p2.pushElo();
		addPlayer(p1);
		addPlayer(p2);
		updateLists();
	}
	
	public void savePressed() {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < playerList.size(); i++) {
			players.add(new Player(playerList.getElementAt(i)));
		}
		DataManager.savePlayers(players);
	}
	
	@SuppressWarnings("unchecked")
	public void rightClickList(MouseEvent event) {
		JList<String> list = (JList<String>) event.getComponent();
		int row = list.locationToIndex(event.getPoint());
        list.setSelectedIndex(row);  

		
	}
	
	public static DefaultListModel<String> getPlayerList() {
		return playerList;
	}
	
	public static void addPlayer(Player player) {
		if (!contains(player.getName())) {
			playerList.addElement(player.toString());
		}
	}

}
