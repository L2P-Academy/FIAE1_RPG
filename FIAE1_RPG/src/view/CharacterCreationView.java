package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CharacterCreationView extends JFrame{
	
	JPanel mainPanel, northPanel, southPanel, centerPanel;
	JTextField characterName;
	JLabel characterPreview, nameLBL;
	JButton finishBtn;
	Font gameFont, gameFontSmall;
	
	public CharacterCreationView() {
			
		setTitle("Charaktererstellung");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//  Set Fontvalues
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		gameFontSmall = new Font("Old English Text MT", Font.BOLD, 25);
		
		// Create the Window-Elements
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		northPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel();
		characterName = new JTextField(20); // Set Textfield Width on 20 Characters
		
		nameLBL = new JLabel("Gib deinem Charakter einen Namen:");
		nameLBL.setFont(gameFontSmall);
		characterPreview = new JLabel("Wähle deinen Charakter");
		characterPreview.setFont(gameFont);
		finishBtn = new JButton("Charakter übernehmen");
		finishBtn.setFont(gameFont);
		
		// Create Label "charakterPreview" and place it on "northPanel"
		characterPreview.setFont(gameFont);
		northPanel.add(characterPreview, BorderLayout.CENTER);
		
		// Place finishBtn on southPanel
		southPanel.add(finishBtn, BorderLayout.CENTER);
		
		// Create centerPanel and place a Description Label and Name-Textfield on it
		centerPanel.add(nameLBL, BorderLayout.NORTH);
		centerPanel.add(characterName, BorderLayout.CENTER);
		
		// Place Elements on mainPanel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		finishBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Close Window if finishbtn is klicked 
				IntroView introView = new IntroView();
				dispose();
				
			}
		});
		
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
