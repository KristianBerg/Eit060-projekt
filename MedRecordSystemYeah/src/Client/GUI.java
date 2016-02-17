package Client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class GUI extends JFrame {
	
    private JLabel labelUsername = new JLabel("username: ");

public GUI(){
	super("Record Manager");
	
	JPanel mainPanel = new JPanel(new GridBagLayout());
	
	GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
       
    mainPanel.add(labelUsername, constraints);
	
    String[] names = new String[10];
	names[0]="Doctor ";
    
	JList list;
    list = new JList(names);
    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    list.setVisibleRowCount(-1);
    JScrollPane listScroller = new JScrollPane(list);
    listScroller.setPreferredSize(new Dimension(250, 80));
    
    
	mainPanel.setPreferredSize(new Dimension(400, 500));
	mainPanel.setMaximumSize(mainPanel.getPreferredSize());
	mainPanel.setMinimumSize(mainPanel.getPreferredSize());
   
	add(list);
	add(mainPanel);
	pack();
	setLocationRelativeTo(null);
}

public static void main(String[] args){
	try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception exception) {
		exception.printStackTrace();
	}
	
	SwingUtilities.invokeLater(new Runnable(){
	public void run(){	
		new GUI().setVisible(true);
	}
	});

}
}
