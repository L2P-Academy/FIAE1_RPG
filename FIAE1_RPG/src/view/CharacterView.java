package view;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharacterView extends JFrame{
	
	JLabel playerName;
	JPanel backgroundPanel, valuesPanel, equipPanel, charaPanel;
	String charaProfileImg = "res/img/placeholderImg.png";
	String CharImagPath = "res/img/CharBackground.png";
	ImageIcon CharImgIcon, introImgIcon;
	
	public CharacterView() {
		setTitle("namePlaceholder");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		backgroundPanel = new BackGroundPanel(new ImageIcon(CharImagPath).getImage());
		backgroundPanel.setLayout(new BorderLayout());
		
		JPanel CharaPanel = new JPanel();
		charaPanel.setSize(100, 200);
		
		JLabel IconHolder = new JLabel();
		IconHolder.setIcon(new javax.swing.ImageIcon(getClass().getResource(charaProfileImg)));
	    CharaPanel.add(IconHolder);
	    CharaPanel.setLayout(new BorderLayout());  
	  
	    backgroundPanel.add(CharaPanel, BorderLayout.WEST);
	    
	    getContentPane().add(backgroundPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}