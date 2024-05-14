package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.SoundController;

import model.SerializationIDs;
public class CharacterCreationView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.characterCreationViewID;
	private JPanel mainPanel, northPanel, southPanel, eastPanel, westPanel, 
	centerPanel, namePanel/**/,raceBtnPanel/**/, genderBtnPanel, 
	racePanel, genderPanel, shapePanel, shapeBtnPanel;
	private JTextField charNameTxtField;
	private JLabel chooseCharLbl, nameLbl, raceLbl, pickRaceLbl, genderLbl, pickGenderLbl, shapeLbl,
	pickShapeLbl;
	private JButton finishBtn, rightRaceBtn/**/, leftRaceBtn/**/, rightGenderBtn, leftGenderBtn,
	rightShapeBtn, leftShapeBtn; // in UML eintragen 
	private Font gameFont, gameFontSmall;
	private SoundController soundController;
	private ArrayList<String> raceList, genderList, shapeList;
	private int index = 1;
	
	// Sampel Image to load into centerPanel
	private String previewCharacterImgPath = "res/img/CharacterPortraits/female_elf1.png"; //Test-Datei
	
	public CharacterCreationView() {
			
		setTitle("Charaktererstellung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		soundController = new SoundController();
		
		//  Set Fontvalues
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		gameFontSmall = new Font("Old English Text MT", Font.BOLD, 34);
		
		// Create Basic Panels
		mainPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel(new GridBagLayout());
		southPanel = new JPanel();
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
		westPanel.setBorder(border);
		centerPanel = new JPanel();
		eastPanel = new JPanel();
		eastPanel.setBackground(Color.GREEN);
		
		// create race-List for selection
		raceList = new ArrayList<>();
		raceList.add("Mensch");
		raceList.add("Ork");
		raceList.add("Elf");
		raceList.add("Zwerg");
		
		// create Race Panel and Components
		racePanel = new JPanel();
		racePanel.setLayout(new BoxLayout(racePanel, BoxLayout.Y_AXIS));
		raceBtnPanel = new JPanel(new FlowLayout());
		raceLbl = new JLabel("Rasse");
		pickRaceLbl = new JLabel("Mensch");
		pickRaceLbl.setFont(gameFontSmall);
		raceLbl.setFont(gameFontSmall);
		rightRaceBtn = new JButton(">");
		leftRaceBtn = new JButton("<");		
		// add components to RacePanel
		raceBtnPanel.add(leftRaceBtn);
		raceBtnPanel.add(rightRaceBtn);
		racePanel.add(raceLbl);
		racePanel.add(pickRaceLbl);
		racePanel.add(raceBtnPanel);
		
		// ActionListeners for Race swapping
		rightRaceBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getNextEntry(pickRaceLbl, raceList);				
			}
		});
		
		// create ArrayList for Genders
		genderList = new ArrayList<>();
		genderList.add("männlich");
		genderList.add("weiblich");
		genderList.add("divers");
		
		// create GenderPanel and Components
		genderPanel = new JPanel();
		genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));
		genderBtnPanel = new JPanel(new FlowLayout());
		genderLbl = new JLabel("Geschlecht");
		pickGenderLbl = new JLabel("weiblich");
		pickGenderLbl.setFont(gameFontSmall);
		genderLbl.setFont(gameFontSmall);
		rightGenderBtn = new JButton(">");
		leftGenderBtn = new JButton("<");
		// add components to GenderPanel
		genderBtnPanel.add(leftGenderBtn);
		genderBtnPanel.add(rightGenderBtn);
		genderPanel.add(genderLbl);
		genderPanel.add(pickGenderLbl);
		genderPanel.add(genderBtnPanel);
		
		rightGenderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				getNextEntry(pickGenderLbl, genderList);		
			}
		});
		
		// create ShapePanel and Components
		shapePanel = new JPanel();
		shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));
		shapeBtnPanel = new JPanel(new FlowLayout());
		shapeLbl = new JLabel("Körperform");
		pickShapeLbl = new JLabel("schlank");
		pickShapeLbl.setFont(gameFontSmall);
		shapeLbl.setFont(gameFontSmall);
		rightShapeBtn = new JButton(">");
		leftShapeBtn = new JButton("<");
		// add components to GenderPanel
		shapeBtnPanel.add(leftShapeBtn);
		shapeBtnPanel.add(rightShapeBtn);
		shapePanel.add(shapeLbl);
		shapePanel.add(pickShapeLbl);
		shapePanel.add(shapeBtnPanel);
		
		// Create Label and TextFields
		charNameTxtField = new JTextField();
		charNameTxtField.setFont(gameFontSmall);
		
		// Create Labels
		nameLbl = new JLabel("Gib deinem Charakter einen Namen:");
		nameLbl.setFont(gameFontSmall);
		
		chooseCharLbl = new JLabel("Wähle deinen Charakter");
		chooseCharLbl.setFont(gameFont);
		
		// Create Buttons
		finishBtn = new JButton("Charakter übernehmen");
		beautifyButton(finishBtn);
		
		// Create GridBagLayout and place Elements on northPanel 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER; // Set Anchor to CENTER 
		gbc.gridx = 0; // Set x to 0 
		gbc.gridy = 0; // Set y to 0
		gbc.insets.top = 20; // Set Distance to Top (20px)
		northPanel.add(chooseCharLbl, gbc);
		
		// Place nameLbl on northPanel to (0,1)
		gbc.gridy = 1;
		northPanel.add(nameLbl, gbc);
		
		// Place characterName on northPanel to (0,2)
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		charNameTxtField.setColumns(20);
		northPanel.add(charNameTxtField, gbc);
		
		// Place westBtnPanel on westPanel
		westPanel.add(racePanel);
		westPanel.add(genderPanel);
		eastPanel.add(shapePanel);
//		westPanel.add(raceLbl);
//		westPanel.add(pickRaceLbl);
//		westPanel.add(raceBtnPanel);
		
		// Place finishBtn on southPanel
		southPanel.add(finishBtn, BorderLayout.CENTER);
		
		// add image to centerPanel
		JLabel imageLabel = new JLabel(new ImageIcon(previewCharacterImgPath));
		centerPanel.add(imageLabel);
		centerPanel.setBackground(Color.BLUE);
	
		// Place Elements on mainPanel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(westPanel, BorderLayout.WEST);
		
		finishBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				// TODO Auto-generated method stub
				// Close Window if finishbtn is clicked
				new IntroView();
				dispose();
				
			}
		});
		
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
		// Modify Buttons
		private void beautifyButton(JButton button) {
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
		 
		 private void getNextEntry(JLabel targetLabel, ArrayList<String> list) {			 
			 
			 // TODO: Character Race must be changed depending on index
			 targetLabel.setText(list.get(index));
			 index += 1;
			 targetLabel.repaint();
			 
			 if (index >= list.size()) {
				 index = 0;
			}			 
		 }
		 
		 // TODO:
		 private void getPreviousEntry() {
			 
		 }
}