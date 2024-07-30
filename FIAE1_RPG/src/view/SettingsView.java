package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.SerializationIDs;

import controller.SoundController;



public class SettingsView extends JFrame{
	
	// unique id for serialization purposes
	private static final long serialVersionUID = SerializationIDs.settingsViewID;
	
	// Background Image for the frame
	private ImageIcon backgroundImage = new ImageIcon("res/img/Backgrounds/settings_background.jpg");
	
	//GameFont
	private Font gameFont;
	
	private String settingsFilePath = "settings.txt";
	File settingsFile = new File(settingsFilePath);
	
	SoundController soundController = new SoundController();
	
	private int globalVolume = 50;
	

	
	
	// Constructor
	public SettingsView() {
		
		
		//set the default window properties
		setTitle("Einstellungen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		
		//create the background panel and add it to the main frame
		BackGroundPanel backgroundPanel = new BackGroundPanel(backgroundImage.getImage());
		backgroundPanel.setLayout(new BorderLayout());
		add(backgroundPanel);
		
		//VolumeSlider
        JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, globalVolume);
        volumeSlider.setFont(gameFont);
        volumeSlider.setPreferredSize(new Dimension(200, 30));
		volumeSlider.setFont(gameFont);
		volumeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int volume = volumeSlider.getValue();
				
				float gain = (float) (volume - 50) / 50;
				float dB = (float) (Math.log(Math.max(gain,  0.0001)) / Math.log(10.0) * 20.0);
				
				if (soundController.musicClip != null) {
					try {
						FloatControl gainControl = (FloatControl) soundController.musicClip.getControl(FloatControl.Type.MASTER_GAIN);
						gainControl.setValue(dB);
					} catch (IllegalArgumentException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		
		//Back-Button
		JButton backButton = new JButton("Zurück");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
			 dispose(); //Closes the Window
			}
		});
		
		
		//Fullscreen Button
		JButton fullscreenButton = new JButton("Vollbild");
		fullscreenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isUndecorated()) { 				//wenn Vollbild aktiv
					dispose();						//Schließe Vollbildfenster
					setUndecorated(false);			//Deaktiviere Vollbild
					setSize(400,300);				//Setze Fenstergröße
					setLocationRelativeTo(null);	//Zentriere das Fenster
					setVisible(true);				//Erzeuge neues Vollbildfenster
				} else {
					dispose();
					setUndecorated(true);
					setExtendedState(JFrame.MAXIMIZED_BOTH);	//Fenster Maximieren
					setVisible(true);							//Zeige Fenster wieder an
				}
			}
		});
		
		
		//Exit Button + Save Settings
		JButton exitButton = new JButton("Beenden");
		exitButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try (FileWriter writer = new FileWriter(settingsFile)) {
		            writer.write("vollbild:" + isUndecorated() + "\n");
		            writer.write("lautstaerke:" + volumeSlider.getValue() + "\n");
		            writer.flush();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }

		        System.exit(0);
		    }
		});

		// Einstellungen laden
		try (BufferedReader reader = new BufferedReader(new FileReader(settingsFile))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(":");
		        if (parts.length == 2) {
		            String settingName = parts[0];
		            String settingValue = parts[1];

		            if (settingName.equals("vollbild")) {
		                boolean isFullscreen = Boolean.parseBoolean(settingValue);
		                setUndecorated(isFullscreen);
		                setExtendedState(isFullscreen ? JFrame.MAXIMIZED_BOTH : JFrame.NORMAL);
		            } else if (settingName.equals("lautstaerke")) {
		                int volume = Integer.parseInt(settingValue);
		                volumeSlider.setValue(volume);

		                float gain = (float) (volume - 50) / 50;
		                float dB = (float) (Math.log(Math.max(gain, 0.0001)) / Math.log(10.0) * 20.0);

		                if (soundController.musicClip != null) {
		                    try {
		                        FloatControl gainControl = (FloatControl) soundController.musicClip.getControl(FloatControl.Type.MASTER_GAIN);
		                        gainControl.setValue(dB);
		                    } catch (IllegalArgumentException ex) {
		                        ex.printStackTrace();
		                    }
		                }
		            } 
		        }
		    } 

		    if (!isUndecorated()) {
		        setSize(800, 600); 
		        setLocationRelativeTo(null);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		}


		//Make Font
		gameFont = new Font("Old English Text MT", Font.BOLD, 32);
		
		//Make Buttons Beautiful Again
		beautifyButton(backButton);
		beautifyButton(fullscreenButton);
		beautifyButton(exitButton);
		
		//Set Font
		backButton.setFont(gameFont);
		fullscreenButton.setFont(gameFont);
		exitButton.setFont(gameFont);
		
		//add buttons to the background panel
		backgroundPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		
		backgroundPanel.add(backButton, gbc);
		
		gbc.gridy = 1;
		backgroundPanel.add(fullscreenButton, gbc);
		
		gbc.gridy = 2;
		backgroundPanel.add(exitButton, gbc);
		
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 1.0;
		backgroundPanel.add(volumeSlider, gbc);
		
		//Check for Settings File
		if (!settingsFile.exists()) {
			try {
				settingsFile.createNewFile();
			} catch (Exception e) {
                e.printStackTrace();
            }
		}
		
		//set the frame to appear
		setLocationRelativeTo(null);
		setVisible(true);
		
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