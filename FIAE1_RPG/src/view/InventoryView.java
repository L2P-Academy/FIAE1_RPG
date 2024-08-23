package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.CharacterController;
import controller.SQLController;
import controller.SoundController;
import model.SerializationIDs;

public class InventoryView extends JFrame {
    private static final long serialVersionUID = SerializationIDs.inventoryViewID;

    private JPanel tablePnl, informationPnl, northEastPnl, centerEastPnl, southEastPnl;
    private JTable inventoryTbl;
    private JScrollPane scrollPane;
    private JLabel tableLbl, itemImgLbl, itemNameLbl;
    private JTextArea itemDescriptionTxt;
    private JButton useBtn, throwBtn, characterBtn, backBtn;

    private SQLController sqlController;
    private SoundController soundController;
    public CharacterController characterController;

    String imgPath = "res/img/MenuImages/InventoryBackgroundSuggestion1.png";

    public InventoryView(CharacterController characterController, SoundController soundController) {

    	this.characterController = characterController;
        sqlController = new SQLController();
        this.soundController = soundController;

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
        inventoryTbl.setFont(new Font("Arial", 0, 28));
        inventoryTbl.setRowHeight(42);
        inventoryTbl.setOpaque(false);
        inventoryTbl.setForeground(Color.white);
        ((DefaultTableCellRenderer) inventoryTbl.getDefaultRenderer(Object.class)).setOpaque(false);
        JTableHeader tableHeader = inventoryTbl.getTableHeader();
        tableHeader.setFont(new Font("Old English Text MT", Font.BOLD, 32));
        scrollPane = new JScrollPane(inventoryTbl);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        tablePnl.add(scrollPane);

        northEastPnl = new JPanel(new BorderLayout());
        northEastPnl.setBackground(new Color(245, 245, 220));
        centerEastPnl = new JPanel(new BorderLayout());
        southEastPnl = new JPanel(new GridBagLayout());
        southEastPnl.setBackground(new Color(245, 245, 220));
        

        // Item Information Pnl
        informationPnl = new JPanel();
        informationPnl.setLayout(new BoxLayout(informationPnl, BoxLayout.Y_AXIS));
        informationPnl.setBackground(new Color(245, 245, 220));
        itemImgLbl = createImageLabel("res/img/ItemModelImages/03_KleinerHeiltrank.png");
        itemNameLbl = new JLabel("Testitem", SwingConstants.CENTER);
        itemNameLbl.setFont(new Font("Old English Text MT", Font.BOLD, 32));
        itemDescriptionTxt = new JTextArea();
        itemDescriptionTxt.setLineWrap(true);
        itemDescriptionTxt.setEditable(false);
        itemDescriptionTxt.setFont(new Font("Arial", 0, 28));
        itemDescriptionTxt.setWrapStyleWord(true);

        // Add Components to east panels
        northEastPnl.add(itemNameLbl, BorderLayout.NORTH);
        northEastPnl.add(itemImgLbl, BorderLayout.CENTER);
        centerEastPnl.add(itemDescriptionTxt, BorderLayout.CENTER);

        // Buttons for informationPnl
        useBtn = new JButton("Benutzen");
        beautifyButton(useBtn);
        throwBtn = new JButton("Wegwerfen");
        beautifyButton(throwBtn);
        characterBtn = new JButton("Charakteransicht");
        beautifyButton(characterBtn);
        backBtn = new JButton("Zurück");
        beautifyButton(backBtn);

        // Adding buttons to southEastPnl with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);
        southEastPnl.add(useBtn, gbc);

        gbc.gridy++;
        southEastPnl.add(throwBtn, gbc);

        gbc.gridy++;
        southEastPnl.add(characterBtn, gbc);

        gbc.gridy++;
        southEastPnl.add(backBtn, gbc);

        // 
        itemDescriptionTxt.setPreferredSize(new Dimension(characterBtn.getWidth(), 250));

        informationPnl.add(northEastPnl);
        informationPnl.add(centerEastPnl);
        informationPnl.add(southEastPnl);

        getContentPane().add(tablePnl, BorderLayout.CENTER);
        getContentPane().add(informationPnl, BorderLayout.EAST);
        setVisible(true);
        
        // preselect the first row when opening
        if (inventoryTbl.getRowCount() > 0) {
        	inventoryTbl.setRowSelectionInterval(0, 0);
        	updateItemInformation(inventoryTbl.getValueAt(0, 1).toString());
			
		}
        
        // Listeners        
        useBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: implement method for using an item 
				// (e.g. change values on char)				
			}
		});
        
        throwBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: delete Item from Database and update table				
			}
		});
        
        characterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				new CharacterView(characterController, soundController);
			}
		});
        
        backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				soundController.playButtonClickSound();
				dispose();
			}
		});
        
        inventoryTbl.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e ) {
        		int selectedRow = inventoryTbl.getSelectedRow();
        		if(selectedRow != -1) {
        			String itemName = inventoryTbl.getValueAt(selectedRow, 1).toString();
        			updateItemInformation(itemName);
        		}
        	}
		});

    }
    private synchronized void updateItemInformation(String itemName) {
        String displayName = itemName;
        String imagePath = "res/img/ItemModelImages/" + itemName + ".png";

        itemNameLbl.setText(displayName);
        itemImgLbl.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        
        // Fetch and set the item description using itemName
        String description = sqlController.getItemDescription(itemName);
        itemDescriptionTxt.setText(description);
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
