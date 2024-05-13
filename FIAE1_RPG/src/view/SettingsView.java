package view;

import javax.swing.JFrame;

import model.SerializationIDs;

public class SettingsView extends JFrame{

	private static final long serialVersionUID = SerializationIDs.settingsViewID;

	public SettingsView() {
		
		setTitle("Einstellungen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
