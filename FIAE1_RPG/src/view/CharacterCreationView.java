package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CharacterCreationView extends JFrame{
	
	JPanel mainPanel;
	JTextField characterName;
	JLabel characterPreview;
	JButton finishBtn;
	
	public CharacterCreationView() {
			
		setTitle("Charaktererstellung");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
}
