package view.AnimationComponents;

public class Waypoint {
	private int x, y;

	public Waypoint(int x, int y) {
		this.x = x;
		this.y = y;
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
}
