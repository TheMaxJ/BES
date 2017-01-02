import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
       // abc.addActionListener();
        
        
        rank = new JMenuItem("Ranked");
        
        
        sort = new JMenu("Sort");
        sort.add(abc);
        sort.add(rank);
        
        add(delete);
        add(changeElo);
        this.addSeparator();
    }
    
    public ListPopup getInstance() {
    	return this;
    }
}
