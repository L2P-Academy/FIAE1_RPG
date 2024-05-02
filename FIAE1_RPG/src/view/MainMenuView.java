package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class MainMenuView extends JFrame{
	
	JLabel gameTitleLbl;
	JPanel backgroundPanel, buttonPanel;
	JButton newGameBtn, loadGameBtn, closeGameBtn;
	String introImgPath = "res/img/Intro_Picture.jpg";
	ImageIcon introImgIcon;
	Font gameFont;
	Clip introMusicClip;
	
	public MainMenuView() {
		
		setTitle("Dragons & Dungeons");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		newGameBtn = new JButton("Neues Spiel");
		
		newGameBtn.setFont(gameFont);
		loadGameBtn = new JButton("Spiel laden");
		
		loadGameBtn.setFont(gameFont);
		closeGameBtn = new JButton("Beenden");
		closeGameBtn.setFont(gameFont);
		
		buttonPanel.add(newGameBtn);
		buttonPanel.add(loadGameBtn);
		buttonPanel.add(closeGameBtn);
		
		// fill the background with contents
		backgroundPanel.add(gameTitleLbl, BorderLayout.NORTH);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		getContentPane().add(backgroundPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		//play Music in background after rendering
		playBackgroundMusic();
		
	}
	
	private void playBackgroundMusic() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/music/intro_music.wav"));
			introMusicClip = AudioSystem.getClip();
			introMusicClip.open(audioInputStream);
			introMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void stopBackgroundMusic() {
		if (introMusicClip != null && introMusicClip.isOpen()) {
			introMusicClip.stop();
			introMusicClip.close();
		}
	}

}
