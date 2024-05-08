package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.SoundController;

public class MainMenuView extends JFrame{
	
	JLabel gameTitleLbl;
	JPanel backgroundPanel, buttonPanel;
	JButton newGameBtn, loadGameBtn, closeGameBtn;
	String introImgPath = "res/img/Intro_Picture.jpg";
	ImageIcon introImgIcon;
	Font gameFont;
	SoundController soundController;
	
	public MainMenuView() {
		
		setTitle("Dragons & Dungeons");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		soundController = new SoundController();
		
		// create background Panel
		backgroundPanel = new BackGroundPanel(new ImageIcon(introImgPath).getImage());
		backgroundPanel.setLayout(new BorderLayout());
		
		// create JLabel for the Game Title
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		gameTitleLbl = new JLabel("Dragons & Dungeons!", SwingConstants.CENTER);
		gameTitleLbl.setFont(gameFont);
		gameTitleLbl.setForeground(new Color(245, 245, 220));
		
		// button panel for better Alignment
		buttonPanel = new JPanel(new FlowLayout());
		
		// create buttons and assign Fonts
		newGameBtn = new JButton("Neues Spiel");		
		newGameBtn.setFont(gameFont);		
		loadGameBtn = new JButton("Spiel laden");		
		loadGameBtn.setFont(gameFont);		
		closeGameBtn = new JButton("Beenden");
		closeGameBtn.setFont(gameFont);

		// fill buttonPanel with Buttons
		buttonPanel.add(newGameBtn);
		buttonPanel.add(loadGameBtn);
		buttonPanel.add(closeGameBtn);
		
		// fill the background with contents
		backgroundPanel.add(gameTitleLbl, BorderLayout.NORTH);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		newGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				soundController.stopMusicLoop();
				CharacterCreationView characterCreationView = new CharacterCreationView();				
				dispose();								
			}
		});
		
		loadGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				SavegameView savegameView = new SavegameView();
				
			}
		});
		
		closeGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				soundController.stopMusicLoop();

				dispose();
				
			}
		});
		
		getContentPane().add(backgroundPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		//play Music in background after rendering		
		soundController.playMusicLoop("res/soundFX/music/mainMenu_music.wav");
		
	}

}
