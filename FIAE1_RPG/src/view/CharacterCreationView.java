package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
	private JPanel mainPanel, northPanel, southPanel, eastPanel, westPanel, centerPanel, namePanel/**/,westBtnPanel/**/, westImgPanel/**/;
	private JTextField characterName;
	private JLabel characterPreview, nameLbl, testLbl;
	private JButton finishBtn, forwardBtn/**/, backBtn/**/; // in UML eintragen 
	private JList<String> lookElements;
	private Font gameFont, gameFontSmall;
	private SoundController soundController;
	
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
		gameFontSmall = new Font("Old English Text MT", Font.BOLD, 25);
		
		// Create Labels
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		northPanel = new JPanel(new GridBagLayout());
		southPanel = new JPanel();
		westPanel = new JPanel(new BorderLayout());
		westBtnPanel = new JPanel(new BorderLayout());
		westImgPanel = new JPanel();
		centerPanel = new JPanel();
		addPreview(centerPanel);
		namePanel = new JPanel();
		
		// Create TextField
		characterName = new JTextField();
		
		// Create Labels
		nameLbl = new JLabel("Gib deinem Charakter einen Namen:");
		nameLbl.setFont(gameFontSmall);
		testLbl = new JLabel("Rassen");
		characterPreview = new JLabel("Wähle deinen Charakter");
		characterPreview.setFont(gameFont);
		
		// Create Buttons
		forwardBtn = new JButton(">");
		backBtn = new JButton("<");
		finishBtn = new JButton("Charakter übernehmen");
		beautifyButton(finishBtn);
		
		// Create ListModel
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		lookElements = new JList<>(listModel);
		
		// Create GridBagLayout and place Elements on northPanel 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER; // Set Anchor to CENTER 
		gbc.gridx = 0; // Set x to 0 
		gbc.gridy = 0; // Set y to 0
		gbc.insets.top = 20; // Set Distance to Top (20px)
		northPanel.add(characterPreview, gbc);
		
		// Place nameLbl on northPanel to (0,1)
		gbc.gridy = 1;
		northPanel.add(nameLbl, gbc);
		
		// Place characterName on northPanel to (0,2)
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		characterName.setColumns(20);
		northPanel.add(characterName, gbc);
		
		// Fill Sampel-Elements into lookElements
		westPanel.add(lookElements, BorderLayout.CENTER);
		listModel.addElement("Mensch");
		listModel.addElement("Elf");
		listModel.addElement("Zwerg");
		listModel.addElement("Orc");
		listModel.addElement("Troll");
		
		// Place Buttons on westBtnPanel 
		westBtnPanel.add(backBtn, BorderLayout.WEST);
		westBtnPanel.add(forwardBtn, BorderLayout.EAST);
		
		// Place westBtnPanel on westPanel
		westPanel.setBackground(Color.YELLOW);
		testLbl.setFont(gameFontSmall);
		westImgPanel.add(testLbl, BorderLayout.CENTER);
		westPanel.add(westImgPanel, BorderLayout.NORTH);
		westPanel.add(westBtnPanel, BorderLayout.SOUTH);
		
		// Place finishBtn on southPanel
		southPanel.add(finishBtn, BorderLayout.CENTER);
	
		// Place Elements on mainPanel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(westPanel, BorderLayout.WEST);
		
		finishBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				// TODO Auto-generated method stub
				// Close Window if finishbtn is klicked 
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
		
		 // Get Preview Img (Sample)
		 private void addPreview(JPanel panel) {
				panel = new BackGroundPanel(new ImageIcon(this.previewCharacterImgPath).getImage());
				panel.setBounds(400, 225, 800, 500);
				getContentPane().add(panel);
		 }
}
