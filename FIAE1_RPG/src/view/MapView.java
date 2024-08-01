package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.SoundController;
import model.SerializationIDs;
import view.AnimationComponents.MapAnimationPanel;
import view.AnimationComponents.MapCharacter;
import view.AnimationComponents.Waypoint;

public class MapView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.mapViewID;
	JPanel buttonPanel, mainPanel;
	JLabel mapTitleLbl;
	JButton characterBtn, inventoryBtn, questsBtn, settingsBtn, saveBtn, creditsBtn;
	String mapImagePath = "res/img/MapViewImages/Map_Start_Suggestion.png";
	String buttonPanelBackgroundPath = "res/img/MapViewImages/ButtonPanelBackground6.png";

	////////////////////////// TestButtons////////////////////////////
	JButton savegameBtn, combatViewBtn, journalViewBtn;

	SoundController soundController;

	Font gameFont;
	List<Waypoint> waypoints;
	MapCharacter mapCharacter;
	MapAnimationPanel mapPanel;

	public MapView() {

		setTitle("Weltkarte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		soundController = new SoundController();

		// Create Panels
		mainPanel = new JPanel(new BorderLayout());

		// Create BackgroundMusic
		soundController.playMusicLoop("res/soundFX/music/Map_Music.wav");

		// Create buttonPanel with BackgroundImage
		buttonPanel = new BackGroundPanel(new ImageIcon(buttonPanelBackgroundPath).getImage());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10)); // px Abstand horizontal und vertikal

		// create mapPanel with BackgroundImage
		mapPanel = new MapAnimationPanel(new ImageIcon(mapImagePath).getImage());
		mapPanel.setLayout(new BorderLayout());
		mainPanel = new JPanel(new BorderLayout());

		// Create Buttons
		gameFont = new Font("Old English Text MT", Font.BOLD, 32);

		// Create inventoryBtn with bagIcon
		ImageIcon bagIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/New_Button_Panel/new_inventory_icon.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		inventoryBtn = new JButton(bagIcon);
		inventoryBtn.setContentAreaFilled(false); // Make button transparent
		inventoryBtn.setBorderPainted(false); // Remove border
		inventoryBtn.setToolTipText("Inventar"); // hoverText

		// Create CharacterBtn with characterIcon
		ImageIcon characterIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/New_Button_Panel/new_character_icon.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		characterBtn = new JButton(characterIcon);
		characterBtn.setContentAreaFilled(false); // Make button transparent
		characterBtn.setBorderPainted(false); // Remove border
		characterBtn.setToolTipText("Charakter");

		// Create questsBtn with questsIcon
		ImageIcon questsIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/New_Button_Panel/new_quest_icon.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		questsBtn = new JButton(questsIcon);
		questsBtn.setContentAreaFilled(false); // Make button transparent
		questsBtn.setBorderPainted(false); // Remove border
		questsBtn.setToolTipText("Quests");

		// Create Gear-Icon for Settings-Button
		ImageIcon gearIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/New_Button_Panel/new_settings_icon.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		settingsBtn = new JButton(gearIcon);
		settingsBtn.setContentAreaFilled(false); // Make button transparent
		settingsBtn.setBorderPainted(false); // Remove border
		settingsBtn.setToolTipText("Einstellungen");
		
		// Create SavegameViewButton
		
		ImageIcon saveIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/New_Button_Panel/new_save_icon.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		savegameBtn = new JButton(saveIcon);
		savegameBtn.setContentAreaFilled(false); // Make button transparent
		savegameBtn.setBorderPainted(false); // Remove border
		savegameBtn.setToolTipText("Speichern");

		// Create CreditViewButton
		ImageIcon creditIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/New_Button_Panel/new_credit_icon.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		
		creditsBtn = new JButton(creditIcon);
		creditsBtn.setContentAreaFilled(false); // Make button transparent
		creditsBtn.setBorderPainted(false); // Remove border
		creditsBtn.setToolTipText("Credits");
		

		// Create CombatViewButton
		combatViewBtn = new JButton("Combat");
		combatViewBtn.setContentAreaFilled(false); // Make button transparent
		combatViewBtn.setBorderPainted(false); // Remove border
		combatViewBtn.setToolTipText("Kampf");


		/////////////////////////////////////////// Actionlisterners//////////////////////////////////////////////////////
		savegameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SavegameView();
			}
		});
		combatViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CombatView("Forest01.jpg");
				soundController.stopMusicLoop();
			}
		});
		
		// Add ActionListener to buttons
		inventoryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playFxSound("res/soundFX/fxEffects/cloth-inventory.wav");
				new InventoryView();
			}
		});

		characterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				new CharacterView();
			}
		});

		questsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playFxSound("res/soundFX/fxEffects/turn_page.wav");
				new JournalView();
			}
		});

		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingsView();
			}
		});
		
		creditsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreditView();				
			}
		});

		// Add buttons to buttonPanel
		
		buttonPanel.add(inventoryBtn);
		buttonPanel.add(characterBtn);
		buttonPanel.add(questsBtn);
		buttonPanel.add(settingsBtn);
		buttonPanel.add(savegameBtn);
		buttonPanel.add(combatViewBtn);
		buttonPanel.add(creditsBtn);
		

		// Add panels to Window(mainPanel)
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(mapPanel, BorderLayout.CENTER);
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
		
		// waypoints / targets for movement
		waypoints = new ArrayList<>();

		// Start
		waypoints.add(new Waypoint(1, 200, 50, "res/img/MapViewImages/01_Taverne.png"));
		// Waldrand
		waypoints.add(new Waypoint(2, 350, 100, "res/img/MapViewImages/02_Expedition.png"));
		// Grabmal
		waypoints.add(new Waypoint(3, 520, 80, "res/img/MapViewImages/03_Grabmahl.png"));
		// Geisterwald
		waypoints.add(new Waypoint(4, 350, 400, "res/img/MapViewImages/04_Geisterwald.png"));
		// Hexensee
		waypoints.add(new Waypoint(5, 400, 500, "res/img/MapViewImages/05_Kraeutersammler.png"));
		// Trollbrücke
		waypoints.add(new Waypoint(6, 170, 580, "res/img/MapViewImages/06_trollbruecke.png"));
		// Banditenlager
		waypoints.add(new Waypoint(7, 250, 750, "res/img/MapViewImages/07_Banditen.png"));
		// Auenhain
		waypoints.add(new Waypoint(8, 550, 820, "res/img/MapViewImages/08_Auenhain.png"));
		// Schmied
		waypoints.add(new Waypoint(9, 990, 910, "res/img/MapViewImages/09_Schmied.png"));
		// Dorfbrücke
		waypoints.add(new Waypoint(10, 900, 600, "res/img/MapViewImages/10_Dorfbruecke.png"));
		// Travincal
		waypoints.add(new Waypoint(11, 610, 360, "res/img/MapViewImages/11_Travincal.png"));
		// Geisterfriedhof
		waypoints.add(new Waypoint(12, 150, 350, "res/img/MapViewImages/12_geisterfriedhof.png"));
		// Tempel
		waypoints.add(new Waypoint(13, 850, 250, "res/img/MapViewImages/13_Tempel.png"));
		// Drachenhort
		waypoints.add(new Waypoint(14, 700, 600, "res/img/MapViewImages/14_Drache.png"));
		// Bauernhof
		waypoints.add(new Waypoint(15, 1300, 500, "res/img/MapViewImages/15_Farmhaeuser.png"));
		// Hafen
		waypoints.add(new Waypoint(16, 1570, 750, "res/img/MapViewImages/16_Hafen.png"));
		// Stadtmauer
		waypoints.add(new Waypoint(17, 1630, 250, "res/img/MapViewImages/17_Stadtmauer.png"));
		// Hauptstadt TO BE NAMED
		waypoints.add(new Waypoint(18, 1520, 100, "res/img/MapViewImages/18_Stadt2.png"));
		// Fährhafen
		waypoints.add(new Waypoint(19, 1870, 100, "res/img/MapViewImages/19_Faehrhafen.png"));

		
		// Starting position for map character
		mapCharacter = new MapCharacter(90, 20);
		
		// set waypoints and character for mapAnimationPanel
		mapPanel.setWayPoints(waypoints);
		mapPanel.setMapCharacter(mapCharacter);
		
		mapPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Waypoint closestWaypoint = null;
				double minDistance = Double.MAX_VALUE;
				for (Waypoint waypoint : waypoints) {
					double distance = waypoint.distanceTo(e.getX(), e.getY());
					if (distance < minDistance) {
						minDistance = distance;
						closestWaypoint = waypoint;						
					}
				}
				if (closestWaypoint != null) {
					mapCharacter.moveTo(closestWaypoint.getX(), closestWaypoint.getY());
				}				
			}
		});
		// Animation timer for map updates
		Timer timer = new Timer(30, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				mapCharacter.update();
				mapPanel.repaint();
//				if (mapCharacter.isAtTarget()) {
//					new QuestView();
//				}
			}
		});
		timer.start();
	}

}
