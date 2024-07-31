package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.SoundController;
import controller.CombatController;
import model.SerializationIDs;

public class CombatView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.combatViewID;
	
	private JPanel mainPanel, buttonPanel, heroPanel, backgroundPanel, textPanel, enemyPanel, topPanel, enemy1Panel, enemy2Panel;
	private JLabel enemy1Label, enemy2Label, enemy2hpLabel, dialogueTopMessage, hero1Label;
	private JButton continueBtn, clickBtn;
	private SoundController soundController;
	private JTextArea dialogueText;
	private String battleBackgroundPath;
	private Font defaultFont = new Font("Calisto MT", Font.PLAIN, 26);
	private String enemyPath = "res/img/EnemyPortraits/";
	private Image resizedImage;
	private ImageIcon enemy1Icon, enemy2Icon, player1Icon;
	private JProgressBar enemy1hp, enemy2hp, player1hp;
	
	public CombatView(int battlemap) {
		
		// frame initialize
		setTitle("Kampfübung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		soundController = new SoundController();
		
	//// create main Panel
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		
		
	}
	 
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
		
			/// add leftPanel (empty)
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(150,0));
		topPanel.setOpaque(false);
		
			/// add enemyPanel on the center of main
		enemyPanel = new JPanel();
		enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.X_AXIS));
		enemyPanel.setOpaque(false);
		
				// prepare enemy1 
		enemy1Panel = new JPanel();
		enemy1Panel.setLayout(new BoxLayout(enemy1Panel, BoxLayout.Y_AXIS));
		enemy1Panel.setOpaque(false);
		enemy1Panel.setMaximumSize(new Dimension(200,300));
		
		enemy1Label = new JLabel();
		
		resizedImage = new ImageIcon(enemyPath+"Wolf2.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		enemy1Icon = new ImageIcon(resizedImage);
		enemy1Label.setIcon(enemy1Icon);
		enemy1Label.setPreferredSize(new Dimension(200,200));
		enemy1Label.setText("Wolf 01");
		enemy1Label.setFont(defaultFont);
		enemy1Label.setForeground(Color.white);
		enemy1Label.setVerticalTextPosition(JLabel.TOP);
		enemy1Label.setHorizontalTextPosition(JLabel.CENTER);
		enemy1Label.setIconTextGap(-25);
		
		enemy1hp = new JProgressBar(0,40);
		enemy1hp.setForeground(Color.green);
		enemy1hp.setBackground(Color.red);
		enemy1hp.setValue(40);

		enemy1Panel.add(enemy1Label);
		enemy1Panel.add(enemy1hp);
		
				// prepare enemy2 
		enemy2Panel = new JPanel();
		enemy2Panel.setLayout(new BoxLayout(enemy2Panel, BoxLayout.Y_AXIS));
		enemy2Panel.setOpaque(false);
		enemy2Panel.setMaximumSize(new Dimension(200,300));
		
		enemy2Label = new JLabel();
		
		resizedImage = new ImageIcon(enemyPath+"Wolf2.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		enemy2Icon = new ImageIcon(resizedImage);
		enemy2Label.setIcon(enemy2Icon);
		enemy2Label.setText("Wolf 02");
		enemy2Label.setFont(defaultFont);
		enemy2Label.setForeground(Color.white);
		enemy2Label.setVerticalTextPosition(JLabel.TOP);
		enemy2Label.setHorizontalTextPosition(JLabel.CENTER);
		enemy2Label.setIconTextGap(-25);
		enemy2hpLabel = new JLabel();
		enemy2Label.add(enemy2hpLabel);

		enemy2hp = new JProgressBar(0,30);
		enemy2hp.setForeground(Color.green);
		enemy2hp.setBackground(Color.red);
		enemy2hp.setValue(30);

		enemy2Panel.add(enemy2Label);
		enemy2Panel.add(enemy2hp);
		
				// add enemy labels to the enemyPanel
		enemyPanel.add(enemy1Panel);
		enemyPanel.add(Box.createRigidArea(new Dimension(50,0)));
		enemyPanel.add(enemy2Panel);
		
		
			/// add heroPanel on the right of main		
		heroPanel = new JPanel();
		heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
		heroPanel.setOpaque(false);
		heroPanel.setMaximumSize(new Dimension(200, 1000));
		
				// add heroes name, picture and HP to the heroPanel
		resizedImage = new ImageIcon("res/img/CharacterPortraits/male_dwarf1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		player1Icon = new ImageIcon(resizedImage);
		hero1Label = new JLabel();
		hero1Label.setIcon(player1Icon);
		hero1Label.setText("Zwergenmagier");
		hero1Label.setFont(defaultFont);
		hero1Label.setForeground(Color.white);
		hero1Label.setVerticalTextPosition(JLabel.TOP);
		hero1Label.setHorizontalTextPosition(JLabel.CENTER);
		hero1Label.setIconTextGap(-25);
		
		player1hp = new JProgressBar(0,50);
		player1hp.setForeground(Color.green);
		player1hp.setBackground(Color.red);
		player1hp.setValue(45);
		player1hp.setMaximumSize(new Dimension(400, 20));
		

		heroPanel.add(Box.createRigidArea(new Dimension(0,100)));
		heroPanel.add(player1hp);
		heroPanel.add(hero1Label);
		
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
        dialogueText.setText("Bei Moradin!\nEin Kampf steht mir hervor!");
        
		
				// prepare Buttons
		continueBtn = new JButton("Weiter");
		beautifyButtons(continueBtn);
		clickBtn = new JButton("Click");
		beautifyButtons(clickBtn);
		
				// Continue Button function
		continueBtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.stopMusicLoop();
            	soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");
            	dispose();
			}
		});

				// Click Button function
		clickBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (enemy1hp.getValue() != 0) {
					soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
					enemy1hp.setValue((enemy1hp.getValue()-10));
				} else if (enemy2hp.getValue() != 0) {
					soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
					enemy2hp.setValue((enemy2hp.getValue()-10));
				} else {
					soundController.playFxSound("res/soundFX/fxEffects/helloThere_sound.wav");
					dialogueText.setText("Ich.. habe es endlich.. geschafft!");
					clickBtn.setEnabled(false);
				} if (enemy2hp.getValue() == 0) {
					clickBtn.setEnabled(false);
					//dialogueText.setText("");
					//dialogueText.setText(dialogueText.getText() + "");
					//dialogueText.setText(dialogueText.getText() + "");
					dialogueText.setText(dialogueText.getText() + "...! Endlich. Das war ein harter Kampf.\nIch bin fast ins schwitzen gekommen.");
				}
			}
		});
		

				// add buttons to the button Panel
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(continueBtn, BorderLayout.EAST);
		buttonPanel.add(clickBtn, BorderLayout.EAST);
		
			/// add all Panels onto the text Panel
		
		textPanel.add(dialogueText, BorderLayout.CENTER);
		textPanel.add(dialogueTopMessage, BorderLayout.NORTH);
		textPanel.add(buttonPanel, BorderLayout.EAST);
		
		
	
		// frame finish-up
		backgroundPanel.add(topPanel, BorderLayout.WEST);
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
