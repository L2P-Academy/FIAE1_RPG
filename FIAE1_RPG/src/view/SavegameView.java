package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.SQLController;
import controller.SoundController;
import model.PlayerCharacterModel;
import model.SerializationIDs;

public class SavegameView extends JFrame {
	

	private static final long serialVersionUID = SerializationIDs.saveGameViewID;
	private JButton saveBtn, loadBtn, deleteBtn, backBtn, closeGameBtn;
	private JLabel titleLbl;
	private JPanel buttonPnl, listPnl, mainPnl;
	private JTable saveTbl;
	private Font gameFont;
	private DefaultTableModel tableModel;
	private SoundController soundController;
	public Clip musicClip;
	SQLController sqlController = new SQLController();
	PlayerCharacterModel playerCharacter;

	public SavegameView() {

		setTitle("Savegames");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// create Controllers
		soundController = new SoundController();
//		this.musicClip = soundController.getMusicClip();
		
		// create Panels
		buttonPnl = new JPanel(new FlowLayout());
		listPnl = new JPanel(new BorderLayout());
		mainPnl = new JPanel(new BorderLayout());

		// create Font
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		Font headerFont = new Font("Arial", Font.BOLD, 38);
		Font tableFont = new Font("Arial", Font.ITALIC, 36);

		// create Buttons
		saveBtn = new JButton("Spiel speichern");
		beautifyButton(saveBtn);
		loadBtn = new JButton("Spiel laden");
		beautifyButton(loadBtn);
		deleteBtn = new JButton("Spielstand löschen");
		beautifyButton(deleteBtn);
		backBtn = new JButton("Zurück");
		beautifyButton(backBtn);
		closeGameBtn = new JButton("Spiel beenden");
		beautifyButton(closeGameBtn);

		// create table model and table
		tableModel = new DefaultTableModel(
				new String[] { "Charakter Nr.", "Name", "Rasse", "Klasse", "HP", "Mana", "XP", "Level" }, 0);
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

		// add Button to Panel
		buttonPnl.add(saveBtn);
		buttonPnl.add(loadBtn);
		buttonPnl.add(deleteBtn);
		buttonPnl.add(backBtn);
		buttonPnl.add(closeGameBtn);

		// add ActionListeners to Buttons
		loadBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				int selectedRow = saveTbl.getSelectedRow() + 1;
				playerCharacter = sqlController.getCharacterInformation(selectedRow);
				new MapView();
				dispose();
			}
		});
		
		
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				dispose();
			}
		});

		closeGameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				System.exit(0);
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = saveTbl.getSelectedRow() + 1;
				sqlController.deleteDatasetFromTable(selectedRow, "playercharacter", "CharacterID");
			}
		});
		//TODO : playermap edit
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, String> playerMap = new HashMap<>();
				sqlController.insertIntoTable("playercharacter", playerMap);
					
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
		
		int numberOfDatasets = sqlController.getNumberOfDatasets("playercharacter");
		// create model for data transfer	
		for (int i = 1; i <= numberOfDatasets; i++) {
	
		PlayerCharacterModel characterModel = sqlController.getCharacterInformation(i);
		
		// add character data to table
		tableModel.addRow(new Object[] { characterModel.getCharacterID(), characterModel.getName(), 
				sqlController.convertRaceIDToString(characterModel.getRaceID()), 
				sqlController.convertClassIDToString(characterModel.getClassID()),
				characterModel.getCurrentHP(), characterModel.getCurrentMana(), characterModel.getCurrentXP(),
				characterModel.getLevel() });
		}
	}

//Modify Buttons
	public void beautifyButton(JButton button) {
		button.setFocusPainted(false);
		button.setBackground(new Color(10, 50, 100));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Old English Text MT", Font.BOLD, 36));

		// Rounded Corners
		Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
		Border roundedBorder = BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 20, 10, 20));
		button.setBorder(
				BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		// color change when MouseOver is happening
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(10, 50, 100));
			}
		});
	}
}
