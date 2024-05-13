package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.SerializationIDs;

public class CharacterView extends JFrame{
	
	private static final long serialVersionUID = SerializationIDs.characterViewID;
	JLabel playerName;
	JPanel backgroundPanel, valuesPanel, equipPanel, charaPanel;
	String charaProfileImg = "res/img/placeholderImg.png";
	String CharImagPath = "res/img/CharBackground.png";
	ImageIcon CharImgIcon, introImgIcon;
	
	public CharacterView() {
		setTitle("Charakterübersicht");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}