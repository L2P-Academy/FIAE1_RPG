package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.SerializationIDs;


public class SettingsView extends JFrame{
	
	// unique id for serialization purposes
	private static final long serialVersionUID = SerializationIDs.settingsViewID;

	// Background Image for the frame
	private ImageIcon backgroundImage = new ImageIcon("res/img/Backgrounds/settings_background.jpg");
	

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
		
		
		//Back-Button
		JButton backButton = new JButton("Zurück");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
			 dispose(); //Closes the Window
			}
		});
		
		backgroundPanel.add(backButton, BorderLayout.SOUTH);
		
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
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(backButton);
		buttonPanel.add(fullscreenButton);
		
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		//set the frame to appear
		setLocationRelativeTo(null);
		setVisible(true);
		
	 }
	
	
}
