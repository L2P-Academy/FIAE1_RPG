package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuView extends JFrame{
	
	JLabel titlePictureLbl;
	JPanel mainPanel;
	JButton newGameBtn, loadGameBtn, closeGameBtn;
	
	public MainMenuView() {
		
		setTitle("Dragons & Dungeons");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
	}

}
