package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements ActionListener {
    private JLabel labelUsername = new JLabel("  ID of a patient: ");
    private JTextField textUsername;
    private JButton buttonLogin = new JButton("Search");
	private	JPanel	topPanel;
	private	JPanel	insidePanel=new JPanel();
	private ClientConnect client;


public GUI(){
	super("Record Manager");
	
	client = new ClientConnect(this);
    String s = "Enter an ID";

	topPanel = new JPanel();
	textUsername = new JTextField(s, 15);
	
	
	topPanel.setLayout( new BorderLayout() );	
	JPanel mainPanel = new JPanel(new GridBagLayout());

	buttonLogin.addActionListener(this); 

	
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);
    
    topPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Database browser"));
    insidePanel.setPreferredSize(new Dimension(150,30));
    insidePanel.setMaximumSize( insidePanel.getPreferredSize());
    insidePanel.setMinimumSize( insidePanel.getPreferredSize());
    
    insidePanel.add(textUsername);
    JSplitPane pane2 = new JSplitPane( JSplitPane.VERTICAL_SPLIT, 
            labelUsername, insidePanel );
    JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, 
            pane2, buttonLogin );
   
    
    topPanel.add(pane);

    
    constraints.gridx = 0;
    constraints.gridy = 0;  
    topPanel.setPreferredSize(new Dimension(250,100));
    topPanel.setMaximumSize( topPanel.getPreferredSize());
    topPanel.setMinimumSize( topPanel.getPreferredSize());
    mainPanel.add(topPanel, constraints);

    
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

    constraints.gridx = 0;
    constraints.gridy = 1;  
    mainPanel.add(listScroller, constraints);

    
	mainPanel.setPreferredSize(new Dimension(280, 370));
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

@Override
public void actionPerformed(ActionEvent arg0) {
	client.sendToServer(textUsername.getText());
}
}
