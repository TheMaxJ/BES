import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ListPopup extends JPopupMenu {
	
	private static final long serialVersionUID = -3664098472519388734L;
	
	JMenuItem delete, changeElo, abc, rank;
	JMenu sort;
    public ListPopup(){
        delete = new JMenuItem("Delete");
        delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JList<String> list = ((JList<String>)getInstance().getInvoker());
				MainWindow.getPlayerList().removeElement(list.getSelectedValue());
			}	
        });
        
        changeElo = new JMenuItem("Change Elo");
        changeElo.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		int elo = Integer.parseInt(JOptionPane.showInputDialog("Enter new Elo"));
				@SuppressWarnings("unchecked")
				JList<String> list = ((JList<String>)getInstance().getInvoker());
				String pstring = list.getSelectedValue();
				Player player = new Player(pstring);
				MainWindow.removePlayer(player);
				player.setElo(elo);
				MainWindow.addPlayer(player);
        	}
        });
        
        abc = new JMenuItem("Alphabetically");
        abc.addActionListener(new ActionListener() {

        	@Override
        	public void actionPerformed(ActionEvent event) {
        		List<String> sortme = new ArrayList<String>();
        		for (int i = 0; i < MainWindow.getPlayerList().size(); i++) {
        			sortme.add(MainWindow.getPlayerList().getElementAt(i));
        		}
        		Collections.sort(sortme, String.CASE_INSENSITIVE_ORDER);
        		MainWindow.getPlayerList().clear();
        		
        		for (String s : sortme) {
        			MainWindow.getPlayerList().addElement(s);
        		}
        	}
    	   
        });
        
        rank = new JMenuItem("Ranked");
        rank.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				List<String> sortme = new ArrayList<String>();
        		for (int i = 0; i < MainWindow.getPlayerList().size(); i++) {
        			sortme.add(MainWindow.getPlayerList().getElementAt(i));
        		}
        		Collections.sort(sortme, new EloComparator());
        		MainWindow.getPlayerList().clear();
        		
        		for (String s : sortme) {
        			MainWindow.getPlayerList().addElement(s);
        		}
			}
        	
        });        
        
        sort = new JMenu("Sort");
        sort.add(abc);
        sort.add(rank);
        
        add(delete);
        add(changeElo);
        
        this.addSeparator();
        
        add(sort);
    }
    
    public ListPopup getInstance() {
    	return this;
    }
}
