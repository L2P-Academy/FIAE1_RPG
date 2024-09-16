package view.AnimationComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import controller.CharacterController;
import controller.SoundController;
import view.CombatView;

public class MapCharacter {
	private int x, y, targetX, targetY;
	private double speed = 4;
	private boolean atTarget = false;
	private Image characterImage;
	private CharacterController characterController;
	private SoundController soundController;
	private boolean combatTriggered = false;
	
	public MapCharacter(int x, int y, CharacterController characterController, SoundController soundController) {
		this.x = x;
		this.y = y;
		this.targetX = x;
		this.targetY = y;
		this.characterImage = new ImageIcon("res/img/MapViewImages/Map_Player.png").getImage();
		this.characterController = characterController;
		this.soundController = soundController;
		}
	
	public void moveTo(int targetX, int targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.atTarget = false;
		this.combatTriggered = false; // -> max. 1 combat per move, reset
	}
	
	public boolean isCombatTriggered() {	
		return combatTriggered;
	}
	
	public void setCombatTriggered(boolean combatTriggered) {
		this.combatTriggered = combatTriggered;
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
