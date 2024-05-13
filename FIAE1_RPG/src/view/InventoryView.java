package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.CharacterController;
import controller.InventoryController;
import model.ItemModel;
import model.SerializationIDs;

public class InventoryView extends JFrame{
	
	private static final long serialVersionUID = SerializationIDs.inventoryViewID;
	//Controller
	InventoryController inventoryController;
	CharacterController characterController;
	
	//Panel
    JPanel backgroundPanel, southPanel, buttonPanel, goldPanel, tablePanel, spacingPanelWest, spacingPanelEast;
    
    //Table
    JTable inventoryTable;
    JScrollPane inventoryScrollPane;
    
    //Label
    JLabel goldBalanceLabel, infoLabel;
    
    //Button
	JButton closeInvBtn, equipBtn, unequipBtn;
    JPanel closeInvBtnPan, equipBtnPan, unequipBtnPan;
    
    //Images
	String introImgPath = "res/img/InventoryBackground.png";
	
	//Font
	Font gameFont;
	
	//Variables
	int goldBalance = 1;
	ArrayList<ItemModel> inventoryList;
	Object[] columns = {"Name", "Quantity", "Price", "Damage"};
	DefaultTableModel tabModel = new DefaultTableModel();
	
	public InventoryView() {
        setTitle("Inventar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
                
//        goldBalance = characterController.getGoldBalance();
//        inventoryList = characterController.getInventoryList();
        
        tabModel.setColumnIdentifiers(columns);
        
		gameFont = new Font("Old English Text MT", Font.BOLD, 24);

    	// create background Panel
    	backgroundPanel = new BackGroundPanel(new ImageIcon(introImgPath).getImage());
    	backgroundPanel.setLayout(new BorderLayout());

    	// create spacing Panel
    	spacingPanelWest = new JPanel();
    	spacingPanelWest.setOpaque(false);
    	spacingPanelWest.setPreferredSize(new Dimension(160,50));
    	// create spacing Panel
    	spacingPanelEast = new JPanel();
    	spacingPanelEast.setOpaque(false);
    	spacingPanelEast.setPreferredSize(new Dimension(160,50));
    	
    	// create south Panel
    	southPanel = new JPanel(new BorderLayout());
    	southPanel.setOpaque(false);
    	southPanel.setPreferredSize(new Dimension(1200, 220));

    	// create gold Panel and Label
		goldBalanceLabel = new JLabel(Integer.toString(goldBalance), SwingConstants.CENTER);
		goldBalanceLabel.setFont(gameFont);
		goldBalanceLabel.setForeground(new Color(245, 245, 220));

    	goldPanel = new JPanel(new BorderLayout());
    	goldPanel.setOpaque(false);
    	goldPanel.setPreferredSize(new Dimension(50, 120));
		goldPanel.add(goldBalanceLabel, BorderLayout.CENTER);

    	// create inventory table
    	inventoryTable = new JTable();
    	inventoryTable.setBackground(new Color(252, 164, 89));
    	inventoryTable.setPreferredSize(new Dimension(880,460));
    	inventoryTable.setModel(tabModel);
    	inventoryTable.setGridColor(new Color(21, 37, 63));
    	inventoryTable.setFont(gameFont);
    	inventoryTable.setRowHeight(30);
    	inventoryTable.setAutoCreateRowSorter(true);
    	
    	inventoryScrollPane = new JScrollPane(inventoryTable);
    	inventoryScrollPane.setOpaque(false);
    	inventoryScrollPane.setBounds(10, 10, 10, 10);
    	inventoryScrollPane.getVerticalScrollBar().setBackground(new Color(252, 164, 89));
        
    	// create Table Panel
    	tablePanel = new JPanel(new BorderLayout());
    	tablePanel.setOpaque(false);
        tablePanel.add(inventoryScrollPane, BorderLayout.CENTER);
    	
    	// create button Panel
    	buttonPanel = new JPanel(new FlowLayout());
    	buttonPanel.setOpaque(false);
    	buttonPanel.setPreferredSize(new Dimension(1200, 100));

		// create close buttons
		closeInvBtn = new JButton("Schließen");
		closeInvBtn.setFont(gameFont);
		closeInvBtn.setForeground(new Color(245, 245, 220));
		closeInvBtn.setBackground(new Color(21, 37, 63));
		closeInvBtn.setFocusPainted(false);
		closeInvBtnPan = new JPanel(new FlowLayout());
		closeInvBtnPan.setOpaque(false);
		closeInvBtnPan.add(closeInvBtn);

		// create equip buttons
		equipBtn = new JButton("Ausrüsten");
		equipBtn.setFont(gameFont);
		equipBtn.setForeground(new Color(245, 245, 220));
		equipBtn.setBackground(new Color(21, 37, 63));
		equipBtn.setFocusPainted(false);
		equipBtnPan = new JPanel(new FlowLayout());
		equipBtnPan.setOpaque(false);
		equipBtnPan.add(equipBtn);
		
		// create unequip buttons
		unequipBtn = new JButton("Ablegen");
		unequipBtn.setFont(gameFont);
		unequipBtn.setForeground(new Color(245, 245, 220));
		unequipBtn.setBackground(new Color(21, 37, 63));
		unequipBtn.setFocusPainted(false);
		unequipBtnPan = new JPanel(new FlowLayout());
		unequipBtnPan.setOpaque(false);
		unequipBtnPan.add(unequipBtn);
		
		// fill buttonPanel with Buttons
		buttonPanel.add(equipBtnPan);
		buttonPanel.add(unequipBtnPan);
		southPanel.add(buttonPanel, BorderLayout.CENTER);
		southPanel.add(closeInvBtnPan, BorderLayout.SOUTH);
    	
		// fill the panels with contents
		backgroundPanel.add(goldPanel, BorderLayout.NORTH);
		backgroundPanel.add(spacingPanelWest, BorderLayout.WEST);
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		backgroundPanel.add(spacingPanelEast, BorderLayout.EAST);
		backgroundPanel.add(southPanel, BorderLayout.SOUTH);
		
		getContentPane().add(backgroundPanel);
		setLocationRelativeTo(null);
		setVisible(true);

		//close buttons function
		closeInvBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		//equip buttons function
		equipBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        goldBalance += 1;
		        goldBalanceLabel.setText(Integer.toString(goldBalance));
//				inventoryController.equipItem("");//Fehlt komlette struktur
				
			}
		});
		
		//unequip buttons function
		unequipBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        goldBalance -= 1;
		        goldBalanceLabel.setText(Integer.toString(goldBalance));
//				inventoryController.unequipItem("");//Fehlt komlette struktur
				
			}
		});
		
	}
}
