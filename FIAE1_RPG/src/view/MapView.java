package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SoundController;
import model.SerializationIDs;

public class MapView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.mapViewID;
	JPanel buttonPanel, mapPanel, mainPanel;
	JLabel mapTitleLbl;
	JButton inventoryBtn, characterBtn, questsBtn, settingsBtn, creditsBtn;
	String mapImagePath = "res/img/MapViewImages/map_start.jpg";
	String buttonPanelBackgroundPath = "res/img/MapViewImages/ButtonPanelBackground5.jpg";

	////////////////////////// TestButtons////////////////////////////
	JButton savegameBtn, combatViewBtn, journalViewBtn, creditViewBtn;

	SoundController soundController;

	Font gameFont;

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
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0)); // px Abstand horizontal und vertikal

		// create mapPanel with BackgroundImage
		mapPanel = new BackGroundPanel(new ImageIcon(mapImagePath).getImage());
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
		creditViewBtn = new JButton("Credits");
		creditViewBtn.setToolTipText("Hier passieren lustige Sachen");
		/////////////////////////////////////////// Actionlisterners//////////////////////////////////////////////////////
		savegameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SavegameView savegameView = new SavegameView();
			}
		});
		combatViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CombatView combatView = new CombatView(("Forest"));
				soundController.stopMusicLoop();
			}
		});
		
		// Add ActionListener to buttons
		inventoryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playFxSound("res/soundFX/fxEffects/cloth-inventory.wav");
				InventoryView inventoryView = new InventoryView();
			}
		});

		characterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				CharacterView characterView = new CharacterView(); // TODO: Controller anbinden
			}
		});

		questsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playFxSound("res/soundFX/fxEffects/turn_page.wav");
				JournalView journalView = new JournalView();
			}
		});

		settingsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsView settingsView = new SettingsView();
			}
		});
		
		creditViewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreditView creditView = new CreditView();				
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
		// buttonPanel.add(journalView);
		// buttonPanel.add(creditView);
		////////////////////////////////

		// Add panels to Window(mainPanel)
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(mapPanel, BorderLayout.CENTER);
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Custom JPanel with background image
	class BackGroundPanel extends JPanel {
		private static final long serialVersionUID = SerializationIDs.backGroundPanelID;
		private Image backgroundImage;

		public BackGroundPanel(Image backgroundImage) {
			this.backgroundImage = backgroundImage;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		}
	}

}
