package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import controller.InventoryController;
import controller.SQLController;
import model.AbilityModel;
import model.BossModel;
import model.AbilityModel.AbilityElement;
import model.NpcModel;
import model.PlayerCharacterModel;
import model.RaceAbilityModel;
import model.SerializationIDs;

public class CombatView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.combatViewID;

	private int amountOfEnemies = 0;
	private String battlemapPath = "res/img/BattleBackgrounds/";
	private String enemyPath = "res/img/EnemyPortraits/";
	private String battlemapImg = "";
	
	private JPanel backgroundPnl, topPnl, btnPnl, dialoguePnl, enemiesPnl, enemy1Pnl, enemy2Pnl, heroPnl; 
	private JLabel dialogueTopMsg, heroLbl, enemy1Lbl, enemy2Lbl;
	private JTextArea dialogueText;
	private JButton continueBtn, clickBtn;
	private JProgressBar enemy1Hp, enemy2Hp, heroHp;
	private ImageIcon enemy1Icon, enemy2Icon, heroIcon;
	private Image resizedImg;
	private Font defaultFont = new Font("Calisto MT", Font.PLAIN, 26);
	
	private PlayerCharacterModel playerCharacterModel;
	private NpcModel npcModel;
	//private AbilityModel<Enum<AbilityElement>> abililtyModel;
	private BossModel bossModel;
	private RaceAbilityModel raceAbilityModel;
	//private InventoryController inventoryController;
	private SQLController sqlController;
	private SoundController soundController;
	
	public CombatView(int battlemapID, int... npcID) {
		
		// frame initialize
		setTitle("Kampfuebung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// Controllers
		soundController = new SoundController();
		//sqlController = new SQLController();
		//combatController = new CombatController();
		
		// load battlemapID
		if (battlemapID == 1) {battlemapImg = "Forest.jpg";}
		
		// battlemap Panel and adjustments
		backgroundPnl = new BackGroundPanel(new ImageIcon(battlemapPath+battlemapImg).getImage());
		backgroundPnl.setLayout(new BorderLayout()); 
		getContentPane().add(backgroundPnl);
		
		// top Panel will be used as an empty space to adjust the position of enemies
		topPnl = new JPanel();
		topPnl.setPreferredSize(new Dimension(100,0)); topPnl.setOpaque(false);
		
		// other Panels	
		amountOfEnemies = 0;
		enemiesPnl = new JPanel();	// will hold the enemy1..4 panels
		enemiesPnl.setLayout(new BoxLayout(enemiesPnl, BoxLayout.X_AXIS)); enemiesPnl.setOpaque(false);
		for (int i : npcID) {
			if (i == 1) {
				addEnemiesPnl();
			}
		}

		addHeroPnl();
		heroPnl.add(heroLbl);
		
		dialoguePnl = new JPanel(); // will hold the title, text and buttons
		dialoguePnl.setLayout(new BorderLayout());
		// Word above Text / Dialogue
		dialogueTopMsg = new JLabel("Player Hero");
		dialogueTopMsg.setFont(new Font("Calisto MT", Font.BOLD, 28));
		// Text / Dialogue
		dialogueText = new JTextArea();
		dialogueText.setFont(defaultFont); // Schriftart anpassen
		dialogueText.setLineWrap(true); // Textumbruch aktivieren
		dialogueText.setWrapStyleWord(true); // Wortumbruch aktivieren
        dialogueText.setEditable(false); // Text nicht editierbar machen
        dialogueText.setPreferredSize(new Dimension(400, 120));
        dialogueText.setBackground(new Color(245, 245, 220));
        updateDialogue("Start");
				
		// prepare Buttons
		continueBtn = new JButton("Weiter");
		beautifyButtons(continueBtn);
		clickBtn = new JButton("Click");
		beautifyButtons(clickBtn);		
		
		// Continue Btn function
		continueBtn.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.stopMusicLoop();
            	//soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");
            	dispose();
			}
		});
		
		// Click Button function
//		clickBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (enemy1hp.getValue() != 0) {
//					soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
//					enemy1hp.setValue((enemy1hp.getValue()-10));
//				} else if (enemy2hp.getValue() != 0) {
//					soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
//					enemy2hp.setValue((enemy2hp.getValue()-10));
//				} else {
//					soundController.playFxSound("res/soundFX/fxEffects/helloThere_sound.wav");
//					dialogueText.setText("Ich.. habe es endlich.. geschafft!");
//					clickBtn.setEnabled(false);
//				} if (enemy2hp.getValue() == 0) {
//					clickBtn.setEnabled(false);
//					//dialogueText.setText("");
//					//dialogueText.setText(dialogueText.getText() + "");
//					//dialogueText.setText(dialogueText.getText() + "");
//					dialogueText.setText(dialogueText.getText() + "...! Endlich. Das war ein harter Kampf.\nIch bin fast ins schwitzen gekommen.");
//				}
//			}
//		});

		// add buttons to the btnPnl
		btnPnl = new JPanel();
		btnPnl.setLayout(new BoxLayout(btnPnl, BoxLayout.Y_AXIS));
		btnPnl.add(continueBtn);
		btnPnl.add(clickBtn);

		// filling dialoguePanel (bottom Panel)
		dialoguePnl.add(dialogueText, BorderLayout.CENTER);
		dialoguePnl.add(dialogueTopMsg, BorderLayout.NORTH);
		dialoguePnl.add(btnPnl, BorderLayout.EAST);
		
		// frame finish-up
		backgroundPnl.add(topPnl, BorderLayout.WEST);
		backgroundPnl.add(enemiesPnl, BorderLayout.CENTER);
		backgroundPnl.add(dialoguePnl, BorderLayout.SOUTH);
		backgroundPnl.add(heroPnl, BorderLayout.EAST);
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
	
	private void addEnemiesPnl() {
		amountOfEnemies = amountOfEnemies + 1;
		switch (amountOfEnemies) {
		case 1:
			System.out.println("Amount: " + amountOfEnemies + "(Add Monster 1)");
			enemy1Pnl = new JPanel();
			enemy1Pnl.setLayout(new BoxLayout(enemy1Pnl, BoxLayout.Y_AXIS));
			enemy1Pnl.setOpaque(false);
			enemy1Pnl.setMaximumSize(new Dimension(200,300));
			
			enemy1Lbl = new JLabel();
			
			resizedImg = new ImageIcon(enemyPath+"Wolf.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			enemy1Icon = new ImageIcon(resizedImg);
			enemy1Lbl.setIcon(enemy1Icon);
			enemy1Lbl.setPreferredSize(new Dimension(200,200));
			enemy1Lbl.setText("Wolf");
			enemy1Lbl.setFont(defaultFont);
			enemy1Lbl.setForeground(Color.white);
			enemy1Lbl.setVerticalTextPosition(JLabel.TOP);
			enemy1Lbl.setHorizontalTextPosition(JLabel.CENTER);
			enemy1Lbl.setIconTextGap(-25);
			 
			enemy1Hp = new JProgressBar(0,40);
			enemy1Hp.setForeground(Color.green);
			enemy1Hp.setBackground(Color.red);
			enemy1Hp.setValue(40);

			enemy1Pnl.add(enemy1Lbl);
			enemy1Pnl.add(enemy1Hp);

			enemy1Pnl.setAlignmentY(Component.BOTTOM_ALIGNMENT); // Bottom Alignment for ground creatures and default for flying creatures
			
			enemiesPnl.add(enemy1Pnl);
			break;
		case 2:
			System.out.println("Amount: " + amountOfEnemies + "(Add Monster 1)");
			enemy2Pnl = new JPanel();
			enemy2Pnl.setLayout(new BoxLayout(enemy2Pnl, BoxLayout.Y_AXIS));
			enemy2Pnl.setOpaque(false);
			enemy2Pnl.setMaximumSize(new Dimension(200,300));
			
			enemy2Lbl = new JLabel();
			
			resizedImg = new ImageIcon(enemyPath+"Wolf.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			enemy2Icon = new ImageIcon(resizedImg);
			enemy2Lbl.setIcon(enemy1Icon);
			enemy2Lbl.setPreferredSize(new Dimension(200,200));
			enemy2Lbl.setText("Wolf");
			enemy2Lbl.setFont(defaultFont);
			enemy2Lbl.setForeground(Color.white);
			enemy2Lbl.setVerticalTextPosition(JLabel.TOP);
			enemy2Lbl.setHorizontalTextPosition(JLabel.CENTER);
			enemy2Lbl.setIconTextGap(-25);
			
			enemy2Hp = new JProgressBar(0,40);
			enemy2Hp.setForeground(Color.green);
			enemy2Hp.setBackground(Color.red);
			enemy2Hp.setValue(40);

			enemy2Pnl.add(enemy2Lbl);
			enemy2Pnl.add(enemy2Hp);

			enemy2Pnl.setAlignmentY(Component.BOTTOM_ALIGNMENT); // Bottom Alignment for ground creatures and default for flying creatures

			enemiesPnl.add(Box.createRigidArea(new Dimension(50,0)));
			enemiesPnl.add(enemy2Pnl);
			break;
		default:
		}
		
	}
	
	private void addHeroPnl() {
		heroPnl = new JPanel();
		heroPnl.setLayout(new BoxLayout(heroPnl, BoxLayout.Y_AXIS));
		heroPnl.setOpaque(false);
		heroPnl.setMaximumSize(new Dimension(200, 1000));
		
		// add heroes name, picture and HP to the heroPanel
		resizedImg = new ImageIcon("res/img/CharacterPortraits/male_dwarf1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		heroIcon = new ImageIcon(resizedImg);
		heroLbl = new JLabel();
		heroLbl.setIcon(heroIcon);
		heroLbl.setText("Zwergenmagier");
		heroLbl.setFont(defaultFont);
		heroLbl.setForeground(Color.white);
		heroLbl.setVerticalTextPosition(JLabel.TOP); heroLbl.setHorizontalTextPosition(JLabel.CENTER); heroLbl.setIconTextGap(-25);

		heroHp = new JProgressBar(0,50);
		heroHp.setForeground(Color.green);
		heroHp.setBackground(Color.red);
		heroHp.setValue(45);
		heroHp.setMaximumSize(new Dimension(400, 20));

		heroPnl.add(Box.createRigidArea(new Dimension(0,100)));
		heroPnl.add(heroHp);
	}
	
	private void updateDialogue(String dialogue) {
		switch (dialogue) {
		case "Start":
	        dialogueText.setText("Christoph Bauch steh mir bei... Ein Wolf!");
	        break;
		default:
			dialogueText.setText("");
		}
		
	}
}


//public CombatView(String battleLocation) {
//	
//	// frame initialize
//	setTitle("Kampfübung");
//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	setExtendedState(JFrame.MAXIMIZED_BOTH);
//	setUndecorated(true);
//	soundController = new SoundController();
//	
//	//// create main background Panel
//	battleBackgroundPath = "res/img/BattleBackgrounds/" + battleLocation;
//	backgroundPanel = new BackGroundPanel(new ImageIcon(battleBackgroundPath).getImage());
//	backgroundPanel.setLayout(new BorderLayout());
//	getContentPane().add(backgroundPanel);
//	
//		/// add leftPanel (empty)
//	topPanel = new JPanel();
//	topPanel.setPreferredSize(new Dimension(150,0));
//	topPanel.setOpaque(false);
//	
//		/// add enemyPanel on the center of main
//	enemyPanel = new JPanel();
//	enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.X_AXIS));
//	enemyPanel.setOpaque(false);
//	
//			// prepare enemy1 
//	enemy1Panel = new JPanel();
//	enemy1Panel.setLayout(new BoxLayout(enemy1Panel, BoxLayout.Y_AXIS));
//	enemy1Panel.setOpaque(false);
//	enemy1Panel.setMaximumSize(new Dimension(200,300));
//	
//	enemy1Label = new JLabel();
//	
//	resizedImage = new ImageIcon(enemyPath+"Wolf2.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//	enemy1Icon = new ImageIcon(resizedImage);
//	enemy1Label.setIcon(enemy1Icon);
//	enemy1Label.setPreferredSize(new Dimension(200,200));
//	enemy1Label.setText("Wolf 01");
//	enemy1Label.setFont(defaultFont);
//	enemy1Label.setForeground(Color.white);
//	enemy1Label.setVerticalTextPosition(JLabel.TOP);
//	enemy1Label.setHorizontalTextPosition(JLabel.CENTER);
//	enemy1Label.setIconTextGap(-25);
//	
//	enemy1hp = new JProgressBar(0,40);
//	enemy1hp.setForeground(Color.green);
//	enemy1hp.setBackground(Color.red);
//	enemy1hp.setValue(40);
//
//	enemy1Panel.add(enemy1Label);
//	enemy1Panel.add(enemy1hp);
//	
//			// prepare enemy2 
//	enemy2Panel = new JPanel();
//	enemy2Panel.setLayout(new BoxLayout(enemy2Panel, BoxLayout.Y_AXIS));
//	enemy2Panel.setOpaque(false);
//	enemy2Panel.setMaximumSize(new Dimension(200,300));
//	
//	enemy2Label = new JLabel();
//	
//	resizedImage = new ImageIcon(enemyPath+"Wolf2.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//	enemy2Icon = new ImageIcon(resizedImage);
//	enemy2Label.setIcon(enemy2Icon);
//	enemy2Label.setText("Wolf 02");
//	enemy2Label.setFont(defaultFont);
//	enemy2Label.setForeground(Color.white);
//	enemy2Label.setVerticalTextPosition(JLabel.TOP);
//	enemy2Label.setHorizontalTextPosition(JLabel.CENTER);
//	enemy2Label.setIconTextGap(-25);
//	enemy2hpLabel = new JLabel();
//	enemy2Label.add(enemy2hpLabel);
//
//	enemy2hp = new JProgressBar(0,30);
//	enemy2hp.setForeground(Color.green);
//	enemy2hp.setBackground(Color.red);
//	enemy2hp.setValue(30);
//
//	enemy2Panel.add(enemy2Label);
//	enemy2Panel.add(enemy2hp);
//	
//			// add enemy labels to the enemyPanel
//	enemyPanel.add(enemy1Panel);
//	enemyPanel.add(Box.createRigidArea(new Dimension(50,0)));
//	enemyPanel.add(enemy2Panel);
//	
//	
//		/// add heroPanel on the right of main		
//	heroPanel = new JPanel();
//	heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
//	heroPanel.setOpaque(false);
//	heroPanel.setMaximumSize(new Dimension(200, 1000));
//	
//			// add heroes name, picture and HP to the heroPanel
//	resizedImage = new ImageIcon("res/img/CharacterPortraits/male_dwarf1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//	player1Icon = new ImageIcon(resizedImage);
//	hero1Label = new JLabel();
//	hero1Label.setIcon(player1Icon);
//	hero1Label.setText("Zwergenmagier");
//	hero1Label.setFont(defaultFont);
//	hero1Label.setForeground(Color.white);
//	hero1Label.setVerticalTextPosition(JLabel.TOP);
//	hero1Label.setHorizontalTextPosition(JLabel.CENTER);
//	hero1Label.setIconTextGap(-25);
//	
//	player1hp = new JProgressBar(0,50);
//	player1hp.setForeground(Color.green);
//	player1hp.setBackground(Color.red);
//	player1hp.setValue(45);
//	player1hp.setMaximumSize(new Dimension(400, 20));
//	
//
//	heroPanel.add(Box.createRigidArea(new Dimension(0,100)));
//	heroPanel.add(player1hp);
//	heroPanel.add(hero1Label);
//	
//		/// add textPanel on the bottom of main
//	textPanel = new JPanel();
//	textPanel.setLayout(new BorderLayout());
//	
//			// add dialogue Text and Name Label to the textPanel
//	dialogueTopMessage = new JLabel("Player Hero");
//	dialogueTopMessage.setFont(new Font("Calisto MT", Font.BOLD, 28));
//	
//	dialogueText = new JTextArea();
//	dialogueText.setFont(defaultFont); // Schriftart anpassen
//	dialogueText.setLineWrap(true); // Textumbruch aktivieren
//	dialogueText.setWrapStyleWord(true); // Wortumbruch aktivieren
//    dialogueText.setEditable(false); // Text nicht editierbar machen
//    dialogueText.setPreferredSize(new Dimension(400, 120));
//    dialogueText.setBackground(new Color(245, 245, 220));
//    dialogueText.setText("Bei Moradin!\nEin Kampf steht mir hervor!");
//    
//	
//			
//	
//			// Continue Button function
//	continueBtn.addActionListener(new ActionListener() {
//		
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			soundController.stopMusicLoop();
//        	soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");
//        	dispose();
//		}
//	});
//
//			// Click Button function
//	clickBtn.addActionListener(new ActionListener() {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			if (enemy1hp.getValue() != 0) {
//				soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
//				enemy1hp.setValue((enemy1hp.getValue()-10));
//			} else if (enemy2hp.getValue() != 0) {
//				soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
//				enemy2hp.setValue((enemy2hp.getValue()-10));
//			} else {
//				soundController.playFxSound("res/soundFX/fxEffects/helloThere_sound.wav");
//				dialogueText.setText("Ich.. habe es endlich.. geschafft!");
//				clickBtn.setEnabled(false);
//			} if (enemy2hp.getValue() == 0) {
//				clickBtn.setEnabled(false);
//				//dialogueText.setText("");
//				//dialogueText.setText(dialogueText.getText() + "");
//				//dialogueText.setText(dialogueText.getText() + "");
//				dialogueText.setText(dialogueText.getText() + "...! Endlich. Das war ein harter Kampf.\nIch bin fast ins schwitzen gekommen.");
//			}
//		}
//	});
//	
//
//			// add buttons to the button Panel
//	
//	buttonPanel = new JPanel();
//	buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//	buttonPanel.add(continueBtn, BorderLayout.EAST);
//	buttonPanel.add(clickBtn, BorderLayout.EAST);
//	
//		/// add all Panels onto the text Panel
//	
//	textPanel.add(dialogueText, BorderLayout.CENTER);
//	textPanel.add(dialogueTopMessage, BorderLayout.NORTH);
//	textPanel.add(buttonPanel, BorderLayout.EAST);
//	
//	
//
//	// frame finish-up
//	backgroundPanel.add(topPanel, BorderLayout.WEST);
//	backgroundPanel.add(enemyPanel, BorderLayout.CENTER);
//	backgroundPanel.add(textPanel, BorderLayout.SOUTH);
//	backgroundPanel.add(heroPanel, BorderLayout.EAST);
//	setLocationRelativeTo(null);
//	setVisible(true);
//
//	// play Music in background after rendering
//	soundController.playMusicLoop("res/soundFX/music/Combat_Music.wav");
//	
//}
