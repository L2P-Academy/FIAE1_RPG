package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.SQLController;
import controller.SoundController;
import model.PlayerCharacterModel;
import model.SerializationIDs;
public class CharacterCreationView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.characterCreationViewID;
	
	private int raceIndex = 0;
	private int genderIndex = 0;
	private int classIndex = 0;
	
	private JTextField charNameTxtField;
	
	private JPanel backgroundPanel, northPanel, southPanel, eastPanel, westPanel, 
		centerPanel, raceBtnPanel, genderBtnPanel, pickGenderLblPanel, pickGenderPanel, racePanel, pickRaceLblPanel,raceLblPanel, pickRacePanel, genderLblPanel, genderPanel, 
		classPanel, pickClassPanel, pickClassLblPanel, classLblPanel, classBtnPanel, shapePanel, pickShapePanel, shapeLblPanel, pickShapeLblPanel, shapeBtnPanel;
	
	private JLabel chooseCharLbl, nameLbl, raceLbl, pickRaceLbl, genderLbl, pickGenderLbl, 
		classLbl, pickClassLbl, shapeLbl, pickShapeLbl;
	
	private JButton finishBtn, rightRaceBtn, leftRaceBtn, rightGenderBtn, leftGenderBtn,
		rightClassBtn, leftClassBtn, rightShapeBtn, leftShapeBtn; // in UML eintragen
	
	private Font gameFont, gameFontSmall;
	private SoundController soundController;
	private SQLController sqlController;
	private ArrayList<String> raceList, genderList, classList, shapeList;
	
	private PlayerCharacterModel playerCharacterModel;
	
	// Image Paths
	private final String[][] imagePaths = {
			{"res/img/CharacterPortraits/New_Race_Gender/new_human_male.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_human_female.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_human_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_elf_male.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_elf_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_elf_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_halfling_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_halfling_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_halfling_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_orc_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_orc_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_orc_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_goblin_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_goblin_female.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_goblin_divers.png"}
	};
	
	private String backgroundImgPath = "res/img/Backgrounds/backgr_ccv_charactercreation1.jpeg";
		
	public CharacterCreationView() {
			
		setTitle("Charaktererstellung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		soundController = new SoundController();
		sqlController = new SQLController();
		
		//  Set Fontvalues
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		gameFontSmall = new Font("Old English Text MT", Font.BOLD, 34);
		
		// Create Basic Panels
		backgroundPanel = new BackGroundPanel(new ImageIcon(backgroundImgPath).getImage());
		backgroundPanel.setLayout(new BorderLayout());
		northPanel = new JPanel(new GridBagLayout());
		northPanel.setOpaque(false);
		southPanel = new JPanel();
		southPanel.setOpaque(false);
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		
		// create westPanel
		westPanel = new JPanel();
		createSidePanel(westPanel);
		
		// create eastPanel
		eastPanel = new JPanel();
		createSidePanel(eastPanel);
		
		// create race-List for selection
		raceList = new ArrayList<>();
		raceList.add("Mensch");
		raceList.add("Elf");
		raceList.add("Zwerg");
		raceList.add("Halbling");
		raceList.add("Ork");
		raceList.add("Goblin");
	
		// create Race Panel and Components
		racePanel = new JPanel();
		raceBtnPanel = new JPanel();
		pickRacePanel = new JPanel();
		pickRaceLblPanel = new JPanel();
		raceLblPanel = new JPanel();
		raceLbl = new JLabel("Rasse");
		pickRaceLbl = new JLabel("Mensch");
		rightRaceBtn = new JButton(">");
		leftRaceBtn = new JButton("<");		
		// add components to RacePanel
		fillSidePanel(raceBtnPanel, pickRacePanel, racePanel,
				pickRaceLblPanel, raceLblPanel, leftRaceBtn, rightRaceBtn, 
				raceLbl, pickRaceLbl);
		
		// create ArrayList for Genders
		genderList = new ArrayList<>();
		genderList.add("männlich");
		genderList.add("weiblich");
		genderList.add("divers");
		
		// create GenderPanel and Components
		genderPanel = new JPanel();
		genderBtnPanel = new JPanel();
		genderLbl = new JLabel("Geschlecht");
		genderLblPanel = new JPanel();
		pickGenderPanel = new JPanel();
		pickGenderLbl = new JLabel("männlich");
		pickGenderLblPanel = new JPanel();
		rightGenderBtn = new JButton(">");
		leftGenderBtn = new JButton("<");
		// add components to GenderPanel
		fillSidePanel(genderBtnPanel, pickGenderPanel, genderPanel,
				pickGenderLblPanel, genderLblPanel, leftGenderBtn, 
				rightGenderBtn,	genderLbl, pickGenderLbl);
		
		// ActionListeners for Race swapping
		rightRaceBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						raceIndex = getNextEntry(pickRaceLbl, raceList, raceIndex);
						chooseImgPath(raceIndex, genderIndex);
					}
				});
				
		leftRaceBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						raceIndex = getPreviousEntry(pickRaceLbl, raceList, raceIndex);
						chooseImgPath(raceIndex, genderIndex);
					}
					
				});
		
		// ActionListeners for Gender swapping
		rightGenderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				genderIndex = getNextEntry(pickGenderLbl, genderList, genderIndex);
				chooseImgPath(raceIndex, genderIndex);
			}
		});
		
		leftGenderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				genderIndex = getPreviousEntry(pickGenderLbl, genderList, genderIndex);
				chooseImgPath(raceIndex, genderIndex);
			}
		});
		
		// create ArrayList for Classes
		classList = new ArrayList<>();
		classList.add("Krieger");
		classList.add("Schurke");
		classList.add("Magier");
		classList.add("Elementartist");
		classList.add("Waldläufer");
		
		// create classPanel and Components
		classPanel = new JPanel();
		classLblPanel = new JPanel();
		pickClassPanel = new JPanel();
		pickClassLblPanel = new JPanel();
		classBtnPanel = new JPanel();
		pickClassLbl = new JLabel("Krieger");
		classLbl = new JLabel("Klasse");
		leftClassBtn = new JButton("<");
		rightClassBtn = new JButton(">");
		
		fillSidePanel(classBtnPanel, pickClassPanel, classPanel,
				pickClassLblPanel, classLblPanel, leftClassBtn, rightClassBtn, 
				classLbl, pickClassLbl);
		
		// create ShapePanel and Components
		shapePanel = new JPanel();
		pickShapePanel = new JPanel();
		pickShapeLblPanel = new JPanel();
		shapeLblPanel = new JPanel();
		shapeBtnPanel = new JPanel();
		rightShapeBtn = new JButton(">");
		rightShapeBtn.setEnabled(false);
		leftShapeBtn = new JButton("<");
		leftShapeBtn.setEnabled(false);
		pickShapeLbl = new JLabel("Schlank");
		shapeLbl = new JLabel("Körperform");
		
		fillSidePanel(shapeBtnPanel, pickShapePanel, shapePanel,
				pickShapeLblPanel, shapeLblPanel, leftShapeBtn, rightShapeBtn, 
				shapeLbl, pickShapeLbl);
		
		// choose Imgpath for Img in centerPanel depending on race-/ genderIndex
		chooseImgPath(raceIndex, genderIndex);
		
		rightClassBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				classIndex = getNextEntry(pickClassLbl, classList, classIndex);				
			}
		});
		
		leftClassBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				classIndex = getPreviousEntry(pickClassLbl, classList, classIndex);				
			}
		});
		
		// Create Label and TextFields
		charNameTxtField = new JTextField();
		charNameTxtField.setFont(gameFontSmall);
		
		// Create Labels
		nameLbl = new JLabel("Gib deinem Charakter einen Namen:");
		nameLbl.setFont(gameFontSmall);
		nameLbl.setForeground(new Color(160, 255, 255));
		
		chooseCharLbl = new JLabel();
		chooseCharLbl.setFont(gameFont);
		
		// Create Buttons
		finishBtn = new JButton("Charakter übernehmen");
		beautifyButton(finishBtn);
		
		// Create GridBagLayout and place Elements on northPanel 
	    GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER; // Set Anchor to CENTER 
		gbc.gridx = 1; // Set x to 0 
		gbc.gridy = 0; // Set y to 0
		gbc.insets.top = 10; // Set Distance to Top (10px)
		northPanel.add(chooseCharLbl, gbc);
		
		// Place nameLbl on northPanel to (0,1)
		gbc.gridy = 0;
		northPanel.add(nameLbl, gbc);
		
		// Place characterName on northPanel to (0,2)
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		charNameTxtField.setColumns(15);
		northPanel.add(charNameTxtField, gbc);
		
		// Place westBtnPanel on westPanel
		westPanel.add(racePanel);
		westPanel.add(genderPanel);
		
		eastPanel.add(shapePanel);
		eastPanel.add(classPanel);
		
		// Place finishBtn on southPanel
		southPanel.add(finishBtn, BorderLayout.CENTER);
	
		// Place Elements on backgroundPanel
		backgroundPanel.add(northPanel, BorderLayout.NORTH);
		backgroundPanel.add(eastPanel, BorderLayout.EAST);
		backgroundPanel.add(southPanel, BorderLayout.SOUTH);
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
		backgroundPanel.add(westPanel, BorderLayout.WEST);
		
		// ActionListener for playing Clicksound if finishButton is clicked
		finishBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// if given charactername is less then 3 signs 
				if(!checkTextField(charNameTxtField.getText())) {
					JOptionPane.showMessageDialog(rootPane, 
							"Bitte gib mindestens 3 Buchstaben ein!");
				} else {
					//TODO: Dynamic constructor information not retrieved yet: ID, RaceID, ClassID, Gender
					playerCharacterModel = new PlayerCharacterModel(1, charNameTxtField.getText(), 3, 2, "m", 1, 0, 100, 1, 100, 100, 100, 100);
					
					Map<String, String> playerMap = new HashMap<>();
					playerMap.put("Name", playerCharacterModel.getName());
					playerMap.put("RaceID", playerCharacterModel.getRaceID() + "");
					playerMap.put("ClassID", playerCharacterModel.getClassID() + "");
					playerMap.put("Gender", playerCharacterModel.getGender());
					playerMap.put("CurrentLocation", playerCharacterModel.getCurrentLocation() + "");
					playerMap.put("CurrentXP", playerCharacterModel.getCurrentXP() + "");
					playerMap.put("MaxXP", playerCharacterModel.getMaxXP() + "");
					playerMap.put("Level", playerCharacterModel.getLevel() + "");
					playerMap.put("CurrentHP", playerCharacterModel.getCurrentHP() + "");
					playerMap.put("MaxHP", playerCharacterModel.getMaxHP() + "");
					playerMap.put("CurrentMana", playerCharacterModel.getCurrentMana() + "");
					playerMap.put("MaxMana", playerCharacterModel.getMaxMana() + "");
					sqlController.insertIntoTable("playercharacter", playerMap);
					soundController.playButtonClickSound();
					new IntroView();
					dispose();
				} 
			}
		});
		
		getContentPane().add(backgroundPanel);
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
		 
		 // Get next Entry from ArrayLists and iterate through
		 // return actual Indexes
		 private int getNextEntry(JLabel targetLabel, ArrayList<String> list, int index) {			 
			 
			 // TODO: Character Race must be changed depending on index
			 index += 1;
			 if (index >= list.size()) {
				 index = 0;
			}
			 
			 targetLabel.setText(list.get(index));
			 targetLabel.repaint();
			 return index;			 
		 }
		 
		 // Get previous Entry from ArrayLists and iterate through
		 // return actual Indexes
		 private int getPreviousEntry(JLabel targetLabel, ArrayList<String> list, int index) {
			 index -= 1;
			 if (index < 0) {
				 index = (list.size()-1);
			}	
			 
			 targetLabel.setText(list.get(index));
			 targetLabel.repaint();
			 return index;	 
		 }
		 
		 //Choose Image-Path by using (array) imagePaths
		 private void chooseImgPath(int raceIndex, int genderIndex) {
			 String imagePath = imagePaths[raceIndex][genderIndex];		 
			 if (imagePath != null) {
				 centerPanel.removeAll();
				 ImageIcon icon = new ImageIcon(imagePath);
				 JLabel label = new JLabel(icon);
				 
				 //select GridBagLayout for centerPanel
				 //if GridBagLayout is not used for centerPanel generate new GridBagLayout
				 if(!(centerPanel.getLayout() instanceof GridBagLayout)) {
					 centerPanel.setLayout(new GridBagLayout());
				 }
				 
				 //set values for new GridBagLayout
				 GridBagConstraints gbc = new GridBagConstraints();
				 gbc.gridx = 0;
				 gbc.gridy = 0;
				 gbc.weightx = 1.0;
				 gbc.weighty = 1.0;
				 gbc.anchor = GridBagConstraints.NORTH;
				 gbc.insets = new Insets(75, 0, 0, 0); // set label 75px from northPanel
				 
				 //add Img-label to centerPanel
				 centerPanel.add(label, gbc);
				 centerPanel.revalidate();
				 centerPanel.repaint();
				 //draw Border around Img-label
				 label.setBorder(BorderFactory.createLineBorder(new Color(160, 255, 255), 3)); 
			 }
		 }
		 
		 // Method to check if quantity of signs in textfield is at least 3
		 private boolean checkTextField(String playerName) { 
			 if (playerName.length() < 3) {
				 return false;
			 } else {
				 return true;
			 }
		 }
		 // set Settings for east- / westPanel (Sidepanel)
		 private void createSidePanel(JPanel sidePanel) {
			sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
			sidePanel.setOpaque(false);
			sidePanel.setPreferredSize(new Dimension(225,100));
			sidePanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
		 }
		 // place Elements on Sidepanel
		 private void fillSidePanel(JPanel btnPanel, JPanel pickPanel, 
				 JPanel attributePanel, JPanel pickLblPanel, 
				 JPanel attributeLblPanel, JButton leftButton, 
				 JButton rightButton, JLabel attributeLbl, JLabel pickLbl) {
			 
			 attributeLbl.setFont(gameFontSmall);
			 attributeLbl.setForeground(new Color(160, 255, 255));
			 
			 pickLbl.setFont(gameFontSmall);
			 pickLbl.setForeground(new Color(160, 255, 255));
			 // create buttonPanel and place left- / rightButton 
			 btnPanel.setLayout(new FlowLayout());
			 btnPanel.setOpaque(false);
			 btnPanel.add(leftButton);
			 btnPanel.add(rightButton);
			 // set settings for pickLblPanel
			 pickLblPanel.setLayout(new GridBagLayout());
			 pickLblPanel.setOpaque(false);
			 pickLblPanel.add(pickLbl);
			 
			 attributeLblPanel.setLayout(new GridBagLayout());
			 attributeLblPanel.setOpaque(false);
			 attributeLblPanel.add(attributeLbl);
			 
			 pickPanel.setLayout(new BorderLayout());
			 pickPanel.setOpaque(false);
			 pickPanel.add(pickLblPanel, BorderLayout.NORTH);
			 pickPanel.add(btnPanel, BorderLayout.CENTER);
			 
			 attributePanel.setLayout(new BorderLayout());
			 attributePanel.setOpaque(false);
			 attributePanel.add(attributeLblPanel, BorderLayout.NORTH);
			 attributePanel.add(pickPanel, BorderLayout.CENTER);
		 }
}