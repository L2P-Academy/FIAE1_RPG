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

	private int amountOfEnemies = 0, damageDealt = 10, currentTurn = 0;
	private String enemyName = "";
	private String battlemapPath = "res/img/BattleBackgrounds/";
	private String enemyPath = "res/img/EnemyPortraits/";
	private String battlemapImg = "";
	
	private JPanel backgroundPnl, topPnl, btnPnl, dialoguePnl, enemiesPnl, enemyPnl, heroPnl; 
	private JLabel dialogueTopMsg, heroLbl, enemyLbl1, enemyLbl2;
	private JTextArea dialogueText;
	//private JButton continueBtn, clickBtn;
	private JProgressBar enemyHp1, enemyHp2, heroHp;
	private ImageIcon enemyIcon, heroIcon;
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
		dialoguePnl.setPreferredSize(new Dimension(WIDTH, 200));
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
        //updateBtn("Start");
		//continueBtn = new JButton("Weiter");
		//beautifyButtons(continueBtn);
		//clickBtn = new JButton("Click");
//		//beautifyButtons(clickBtn);		
//		
//		// Continue Btn function
//		continueBtn.addActionListener(new ActionListener() {
//			
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				soundController.stopMusicLoop();
//            	//soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");
//            	dispose();
//			}
//		});
		
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
        updateBtn("Zurück");

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
		button.setFont(new Font("Old English Text MT", Font.BOLD, 24));

		// Rounded Corners
		Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
		Border roundedBorder = BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 20, 10, 20));
		button.setBorder(
				BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(3, 5, 3, 5)));

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
			enemyPnl = new JPanel();
			enemyPnl.setLayout(new BoxLayout(enemyPnl, BoxLayout.Y_AXIS));
			enemyPnl.setOpaque(false);
			enemyPnl.setMaximumSize(new Dimension(200,300));
			
			enemyLbl1 = new JLabel();
			
			resizedImg = new ImageIcon(enemyPath+"Wolf.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			enemyIcon = new ImageIcon(resizedImg);
			enemyLbl1.setIcon(enemyIcon);
			enemyLbl1.setPreferredSize(new Dimension(200,200));
			enemyLbl1.setText("Wolf");
			enemyLbl1.setFont(defaultFont);
			enemyLbl1.setForeground(Color.white);
			enemyLbl1.setVerticalTextPosition(JLabel.TOP);
			enemyLbl1.setHorizontalTextPosition(JLabel.CENTER);
			enemyLbl1.setIconTextGap(-25);
			 
			enemyHp1 = new JProgressBar(0,40);
			enemyHp1.setForeground(Color.green);
			enemyHp1.setBackground(Color.red);
			enemyHp1.setValue(40);
			
			enemyPnl.add(enemyLbl1);
			enemyPnl.add(enemyHp1);

			enemyPnl.setAlignmentY(Component.BOTTOM_ALIGNMENT); // Bottom Alignment for ground creatures and default for flying creatures
			
			enemiesPnl.add(enemyPnl);
			break;
		case 2:
			System.out.println("Amount: " + amountOfEnemies + "(Add Monster 1)");
			enemyPnl = new JPanel();
			enemyPnl.setLayout(new BoxLayout(enemyPnl, BoxLayout.Y_AXIS));
			enemyPnl.setOpaque(false);
			enemyPnl.setMaximumSize(new Dimension(200,300));
			
			enemyLbl2 = new JLabel();
			
			resizedImg = new ImageIcon(enemyPath+"Wolf.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			enemyIcon = new ImageIcon(resizedImg);
			enemyLbl2.setIcon(enemyIcon);
			enemyLbl2.setPreferredSize(new Dimension(200,200));
			enemyLbl2.setText("Wolf 2");
			enemyLbl2.setFont(defaultFont);
			enemyLbl2.setForeground(Color.white);
			enemyLbl2.setVerticalTextPosition(JLabel.TOP);
			enemyLbl2.setHorizontalTextPosition(JLabel.CENTER);
			enemyLbl2.setIconTextGap(-25);
			
			enemyHp2 = new JProgressBar(0,40);
			enemyHp2.setForeground(Color.green);
			enemyHp2.setBackground(Color.red);
			enemyHp2.setValue(30);

			enemyPnl.add(enemyLbl2);
			enemyPnl.add(enemyHp2);

			enemyPnl.setAlignmentY(Component.BOTTOM_ALIGNMENT); // Bottom Alignment for ground creatures and default for flying creatures

			enemiesPnl.add(Box.createRigidArea(new Dimension(50,0)));
			enemiesPnl.add(enemyPnl);
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
		case "Haupt":
	        dialogueText.setText("Lord Christoph stehe mir bei... Möge der Bessere gewinnen!\n\nKampf: Zum direkten Kampf wechseln.\nInventar: Öffnet das Inventar.\nFlüchten: Die Flucht ergreifen.");
	        break;
		case "Kampf":
	        dialogueText.setText("Angreifen: Direkter Angriff gegen einen Gegner.\nFähigkeit: Magie oder eine Fähigkeit einsetzen.\nZurück: Vorherige Auswahl wiederbringen.");
	        break;
		case "Angreifen":
			dialogueText.setText("Welcher Gegner soll angegriffen werden?\n\nOder drücken Sie auf Zurück; um zur Haupt-Auswahl zu gelangen.");
			break;
		case "Gegner 1":
			enemyName = enemyLbl1.getText();
			dialogueText.setText("Ihr greift " + enemyName + " an, und fügt diesem " + damageDealt + " Schaden zu!");
			break;
		case "Gegner 2":
			enemyName = enemyLbl2.getText();
			dialogueText.setText("Ihr greift " + enemyName + " an, und fügt diesem " + damageDealt + " Schaden zu!");
			break;
		case "EnemyTurn":
			dialogueText.setText("Die Wölfe schauen erstmal nur dumm aus der Wäsche raus.");
			break;
		case "PlayerTurn":
	        dialogueText.setText("Kampf: Zum direkten Kampf wechseln.\nInventar: Öffnet das Inventar.\nFlüchten: Die Flucht ergreifen.");
	        break;
		default:
			dialogueText.setText("");
		}
		
	}
	
	private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();
            if (buttonText == "Flüchten") {
            	soundController.stopMusicLoop();
            	soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");
            	dispose();
            }
            // Update the panel based on button text
            updateBtn(buttonText);
        }
	}
	
	private void updateBtn(String word) {
		btnPnl.removeAll();
		switch (word) {
		case "Zurück":
			addBtn("Kampf");
			addBtn("Inventar");
			addBtn("Flüchten");
			updateDialogue("Haupt");
			break;
		case "Kampf":
			addBtn("Angreifen");
			addBtn("Fähigkeiten");
			addBtn("Zurück");
			updateDialogue("Kampf");
			break;
		case "Angreifen":
			addBtn("Gegner 1");
			addBtn("Gegner 2");
			addBtn("Zurück");
			updateDialogue("Angreifen");
			break;
		case "Gegner 1":
			updateDialogue("Gegner 1");
			enemyHp1.setValue((enemyHp1.getValue()-damageDealt));
			addBtn("Weiter");
			currentTurn += 1;
			break;
		case "Gegner 2":
			updateDialogue("Gegner 2");
			enemyHp2.setValue((enemyHp2.getValue()-damageDealt));
			addBtn("Weiter");
			currentTurn += 1;
			break;
		case "Weiter":
			if (currentTurn >= 1) { // turn 0 is player, turn 1+ is enemy
				updateDialogue("EnemyTurn");
				addBtn("Weiter");
				currentTurn = 0;
			}
			else {
				updateDialogue("PlayerTurn");
				addBtn("Kampf");
				addBtn("Inventar");
				addBtn("Flüchten");
			}
			break;
		default:
			
			
		}
		// Refresh the panel
		btnPnl.revalidate();
		btnPnl.repaint();

	}
	
	private void addBtn(String word) { // Here is the order to add a button to the choices panel
		switch (word) {
		case "Zurück":
			JButton btnBack = new JButton("Zurück");
			btnBack.addActionListener(new ButtonClickListener());
			beautifyButtons(btnBack);
			btnPnl.add(btnBack);
			break;
		case "Weiter":
			JButton btnContinue = new JButton("Weiter");
			btnContinue.addActionListener(new ButtonClickListener());
			beautifyButtons(btnContinue);
			btnPnl.add(btnContinue);
			break;
		case "Angreifen":
			JButton btnAttack = new JButton("Angreifen");
			btnAttack.addActionListener(new ButtonClickListener());
			beautifyButtons(btnAttack);
			btnPnl.add(btnAttack);			
			break;
		case "Kampf":
			JButton btnAction = new JButton("Kampf");
			btnAction.addActionListener(new ButtonClickListener());
			beautifyButtons(btnAction);
			btnPnl.add(btnAction);			
			break;
		case "Gegner 1":
			JButton btnEnemy1 = new JButton("Gegner 1");
			beautifyButtons(btnEnemy1);
			if (enemyHp1.getValue() != 0) {
				btnEnemy1.addActionListener(new ButtonClickListener());				
			}
			else {
				btnEnemy1.setEnabled(false);
			}
			btnPnl.add(btnEnemy1);		
			break;
		case "Gegner 2":
			JButton btnEnemy2 = new JButton("Gegner 2");
			beautifyButtons(btnEnemy2);
			if (enemyHp2.getValue() != 0) {
				btnEnemy2.addActionListener(new ButtonClickListener());				
			}
			else {
				btnEnemy2.setEnabled(false);
			}
			btnPnl.add(btnEnemy2);			
			break;
		case "Fähigkeiten":
			JButton btnAbility = new JButton("Fähigkeiten");
			//btnAbility.addActionListener(new ButtonClickListener());
			btnAbility.setEnabled(false);
			beautifyButtons(btnAbility);
			btnPnl.add(btnAbility);			
			break;
		case "Inventar":
			JButton btnInventory = new JButton("Inventar");
			//btnInventory.addActionListener(new ButtonClickListener());
			btnInventory.setEnabled(false);
			beautifyButtons(btnInventory);
			btnPnl.add(btnInventory);			
			break;
		case "Flüchten":
			JButton btnEscape = new JButton("Flüchten");
			btnEscape.addActionListener(new ButtonClickListener());
			beautifyButtons(btnEscape);
			btnPnl.add(btnEscape);			
			break;
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
