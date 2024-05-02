package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

// Helper Class for Background Drawing in Main Menu
public class BackGroundPanel extends JPanel{
	
	private Image backgroundImage;
	
	public BackGroundPanel(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
		setLayout(new BorderLayout());	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
	
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
		
	}
}
