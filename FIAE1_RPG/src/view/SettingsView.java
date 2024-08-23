package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

	private SoundController soundController;
	private SQLController sqlController;

	public SettingsView(SoundController soundController) {
		
		// initialize global SoundController Class
		this.soundController = soundController;

		// Create Window
		setTitle("Einstellungen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// SQLController
		sqlController = new SQLController();

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
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(resolutionLbl);
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(resolution);
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(volumeLbl);
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(volumeSlider);
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(fullscreenLbl);
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(fullscreenBtn);
		settingsPnl.add(new JLabel(" "));
		settingsPnl.add(windowBtn);
		settingsPnl.setOpaque(false);

		// Add everything to BackgroundPanel
		settingsBgrPnl = new BackGroundPanel(new ImageIcon("res/img/Backgrounds/settings_background.jpg").getImage());
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

		// Fullscreen Settings
		fullscreenBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				setExtendedState(JFrame.MAXIMIZED_BOTH);
				setUndecorated(true);
			}
		});

		windowBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				dispose();
				setUndecorated(false);
				// TODO: Size must be set for the whole application
				setSize(1280, 720);
				setLocationRelativeTo(null);
				setVisible(true);
			}
		});

		// Submit Button
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				String selectedResolution = (String) resolution.getSelectedItem();
				switch (selectedResolution) {
				case "1280x720":
					setSize(1280, 720);
					break;
				case "1920x1080":
					setSize(1920, 1080);
					break;
				case "2560x1440":
					setSize(2560, 1440);
					break;
				}
				int volume = volumeSlider.getValue();
				soundController.setVolume(volume);
				setLocationRelativeTo(null);
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
