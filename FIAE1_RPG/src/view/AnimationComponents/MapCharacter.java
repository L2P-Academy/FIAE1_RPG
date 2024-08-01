package view.AnimationComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MapCharacter {
	private int x, y, targetX, targetY;
	private double speed = 4;
	private boolean atTarget = false;
	private Image characterImage;
	
	public MapCharacter(int x, int y) {
		this.x = x;
		this.y = y;
		this.targetX = x;
		this.targetY = y;
		this.characterImage = new ImageIcon("res/img/MapViewImages/Map_Player.png").getImage();
		}
	
	public void moveTo(int targetX, int targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.atTarget = false;
	}
	
	public boolean isAtTarget() {
		return atTarget;
	}
	
	public void update() {
		if (atTarget) {
			return;
		}
		
		boolean reachedX = false;
		boolean reachedY = false;
		
		if (x < targetX) {
			x += speed;
			if (x > targetX) {
				x = targetX;
				reachedX = true;
			}			
		} else if (x > targetX) {
			x -= speed;
			if (x < targetX) {
				x = targetX;
				reachedX = true;
			}
		}
		if (y < targetY) {
			y += speed;
			if (y > targetY) {
				y = targetY;
				reachedY = true;
			}
		} else if (y > targetY) {
			y -= speed;
			if (y < targetY) {
				y = targetY;
				reachedY = true;
			}
		}
		
		if (reachedX && reachedY) {
			atTarget = true;
		}
	}
	
	public void draw(Graphics g) {
		int size = 40;
		g.drawImage(characterImage, x - size / 2, y - size/2, size, size, null);
//		g.setColor(Color.RED);
//		g.fillOval(x - 5, y - 5, 30, 30);
	}
}
