package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import controller.CharacterController;
import controller.SoundController;
import model.PlayerCharacterModel;
import model.SerializationIDs;

public class MainMenuView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.mainMenuViewID;
	
	private JLabel gameTitleLbl;
	private JPanel backgroundPanel, buttonPanel;
	private JButton newGameBtn, loadGameBtn, closeGameBtn, settingsBtn;
	private String introImgPath = "res/img/Backgrounds/Background_Intro.png";
	private Font gameFont;
	private SoundController soundController;
	private PlayerCharacterModel characterModel;
	private CharacterController characterController;

	public MainMenuView(CharacterController characterController, SoundController soundController) {

		// initialize
		this.characterController = characterController;
		characterModel = characterController.getCharacter();
		this.soundController = soundController;
		
		setTitle("Dungeons & Legends");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		soundController.playMusicLoop("res/soundFX/music/mainMenu_music.wav");

		// create background Panel
		backgroundPanel = new BackGroundPanel(new ImageIcon(introImgPath).getImage());
		backgroundPanel.setLayout(new BorderLayout());

		// create JLabel for the Game Title
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		gameTitleLbl = new JLabel("Dungeons & Legends!", SwingConstants.CENTER);
		gameTitleLbl.setFont(gameFont);
		gameTitleLbl.setForeground(new Color(245, 245, 220));

		// button panel for better Alignment
		buttonPanel = new JPanel(new FlowLayout());

		// create buttons and assign Fonts
		newGameBtn = new JButton("Neues Spiel");
		beautifyButton(newGameBtn);
		loadGameBtn = new JButton("Spiel laden");
		beautifyButton(loadGameBtn);
		closeGameBtn = new JButton("Beenden");
		beautifyButton(closeGameBtn);
		settingsBtn = new JButton("Einstellungen");
		beautifyButton(settingsBtn);

		// fill buttonPanel with Buttons
		buttonPanel.add(newGameBtn);
		buttonPanel.add(loadGameBtn);
		buttonPanel.add(closeGameBtn);
		buttonPanel.add(settingsBtn);
		buttonPanel.setOpaque(false);

		// fill the background with contents
		backgroundPanel.add(gameTitleLbl, BorderLayout.NORTH);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

		newGameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				soundController.stopMusicLoop();
				new CharacterCreationView(characterController, soundController);
				dispose();
			}
		});

		loadGameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				new SavegameView(characterController, soundController);

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
		
		settingsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				new SettingsView(soundController);				
			}
		});

		getContentPane().add(backgroundPanel);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	// Modify Buttons
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
