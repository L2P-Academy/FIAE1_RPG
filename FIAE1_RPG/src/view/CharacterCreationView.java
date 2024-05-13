package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SerializationIDs;
public class CharacterCreationView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.characterCreationViewID;
	JPanel mainPanel, northPanel, southPanel, eastPanel, westPanel, centerPanel, namePanel/**/,westBtnPanel/**/, westImgPanel/**/;
	JTextField characterName;
	JLabel characterPreview, nameLBL, testLbl;
	JButton finishBtn, forwardBtn/**/, backBtn/**/; // in UML eintragen 
	JList<String> lookElements;
	Font gameFont, gameFontSmall;
	
	
	public CharacterCreationView() {
			
		setTitle("Charaktererstellung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		//  Set Fontvalues
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		gameFontSmall = new Font("Old English Text MT", Font.BOLD, 25);
		
		// Create the Window-Elements
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		northPanel = new JPanel(new GridBagLayout());
		southPanel = new JPanel();
		westPanel = new JPanel(new BorderLayout());
		westBtnPanel = new JPanel(new BorderLayout());
		westImgPanel = new JPanel();
		centerPanel = new JPanel();
		namePanel = new JPanel();
		characterName = new JTextField();
		nameLBL = new JLabel("Gib deinem Charakter einen Namen:");
		nameLBL.setFont(gameFontSmall);
		testLbl = new JLabel("Platzhalter");
		characterPreview = new JLabel("Wähle deinen Charakter");
		characterPreview.setFont(gameFont);
		forwardBtn = new JButton("=>");
		backBtn = new JButton("<=");
		finishBtn = new JButton("Charakter übernehmen");
		finishBtn.setFont(gameFont);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		lookElements = new JList<>(listModel);
		
		// Create GridBagLayout and place Elements on northPanel 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER; // Set Anchor to CENTER 
		gbc.gridx = 0; // Set x to 0 
		gbc.gridy = 0; // Set y to 0
		gbc.insets.top = 20; // Set Distance to Top (20px)
		northPanel.add(characterPreview, gbc);
		
		// Place nameLBL on northPanel to (0,1)
		gbc.gridy = 1;
		northPanel.add(nameLBL, gbc);
		
		// Place characterName on northPanel to (0,2)
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		characterName.setColumns(20);
		northPanel.add(characterName, gbc);
		
		// Fill Sampel-Elements into lookElements
		westPanel.add(lookElements, BorderLayout.CENTER);
		listModel.addElement("Test1");
		listModel.addElement("Test2");
		listModel.addElement("Test3");
		
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
}
