package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import controller.SQLController;
import controller.SoundController;
import model.SerializationIDs;

public class SettingsView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.settingsViewID;

	private BackGroundPanel settingsBgrPnl;
	private JPanel titlePnl, buttonPnl, settingsPnl;
	private JLabel titleLbl, resolutionLbl, volumeLbl, fullscreenLbl;
	private JButton quitBtn, backBtn, submitBtn, fullscreenBtn, windowBtn;
	private JSlider volumeSlider;

	private Font settingsFont, titleFont;

	private boolean isFullscreen;

	private SoundController soundController;
	private SQLController sqlController;

	public SettingsView(SoundController soundController) {
		
		// Initialize global SoundController / SQLController Class
		this.soundController = soundController;
		this.sqlController = new SQLController();


		// Create Window
		setTitle("Einstellungen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Create Fonts
		settingsFont = new Font("Old English Text MT", Font.BOLD, 32);
		titleFont = new Font("Old English Text MT", Font.BOLD, 48);

		// Title Panel
		titlePnl = new JPanel(new FlowLayout());
		titleLbl = new JLabel("Einstellungen");
		titleLbl.setFont(titleFont);
		titlePnl.add(titleLbl, BorderLayout.CENTER);
		titlePnl.setOpaque(false);

		// Buttons Panel
		buttonPnl = new JPanel(new FlowLayout());
		backBtn = new JButton("Zurück");
		quitBtn = new JButton("Spiel Beenden");
		submitBtn = new JButton("Einstellung Übernehmen");
		beautifyButton(backBtn);
		beautifyButton(quitBtn);
		beautifyButton(submitBtn);
		buttonPnl.add(backBtn, BorderLayout.WEST);
		buttonPnl.add(quitBtn, BorderLayout.CENTER);
		buttonPnl.add(submitBtn, BorderLayout.EAST);
		buttonPnl.setOpaque(false);

		// SettingsPanel
		settingsPnl = new JPanel();
		settingsPnl.setLayout(new BoxLayout(settingsPnl, BoxLayout.Y_AXIS));

		// Resolution Settings
		String comboBoxListe[] = { "1280x720", "1920x1080", "2560x1440" };
		JComboBox resolution = new JComboBox(comboBoxListe);
		resolution.setMaximumSize(new Dimension(200, resolution.getPreferredSize().height));
		resolution.setAlignmentX(Component.CENTER_ALIGNMENT);
		resolutionLbl = new JLabel("Auflösung");
		resolutionLbl.setFont(settingsFont);
		resolutionLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Volume Settings
		volumeSlider = new JSlider(0, 100);
		volumeSlider.setFont(settingsFont);
		volumeSlider.setMaximumSize(new Dimension(200, 30));
		volumeSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
		volumeSlider.setValue(soundController.getVolume());
		
		volumeLbl = new JLabel("Lautstärke");
		volumeLbl.setFont(settingsFont);
		volumeLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Fullscreen Button
		fullscreenBtn = new JButton("Vollbild");
		beautifyButton(fullscreenBtn);
		fullscreenBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		fullscreenLbl = new JLabel("Vollbild / Fenstermodus");
		fullscreenLbl.setFont(settingsFont);
		fullscreenLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Window Button
		windowBtn = new JButton("Fenstermodus");
		beautifyButton(windowBtn);
		windowBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Add everything to SettingsPanel
		settingsPnl.add(Box.createRigidArea(new Dimension(100, 200)));
		settingsPnl.add(resolutionLbl);
		settingsPnl.add(Box.createRigidArea(new Dimension(100, 10)));
		settingsPnl.add(resolution);
		settingsPnl.add(Box.createRigidArea(new Dimension(100, 40)));
		settingsPnl.add(volumeLbl);
		settingsPnl.add(Box.createRigidArea(new Dimension(100, 10)));
		settingsPnl.add(volumeSlider);
		settingsPnl.add(Box.createRigidArea(new Dimension(100, 40)));
		settingsPnl.add(fullscreenLbl);
		settingsPnl.add(Box.createRigidArea(new Dimension(100, 20)));
		settingsPnl.add(fullscreenBtn);
		settingsPnl.add(Box.createRigidArea(new Dimension(100, 10)));
		settingsPnl.add(windowBtn);
		settingsPnl.setOpaque(false);

		// Add everything to BackgroundPanel
		settingsBgrPnl = new BackGroundPanel(new ImageIcon("res/img/Backgrounds/Background_Settings.jpg").getImage());
		settingsBgrPnl.setLayout(new BorderLayout());
		settingsBgrPnl.add(buttonPnl, BorderLayout.SOUTH);
		settingsBgrPnl.add(titlePnl, BorderLayout.NORTH);
		settingsBgrPnl.add(settingsPnl, BorderLayout.CENTER);

		// add Everything to Window
		getContentPane().add(settingsBgrPnl, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setVisible(true);

		// Action Listener
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				dispose();
			}
		});

		quitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				System.exit(0);
			}
		});

		fullscreenBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				isFullscreen = true;
			}
		});

		windowBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				isFullscreen = false;
				
			}
		});

		submitBtn.addActionListener(new ActionListener() {

			@Override
		    public void actionPerformed(ActionEvent e) {
		        soundController.playButtonClickSound();
		        String selectedResolution = (String) resolution.getSelectedItem();
		        int width = 1280;
		        int height = 720;
		        
		        switch (selectedResolution) {
		            case "1280x720":
		                width = 1280;
		                height = 720;
		                break;
		            case "1920x1080":
		                width = 1920;
		                height = 1080;
		                break;
		            case "2560x1440":
		                width = 2560;
		                height = 1440;
		                break;
		        }
		        
		        int volume = volumeSlider.getValue();
		        
				if (sqlController.doesDataExistInTable("settings")){
		        	sqlController.updateSpecificValue(1, "settings", "Volume", String.valueOf(volume), "SettingsID");
			        sqlController.updateSpecificValue(1, "settings","FensterModus" ,String.valueOf(isFullscreen), "SettingsID");
			        sqlController.updateSpecificValue(1, "settings", "ResWidth", String.valueOf(width), "SettingsID");
			        sqlController.updateSpecificValue(1, "settings", "ResHeight", String.valueOf(height), "SettingsID");
		        }else {
		        Map<String, String> settingsMap = new HashMap<>();
		        settingsMap.put("Volume", String.valueOf(volume));
		        settingsMap.put("FensterModus", isFullscreen ? "0" : "1");
		        settingsMap.put("ResWidth", String.valueOf(width));
		        settingsMap.put("ResHeight", String.valueOf(height));
		        sqlController.insertIntoTable("settings", settingsMap);
		        }
		        
		        soundController.setVolume(volume);
		        
		        if (isFullscreen) {
		        	setExtendedState(JFrame.MAXIMIZED_BOTH);
		        } else {
		        	setExtendedState(JFrame.NORMAL);
		        	setSize(width, height);
		        	setLocationRelativeTo(null);
		        }
		        
		        JOptionPane.showMessageDialog(SettingsView.this, "Einstellungen wurden gespeichert und angewendet.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
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
}
