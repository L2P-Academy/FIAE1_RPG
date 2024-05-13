package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class CombatView extends JFrame{ //implements ActionListener{
	
	/* things to add when going in combat
		Check for combatEnviroment, check for enemies
	
	
	*/
	JPanel topPanel, botPanel, cornerPanel, partyPanel;
	JLabel dialogueLabel, 
		   hero1Label, hero2Label, hero3Label, hero4Label,
		   hero1Profile, hero2Profile, hero3Profile, hero4Profile,
		   hero1Name, hero2Name, hero3Name, hero4Name;
	JButton btn1, btn2, btn3, btn4, btn5;
	Font btnFont, textFont;
	String hero1NameString = "Simply Cedric"; // best under 20 Characters
	String hero2NameString = "Brother Rida";
	String hero3NameString = "Natalia Longfinger";
	String hero4NameString = "Prof. Dr. Elias";
	String hero1PicString = "res/img/IberianFighter.jpg";
	String hero2PicString = "res/img/MorrocanBarbarian.jpg";
	String hero3PicString = "res/img/BlondeWizard.jpg";
	String hero4PicString = "res/img/ProfElias.jpg";
	String spaceBackground = "res/img/spaceBackground.jpg";
	String combatBackground = "";
	Border border = BorderFactory.createLineBorder(Color.black, 1);
	boolean isFightOver = false; // used to check wether the fight is over, can be happen after reaching 0 HP to any side or end via event.
	
	public CombatView(String combatBackground) {
		// Font settings
		btnFont = new Font("Old English Text MT", Font.BOLD, 24);
		textFont = new Font("Calisto MT", Font.PLAIN, 32);
		
		// adding main Panels
		addTopPanel(combatBackground);
		addBotPanel();
		showButton(btn1, "Weiter");
		
		this.setTitle("Combat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200,800);
		this.setResizable(false);
		//
		
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
	}	
	
		// this method exists to add 5 buttons to the cornerPanel
	 private void addButton(JButton btn, int btnPos){
		//btn.addActionListener(this);
		btn.setFont(btnFont);
		btn.setBounds(5,btnPos,175,40);
		btn.setFocusable(false);
		btn.setVisible(false);
		cornerPanel.add(btn);
	 }
	 	// this method is used to show buttons when required
	 private void showButton(JButton btn, String btnText) {
			btn.setText(btnText);
			btn.setVisible(true);
	 }

	 private void addTopPanel(String combatBackground) {
		this.combatBackground = "res/img/CombatBG_" + combatBackground + ".png";
		this.topPanel = new BackGroundPanel(new ImageIcon(this.combatBackground).getImage());
		this.topPanel.setBounds(0, 0, 1186, 513);
		this.topPanel.setBorder(border);
		getContentPane().add(topPanel);
		//this.topPanel.setVisible(false);
	 }
	 private void addBotPanel() {
		botPanel = new BackGroundPanel(new ImageIcon(spaceBackground).getImage());
		//botPanel.setBackground(Color.gray);
		botPanel.setBounds(0, 511, 1186, 250);
		botPanel.setBorder(border);
		addCornerPanel();
		preparePartyPanel();
		getContentPane().add(botPanel);
		//botPanel.setVisible(false);
	 }
	 private void preparePartyPanel() {
		partyPanel = new JPanel(null);
		partyPanel.setBackground(new Color(20,20,50));
		partyPanel.setBounds(25, 511, 950, 250);
		partyPanel.setBorder(border);
		botPanel.add(partyPanel);
		addCharacterPanel(hero1Label, hero1PicString, hero1Profile, hero1Name, hero1NameString, 15);
		addCharacterPanel(hero2Label, hero2PicString, hero2Profile, hero2Name, hero2NameString, 220+30);
		addCharacterPanel(hero3Label, hero3PicString, hero3Profile, hero3Name, hero3NameString, 220+220+45);
		addCharacterPanel(hero4Label, hero4PicString, hero4Profile, hero4Name, hero4NameString, 220+220+220+60);
		getContentPane().add(partyPanel);
		//partyPanel.setVisible(false);
	 }
	 private void addCornerPanel() {
		cornerPanel = new JPanel(null);
		cornerPanel.setBackground(new Color(20,20,50));
		cornerPanel.setBounds(1000, 511, 186, 250);
		cornerPanel.setBorder(border);	
		btn1 = new JButton();
		btn2 = new JButton();
		btn3 = new JButton();
		btn4 = new JButton();
		btn5 = new JButton();
		addButton(btn1,195);
		addButton(btn2,150);
		addButton(btn3,105);
		addButton(btn4,60);
		addButton(btn5,15);
		botPanel.add(cornerPanel);
		getContentPane().add(cornerPanel);
		//cornerPanel.setVisible(false);
	 }
	 private void addCharacterPanel(JLabel heroLabel, String heroPicture, JLabel heroProfile, JLabel heroName, String heroNameString, int labelPos) {
		heroLabel = new JLabel();
		heroLabel.setBounds(labelPos, 0, 220, 250);
		//heroLabel.setBorder(border);
		partyPanel.add(heroLabel);
		
		ImageIcon heroDisplay = new ImageIcon(new ImageIcon(heroPicture).getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH));
		heroProfile = new JLabel(heroDisplay);
		heroProfile.setBounds(15,30,190,190);
		heroProfile.setBorder(border);
		heroProfile.setVisible(true);
		heroLabel.add(heroProfile);
		
		JLabel heroHealthBar = new JLabel();
		heroHealthBar.setBounds(15,225,190,20);
		heroHealthBar.setBorder(border);
		heroHealthBar.setBackground(Color.green);
		heroHealthBar.setOpaque(true);
		heroLabel.add(heroHealthBar);
		
		heroName = new JLabel(heroNameString);
		heroName.setHorizontalAlignment(SwingConstants.CENTER);
		heroName.setBounds(5,2,200,25);
		heroName.setFont(btnFont); 
		heroLabel.add(heroName);
		
	 }
}
