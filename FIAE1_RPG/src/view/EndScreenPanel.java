package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;

public class EndScreenPanel extends JPanel implements ActionListener{
	
	Font gameFont = new Font("Calisto MT", Font.PLAIN, 32);
	Timer creditTimer = new Timer(5, this);
	String text;
	int textY = 600;
	
	public EndScreenPanel() {
		setBackground(Color.BLACK);
		text = "Vielen Dank fürs Spielen!\n\n"
				+ "Project Lead\n"
				+ "L2P-Academy / Christoph Bauch\n"
				+ "\n"
				+ "Developers\n"
				+ "Nicoleta Coman\n"
				+ "Randy Porter\n"
				+ "Cedric Antunes\n"
				+ "Samael Schröder\n"
				+ "Christian Gassmann\n"
				+ "Rida Faouzi\n"
				+ "\n"
				+ "Music\n"
				+ "Lexin_Music (StartMenü, Map)\n"
				+ "Good_B_Music (Credits)\n"
				+ "SoundBay (Intro)\n"
				+ "Udio (AI-Generated Music)\n"
				+ "\n"
				+ "Ambient Sounds\n"
				+ "SoundsForYou (Rain, Campfire)\n"
				+ "GregorQuendel_SoundDesign (Water)\n"
				+ "JCI-21 (Wind)\n"
				+ "SSPsurvival (Birds)\n"
				+ "\n"
				+ "Sound Effects / FX\n"
				+ "DavidDumaisAudio (Blade FX)\n"
				+ "floraphonic (MonsterFX, ArrowShotFX, DragonFX\n"
				+ "Universfield (ClickFX)\n"
				+ "Pixabay\n"
				+ "SergeQuadrado (ByeFX)\n";
				
		creditTimer.start();
	}
	
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(gameFont);
		g2d.setColor(Color.WHITE);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		int y = textY;
		
		for(String line : text.split("\n")) {
			int stringLength = (int)g2d.getFontMetrics().getStringBounds(line, g2d).getWidth();
			int x = getWidth()/2 - stringLength/2;
			g2d.drawString(line, x, y += 28);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(textY);		
		textY--;
		if (textY < -900) {
			creditTimer.stop();			
		}
		repaint();
		
	}
}
