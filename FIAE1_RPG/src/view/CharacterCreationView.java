package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controller.SoundController;

import model.SerializationIDs;
public class CharacterCreationView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.characterCreationViewID;
	private JPanel backgroundPanel, northPanel, southPanel, eastPanel, westPanel, 
	centerPanel,raceBtnPanel, genderBtnPanel, 
	racePanel, genderPanel, shapePanel, shapeBtnPanel;
	private JTextField charNameTxtField;
	private JLabel chooseCharLbl, nameLbl, raceLbl, pickRaceLbl, genderLbl, pickGenderLbl, shapeLbl,
	pickShapeLbl;
	private JButton finishBtn, rightRaceBtn, leftRaceBtn, rightGenderBtn, leftGenderBtn,
	rightShapeBtn, leftShapeBtn; // in UML eintragen 
	private Font gameFont, gameFontSmall;
	private SoundController soundController;
	private ArrayList<String> raceList, genderList, shapeList;
	
	// Set Indexes
	private int raceIndex = 0;
	private int genderIndex = 1;
	private int shapeIndex = 0;
	
	// Image Paths
	private final String[][] imagePaths = {
			{"res/img/CharacterPortraits/male_human5.png","res/img/CharacterPortraits/female_human3.png","res/img/CharacterPortraits/enby_human1.png"},
			{"res/img/CharacterPortraits/male_orc1.png", "res/img/CharacterPortraits/female_orc1.png", null},
			{"res/img/CharacterPortraits/male_elf1.png", "res/img/CharacterPortraits/female_elf3.png", "res/img/CharacterPortraits/enby_elf1.png"},
			{"res/img/CharacterPortraits/male_dwarf1.png", "res/img/CharacterPortraits/female_dwarf2.png", null},
			{"res/img/CharacterPortraits/male_fairy1.png", "res/img/CharacterPortraits/female_fairy1.png", "res/img/CharacterPortraits/enby_fairy1.png"},
			{"res/img/CharacterPortraits/male_goblin3.png", "res/img/CharacterPortraits/female_goblin1.png",null},
			{"res/img/CharacterPortraits/male_troll1.png", "res/img/CharacterPortraits/female_troll1.png", null},
			{"res/img/CharacterPortraits/male_undead1.png", "res/img/CharacterPortraits/female_undead1.png", "res/img/CharacterPortraits/enby_undead1.png"}
	};
	
	private String backgroundImgPath = "res/img/Backgrounds/backgr_ccv_charactercreation1.jpeg";
		
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
		backgroundPanel = new BackGroundPanel(new ImageIcon(backgroundImgPath).getImage());
		backgroundPanel.setLayout(new BorderLayout());
		northPanel = new JPanel(new GridBagLayout());
		northPanel.setOpaque(false);
		southPanel = new JPanel();
		southPanel.setOpaque(false);
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setOpaque(false);
		westPanel.setPreferredSize(new Dimension(225,100));
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setOpaque(false);
		eastPanel = new JPanel();
		eastPanel.setOpaque(false);
		eastPanel.setPreferredSize(new Dimension(225,100));
		eastPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
		
		// create race-List for selection
		raceList = new ArrayList<>();
		raceList.add("Mensch");
		raceList.add("Ork");
		raceList.add("Elf");
		raceList.add("Zwerg");
		raceList.add("Fee");
		raceList.add("Goblin");
		raceList.add("Troll");
		raceList.add("Untot");
		
		// create Race Panel and Components
		racePanel = new JPanel();
		racePanel.setOpaque(false);
		racePanel.setLayout(new BoxLayout(racePanel, BoxLayout.Y_AXIS));
		raceBtnPanel = new JPanel(new FlowLayout());
		raceBtnPanel.setOpaque(false);
		raceLbl = new JLabel("Rasse");
		pickRaceLbl = new JLabel("Mensch");
		pickRaceLbl.setFont(gameFontSmall);
		raceLbl.setFont(gameFontSmall);
		raceLbl.setForeground(new Color(160, 255, 255));
		pickRaceLbl.setForeground(new Color(160, 255, 255));
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
		
		// create ArrayList for Genders
		genderList = new ArrayList<>();
		genderList.add("männlich");
		genderList.add("weiblich");
		genderList.add("divers");
		
		// create GenderPanel and Components
		genderPanel = new JPanel();
		genderPanel.setOpaque(false);
		genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS));
		genderBtnPanel = new JPanel(new FlowLayout());
		genderBtnPanel.setOpaque(false);
		genderLbl = new JLabel("Geschlecht");
		pickGenderLbl = new JLabel("weiblich");
		genderLbl.setForeground(new Color(160, 255, 255));
		pickGenderLbl.setForeground(new Color(160, 255, 255));
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
				genderIndex = getPreviousEntry(pickGenderLbl, genderList, genderIndex);
				chooseImgPath(raceIndex, genderIndex);
			}
		});
		
		// create ShapePanel and Components
		shapePanel = new JPanel();
		shapePanel.setOpaque(false);
		shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));
		shapeBtnPanel = new JPanel(new FlowLayout());
		shapeBtnPanel.setOpaque(false);
		shapeLbl = new JLabel("Körperform");
		shapeLbl.setForeground(new Color(160, 255, 255));
		pickShapeLbl = new JLabel("Schlank");
		pickShapeLbl.setForeground(new Color(160, 255, 255));
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
		
		// choose Imgpath for Img in centerPanel depending on race-/ genderIndex
		chooseImgPath(raceIndex, genderIndex);
		
		rightShapeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shapeIndex = getNextEntry(pickShapeLbl, shapeList, shapeIndex);				
			}
		});
		
		leftShapeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shapeIndex = getPreviousEntry(pickShapeLbl, shapeList, shapeIndex);				
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
				soundController.playButtonClickSound();
				// TODO Auto-generated method stub
				// Close Window if finishbtn is clicked
				new IntroView();
				dispose(); 
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
}