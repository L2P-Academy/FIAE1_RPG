package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.SoundController;
import model.SerializationIDs;

public class CreditView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.creditViewID;

	private JButton backBtn;
	private JPanel buttonPnl, listPnl, mainPnl;
	private JLabel titleLbl, creditLbl;
	private Font gameFont;
	private SoundController soundController;

	public CreditView(SoundController soundController) {
		
		this.soundController = soundController;

		setTitle("CreditView");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// create EndScreenPanel
		EndScreenPanel endScreenPnl = new EndScreenPanel();

		// create Font
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);

		// create Buttons
		backBtn = new JButton("Zurück");
		beautifyButton(backBtn);

		// create Label
		titleLbl = new JLabel("Credits", SwingConstants.CENTER);
		titleLbl.setFont(gameFont);

		// add ActionListeners to Buttons
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound(); 
				dispose();
			}
		});

		getContentPane().add(titleLbl, BorderLayout.NORTH);
		getContentPane().add(endScreenPnl, BorderLayout.CENTER);
		getContentPane().add(backBtn, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// Modify Buttons
		private void beautifyButton(JButton button) {
			button.setFocusPainted(false);
			button.setBackground(new Color(10, 50, 100));
			button.setForeground(Color.WHITE);
			button.setFont(new Font("Old English Text MT", Font.BOLD, 36));

			// Rounded Corners
			Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 2);
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
