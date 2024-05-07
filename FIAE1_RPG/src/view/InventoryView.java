package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class InventoryView extends JFrame{

    JPanel backgroundPanel, buttonPanel, goldPanel, tablePanel, spacingPanelWest, spacingPanelEast;
    JTable inventoryTable;
    JScrollPane inventoryScrollPane;
    JLabel goldBalanceLabel;
	JButton closeInvBtn;
    JPanel closeInvBtnPan;
	String introImgPath = "res/img/InventoryBackground.png";
	Font gameFont;
	String goldBalance;
	
	public InventoryView() {
        setTitle("Inventar");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        goldBalance = "999";
        
		gameFont = new Font("Old English Text MT", Font.BOLD, 24);

    	// create background Panel
    	backgroundPanel = new BackGroundPanel(new ImageIcon(introImgPath).getImage());
    	backgroundPanel.setLayout(new BorderLayout());

    	// create background Panel
    	spacingPanelWest = new JPanel();
    	spacingPanelWest.setOpaque(false);
    	spacingPanelWest.setPreferredSize(new Dimension(160,50));
    	// create background Panel
    	spacingPanelEast = new JPanel();
    	spacingPanelEast.setOpaque(false);
    	spacingPanelEast.setPreferredSize(new Dimension(160,50));

    	// create gold Panel and Label
		goldBalanceLabel = new JLabel(goldBalance, SwingConstants.CENTER);
		goldBalanceLabel.setFont(gameFont);
		goldBalanceLabel.setForeground(new Color(245, 245, 220));

    	goldPanel = new JPanel(new BorderLayout());
    	goldPanel.setOpaque(false);
    	goldPanel.setPreferredSize(new Dimension(50, 120));
		goldPanel.add(goldBalanceLabel, BorderLayout.CENTER);

    	// create inventory table
    	inventoryTable = new JTable();
    	inventoryTable.setBackground(new Color(50,50,50,120));
    	inventoryTable.setPreferredSize(new Dimension(880,460));
        
    	// create Table Panel
    	tablePanel = new JPanel(new BorderLayout());
    	tablePanel.setOpaque(false);
        tablePanel.add(inventoryTable, BorderLayout.CENTER);
    	
    	// create button Panel
    	buttonPanel = new JPanel(new BorderLayout());
    	buttonPanel.setOpaque(false);
    	buttonPanel.setPreferredSize(new Dimension(1200, 220));

		// create close buttons
		closeInvBtn = new JButton("Schließen");
		closeInvBtn.setFont(gameFont);
		closeInvBtn.setForeground(new Color(245, 245, 220));
		closeInvBtn.setBackground(new Color(21, 37, 63));
		closeInvBtn.setFocusPainted(false);
		closeInvBtnPan = new JPanel(new FlowLayout());
		closeInvBtnPan.setOpaque(false);
		closeInvBtnPan.add(closeInvBtn);
		
		// fill buttonPanel with Buttons
		buttonPanel.add(closeInvBtnPan, BorderLayout.SOUTH);
    	
		// fill the panels with contents
		backgroundPanel.add(goldPanel, BorderLayout.NORTH);
		backgroundPanel.add(spacingPanelWest, BorderLayout.WEST);
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		backgroundPanel.add(spacingPanelEast, BorderLayout.EAST);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		getContentPane().add(backgroundPanel);
		setLocationRelativeTo(null);
		setVisible(true);

		//close buttons function
		closeInvBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MapView mapView = new MapView();
				dispose();
				
			}
		});
		
	}
}
