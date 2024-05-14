package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.SerializationIDs;

public class CreditView extends JFrame{
	
	private static final long serialVersionUID = SerializationIDs.creditViewID;
	
	JButton backBtn;
	JPanel buttonPnl, listPnl, mainPnl;
	JLabel titleLbl, creditLbl;
	Font gameFont;
	
	
public CreditView() {
	
	setTitle("CreditView");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setUndecorated(true);
	
	//create Panels
	buttonPnl = new JPanel(new FlowLayout());
	listPnl = new JPanel(new BorderLayout());
	mainPnl = new JPanel(new BorderLayout());
		
	// create Font
	gameFont = new Font("Old English Text MT", Font.BOLD, 64);
		
	// create Buttons
	backBtn = new JButton("Zurück");
	
	// create Label
	titleLbl = new JLabel("Credit", SwingConstants.CENTER);
	titleLbl.setFont(gameFont);
	creditLbl = new JLabel("Credits", SwingConstants.CENTER);

		
	// add Button to Panel
	buttonPnl.add(backBtn);
	
	// add ActionListeners to Buttons
		backBtn.addActionListener(new ActionListener() {
	
		@Override
		public void actionPerformed(ActionEvent e) {
		dispose();			
		}
	});
	
	//add Lable to Panel
	mainPnl.add(titleLbl, BorderLayout.NORTH);
	mainPnl.add(buttonPnl, BorderLayout.SOUTH);
	mainPnl.add(creditLbl, BorderLayout.CENTER);
		
	getContentPane().add(mainPnl);
	 
	setLocationRelativeTo(null);
	setVisible(true);
}
}
