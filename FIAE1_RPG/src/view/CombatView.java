package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	private int genderIndex;
	private String battlemapPath = "res/img/BattleBackgrounds/";
	private String enemyPath = "res/img/EnemyPortraits/";
	private String battlemapImg = "";

	private JPanel backgroundPnl, spacerPnl1, spacerPnl2, btnPnl, dialoguePnl, enemiesPnl, enemyPnl, heroPnl;
	private JLabel heroImgLbl, selectedNpcLbl;
	private JTextArea combatLog;
	private JProgressBar heroHp, heroMana;
	private ImageIcon heroIcon;
	private Image resizedImg;
	private Font defaultFont = new Font("Calisto MT", Font.PLAIN, 26);
	public JButton attackBtn, inventoryBtn, abilityBtn, escapeBtn;

	private PlayerCharacterModel characterModel;
	private NpcModel selectedNpc;
	// private AbilityModel<Enum<AbilityElement>> abililtyModel;
	private BossModel bossModel;
	private RaceAbilityModel raceAbilityModel;
	private Map<NpcModel, JProgressBar> npcProgressBars;
	
	private SQLController sqlController;
	private SoundController soundController;
	private CharacterController characterController;
	private CombatController combatController;
	
	private List<NpcModel> enemiesList;
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
		combatController = new CombatController(characterController.getCharacter(), soundController, this);
		
		// frame initialize
		setTitle("Kampfuebung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// Combat Initialize
		npcProgressBars = new HashMap<>();		
		enemiesList = combatController.getCombatantsNpcList();

		// setup for view: adding battlebackground by ID, enemies by IDs, hero by data and adjust all panels/labels
		loadBattleBackgroundPath(1); // load battlemap by ID and update backgroundPnl
		createHeroPanel(); // load and prepare heroPnl
		createEnemiesPnl(enemiesList);
		prepareDialoguePnl();

		// frame finish-up
		setLocationRelativeTo(null);
		setVisible(true);

		// play Music in background after rendering
		soundController.playMusicLoop("res/soundFX/music/Combat_Music.wav");		
		
		// ActionListeners		
		attackBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playFxSound("res/soundFX/fxEffects/sword_sound.wav");
				combatController.processPlayerAction("Angreifen");
			}
		});
		
		escapeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				combatController.processPlayerAction("Flüchten");
			}
		});
	}
	
	// Mapping Model to ProgressBar
	public void updateNpcHealth(NpcModel npcModel) {
		JProgressBar healthBar = npcProgressBars.get(npcModel);
		if (healthBar != null) {
			healthBar.setValue(npcModel.getHp());
			revalidate();
			repaint();
		}
	}
	
	// Mapping Model to ProgressBar
	public void updatePlayerHealth(PlayerCharacterModel character) {
			heroHp.setValue(character.getCurrentHP());
			heroHp.setString(characterModel.getCurrentHP() + "/" + characterModel.getMaxHP());
			revalidate();
			repaint();
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
	
	private void createEnemiesPnl(List<NpcModel> npcList) {
		// Spacing for better UI experience
		spacerPnl1 = new JPanel();
		spacerPnl1.setPreferredSize(new Dimension(100, 0));
		spacerPnl1.setOpaque(false);
		spacerPnl2 = new JPanel();
		spacerPnl2.setPreferredSize(new Dimension(200, 0));
		spacerPnl2.setOpaque(false);
		
		// create all enemies in seperate panels
		enemiesPnl = new JPanel();
		enemiesPnl.setLayout(new BoxLayout(enemiesPnl, BoxLayout.X_AXIS));
		enemiesPnl.setOpaque(false);
		// Get all NPCModels from npcList and retrieve information about every single enemy
		for (NpcModel npcModel : npcList) {
			enemyPnl = new JPanel();
			enemyPnl.setLayout(new BoxLayout(enemyPnl, BoxLayout.Y_AXIS));
			enemyPnl.setOpaque(false);
			enemyPnl.setMaximumSize(new Dimension(350, 450));
			enemyPnl.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			
			JLabel enemyImgLbl = new JLabel();
			JLabel enemyNameLbl = new JLabel();
		
			Image resizedImg = new ImageIcon(enemyPath + npcModel.getName() + ".png").getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
			ImageIcon enemyIcon = new ImageIcon(resizedImg);
			enemyImgLbl.setIcon(enemyIcon);
			enemyImgLbl.setPreferredSize(new Dimension(350, 350));
			enemyImgLbl.setForeground(Color.white);
			enemyImgLbl.setHorizontalAlignment(JLabel.CENTER);
			
			enemyNameLbl.setText(npcModel.getName());
			enemyNameLbl.setFont(defaultFont);
			enemyNameLbl.setHorizontalAlignment(JLabel.CENTER);
			enemyNameLbl.setForeground(Color.white);
			
			// Health Bar
			JProgressBar enemyHp = new JProgressBar(0, npcModel.getHp());
			enemyHp.setForeground(Color.green);
			enemyHp.setBackground(Color.red);
			enemyHp.setValue(npcModel.getHp());
			enemyHp.setSize(new Dimension(350, 40));
			enemyHp.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			// ProgressBar Mapping
			npcProgressBars.put(npcModel, enemyHp);
			System.out.println("Mapped " + npcModel + "to" + enemyHp);
			
			// Event-Listener for enemy selection
			enemyImgLbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selectNpcForCombat(npcModel, enemyImgLbl);
				}
			});
			
			// add components to singleEnemyPnl
			enemyPnl.add(enemyNameLbl);
			enemyPnl.add(enemyImgLbl);
			enemyPnl.add(enemyHp);
			
			enemiesPnl.add(enemyPnl);
			enemiesPnl.add(Box.createRigidArea(new Dimension(50, 50)));
			
		}
		backgroundPnl.add(spacerPnl1, BorderLayout.WEST);
		backgroundPnl.add(spacerPnl2, BorderLayout.SOUTH);
		backgroundPnl.add(enemiesPnl, BorderLayout.CENTER);
	}
	
	// select Npcs to fight
	public void selectNpcForCombat(NpcModel npcModel, JLabel npcLabel) {
		if (selectedNpcLbl != null) {
			selectedNpcLbl.setBorder(null);
		}
		
		// set Npc
		selectedNpc = npcModel;
		selectedNpcLbl = npcLabel;
		
		combatController.selectNpc(npcModel);
		
		System.out.println("NPC ausgewählt: " + npcModel.getName() + " und hat noch Leben: " + npcModel.getHp());
		
		// highlight the selected Npc
		Border highlighter = BorderFactory.createLineBorder(Color.RED);
		selectedNpcLbl.setBorder(highlighter);
		
		selectedNpcLbl.revalidate();
		selectedNpcLbl.repaint();
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
		heroHp.setStringPainted(true);
		heroHp.setString(characterModel.getCurrentHP() + "/" + characterModel.getMaxHP());
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

		int numberOfEnemies = combatController.getNumberOfEnemies();
		List<NpcModel> combatantsNpcList = combatController.getCombatantsNpcList();
		Dimension btnDimension = new Dimension(200, 25);
		dialoguePnl = new JPanel(); // will hold the title, text and buttons
		dialoguePnl.setLayout(new BorderLayout());
		dialoguePnl.setPreferredSize(new Dimension(WIDTH, 250));
		
		StringBuilder introText = new StringBuilder("Holy Sh*t! Ein Kampf steht mir bevor! \nVor mir ");
		
		// Text / Dialogue
		combatLog = new JTextArea();
		
		if (numberOfEnemies == 1) {
			introText.append("steht ein/e ").append(combatantsNpcList.get(0).getName()).append("!");
		} else {
			introText.append("stehen mehrere Gegner: \n");
			for (int i = 0; i < combatantsNpcList.size(); i++) {
				if (i == combatantsNpcList.size() - 1) {
					introText.append("und ein(e) ").append(combatantsNpcList.get(i).getName()).append("!");
				} else {
					introText.append("ein(e) ").append(combatantsNpcList.get(i).getName()).append(", ");
				}
			}
		}
		
		combatLog.setText(introText.toString());
		combatLog.setFont(defaultFont); // Schriftart anpassen
		combatLog.setLineWrap(true); // Textumbruch aktivieren
		combatLog.setWrapStyleWord(true); // Wortumbruch aktivieren
		combatLog.setEditable(false); // Text nicht editierbar machen
		combatLog.setPreferredSize(new Dimension(400, 120));
		combatLog.setBackground(new Color(245, 245, 220));
		
		btnPnl = new JPanel();
		btnPnl.setLayout(new BoxLayout(btnPnl, BoxLayout.Y_AXIS));
		
		attackBtn = new JButton("Angreifen");
		beautifyButtons(attackBtn);		
		inventoryBtn = new JButton("Inventar");
		beautifyButtons(inventoryBtn);
		abilityBtn = new JButton("Fähigkeiten");
		beautifyButtons(abilityBtn);
		escapeBtn = new JButton("Flüchten");
		beautifyButtons(escapeBtn);

		
		btnPnl.add(attackBtn);
		btnPnl.add(abilityBtn);
		btnPnl.add(inventoryBtn);
		btnPnl.add(escapeBtn);

		// filling dialoguePanel (bottom Panel)
		dialoguePnl.add(combatLog, BorderLayout.CENTER);
		dialoguePnl.add(btnPnl, BorderLayout.EAST);
		backgroundPnl.add(dialoguePnl, BorderLayout.SOUTH);
	}
	
	public void setTextToCombatLog(String message) {
		combatLog.setText(message);
	}

	public JTextArea getDialogueText() {
		return combatLog;
	}

	public void setDialogueText(JTextArea dialogueText) {
		this.combatLog = dialogueText;
	}
}

