//package view;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//
//import controller.SoundController;
//import model.QuestModel;
//import model.SerializationIDs;
//
//public class QuestView_old extends JFrame {
//	private static final long serialVersionUID = SerializationIDs.questViewID;
//
//	// Model
//	QuestModel questModel;
//
//	// Label
//	JLabel questTitleLbl, questDescLbl, questImgLbl, questItemLbl;
//
//	// Buttons
//	JButton acceptQuestBtn, exitQuestBtn;
//
//	// Panel
//	JPanel buttonPnl, questBgPnl, questItemPnl, questTitlePnl, mainPnl;
//
//	// Text area
//	JTextArea questDescArea;
//
//	// Scroll
//	JScrollPane scrollPane;
//
//	// Background
//	String questBackgroundPath = "res/img/MenuImages/QuestDescBack.png";
//	String questItemBackground = "res/img/MenuImages/QuestItem_Placeholder.png";
//	String questTitleBackground = "res/img/MenuImages/QuestTitleBackground.png";
//
//	// Font
//	Font gameFont;
//	Font questDesc;
//	Font questTitle;
//
//	// Sounds
//	SoundController soundController;
//
//	public QuestView() {
//
//	    // Create example quest
//	    questModel = new QuestModel(
//	        1, // questID
//	        1, // reqLevel
//	        100, // rewardXP
//	        4, // itemID
//	        50, // rewardGold
//	        true, // isMainQuest
//	        "Eine lang erwartete Reise", // name
//	        "Besuche die Taverne im Dorf!" // description
//	    );
//	    
//	    // Create window
//        setTitle(questModel.getName());
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
//
//		soundController = new SoundController();
//
//		// Create font
//		gameFont = new Font("Old English Text MT", Font.BOLD, 32);
//		questDesc = new Font("Old English Text MT", Font.BOLD, 32);
//		questTitle = new Font("Old English Text MT", Font.BOLD, 32);
//
//		// Create quest title label
//		questTitleLbl = new JLabel();
//		questTitleLbl.setText(questModel.getName());
//		questTitleLbl.setFont(questTitle);
//
//		// Create quest area with scroll
//		questDescArea = new JTextArea(questModel.getDescription());
//		questDescArea.setFont(questDesc);
//		questDescArea.setLineWrap(true);
//		questDescArea.setWrapStyleWord(true);
//		scrollPane = new JScrollPane(questDescArea);
//		scrollPane.setLocation(scrollPane.getX(), scrollPane.getY() + 100);
//		scrollPane.setBounds(scrollPane.getX(), scrollPane.getY() + 100, scrollPane.getWidth(), scrollPane.getHeight());
//
//		// Button panel
//		buttonPnl = new JPanel(new FlowLayout());
//
//		// Quest item panel
//		questItemPnl = new JPanel(new FlowLayout());
//		questItemPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
//		questItemPnl.setPreferredSize(new Dimension(250, 500));
//
//		// Quest title panel
//		questItemLbl = new JLabel("Item: " + questModel.getItemID());
//		questItemPnl.add(questItemLbl);
//		
//		// Main panel
//		mainPnl = new JPanel(new BorderLayout());
//
//		// Create item background
//		questItemPnl = new BackGroundPanel(new ImageIcon(questItemBackground).getImage());
//		questItemPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
//
//		// Create background panel
//		questBgPnl = new BackGroundPanel(new ImageIcon(questBackgroundPath).getImage());
//		questBgPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//		// Create quest title background
//		questTitlePnl = new BackGroundPanel(new ImageIcon(questTitleBackground).getImage());
//		questTitlePnl.setLayout(new FlowLayout(FlowLayout.CENTER));
//		
//		
//		// Accept quest button
//		questBgPnl.add(scrollPane);
//		questTitlePnl.add(questTitleLbl);
//
//
//		// Set scroll pane size
//		scrollPane.setPreferredSize(new Dimension(600, 340));
//
//		// Quest title label postition
//		questTitleLbl.setPreferredSize(new Dimension(1000, 65));
//
//		// Set panel background
//		questBgPnl.setBackground(new Color(190, 196, 168));
//		buttonPnl.setBackground(new Color(190, 196, 168));
//		questItemPnl.setBackground(new Color(190, 196, 168));
//		questTitlePnl.setBackground(new Color(190, 196, 168));
//
//		// Create button
//		acceptQuestBtn = new JButton("Mit freuden, mein Edler");// Worktitle
//		acceptQuestBtn.setFont(gameFont);
//		exitQuestBtn = new JButton("Oh nein, mein Edler");// Worktitle
//		exitQuestBtn.setFont(gameFont);
//
//		// Fill button
//		buttonPnl.add(acceptQuestBtn);
//		buttonPnl.add(exitQuestBtn);
//
//		// Fill panel with labels
//		questBgPnl.add(scrollPane);
//		questTitlePnl.add(questTitleLbl);
//		questItemPnl.add(questItemLbl);
//
//		// Set position for panels
//		mainPnl.add(questTitlePnl, BorderLayout.NORTH);
//		mainPnl.add(questBgPnl, BorderLayout.CENTER);
//		mainPnl.add(buttonPnl, BorderLayout.SOUTH);
//		mainPnl.add(questItemPnl, BorderLayout.WEST);
//
//		getContentPane().add(mainPnl);
//
//		// Give function to buttons
//		acceptQuestBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				soundController.playButtonClickSound();
//				new MapView();
//				soundController.stopMusicLoop();
//				dispose();
//			}
//		});
//
//		exitQuestBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				soundController.playButtonClickSound();
//				new MapView();
//				soundController.stopMusicLoop();
//				dispose();
//			}
//		});
//
//		setLocationRelativeTo(null);
//		setVisible(true);
//
//		// Play music in background after rendering
//		soundController.playMusicLoop("res/soundFX/ambientSounds/village_ambient.wav");
//	}
//}