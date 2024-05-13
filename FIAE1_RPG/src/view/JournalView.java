package view;

import javax.swing.JFrame;

import model.SerializationIDs;

public class JournalView extends JFrame{
	
	private static final long serialVersionUID = SerializationIDs.journalViewID;

	public JournalView() {
		
		setTitle("Quest-Tagebuch");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
