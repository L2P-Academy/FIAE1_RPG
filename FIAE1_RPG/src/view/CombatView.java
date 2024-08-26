package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.Border;

import controller.SoundController;
import controller.CharacterController;
import controller.CombatController;
import controller.SQLController;
import model.BossModel;
import model.NpcModel;
import model.PlayerCharacterModel;
import model.RaceAbilityModel;
import model.SerializationIDs;

public class CombatView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.combatViewID;

	private int amountOfEnemies = 0, damageDealt = 10, currentTurn = 0;
	private int genderIndex;
	private String enemyName = "";
	private String battlemapPath = "res/img/BattleBackgrounds/";
	private String enemyPath = "res/img/EnemyPortraits/";
	private String battlemapImg = "";

	private JPanel backgroundPnl, spacerPnl, btnPnl, dialoguePnl, enemiesPnl, enemyPnl, heroPnl;
	private JLabel dialogueTopMsg, heroImgLbl, enemyLbl1, enemyLbl2;
	private JLabel[] enemyLbl = new JLabel[4];
	private JTextArea dialogueText;
	// private JButton continueBtn, clickBtn;
	private JProgressBar enemyHp1, enemyHp2, heroHp, heroMana;
	private JProgressBar[] enemyHp = new JProgressBar[4];
	private ImageIcon enemyIcon, heroIcon;
	private Image resizedImg;
	private Font defaultFont = new Font("Calisto MT", Font.PLAIN, 26);

	private PlayerCharacterModel characterModel;
	private NpcModel npcModel;
	// private AbilityModel<Enum<AbilityElement>> abililtyModel;
	private BossModel bossModel;
	private RaceAbilityModel raceAbilityModel;
	// private InventoryController inventoryController;
	private SQLController sqlController;
	private SoundController soundController;
	private CharacterController characterController;
	private CombatController combatController;
	private String[][] imagePaths = {
			{"res/img/CharacterPortraits/New_Race_Gender/new_human_male.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_human_female.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_human_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_elf_male.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_elf_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_elf_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_halfling_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_halfling_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_halfling_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_orc_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_orc_female.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_orc_divers.png"},
			{"res/img/CharacterPortraits/New_Race_Gender/new_goblin_male.png", 
				"res/img/CharacterPortraits/New_Race_Gender/new_goblin_female.png",
				"res/img/CharacterPortraits/New_Race_Gender/new_goblin_divers.png"}
	};

	public CombatView(SoundController soundController, CharacterController characterController) {		

		this.characterController = characterController;
		this.soundController = soundController;

		// Controllers
		sqlController = new SQLController();
		characterModel = characterController.getCharacter();
		combatController = new CombatController(characterModel, soundController);
		
		// frame initialize
		setTitle("Kampfuebung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		combatController.combatInitialize();

		// setup for view: adding battlebackground by ID, enemies by IDs, hero by data and adjust all panels/labels
		loadBattleBackgroundPath(1); // load battlemap by ID and update backgroundPnl
		createHeroPanel(); // load and prepare heroPnl
		prepareDialoguePnl(); // prepare dialoguePnl

		// frame finish-up
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
	
	private String getEnemyImagePath(String name) {
		// TODO: Here is the list of monster portraits, adjust if name is not equal to X_01 file
		switch (name) {
		case "Dire Wolf":
			return enemyPath + "wolf_02.png";
		default:
			return enemyPath + name + "_01.png";
		}
	}
	
	private void addEnemiesPnl() { // old
		amountOfEnemies = amountOfEnemies + 1;
		switch (amountOfEnemies) {
		case 1:
			System.out.println("Amount: " + amountOfEnemies + "(Add Monster 1)");
			enemyPnl = new JPanel();
			enemyPnl.setLayout(new BoxLayout(enemyPnl, BoxLayout.Y_AXIS));
			enemyPnl.setOpaque(false);
			enemyPnl.setMaximumSize(new Dimension(200, 300));

			enemyLbl1 = new JLabel();

			resizedImg = new ImageIcon(enemyPath + "Wolf.png").getImage().getScaledInstance(200, 200,
					Image.SCALE_SMOOTH);
			enemyIcon = new ImageIcon(resizedImg);
			enemyLbl1.setIcon(enemyIcon);
			enemyLbl1.setPreferredSize(new Dimension(200, 200));
			enemyLbl1.setText("Wolf");
			enemyLbl1.setFont(defaultFont);
			enemyLbl1.setForeground(Color.white);
			enemyLbl1.setVerticalTextPosition(JLabel.TOP);
			enemyLbl1.setHorizontalTextPosition(JLabel.CENTER);
			enemyLbl1.setIconTextGap(-25);

			enemyHp1 = new JProgressBar(0, 40);
			enemyHp1.setForeground(Color.green);
			enemyHp1.setBackground(Color.red);
			enemyHp1.setValue(40);

			enemyPnl.add(enemyLbl1);
			enemyPnl.add(enemyHp1);

			enemyPnl.setAlignmentY(Component.BOTTOM_ALIGNMENT); // Bottom Alignment for ground creatures and default for
																// flying creatures

			enemiesPnl.add(enemyPnl);
			break;
		case 2:
			System.out.println("Amount: " + amountOfEnemies + "(Add Monster 1)");
			enemyPnl = new JPanel();
			enemyPnl.setLayout(new BoxLayout(enemyPnl, BoxLayout.Y_AXIS));
			enemyPnl.setOpaque(false);
			enemyPnl.setMaximumSize(new Dimension(200, 300));

			enemyLbl2 = new JLabel();

			resizedImg = new ImageIcon(enemyPath + "Wolf.png").getImage().getScaledInstance(200, 200,
					Image.SCALE_SMOOTH);
			enemyIcon = new ImageIcon(resizedImg);
			enemyLbl2.setIcon(enemyIcon);
			enemyLbl2.setPreferredSize(new Dimension(200, 200));
			enemyLbl2.setText("Wolf 2");
			enemyLbl2.setFont(defaultFont);
			enemyLbl2.setForeground(Color.white);
			enemyLbl2.setVerticalTextPosition(JLabel.TOP);
			enemyLbl2.setHorizontalTextPosition(JLabel.CENTER);
			enemyLbl2.setIconTextGap(-25);

			enemyHp2 = new JProgressBar(0, 40);
			enemyHp2.setForeground(Color.green);
			enemyHp2.setBackground(Color.red);
			enemyHp2.setValue(30);

			enemyPnl.add(enemyLbl2);
			enemyPnl.add(enemyHp2);

			enemyPnl.setAlignmentY(Component.BOTTOM_ALIGNMENT); // Bottom Alignment for ground creatures and default for
																// flying creatures

			enemiesPnl.add(Box.createRigidArea(new Dimension(50, 0)));
			enemiesPnl.add(enemyPnl);
			break;
		default:
		}

	}

	// creates the hero Panel, loads character information
	private void createHeroPanel() {
		heroPnl = new JPanel();
		heroPnl.setLayout(new BoxLayout(heroPnl, BoxLayout.Y_AXIS));
		heroPnl.setOpaque(false);
		heroPnl.setMaximumSize(new Dimension(200, 1000));

		// add heroes name, picture and HP to the heroPanel
		resizedImg = new ImageIcon(selectHeroImgPath(characterModel.getRaceID()-1, characterModel.getGender())).getImage().getScaledInstance(200, 200,
				Image.SCALE_SMOOTH);
		heroIcon = new ImageIcon(resizedImg);
		heroImgLbl = new JLabel();
		heroImgLbl.setIcon(heroIcon);
		heroImgLbl.setText(characterModel.getName());
		heroImgLbl.setFont(defaultFont);
		heroImgLbl.setForeground(Color.white);
		heroImgLbl.setVerticalTextPosition(JLabel.TOP);
		heroImgLbl.setHorizontalTextPosition(JLabel.CENTER);
		heroImgLbl.setIconTextGap(-25);

		heroHp = new JProgressBar(0, characterModel.getMaxHP());
		heroHp.setForeground(Color.green);
		heroHp.setBackground(Color.red);
		heroHp.setValue(characterModel.getCurrentHP());
		heroHp.setMaximumSize(new Dimension(400, 20));
		heroMana = new JProgressBar(0, characterModel.getMaxMana());
		heroMana.setForeground(Color.blue);
		heroMana.setBackground(Color.pink);
		heroMana.setValue(characterModel.getCurrentMana());
		heroMana.setMaximumSize(new Dimension(400, 20));

		heroPnl.add(Box.createRigidArea(new Dimension(0, 100)));
		heroPnl.add(heroImgLbl);
		heroPnl.add(heroHp);
		heroPnl.add(heroMana);
		backgroundPnl.add(heroPnl, BorderLayout.EAST);
	}

	private void updateDialogue(String dialogue) {
		switch (dialogue) {
		case "Haupt":
			dialogueText.setText(
					"Lord Christoph stehe mir bei... Möge der Bessere gewinnen!\n\nKampf: Zum direkten Kampf wechseln.\nInventar: Öffnet das Inventar.\nFlüchten: Die Flucht ergreifen.");
			break;
		case "Kampf":
			dialogueText.setText(
					"Angreifen: Direkter Angriff gegen einen Gegner.\nFähigkeit: Magie oder eine Fähigkeit einsetzen.\nZurück: Vorherige Auswahl wiederbringen.");
			break;
		case "Angreifen":
			dialogueText.setText(
					"Welcher Gegner soll angegriffen werden?\n\nOder drücken Sie auf Zurück; um zur Haupt-Auswahl zu gelangen.");
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
			dialogueText.setText(
					"Kampf: Zum direkten Kampf wechseln.\nInventar: Öffnet das Inventar.\nFlüchten: Die Flucht ergreifen.");
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
			enemyHp1.setValue((enemyHp1.getValue() - damageDealt));
			addBtn("Weiter");
			currentTurn += 1;
			break;
		case "Gegner 2":
			updateDialogue("Gegner 2");
			enemyHp2.setValue((enemyHp2.getValue() - damageDealt));
			addBtn("Weiter");
			currentTurn += 1;
			break;
		case "Weiter":
			if (currentTurn >= 1) { // turn 0 is player, turn 1+ is enemy
				updateDialogue("EnemyTurn");
				addBtn("Weiter");
				currentTurn = 0;
			} else {
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
			} else {
				btnEnemy1.setEnabled(false);
			}
			btnPnl.add(btnEnemy1);
			break;
		case "Gegner 2":
			JButton btnEnemy2 = new JButton("Gegner 2");
			beautifyButtons(btnEnemy2);
			if (enemyHp2.getValue() != 0) {
				btnEnemy2.addActionListener(new ButtonClickListener());
			} else {
				btnEnemy2.setEnabled(false);
			}
			btnPnl.add(btnEnemy2);
			break;
		case "Fähigkeiten":
			JButton btnAbility = new JButton("Fähigkeiten");
			// btnAbility.addActionListener(new ButtonClickListener());
			btnAbility.setEnabled(false);
			beautifyButtons(btnAbility);
			btnPnl.add(btnAbility);
			break;
		case "Inventar":
			JButton btnInventory = new JButton("Inventar");
			// btnInventory.addActionListener(new ButtonClickListener());
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
	
	private String selectHeroImgPath(int raceID, String gender) {
		
		switch (gender) {
		case "m": 
			genderIndex = 0;			
			break;
		case "w":
			genderIndex = 1;
			break;
		case "d":
			genderIndex = 2;
			break;
		default:
			break;
		}
		// Image Paths
		return imagePaths[raceID][genderIndex];	
	}
		
	private void prepareMonsters() {
		System.out.println("Prepare to create monsters... ");
		NpcModel wildboar = new NpcModel(13, 10, 50, 10, 5, 0, null, "Wildboar");
		NpcModel wolf = new NpcModel(7, 20, 100, 20, 5, 0, null, "Wolf");
		NpcModel direwolf = new NpcModel(14, 50, 200, 30, 15, 0, null, "Dire Wolf");
		System.out.println("Added monsters to the List");
	}
	
	private void loadBattleBackgroundPath(int id) {
		// TODO: List of battleground imagepaths by id
		switch (id) {
		case 1:
				battlemapImg = "Forest.jpg";
		default:
			break;
		}

		// add backgroundPnl and adjust battleground img
		backgroundPnl = new BackGroundPanel(new ImageIcon(battlemapPath + battlemapImg).getImage());
		backgroundPnl.setLayout(new BorderLayout());
		getContentPane().add(backgroundPnl);
	}
	
	private void prepareDialoguePnl() {

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
		

		// add buttons to the btnPnl
		btnPnl = new JPanel();
		btnPnl.setLayout(new BoxLayout(btnPnl, BoxLayout.Y_AXIS));
		updateBtn("Zurück");
		

		// filling dialoguePanel (bottom Panel)
		dialoguePnl.add(dialogueText, BorderLayout.CENTER);
		dialoguePnl.add(dialogueTopMsg, BorderLayout.NORTH);
		dialoguePnl.add(btnPnl, BorderLayout.EAST);
		backgroundPnl.add(dialoguePnl, BorderLayout.SOUTH);
	}
}

