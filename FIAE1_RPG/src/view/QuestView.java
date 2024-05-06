package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestView extends JFrame{
	
	JLabel questTitleLbl, questDescription, questImgLbl;
	JButton acceptQuestBtn, exitQuestBtn;
	JPanel buttonPanel;
	Font gameFont;
	
	public QuestView() {
		
		setTitle("Neue Quest!");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create Font
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		
		//Create ButtonPanel
		buttonPanel = new JPanel();
		
		//Panel Layout
		buttonPanel.setLayout(new FlowLayout());
		
		//Create Button
		acceptQuestBtn = new JButton("Wenns sein muss");//Worktitle
		acceptQuestBtn.setFont(gameFont);
		exitQuestBtn = new JButton("Ne du, keine Lust");//Worktitle
		exitQuestBtn.setFont(gameFont);
		
		//Fill Button
		buttonPanel.add(acceptQuestBtn);
		buttonPanel.add(exitQuestBtn);
		
		//Set Position for Button
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		//give function to Buttons
		acceptQuestBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuView mainMenuView = new MainMenuView();//No Map class to switch to
				dispose();
				
				
			}
		});
		
		exitQuestBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuView mainMenuView = new MainMenuView();//No Map class to switch to
				dispose();
				
			}
		});
		
		setVisible(true);
		
	}

}
