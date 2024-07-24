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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.SQLController;
import model.PlayerCharacterModel;
import model.SerializationIDs;

public class SavegameView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.saveGameViewID;
	JButton saveBtn, loadBtn, deleteBtn, backBtn, closeGameBtn;
	JLabel titleLbl;
	JPanel buttonPnl, listPnl, mainPnl;
	JTable saveTbl;
	Font gameFont;
	DefaultTableModel tableModel;
	

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
	Font headerFont = new Font("Arial", Font.BOLD, 38);
	Font tableFont = new Font("Arial", Font.ITALIC, 36);
	
	// create Buttons
	saveBtn = new JButton("Spiel speichern");
	loadBtn = new JButton("Spiel laden");
	deleteBtn = new JButton("Spielstand löschen");
	backBtn = new JButton("Zurück");
	closeGameBtn = new JButton("Spiel beenden");
	
	// create table model and table
	tableModel = new DefaultTableModel(new String[]{"Charakter Nr.", "Name", "Rasse", 
			"Klasse", "HP", "Mana", "XP", "Level"}, 0);
	saveTbl = new JTable(tableModel);
	saveTbl.setFont(tableFont);
	saveTbl.setRowHeight(36);
	
	// create tableHeader 
	JTableHeader tableHeader = saveTbl.getTableHeader();
	tableHeader.setFont(headerFont);
	
	// create Container for table
	JScrollPane scrollPane = new JScrollPane(saveTbl);
	
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
	
	// Load all Character information
	loadCharacterInformation();
	
	// add Label to Panel
	listPnl.add(titleLbl, BorderLayout.NORTH);
	listPnl.add(scrollPane, BorderLayout.CENTER);	
	
	// add panels to mainPanel
	mainPnl.add(listPnl, BorderLayout.NORTH);
	mainPnl.add(buttonPnl, BorderLayout.SOUTH);
	
	getContentPane().add(mainPnl);
		 
	setLocationRelativeTo(null);
	setVisible(true);
}


private void loadCharacterInformation() {
	// create controller and model for data transfer
	SQLController sqlController = new SQLController();
	PlayerCharacterModel characterModel = sqlController.getCharacterInformation(1);
	
	if (characterModel != null) {
		tableModel.setRowCount(0);
	}
	
	// add character data to table
	tableModel.addRow(new Object[] {
			characterModel.getCharacterID(),
			characterModel.getName(),
			"Mensch",
			"Krieger",
			characterModel.getCurrentHP(),
			characterModel.getCurrentMana(),
			characterModel.getCurrentXP(),
			characterModel.getLevel()
	});
	
}	
}
