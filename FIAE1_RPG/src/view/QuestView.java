package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class QuestView extends JFrame{
	
	JLabel questTitleLbl, questDescription, questImgLbl;
	JButton acceptQuestBtn, exitQuestBtn;	
	
	public QuestView() {
		
		setTitle("Neue Quest!");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
	}

}
