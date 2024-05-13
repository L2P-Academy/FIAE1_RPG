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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.QuestController;
import controller.SoundController;
import model.QuestModel;
import model.SerializationIDs;

public class QuestView extends JFrame{
	
	private static final long serialVersionUID = SerializationIDs.questViewID;

	//Controller
	QuestController questController;
	
	//Model
	QuestModel questModel;
	
	//Label
	JLabel questTitleLbl, questDescLbl, questImgLbl, questItemLbl;
	
	//Buttons
	JButton acceptQuestBtn, exitQuestBtn;
	
	//Panel
	JPanel buttonPnl, questBgPnl, questItemPnl, questTitlePnl, mainPnl;
	
	//textarea
	JTextArea questDescArea;
	
	//Scroll
	JScrollPane scrollPane;
	
	//Background
	String questBackgroundPath = "res/img/QuestDescBack.png";
	String questItemBackground = "res/img/QuestItem_Placeholder.png";
	String questTitleBackground = "res/img/QuestTitleBackground.png";
	
	//Font
	Font gameFont;
	Font questDesc;
	Font questTitle;
	
	//Sounds
	SoundController soundController;
	
	public QuestView() {
		
		
		//Test Quest
		QuestModel questModel = new QuestModel("Test Titel", "Vor langer Zeit verschwand das magische Artefakt aus dem Königreich und seine Spur verblasste im Nebel der Zeit. Die Bewohner des Landes sehnen sich nach seiner Rückkehr und die Königin hat eine Belohnung ausgesetzt für jene, die es finden. Mutige Abenteurer werden gesucht, um die Geheimnisse des verschwundenen Artefakts zu enthüllen und das Land vor dem Verfall zu retten. Doch der Weg zum Ruhm ist gefährlich, und nur die Tapfersten werden sich der Herausforderung stellen.", 1, true, 100.0, 50, null, 1, null, 0, 10);
		
		
		//Create Window
		setTitle("Neue Quest!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		soundController = new SoundController();
		
		//Create Font
		gameFont = new Font("Old English Text MT", Font.BOLD, 32);
		questDesc = new Font("Old English Text MT", Font.BOLD, 32);
		questTitle = new Font("Old English Text MT", Font.BOLD, 32);
		
		//Create QuestTitle Label
		questTitleLbl = new JLabel();
        questTitleLbl.setText(questModel.getTitle());
		questTitleLbl.setFont(questTitle);
		
		//Create Quest Desc Area with Scroll
		questDescArea = new JTextArea(questModel.getDescription());
		questDescArea.setFont(questDesc);
		questDescArea.setLineWrap(true);
		questDescArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(questDescArea);
		scrollPane.setLocation(scrollPane.getX(), scrollPane.getY() + 100);
		scrollPane.setBounds(scrollPane.getX(), scrollPane.getY() + 100, scrollPane.getWidth(), scrollPane.getHeight());
		
		
		//Create ItemLabel From Model
	    questItemLbl = new JLabel();
	    if (questModel.getQuestItemRequired() != null) {
	    questItemLbl.setText(questModel.getQuestItemRequired().getItemName());
	    } else {
	    questItemLbl.setText("Kein Quest-Item erforderlich");	     }

		
		//Button Panel
		buttonPnl = new JPanel(new FlowLayout());
		
		//QuestItem  Panel
		questItemPnl = new JPanel(new FlowLayout());
		questItemPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		questItemPnl.setPreferredSize(new Dimension(250, 500));

		
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
		
		//Create questTitle Background
		questTitlePnl = new BackGroundPanel(new ImageIcon(questTitleBackground).getImage());
		questTitlePnl.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		// set ScrollPane Size
		scrollPane.setPreferredSize(new Dimension(600, 340));
		
		//questTitleLbl postition
		questTitleLbl.setPreferredSize(new Dimension(1000,65));
		
		//set Panel Background for better Sichtbarkeit und Beautiness
		questBgPnl.setBackground(new Color(190, 196, 168));
		buttonPnl.setBackground(new Color(190, 196, 168));
		questItemPnl.setBackground(new Color(190, 196, 168));
		questTitlePnl.setBackground(new Color(190, 196, 168));
		
		//Create Button
		acceptQuestBtn = new JButton("Mit freuden, mein Edler");//Worktitle
		acceptQuestBtn.setFont(gameFont);
		exitQuestBtn = new JButton("Oh nein, mein Edler");//Worktitle
		exitQuestBtn.setFont(gameFont);
		
		//Fill Button
		buttonPnl.add(acceptQuestBtn);
		buttonPnl.add(exitQuestBtn);
		
		//Fill Panel with Labels
		questBgPnl.add(scrollPane);
		questTitlePnl.add(questTitleLbl);
		questItemPnl.add(questItemLbl);
		
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
				soundController.playButtonClickSound();
				new MapView();
				soundController.stopMusicLoop();
				dispose();	
			}
		});
		
		exitQuestBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				new MapView();
				soundController.stopMusicLoop();
				dispose();	
			}
		});
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		//play Music in background after rendering		
		soundController.playMusicLoop("res/soundFX/ambientSounds/village_ambient.wav");
	}

}