package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.CharacterController;
import controller.SQLController;
import controller.TableTransferHandler;
import model.PlayerCharacterModel;
import model.SerializationIDs;

public class CharacterView extends JFrame {

	private static final long serialVersionUID = SerializationIDs.characterViewID;
	private JPanel mainEquipPnl, leftEquipPnl, rightEquipPnl, characterPnl, buttonPnl, equipListPnl, statsPnl;
	private JButton equipBtn, unequipBtn, backBtn;
	private JLabel nameLbl, levelLbl, equipLbl, helmetImgLbl, chestImgLbl, glovesImgLbl, weapon1ImgLbl, legsImgLbl,
			feetImgLbl, weapon2ImgLbl, ringImgLbl, characterImgLbl;
	private JTable equipTbl;
	private JScrollPane scrollPane;
	private JProgressBar hpBar, manaBar, xpBar;
	private SQLController sqlController;
	public CharacterController characterController;
	private PlayerCharacterModel characterModel;
	private int genderIndex;
	private String gender;
	private String[][] imagePaths = {
			{ "res/img/CharacterPortraits/New_Race_Gender/new_human_male.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_human_female.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_human_divers.png" },
			{ "res/img/CharacterPortraits/New_Race_Gender/new_elf_male.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_elf_female.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_elf_divers.png" },
			{ "res/img/CharacterPortraits/New_Race_Gender/new_dwarf_male.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_female.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_dwarf_divers.png" },
			{ "res/img/CharacterPortraits/New_Race_Gender/new_halfling_male.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_halfling_female.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_halfling_divers.png" },
			{ "res/img/CharacterPortraits/New_Race_Gender/new_orc_male.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_orc_female.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_orc_divers.png" },
			{ "res/img/CharacterPortraits/New_Race_Gender/new_goblin_male.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_goblin_female.png",
					"res/img/CharacterPortraits/New_Race_Gender/new_goblin_divers.png" } };

	public CharacterView(CharacterController characterController) {

		// initialize
		this.characterController = characterController;
		characterModel = characterController.getCharacter();
		setTitle("Charakterübersicht");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setLocationRelativeTo(null);
		sqlController = new SQLController();

		// Main Panel
		mainEquipPnl = new JPanel(new BorderLayout());

		// Left + Right Equip Panel
		leftEquipPnl = new JPanel();
		leftEquipPnl.setLayout(new BoxLayout(leftEquipPnl, BoxLayout.Y_AXIS));
		leftEquipPnl.setPreferredSize(new Dimension(300, getHeight()));

		helmetImgLbl = createImageLabel("res/img/CharacterViewImages/helmet_slot.png");
		chestImgLbl = createImageLabel("res/img/CharacterViewImages/chest_slot.png");
		glovesImgLbl = createImageLabel("res/img/CharacterViewImages/glove_slot.png");
		weapon1ImgLbl = createImageLabel("res/img/CharacterViewImages/weapon1_slot.png");

		leftEquipPnl.add(helmetImgLbl);
		leftEquipPnl.add(Box.createRigidArea(new Dimension(0, 18))); // Adding space between images
		leftEquipPnl.add(chestImgLbl);
		leftEquipPnl.add(Box.createRigidArea(new Dimension(0, 18)));
		leftEquipPnl.add(glovesImgLbl);
		leftEquipPnl.add(Box.createRigidArea(new Dimension(0, 18)));
		leftEquipPnl.add(weapon1ImgLbl);

		rightEquipPnl = new JPanel();
		rightEquipPnl.setLayout(new BoxLayout(rightEquipPnl, BoxLayout.Y_AXIS));
		rightEquipPnl.setPreferredSize(new Dimension(300, getHeight()));

		legsImgLbl = createImageLabel("res/img/CharacterViewImages/legs_slot.png");
		feetImgLbl = createImageLabel("res/img/CharacterViewImages/feet_slot.png");
		ringImgLbl = createImageLabel("res/img/CharacterViewImages/ring_slot.png");
		weapon2ImgLbl = createImageLabel("res/img/CharacterViewImages/weapon2_slot.png");

		rightEquipPnl.add(legsImgLbl);
		rightEquipPnl.add(Box.createRigidArea(new Dimension(100, 18)));
		rightEquipPnl.add(feetImgLbl);
		rightEquipPnl.add(Box.createRigidArea(new Dimension(100, 18)));
		rightEquipPnl.add(ringImgLbl);
		rightEquipPnl.add(Box.createRigidArea(new Dimension(100, 18)));
		rightEquipPnl.add(weapon2ImgLbl);

		// characterPanel
		characterPnl = new JPanel(new BorderLayout());
		characterPnl.setPreferredSize(new Dimension(512, 512));

		// TODO: dynamic Name from database information
		Font headLineFont = new Font("Old English Text MT", Font.BOLD, 42);
		nameLbl = new JLabel(characterModel.getName(), JLabel.CENTER);
		nameLbl.setFont(headLineFont);
		// setFont
		characterPnl.add(nameLbl, BorderLayout.NORTH);

		// TODO: dynamic character image from database information
		switch (characterModel.getGender()) {
		case "m":
			genderIndex = 0;
			break;
		case "w":
			genderIndex = 1;
			break;
		case "d":
			genderIndex = 2;
			break;
		default:
			break;
		}
		characterImgLbl = chooseImgPath(characterModel.getRaceID() - 1, genderIndex);
		characterPnl.add(characterImgLbl, BorderLayout.CENTER);

		// ProgressBars, TODO: Dynamic ProgressBars from database information
		statsPnl = new JPanel();
		statsPnl.setLayout(new BoxLayout(statsPnl, BoxLayout.Y_AXIS));
		hpBar = new JProgressBar();
		hpBar.setStringPainted(true);
		hpBar.setValue(characterModel.getCurrentHP());
		hpBar.setMaximum(characterModel.getMaxHP());
		hpBar.setString("HP: " + characterModel.getCurrentHP() + "/" + characterModel.getMaxHP());
		hpBar.setForeground(Color.GREEN);
		hpBar.setPreferredSize(new Dimension(200, 40));
		hpBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		hpBar.setUI(new BasicProgressBarUI() {
			protected Dimension getPreferredInnerHorizontal() {
				return new Dimension(200, 40);
			}
		});

		manaBar = new JProgressBar();
		manaBar.setStringPainted(true);
		manaBar.setValue(characterModel.getCurrentMana());
		manaBar.setMaximum(characterModel.getMaxMana());
		manaBar.setString("Mana:" + characterModel.getCurrentMana() + "/" + characterModel.getMaxMana());
		manaBar.setForeground(Color.BLUE);
		manaBar.setPreferredSize(new Dimension(200, 40));
		manaBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		manaBar.setUI(new BasicProgressBarUI() {
			protected Dimension getPreferredInnerHorizontal() {
				return new Dimension(200, 40);
			}
		});

		xpBar = new JProgressBar();
		xpBar.setStringPainted(true);
		xpBar.setValue(characterModel.getCurrentXP());
		xpBar.setMaximum(characterModel.getMaxXP());
		xpBar.setString("XP: " + characterModel.getCurrentXP() + "/" + characterModel.getMaxXP());
		xpBar.setForeground(Color.ORANGE);
		xpBar.setPreferredSize(new Dimension(200, 40));
		xpBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		xpBar.setUI(new BasicProgressBarUI() {
			protected Dimension getPreferredInnerHorizontal() {
				return new Dimension(200, 40);
			}
		});

		statsPnl.add(hpBar);
		statsPnl.add(manaBar);
		statsPnl.add(xpBar);

		characterPnl.add(statsPnl, BorderLayout.SOUTH);

        // Buttons & ButtonPnl
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new FlowLayout());
        equipBtn = new JButton("Ausrüsten");
        unequipBtn = new JButton("Ablegen");
        backBtn = new JButton("Zurück");
        beautifyButton(equipBtn);
        beautifyButton(unequipBtn);
        beautifyButton(backBtn);

        backBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // TODO: Set Items equipped on isEquipped=1 on DB!
            }
        });

        buttonPnl.add(equipBtn);
        buttonPnl.add(unequipBtn);
        buttonPnl.add(backBtn);

        mainEquipPnl.add(rightEquipPnl, BorderLayout.EAST);
        mainEquipPnl.add(buttonPnl, BorderLayout.SOUTH);
        mainEquipPnl.add(leftEquipPnl, BorderLayout.WEST);
        mainEquipPnl.add(characterPnl, BorderLayout.CENTER);

        // Right Side Label and Panel
        equipListPnl = new JPanel(new BorderLayout());
        equipListPnl.setPreferredSize(new Dimension(800, getHeight()));
        equipLbl = new JLabel("Ausrüstung");
        equipListPnl.add(equipLbl, BorderLayout.NORTH);

        // Right Side Table
        String[] columnNames = {"Name", "Slot", "Dmg", "Def", "LvL"};
        Object[][] data = sqlController.getEquipFromInventory();	

        // Table Model
        Font tableHeaderFont = new Font("Old English Text MT", Font.BOLD, 32);
        Font tableEntryFont = new Font("Arial", Font.PLAIN, 28);
        equipTbl = new JTable(new DefaultTableModel(data, columnNames));
        equipTbl.setFont(tableEntryFont);
        equipTbl.setRowHeight(42);
        equipTbl.setDragEnabled(true);
        equipTbl.setTransferHandler(new TableTransferHandler("", null));

     // Slots for Drag & Drop
        helmetImgLbl.setTransferHandler(new TableTransferHandler("Head", helmetImgLbl));
        chestImgLbl.setTransferHandler(new TableTransferHandler("Chest", chestImgLbl));
        glovesImgLbl.setTransferHandler(new TableTransferHandler("Hands", glovesImgLbl));
        weapon1ImgLbl.setTransferHandler(new TableTransferHandler("Weapon1", weapon1ImgLbl));
        legsImgLbl.setTransferHandler(new TableTransferHandler("Legs", legsImgLbl));
        feetImgLbl.setTransferHandler(new TableTransferHandler("Feet", feetImgLbl));
        ringImgLbl.setTransferHandler(new TableTransferHandler("Ring", ringImgLbl));
        weapon2ImgLbl.setTransferHandler(new TableTransferHandler("Weapon2", weapon2ImgLbl));


        // Table Header
        JTableHeader tableHeader = equipTbl.getTableHeader();
        tableHeader.setFont(tableHeaderFont);

        scrollPane = new JScrollPane(equipTbl);		
        equipListPnl.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(equipListPnl, BorderLayout.EAST);
        getContentPane().add(mainEquipPnl, BorderLayout.WEST);
        setVisible(true);
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

    private JLabel createImageLabel(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    private JLabel chooseImgPath(int raceIndex, int genderIndex) {
        String imagePath = imagePaths[raceIndex][genderIndex];	
        JLabel label;
        ImageIcon icon = new ImageIcon(imagePath);
        label = new JLabel(icon);
        return label;
    }
}
