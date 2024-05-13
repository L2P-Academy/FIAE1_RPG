package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import model.SerializationIDs;

public class SavegameView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.saveGameViewID;
	JButton saveBtn, loadBtn, deleteBtn, backBtn, closeGameBtn;
	JLabel titleLbl;
	JPanel buttonPnl, listPnl, mainPnl;
	JTable saveTbl;
	Font gameFont;
	

public SavegameView() {
	
	setTitle("Savegames");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setUndecorated(true);
	
	//create Panels
	buttonPnl = new JPanel(new FlowLayout());
	listPnl = new JPanel(new BorderLayout());
	mainPnl = new JPanel(new BorderLayout());
	
	// create Font
	gameFont = new Font("Old English Text MT", Font.BOLD, 64);
	
	
	
	// create Buttons
	saveBtn = new JButton("Spiel speichern");
	loadBtn = new JButton("Spiel laden");
	deleteBtn = new JButton("Spielstand löschen");
	backBtn = new JButton("Zurück");
	closeGameBtn = new JButton("Spiel beenden");
	
	// create Table
	saveTbl = new JTable(10, 2);
	
	// create Label
	titleLbl = new JLabel("Spielstände", SwingConstants.CENTER);
	titleLbl.setFont(gameFont);
	
	//add Button to Panel
	buttonPnl.add(saveBtn);
	buttonPnl.add(loadBtn);
	buttonPnl.add(deleteBtn);
	buttonPnl.add(backBtn);
	buttonPnl.add(closeGameBtn);
	
	// add ActionListeners to Buttons
	backBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();			
		}
	});
	
	closeGameBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);			
		}
	});
	
	//add Lable to Panel
	listPnl.add(titleLbl, BorderLayout.NORTH);
	
	
	// add Table to Panel
	listPnl.add(saveTbl, BorderLayout.SOUTH);
	
	
	
	mainPnl.add(listPnl, BorderLayout.NORTH);
	mainPnl.add(buttonPnl, BorderLayout.SOUTH);
	
	getContentPane().add(mainPnl);
		 
	setLocationRelativeTo(null);
	setVisible(true);
}	
}
