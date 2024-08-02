package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.SQLController;
import controller.SoundController;
import model.SerializationIDs;

public class InventoryView extends JFrame{
	private static final long serialVersionUID = SerializationIDs.inventoryViewID;
	
	private JPanel tablePnl, informationPnl;
	private JTable inventoryTbl;
	private JScrollPane scrollPane;
	private JLabel tableLbl, itemImgLbl;
	private JTextArea itemDescriptionTxt;
	private JButton useBtn, throwBtn, characterBtn;
	
	private SQLController sqlController;
	private SoundController soundController;
	
	String imgPath = "res/img/MenuImages/InventoryBackgroundSuggestion1.png";
	
	public InventoryView() {
		
		sqlController = new SQLController();
		
		setTitle("Inventar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		// Right Side Table
		String[] columnNames = {"Nr.", "Name", "Anzahl", "Wert", "Gesamtwert"};
		Object[][] data = sqlController.getItemsFromInventory();
		
		// Table + Panel
		tablePnl = new BackGroundPanel(new ImageIcon(imgPath).getImage());
		inventoryTbl = new JTable(new DefaultTableModel(data, columnNames));
		scrollPane = new JScrollPane(inventoryTbl);		
		tablePnl.add(scrollPane);
		
		// Item Information Pnl
		informationPnl = new JPanel();
		informationPnl.setLayout(new BoxLayout(informationPnl, BoxLayout.Y_AXIS));
		itemImgLbl = createImageLabel("res/img/ItemModelImages/03_KleinerHeiltrank.png");
		itemDescriptionTxt = new JTextArea();
        itemDescriptionTxt.setLineWrap(true);
        itemDescriptionTxt.setEditable(false);
        itemDescriptionTxt.setBackground(new Color(245, 245, 220));
        itemDescriptionTxt.setText("Testbeschreibung, welche einen langen Text darstellt,"
				+ "um einen Zeilenumbruch zu simulieren");
        // Buttons for informationPnl
        useBtn = new JButton("Benutzen");
        beautifyButton(useBtn);
        throwBtn = new JButton("Wegwerfen");
        beautifyButton(throwBtn);
        characterBtn = new JButton("Charakteransicht");
        beautifyButton(characterBtn);
        
        informationPnl.add(itemImgLbl);
        informationPnl.add(itemDescriptionTxt);
        informationPnl.add(useBtn);
        informationPnl.add(throwBtn);
        informationPnl.add(characterBtn);
        
        getContentPane().add(tablePnl, BorderLayout.CENTER);
        getContentPane().add(informationPnl, BorderLayout.EAST);
        setVisible(true);
        
		
	}
	
	// create ImageLabel
    private JLabel createImageLabel(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
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
