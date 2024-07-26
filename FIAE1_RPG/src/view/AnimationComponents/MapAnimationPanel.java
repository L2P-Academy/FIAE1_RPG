package view.AnimationComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import model.SerializationIDs;

// Helper Class for Background Drawing in MapView (Animation)
public class MapAnimationPanel extends JPanel{
	
//	private static final long serialVersionUID = SerializationIDs.backGroundPanelID;
	private Image backgroundImage;
	private List<Waypoint> waypoints;
	private MapCharacter mapCharacter;
	
	public MapAnimationPanel(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
		// draw waypoints
		g.setColor(Color.BLUE);
		for (Waypoint waypoint : waypoints) {
			g.fillOval(waypoint.getX() - 5, waypoint.getY() - 5, 30, 30);
		}
		
		// draw mapCharacter
		mapCharacter.draw(g);
		
	}
	
	public void setWayPoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}
	
	public void setMapCharacter(MapCharacter mapCharacter) {
		this.mapCharacter = mapCharacter;
	}
}
