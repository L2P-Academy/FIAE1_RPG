package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.CharacterController;
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
	private Clip musicClip;
	private SQLController sqlController;
	public PlayerCharacterModel playerCharacter;
	public CharacterController characterController;
	public PlayerCharacterModel characterModel;

	public SavegameView(CharacterController characterController) {
		
		// initialize
		this.characterController = characterController;
		characterModel = characterController.getCharacter();

		setTitle("Spielstände");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// create Controllers
		sqlController = new SQLController();
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
		
		// Deactivate SaveBtn when Map is closed
		saveBtn.setEnabled(MapView.isMapViewOpen());

		// add ActionListeners to Buttons
		loadBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = saveTbl.getSelectedRow();
				if (selectedRow >= 0) {
					soundController.playButtonClickSound();
					int selectedID = (int) saveTbl.getValueAt(saveTbl.getSelectedRow(), 0);
					characterController.setCharacter((sqlController.getCharacterInformation(selectedID)));
					new MapView(characterController);
					dispose();
				} else  {
					JOptionPane.showMessageDialog(SavegameView.this,
							"Bitte wählen Sie einen Spielstand aus, den Sie laden möchten.", "Keine Auswahl!",
							JOptionPane.WARNING_MESSAGE);					
				}				
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
				int selectedRow = saveTbl.getSelectedRow();

				if (selectedRow >= 0) { // Überprüfen, ob eine Zeile ausgewählt ist
					String characterName = (String) tableModel.getValueAt(selectedRow, 1);																							

					// Bestätigungsdialog anzeigen
					int confirm = JOptionPane.showConfirmDialog(SavegameView.this,
							"Möchten Sie den Spielstand von " + characterName + " wirklich löschen?",
							"Spielstand löschen", JOptionPane.YES_NO_OPTION);

					if (confirm == JOptionPane.YES_OPTION) {
						int characterID = (int) tableModel.getValueAt(selectedRow, 0);
																						
						sqlController.deleteDatasetFromTable(characterID, "playercharacter", "CharacterID");

						// Zeile aus dem TableModel entfernen
						tableModel.removeRow(selectedRow);

						// Tabelle aktualisieren
						saveTbl.revalidate();
						saveTbl.repaint();

						JOptionPane.showMessageDialog(SavegameView.this,
								"Der Spielstand von " + characterName + " wurde gelöscht.");
					}
				} else {
					// Keine Zeile ausgewählt
					JOptionPane.showMessageDialog(SavegameView.this,
							"Bitte wählen Sie einen Spielstand aus, den Sie löschen möchten.", "Keine Auswahl",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// TODO : playermap edit
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

	// loads all Characters into a List of CharacterModels
	private void loadCharacterInformation() {
		List<PlayerCharacterModel> characters = sqlController.getAllCharacters();
		for (PlayerCharacterModel characterModel : characters) {
			tableModel.addRow(new Object[] { characterModel.getCharacterID(), characterModel.getName(),
					sqlController.convertRaceIDToString(characterModel.getRaceID()),
					sqlController.convertClassIDToString(characterModel.getClassID()), characterModel.getCurrentHP(),
					characterModel.getCurrentMana(), characterModel.getCurrentXP(), characterModel.getLevel()

			});
		}
	}

//	private void loadCharacterInformation() {
//		// TODO: check for Gaps between IDs, get all existing entries 
//		int numberOfDatasets = sqlController.getNumberOfDatasets("playercharacter");
//		// create model for data transfer
//		// TODO: check if Datasets exist
////		if (numberOfDatasets == 0) {
////			JDialog noDatasetsDialog = new JDialog();
////			noDatasetsDialog.setSize(400, 200);
////			noDatasetsDialog.setLocationRelativeTo(null);
////		}
//		for (int i = 1; i <= numberOfDatasets; i++) {
//		PlayerCharacterModel characterModel = sqlController.getCharacterInformation(i);
//		if (characterModel != null) {
//			// add character data to table
//			tableModel.addRow(new Object[] { characterModel.getCharacterID(), characterModel.getName(), 
//					sqlController.convertRaceIDToString(characterModel.getRaceID()), 
//					sqlController.convertClassIDToString(characterModel.getClassID()),
//					characterModel.getCurrentHP(), characterModel.getCurrentMana(), characterModel.getCurrentXP(),
//					characterModel.getLevel() });
//			}
//		}
//	}

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
