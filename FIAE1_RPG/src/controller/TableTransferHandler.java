package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.border.Border;

public class TableTransferHandler extends TransferHandler {

    private String slotType;
    private JLabel slotLabel; 
    private Color originalBackgroundColor; 

    public TableTransferHandler(String slotType, JLabel slotLabel) {
        this.slotType = slotType;
        this.slotLabel = slotLabel;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JTable table = (JTable) c;
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String itemName = (String) table.getValueAt(selectedRow, 0);
            String slot = (String) table.getValueAt(selectedRow, 1);

            // Highlight the slot label as soon as the drag starts
            if (slotType.equalsIgnoreCase(slot)) {
                highlightSlotLabel();
            }

            return new StringSelection(itemName + ":" + slot);
        }
        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        if (slotType == null) {
            return false;
        }

        if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                String[] parts = data.split(":");
                String itemSlot = parts[1];

                return slotType.equalsIgnoreCase(itemSlot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void exportDone(JComponent source, Transferable data, int action) {
        resetSlotBackground(); // Reset background when drag operation is done
        super.exportDone(source, data, action);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        if (canImport(support)) {
            try {
                String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                String[] parts = data.split(":");
                String itemName = parts[0];
                String slot = parts[1];

                System.out.println("Item " + itemName + " equipped to " + slot);

                slotLabel.setIcon(loadItemImage(itemName));

                Border darkGrayFrameBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 3);
                slotLabel.setBorder(darkGrayFrameBorder);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private ImageIcon loadItemImage(String itemName) {
        String imagePath = "res/img/ItemModelImages/" + itemName + ".png";
        return new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
    }

    private void highlightSlotLabel() {
        originalBackgroundColor = slotLabel.getBackground();
        slotLabel.setOpaque(true);
        slotLabel.setBackground(Color.GREEN);
        slotLabel.repaint();
    }

    private void resetSlotBackground() {
        if (slotLabel != null && originalBackgroundColor != null) {
            slotLabel.setBackground(originalBackgroundColor);
            slotLabel.setOpaque(false);
            slotLabel.repaint();
        }
    }
}