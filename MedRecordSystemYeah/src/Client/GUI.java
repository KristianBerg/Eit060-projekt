package Client;

import java.awt.BorderLayout;
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
    constraints.insets = new Insets(10, 10, 10, 10);
    
    
    String[] names = new String[10];
	names[0]="Krissi Berg";
	names[1]="Tilla Knut";
	names[2]="Sikk! Squrl";
    
	JList list;
    list = new JList(names);
    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list.setLayoutOrientation(JList.VERTICAL);
    list.setVisibleRowCount(-1);
    JScrollPane listScroller = new JScrollPane(list);
    listScroller.setPreferredSize(new Dimension(250, 200));

    
    mainPanel.add(listScroller, constraints);


    
	mainPanel.setPreferredSize(new Dimension(300, 500));
	mainPanel.setMaximumSize(mainPanel.getPreferredSize());
	mainPanel.setMinimumSize(mainPanel.getPreferredSize());

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
