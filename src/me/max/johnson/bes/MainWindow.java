package me.max.johnson.bes;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JScrollPane;

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
		JScrollPane scrollPane = new JScrollPane();
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
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {
				check(e);
			}
			public void mouseReleased(MouseEvent e) {
				check(e);
			}
			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			        list.setSelectedIndex(list.locationToIndex(e.getPoint())); //select the item
			        popup.show(list, e.getX(), e.getY()); //and show the menu
			    }
			}
			
			@Override
			public void mouseClicked(MouseEvent event) {	
				if(SwingUtilities.isRightMouseButton(event)) {
					EventHandler.listRightClicked(event);
				}
			}
		});
		
		List<Player> loaded = DataManager.loadPlayers();
		for (Player player : loaded) {
			addPlayer(player);
		}
		frame.getContentPane().add(scrollPane, BorderLayout.WEST);
		scrollPane.setViewportView(list);
		
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EventHandler.addPlayerClicked();
			}
		});
		frame.getContentPane().add(btnAddPlayer, BorderLayout.EAST);
		
		JButton btnNewMatch = new JButton("New Match");
		btnNewMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandler.newMatchClicked();
			}
		});
		frame.getContentPane().add(btnNewMatch, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventHandler.saveClicked();
			}
		});
		frame.getContentPane().add(btnSave, BorderLayout.NORTH);
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
	
	public static DefaultListModel<String> getPlayerList() {
		return playerList;
	}
	
	public static void addPlayer(Player player) {
		if (!contains(player.getName())) {
			playerList.addElement(player.toString());
		}
	}

}
