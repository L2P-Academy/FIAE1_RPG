package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapView extends JFrame {

	JPanel buttonPanel, mapPanel, mainPanel;
	JLabel mapTitleLbl;
	JButton mapBtn, inventoryBtn, characterBtn, questsBtn;
	String mapImagePath = "res/img/map_start.jpg";
	Font gameFont;
	
	public MapView() {
		
		setTitle("Weltkarte");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create panels		
		buttonPanel = new JPanel(new FlowLayout());
		mapPanel = new BackGroundPanel(new ImageIcon(mapImagePath).getImage());
		mapPanel.setLayout(new BorderLayout());
		mainPanel = new JPanel(new BorderLayout());
		
		//create buttons
		gameFont = new Font("Old English Text MT", Font.BOLD, 32);
		mapBtn = new JButton("Weltkarte"); 
		mapBtn.setFont(gameFont);
		inventoryBtn = new JButton("Inventar");
		inventoryBtn.setFont(gameFont);
		characterBtn = new JButton("Charakter");
		characterBtn.setFont(gameFont);
		questsBtn = new JButton("Quests");
		questsBtn.setFont(gameFont);
		
		// add buttons to buttonpanel		
		buttonPanel.add(mapBtn);
		buttonPanel.add(inventoryBtn);
		buttonPanel.add(characterBtn);
		buttonPanel.add(questsBtn);
		
		// add panels to Window
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(mapPanel, BorderLayout.CENTER);
		getContentPane().add(mainPanel);
		setLocationRelativeTo(null);
		setVisible(true);
			
	}
	
}
