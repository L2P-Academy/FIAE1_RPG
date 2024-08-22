package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import model.SerializationIDs;

public class JournalView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.journalViewID;
	private JButton detailsBtn, backBtn;
	private JLabel titleLbl;
	private BackGroundPanel backgroundPnl;
	private JPanel buttonPnl;
	private String backgroundImgPath = "res/img/MenuImages/Journalview_Background2.png";
	private JTable questsTbl;
	private Font gameFont;

	public JournalView() {

		setTitle("Quest-Tagebuch");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

		// Game font
		gameFont = new Font("Old English Text MT", Font.BOLD, 64);

		// Labels and buttons
		detailsBtn = new JButton("Quest-Details");
		beautifyButton(detailsBtn);
		backBtn = new JButton("Zurück");
		beautifyButton(backBtn);
		titleLbl = new JLabel("Aktive Quests");
		titleLbl.setFont(gameFont);

		// Panel
		backgroundPnl = new BackGroundPanel(new ImageIcon(backgroundImgPath).getImage());
		backgroundPnl.setLayout(new BorderLayout());
		buttonPnl = new JPanel(new FlowLayout());

		// Example Data for the quest table
		String[] headerData = { "Name", "Beschreibung","Level","Gold","XP","Questgeber", "Belohnung" };
		Object[][] exampleData = {
				{"Eine lang erwartete Reise","Besuche die Taverne im Dorf!","1","50","100","Tavernenwirt","Weltkarte"},
		};
		
		// Creates a table model with example data	
		DefaultTableModel tableModel = new DefaultTableModel(exampleData, headerData);

		// Create table
		questsTbl = new JTable(tableModel);
		questsTbl.setOpaque(false);
		questsTbl.setShowGrid(false);
		questsTbl.setFont(new Font("Old English Text MT", Font.ITALIC, 32));
		questsTbl.setRowHeight(42);
		questsTbl.setForeground(Color.white);
		((DefaultTableCellRenderer) questsTbl.getDefaultRenderer(Object.class)).setOpaque(false);
		
		//Inserts the table into a scroll pane if the amount of data increases
		JScrollPane scrollPane = new JScrollPane(questsTbl);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		// Add buttons
		buttonPnl.add(detailsBtn);
		buttonPnl.add(backBtn);
		buttonPnl.setOpaque(false);

		backgroundPnl.add(buttonPnl, BorderLayout.SOUTH);
		backgroundPnl.add(titleLbl, BorderLayout.NORTH);
		backgroundPnl.add(scrollPane, BorderLayout.CENTER);

		// Add ActionListener to the details button
		detailsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new QuestView();

			}
		});

		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		getContentPane().add(backgroundPnl);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Modify buttons
	private void beautifyButton(JButton button) {
		button.setFocusPainted(false);
		button.setBackground(new Color(10, 50, 100));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Old English Text MT", Font.BOLD, 36));

		// Rounded corners
		Border border = BorderFactory.createLineBorder(new Color(255, 255, 255), 2);
		Border roundedBorder = BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 20, 10, 20));
		button.setBorder(
				BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(5, 15, 5, 15)));

		// Color change on mouseover
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(100, 149, 237));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(10, 50, 100));
			}
		});
	}
		// Main method for testing
	public static void main (String[] args) {
		new JournalView();
	}

}
