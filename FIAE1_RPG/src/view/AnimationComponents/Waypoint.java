package view.AnimationComponents;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Waypoint {
	private int x, y, index;
	private ImageIcon imageIcon;

	public Waypoint(int index, int x, int y, String iconPath) {
		this.index = index; 
		this.x = x;
		this.y = y;
		this.imageIcon = scaleImage(iconPath, 80, 80); // change this to resize all Map Icons
	}
	

	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public double distanceTo(int x, int y) {
		return Math.sqrt((this.x - x) * (this.x-x) + (this.y - y) * (this.y - y));

	}
	
    private ImageIcon scaleImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
    
    public ImageIcon getImage() {
    	return imageIcon;
    }
}
