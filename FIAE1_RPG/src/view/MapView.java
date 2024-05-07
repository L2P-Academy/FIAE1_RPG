package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MapView extends JFrame {

	JPanel buttonPanel, mapPanel, mainPanel;
	JLabel mapTitleLbl;
	JButton mapBtn, inventoryBtn, characterBtn, questsBtn, settingsBtn; 
	String mapImagePath = "res/img/MapViewImages/map_start.jpg";
	String gearImagePath = "C:\\Users\\User\\git\\FIAE1_DnD\\FIAE1_RPG\\res\\img\\MapViewImages\\icon_settings3.png"; 
	String buttonPanelBackgroundPath = "res/img/MapViewImages/dnd_background.jpg"; 
	
	Clip soundFileMap;
	
	Font gameFont;
	
	public MapView() {
		
		setTitle("Weltkarte");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//Create Panels
		//create mainPanel
		mainPanel = new JPanel(new BorderLayout());
		  
		//create buttonPanel
		//buttonPanel = new JPanel(new FlowLayout());
		buttonPanel = new BackGroundPanel(new ImageIcon(buttonPanelBackgroundPath).getImage());
		buttonPanel.setLayout(new FlowLayout());
		
		//create mapPanel
		mapPanel = new BackGroundPanel(new ImageIcon(mapImagePath).getImage());
		mapPanel.setLayout(new BorderLayout());
		mainPanel = new JPanel(new BorderLayout());
				
		//Create Buttons
		gameFont = new Font("Old English Text MT", Font.BOLD, 32);
		mapBtn = new JButton("Weltkarte"); 
		mapBtn.setFont(gameFont);
		inventoryBtn = new JButton("Inventar");
		inventoryBtn.setFont(gameFont);
		characterBtn = new JButton("Charakter");
		characterBtn.setFont(gameFont);
		questsBtn = new JButton("Quests");
		questsBtn.setFont(gameFont);
		//Create Gear-Icon for Settings-Button
		ImageIcon gearIcon = new ImageIcon(new ImageIcon("C:\\Users\\User\\git\\FIAE1_DnD\\FIAE1_RPG\\res\\img\\MapViewImages\\icon_settings3.png").getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));;
		//Create the SettingsBtn with GearIcon
		settingsBtn = new JButton(gearIcon);
		
		
		
		// Add action listeners to buttons
		mapBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		MapView mapView = new MapView();
		
		dispose();					
		}
		});
		
		inventoryBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		InventoryView inventoryView = new InventoryView();
		dispose();
		}
		});
					
		characterBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		CharacterView characterView = new CharacterView();
		dispose();
		}
		});
					 
		questsBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		QuestView questView = new QuestView();
		dispose();
		}
		});
					 
		settingsBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
							//// LOLOL /////
							////////////////
		}
		});
					 
		// add buttons to buttonPanel		
		buttonPanel.add(mapBtn);
		buttonPanel.add(inventoryBtn);
		buttonPanel.add(characterBtn);
		buttonPanel.add(questsBtn);
		////add settingsBtn to buttonPanel
		buttonPanel.add(settingsBtn);
		
		// add panels to Window
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(mapPanel, BorderLayout.CENTER);
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);	
	}
		
		//Custom JPanel with background image
		class BackGroundPanel extends JPanel {
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



