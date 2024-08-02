package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import controller.SQLController;
import controller.SoundController;
import model.QuestModel;
import model.SerializationIDs;

public class QuestView extends JFrame {
	private static final long serialVersionUID = SerializationIDs.questViewID;

	private BackGroundPanel titlePnl, locationPnl;
	private JPanel rewardPnl, dialogPnl, buttonPnl;
	private JLabel titleLbl, rewardsLbl, xpImgLbl, goldImgLbl, itemImgLbl, nameLbl, npcImgLbl, xpCountLbl, goldCountLbl;
	private JTextArea dialogTextArea;
	private JButton acceptBtn, denyBtn;

	private QuestModel questModel;

	private Font titleFont;

	private SoundController soundController;
	private SQLController sqlController;

	public QuestView() {

		// Create example quest
		questModel = new QuestModel(1, // questID
				1, // reqLevel
				100, // rewardXP
				4, // itemID
				50, // rewardGold
				true, // isMainQuest
				true, // isActive
				false, // isCompleted
				"Eine lang erwartete Reise", // name
				"Hey, hey du da! Bist du neu in der Gegend? " + "Ah, du bist auf dem Weg in die große Stadt. "
						+ "Ich glaube, dann habe was für dich. Schau mal in der Taverne vorbei und sag dem Wirt, "
						+ "Kara schickt dich. Viel Erfolg auf deiner Reise und lad mich mal auf ein Bier ein, wenn "
						+ "wir uns in der Stadt wiedersehen! ");

		// Create Window
		setTitle(questModel.getName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// Create Fonts
		titleFont = new Font("Old English Text MT", Font.BOLD, 32);
		Font textFont = new Font("Arial", Font.ITALIC, 28);

		// Controller
		soundController = new SoundController();
		sqlController = new SQLController();

		// Title
		titlePnl = new BackGroundPanel(new ImageIcon("res/img/MenuImages/QuestTitleBackground.png").getImage());
		titlePnl.setLayout(new BorderLayout());
		titleLbl = new JLabel("         " + questModel.getName());
		titleLbl.setFont(titleFont);
		titlePnl.add(titleLbl, BorderLayout.WEST);

		// Rewards
		rewardPnl = new JPanel();
		rewardPnl.setLayout(new BoxLayout(rewardPnl, BoxLayout.Y_AXIS));
		rewardsLbl = new JLabel("Belohnungen");
		rewardsLbl.setFont(titleFont);
		xpImgLbl = createImageLabel("res/img/MenuImages/Experience_Points.png");
		xpCountLbl = new JLabel(questModel.getRewardXP() + "");
		xpCountLbl.setFont(textFont);
		goldImgLbl = createImageLabel("res/img/ItemModelImages/Goldmünze.png");
		goldCountLbl = new JLabel(questModel.getRewardGold() + "");
		goldCountLbl.setFont(textFont);
		itemImgLbl = createImageLabel("res/img/ItemModelImages/Weltkarte.png");
		rewardPnl.add(rewardsLbl);
		rewardPnl.add(xpImgLbl);
		rewardPnl.add(xpCountLbl);
		rewardPnl.add(goldImgLbl);
		rewardPnl.add(goldCountLbl);
		rewardPnl.add(itemImgLbl);

		// Background
		locationPnl = new BackGroundPanel(new ImageIcon("res/img/Backgrounds/backgr_c_smalltown1.png").getImage());
		nameLbl = new JLabel("Kara");
		nameLbl.setFont(titleFont);
		locationPnl.add(nameLbl, BorderLayout.SOUTH);

		// Dialog
		dialogPnl = new JPanel(new BorderLayout());
		npcImgLbl = createImageLabel("res/img/CharacterPortraits/female_orc3.png");
		dialogTextArea = new JTextArea();
		dialogTextArea.setFont(textFont);
		dialogTextArea.setLineWrap(true);
		dialogTextArea.setEditable(false);
		dialogTextArea.setBackground(new Color(245, 245, 220));
		dialogTextArea.setText(questModel.getDescription());

		buttonPnl = new JPanel(new BorderLayout());
		acceptBtn = new JButton("Annehmen");
		denyBtn = new JButton("Ablehnen");
		beautifyButton(acceptBtn);
		beautifyButton(denyBtn);
		buttonPnl.add(acceptBtn, BorderLayout.NORTH);
		buttonPnl.add(denyBtn, BorderLayout.SOUTH);

		dialogPnl.add(npcImgLbl, BorderLayout.WEST);
		dialogPnl.add(dialogTextArea, BorderLayout.CENTER);
		dialogPnl.add(buttonPnl, BorderLayout.EAST);

		// add Everything
		getContentPane().add(titlePnl, BorderLayout.NORTH);
		getContentPane().add(locationPnl, BorderLayout.CENTER);
		getContentPane().add(rewardPnl, BorderLayout.EAST);
		getContentPane().add(dialogPnl, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);

		// animate Quest text
		animateText();

		// ActionListener
		acceptBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		denyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

	}

	// Modify Buttons
	public void beautifyButton(JButton button) {
		button.setFocusPainted(false);
		button.setBackground(new Color(10, 50, 100));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Old English Text MT", Font.BOLD, 36));

		// Rounded Corners
		Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
		Border roundedBorder = BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 20, 10, 20));
		button.setBorder(
				BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		// color change when MouseOver is happening
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(10, 50, 100));
			}
		});
	}

	private JLabel createImageLabel(String path) {
		ImageIcon icon = new ImageIcon(path);
		Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(img));
	}

	private void animateText() {
		new Thread(() -> {
			try {
				for (int i = 0; i <= questModel.getDescription().length(); i++) {
					final String currentText = questModel.getDescription().substring(0, i); // Aktuellen Text speichern
					SwingUtilities.invokeLater(() -> {
						dialogTextArea.setText(currentText);
					});
					TimeUnit.MILLISECONDS.sleep(50); // Anpassen der Geschwindigkeit
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
}

//Quest 2:
//	
//	 questModel = new QuestModel(
//		        2, // questID
//		        1, // reqLevel
//		        200, // rewardXP
//		        4, // itemID
//		        250, // rewardGold
//		        true, // isMainQuest
//		        false, // isActive
//		        false, // isCompleted
//	// Wirt
//	"Die Verlorene Expedition", // name
//	"Wo ich dich gerade hier habe, könntest du mir einen Gefallen tun. Ich warte seit über "
//	+ "zwei Wochen auf eine Nachricht von meinem Freund Torben. Er ist vor einiger Zeit zu einer"
//	+ "kleinen Expedition aufgebrochen und wollte mir ein paar seltene Gewürze für meinen "
//	+ "Gebrannten vorbeibringen, sobald er hier vorbeikommt. Zuletzt haben ihn ein paar Jägerinnen"
//	+ "am Rande des dunklen Forsts gesehen. Kannst du, wenn du das Lager findest, für mich nach "
//	+ "Torben und seiner Gruppe sehen? Vielleicht haben sie sich verlaufen.");
//res/img/CharacterPortraits/male_human8.png
//	
//Quest 3:
//	 questModel = new QuestModel(
//		        3, // questID
//		        2, // reqLevel
//		        350, // rewardXP
//		        0, // itemID
//		        350, // rewardGold
//		        true, // isMainQuest
//		        false, // isActive
//		        false, // isCompleted
//	// Torben
//	"Der Fluch des Vergessenen Grabmals", // name
//	"Danke noch mal für die Rettung in letzter Minute. Vielleicht kannst du den Bewohnern von Waldhain, einige "
//	+ "Reisestunden südlich von hier ebenfalls zur Seite stehen? Auf unserer Expedition haben wir im Osten"
//	+ "ein riesiges Grabmahl gefunden. Die Dorfbewohner behaupten, dort verschwänden immer wieder Personen. "
//	+ "Zuletzt soll es den Sohn des Müllers erwischt haben. Wenn du Zeit findest, solltest du dort mal"
//	+ "vorbeischauen und herausfinden, ob an der Geschichte etwas dran ist. Der Müller hat sogar eine Belohnung"
//	+ "ausgeschrieben!");
//res/img/CharacterPortraits/male_dwarf3.png
