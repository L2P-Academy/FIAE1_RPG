package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.SoundController;
import model.SerializationIDs;

public class CombatView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.combatViewID;
	
	private JPanel heroPanel, backgroundPanel, textPanel, hero1picture, enemyPanel, enemy1picture, topPanel;
	private JLabel hero1nameLabel, hero1hpLabel, enemy1nameLabel, enemy1hpLabel, dialogueTopMessage;
	private JButton continueBtn;
	private SoundController soundController;
	private JTextArea dialogueText;
	private String battleBackgroundPath;
	private Font defaultFont = new Font("Calisto MT", Font.PLAIN, 26);
	private String enemyPath = "res/img/EnemyPortraits/";
	
	
	public CombatView(String battleLocation) {
		
		// frame initialize
		setTitle("Kampfübung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		soundController = new SoundController();
		
		//// create main background Panel
		battleBackgroundPath = "res/img/BattleBackgrounds/" + battleLocation;
		backgroundPanel = new BackGroundPanel(new ImageIcon(battleBackgroundPath).getImage());
		backgroundPanel.setLayout(new BorderLayout());
		getContentPane().add(backgroundPanel);
		
		/// add topPanel (empty)
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0,500));
		topPanel.setOpaque(false);
		
		/// add enemyPanel on the center of main
		enemyPanel = new JPanel();
		enemyPanel.setOpaque(false);
		
		// add enemy1picture to the enemyPanel
		enemy1picture = new BackGroundPanel(new ImageIcon(enemyPath+"Wolf2.png").getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)); 
		enemyPanel.add(enemy1picture);
		
		/// add heroPanel on the right of main
		heroPanel = new JPanel();
		heroPanel.setLayout(new FlowLayout());
		heroPanel.setPreferredSize(new Dimension(200, 600));
		
		// add heroes name, picture and HP to the heroPanel
		hero1nameLabel = new JLabel("Zwerg Magier");
		hero1nameLabel.setFont(defaultFont);
		hero1nameLabel.setForeground(Color.white);
		
		hero1picture = new BackGroundPanel(new ImageIcon("res/img/CharacterPortraits/male_dwarf1.png").getImage()
		.getScaledInstance(200,200,Image.SCALE_SMOOTH));
		heroPanel.setOpaque(false);
		
		hero1hpLabel = new JLabel();
		hero1hpLabel.setBackground(Color.green);
		
		heroPanel.add(hero1nameLabel);
		heroPanel.add(hero1picture);
		heroPanel.add(hero1hpLabel);
		
		/// add textPanel on the bottom of main
		textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		
		// add dialogue Text and Name Label to the textPanel
		dialogueTopMessage = new JLabel("Player Hero");
		dialogueTopMessage.setFont(new Font("Calisto MT", Font.BOLD, 28));
		
		dialogueText = new JTextArea();
		dialogueText.setFont(defaultFont); // Schriftart anpassen
		dialogueText.setLineWrap(true); // Textumbruch aktivieren
		dialogueText.setWrapStyleWord(true); // Wortumbruch aktivieren
        dialogueText.setEditable(false); // Text nicht editierbar machen
        dialogueText.setPreferredSize(new Dimension(400, 120));
        dialogueText.setBackground(new Color(245, 245, 220));
        dialogueText.setText("Bei Moradin!\nEin leerer Kampfplatz!");
        
		
		// add Buttons to the textPanel
		continueBtn = new JButton("Weiter");
		beautifyButtons(continueBtn);
		// Continue Button function
		continueBtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.stopMusicLoop();
            	soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");
            	dispose();
			}
		});

		textPanel.add(dialogueText, BorderLayout.CENTER);
		textPanel.add(dialogueTopMessage, BorderLayout.NORTH);
		textPanel.add(continueBtn, BorderLayout.EAST);
		
	
		// frame finish-up
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
		backgroundPanel.add(enemyPanel, BorderLayout.CENTER);
		backgroundPanel.add(textPanel, BorderLayout.SOUTH);
		backgroundPanel.add(heroPanel, BorderLayout.EAST);
		setLocationRelativeTo(null);
		setVisible(true);

		// play Music in background after rendering
		soundController.playMusicLoop("res/soundFX/music/Combat_Music.wav");
		
	}

	// Modify Buttons
	private void beautifyButtons(JButton button) {
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
