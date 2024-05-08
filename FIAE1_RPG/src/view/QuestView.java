package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.QuestController;

public class QuestView extends JFrame{
	
	//Controller
	QuestController questController;
	
	//Label
	JLabel questTitleLbl, questDescLbl, questImgLbl;
	
	//Buttons
	JButton acceptQuestBtn, exitQuestBtn;
	
	//Panel
	JPanel buttonPnl, questBgPnl, questItemPnl, questTitlePnl, mainPnl;
	
	//Background
	String questBackgroundPath = "res/img/QuestDescBack.png";
	String questItemBackground = "res/img/QuestItem_Placeholder.png";
	
	//Font
	Font gameFont;
	Font questDesc;
	
	public QuestView() {
		
		setTitle("Neue Quest!");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create Font
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		questDesc = new Font("Old English Text MT", Font.BOLD, 32);
		
		//Create Label
		questTitleLbl = new JLabel("Hier kommt der Quest Titel hin");
		questTitleLbl.setFont(gameFont);
		questDescLbl = new JLabel("Hier kommt die Quest Beschreibung hin");
		questDescLbl.setFont(questDesc);
		//questImgLbl = new JLabel("Hier kommt das Quest Bild hin");
		//questImgLbl.setFont(gameFont);
		
		//Button Panel
		buttonPnl = new JPanel(new FlowLayout());
		
		//QuestItem  Panel
		questItemPnl = new JPanel(new FlowLayout());
		questItemPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		questItemPnl.setPreferredSize(new Dimension(250, 500));
		//questImgLbl.setLayout(new FlowLayout(FlowLayout.CENTER));
		//questImgLbl.setPreferredSize(new Dimension(250, 500));
		
		//QuestTitle Panel
		questTitlePnl = new JPanel(new BorderLayout());
		
		//Main Panel
		mainPnl = new JPanel(new BorderLayout());
		
		//Create ItemBackground
		questItemPnl = new BackGroundPanel(new ImageIcon(questItemBackground).getImage());
		questItemPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Create BackgroundPanel
		questBgPnl = new BackGroundPanel(new ImageIcon(questBackgroundPath).getImage());
		questBgPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//questDescLbl position
		questDescLbl.setPreferredSize(new Dimension(625, 350));
		
		//set Panel Background for better Sichtbarkeit und Beautiness
		questBgPnl.setBackground(new Color(172, 134, 70));
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
		questBgPnl.add(questDescLbl);
		//questItemPnl.add(questImgLbl);
		questTitlePnl.add(questTitleLbl);
		
		//Set Position for Panels
		mainPnl.add(questTitlePnl, BorderLayout.NORTH);
		mainPnl.add(questBgPnl, BorderLayout.CENTER);
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
