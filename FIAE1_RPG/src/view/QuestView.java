package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestView extends JFrame{
	
	JLabel questTitleLbl, questDescriptionLbl, questImgLbl;
	JButton acceptQuestBtn, exitQuestBtn;
	JPanel buttonPnl, questPnl, questItemPnl, questTitlePnl, mainPnl;
	String questBackgroundPath = "res/img/QuestDescBack.png";
	Font gameFont;
	
	public QuestView() {
		
		setTitle("Neue Quest!");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create Font
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		
		//Create Label
		questTitleLbl = new JLabel("Hier kommt der Quest Titel hin");
		questDescriptionLbl = new JLabel("Hier kommt die Quest Beschreibung hin");
		questImgLbl = new JLabel("Hier kommt das Quest Bild hin");
		
		//Create Panel
		buttonPnl = new JPanel(new FlowLayout());
		questPnl = new BackGroundPanel(new ImageIcon(questBackgroundPath).getImage());
		questPnl.setLayout(new BorderLayout());
		questItemPnl = new JPanel(new BorderLayout());
		questTitlePnl = new JPanel(new BorderLayout());
		mainPnl = new JPanel(new BorderLayout());	
		
		//Panel Layout (  nicht sicher ob notwendig)
		//buttonPanel.setLayout(new FlowLayout());
		//questPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//questItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//set Panel Background for better Sichtbarkeit
		questPnl.setBackground(Color.CYAN);
		buttonPnl.setBackground(Color.GREEN);
		questItemPnl.setBackground(Color.YELLOW);
		questTitlePnl.setBackground(Color.ORANGE);
		
		//Create Button
		acceptQuestBtn = new JButton("Wenns sein muss");//Worktitle
		acceptQuestBtn.setFont(gameFont);
		exitQuestBtn = new JButton("Ne du, keine Lust");//Worktitle
		exitQuestBtn.setFont(gameFont);
		
		//Fill Button
		buttonPnl.add(acceptQuestBtn);
		buttonPnl.add(exitQuestBtn);
		
		//Fill Panel with Labels
		questPnl.add(questDescriptionLbl);
		questItemPnl.add(questImgLbl);
		questTitlePnl.add(questTitleLbl);
		
		//Set Position for Panels
		mainPnl.add(questTitlePnl, BorderLayout.NORTH);
		mainPnl.add(questPnl, BorderLayout.CENTER);
		mainPnl.add(buttonPnl, BorderLayout.SOUTH);
		mainPnl.add(questItemPnl, BorderLayout.WEST);
		
		getContentPane().add(mainPnl);
		
		//give function to Buttons
		acceptQuestBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MapView mapView = new MapView();
				dispose();
				
				
			}
		});
		
		exitQuestBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MapView mapView = new MapView();
				dispose();
				
			}
		});
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

}
