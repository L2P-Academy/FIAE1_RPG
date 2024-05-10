package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CombatView extends JFrame implements ActionListener{
	
	JPanel topPanel, botPanel, cornerPanel;
	JLabel dialogueLabel, hero1Label, hero2Label, hero3Label, hero4Label,
		   hero1Profile, hero1Name;
	JButton btn1, btn2, btn3, btn4, btn5;
	Font btnFont, textFont;
	String hero1name = "Iberian Fighter";
	String hero1 = "res/img/IberianFighter.jpg";
	String backgroundForest = "res/img/CombatBG_Forest.png";
	Border border = BorderFactory.createLineBorder(Color.black, 1);
	
	public CombatView() {
		// Font settings
		btnFont = new Font("Old English Text MT", Font.BOLD, 24);
		textFont = new Font("Calisto MT", Font.PLAIN, 32);
		
		// add top Panel
		topPanel = new BackGroundPanel(new ImageIcon(backgroundForest).getImage());
		topPanel.setBounds(0, 0, 1186, 513);
		//topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(border);
		
		// add bot Panel
		botPanel = new JPanel(null);
		botPanel.setBackground(Color.gray);
		botPanel.setBounds(0, 513, 1000, 250);
		//botPanel.setLayout(new BorderLayout());
		botPanel.setBorder(border);
		// prepare heroes panels
		hero1Label = new JLabel();
		hero1Label.setBounds(15, 5, 220, 240);
		ImageIcon hero1Picture = new ImageIcon(new ImageIcon(hero1).getImage().getScaledInstance(210, 210, Image.SCALE_SMOOTH));
		hero1Label.setBorder(border);
		hero1Profile = new JLabel(hero1Picture);
		hero1Profile.setBounds(15,25,190,190);
		hero1Profile.setBorder(border);
		hero1Name = new JLabel(hero1name);
		hero1Name.setBounds(25,0,200,25);
		hero1Name.setFont(btnFont);
		// Rest of the party is empty for now
		hero2Label = new JLabel();
		hero2Label.setBounds(250, 5, 220, 240);
		hero2Label.setBorder(border);
		hero3Label = new JLabel();
		hero3Label.setBounds(485, 5, 220, 240);
		hero3Label.setBorder(border);
		hero4Label = new JLabel();
		hero4Label.setBounds(720, 5, 220, 240);
		hero4Label.setBorder(border);
		// add heroes into the botPanel
		botPanel.add(hero1Label);
		hero1Label.add(hero1Profile);
		hero1Label.add(hero1Name);
		botPanel.add(hero2Label);
		botPanel.add(hero3Label);
		botPanel.add(hero4Label);
		
		
		// add corner Panel
		cornerPanel = new JPanel(null);
		cornerPanel.setBackground(Color.LIGHT_GRAY);
		cornerPanel.setBounds(1000, 513, 186, 250);
		cornerPanel.setBorder(border);
		// create buttons
		btn1 = new JButton();
		btn1.addActionListener(this);
		btn1.setFont(btnFont);
		btn1.setText("Angreifen");
		btn1.setBounds(5,15,175,40);
		btn1.setFocusable(false);
		btn1.setVisible(true);
		btn2 = new JButton();
		btn2.addActionListener(this);
		btn2.setFont(btnFont);
		btn2.setText("Verteidigen");
		btn2.setBounds(5,60,175,40);
		btn2.setFocusable(false);
		btn3 = new JButton();
		btn3.addActionListener(this);
		btn3.setFont(btnFont);
		btn3.setText("Inventar");
		btn3.setBounds(5,105,175,40);
		btn3.setFocusable(false);
		btn4 = new JButton();
		btn4.addActionListener(this);
		btn4.setFont(btnFont);
		btn4.setText("Flüchten");
		btn4.setBounds(5,150,175,40);
		btn4.setFocusable(false);
		btn5 = new JButton();
		btn5.addActionListener(this);
		btn5.setFont(btnFont);
		btn5.setText("Mehr ...");
		btn5.setBounds(5,195,175,40);
		btn5.setFocusable(false);
		btn5.setVisible(true);
		// add buttons into the corner
		cornerPanel.add(btn1);
		cornerPanel.add(btn2);
		cornerPanel.add(btn3);
		cornerPanel.add(btn4);
		cornerPanel.add(btn5);
		
		// frame properties
		this.setTitle("Combat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200,800);
		this.setResizable(false);
		getContentPane().add(topPanel);
		getContentPane().add(botPanel);
		getContentPane().add(cornerPanel);
		
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		
	}

}
