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
	JButton inventoryBtn, characterBtn, questsBtn, settingsBtn, creditsBtn;
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
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20)); // px Abstand horizontal und vertikal

		// create mapPanel with BackgroundImage
		mapPanel = new MapAnimationPanel(new ImageIcon(mapImagePath).getImage());
		mapPanel.setLayout(new BorderLayout());
		mainPanel = new JPanel(new BorderLayout());

		// Create Buttons
		gameFont = new Font("Old English Text MT", Font.BOLD, 32);

		// Create inventoryBtn with bagIcon
		ImageIcon bagIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/icon_bag2.png").getImage()
				.getScaledInstance(44, 44, Image.SCALE_SMOOTH));
		
		inventoryBtn = new JButton(bagIcon);
		inventoryBtn.setContentAreaFilled(false); // Make button transparent
		inventoryBtn.setBorderPainted(false); // Remove border
		inventoryBtn.setToolTipText("Inventar"); // hoverText

		// Create CharacterBtn with characterIcon
		ImageIcon characterIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/icon_character2.png").getImage()
				.getScaledInstance(44, 44, Image.SCALE_SMOOTH));
		
		characterBtn = new JButton(characterIcon);
		characterBtn.setContentAreaFilled(false); // Make button transparent
		characterBtn.setBorderPainted(false); // Remove border
		characterBtn.setToolTipText("Charakter");

		// Create questsBtn with questsIcon
		ImageIcon questsIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/icon_quests2.png").getImage()
				.getScaledInstance(44, 44, Image.SCALE_SMOOTH));
		
		questsBtn = new JButton(questsIcon);
		questsBtn.setContentAreaFilled(false); // Make button transparent
		questsBtn.setBorderPainted(false); // Remove border
		questsBtn.setToolTipText("Quests");

		// Create Gear-Icon for Settings-Button
		ImageIcon gearIcon = new ImageIcon(new ImageIcon("res/img/MapViewImages/icon_settings3.png").getImage()
				.getScaledInstance(44, 44, Image.SCALE_SMOOTH));
		
		settingsBtn = new JButton(gearIcon);
		settingsBtn.setContentAreaFilled(false); // Make button transparent
		settingsBtn.setBorderPainted(false); // Remove border
		settingsBtn.setToolTipText("Einstellungen");

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////// Buttons zum Testen
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////
		// Create SavegameViewButton
		savegameBtn = new JButton("Save");
		savegameBtn.setToolTipText("Save");
		// Create CombatViewButton
		combatViewBtn = new JButton("Combat");
		combatViewBtn.setToolTipText("Combat");
		// Create Journal ViewButton
		// journalViewBtn = new JButton("Journal");
		// journalViewBtn.setToolTipText("Journal");
		// Create CreditViewButton
		creditsBtn = new JButton("Credits");
		creditsBtn.setToolTipText("Hier passieren lustige Sachen");
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
		//// add settingsBtn to buttonPanel
		buttonPanel.add(settingsBtn);
		/////////// Testbuttons//////////
		buttonPanel.add(savegameBtn);
		buttonPanel.add(combatViewBtn);
		buttonPanel.add(creditsBtn);
		////////////////////////////////

		// Add panels to Window(mainPanel)
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(mapPanel, BorderLayout.CENTER);
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
		
		// waypoints / targets for movement
		waypoints = new ArrayList<>();
		waypoints.add(new Waypoint(1, 200, 50, "res/img/MapViewImages/icon_bag4.png")); // Start
//		waypoints.add(new Waypoint(350, 100)); // Waldrand
//		waypoints.add(new Waypoint(500, 100)); // Grabmal
//		waypoints.add(new Waypoint(350, 400)); // Geisterwald
//		waypoints.add(new Waypoint(400, 500)); // Hexensee
//		waypoints.add(new Waypoint(200, 600)); // Trollbrücke
//		waypoints.add(new Waypoint(250, 750)); // Banditenlager
//		waypoints.add(new Waypoint(600, 850)); // Auenhain
//		waypoints.add(new Waypoint(1000, 950)); // Schmied
//		waypoints.add(new Waypoint(950, 600)); // Dorfbrücke
//		waypoints.add(new Waypoint(650, 400)); // Travincal
//		waypoints.add(new Waypoint(150, 350)); // Geisterfriedhof
//		waypoints.add(new Waypoint(850, 250)); // Tempel
//		waypoints.add(new Waypoint(700, 600)); // Drachenhort
//		waypoints.add(new Waypoint(1300, 500)); // Bauernhof
//		waypoints.add(new Waypoint(1500, 750)); // Hafen
//		waypoints.add(new Waypoint(1700, 250)); // Stadtmauer
//		waypoints.add(new Waypoint(1650, 100)); // Hauptstadt TO BE NAMED
//		waypoints.add(new Waypoint(1900, 100)); // Fährhafen
		
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
