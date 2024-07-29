package view.AnimationComponents;

public class Waypoint {
	private int x, y, index;
	private String iconPath;

	public Waypoint(int index, int x, int y, String iconPath) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.iconPath = iconPath;
	}
	

	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public String getIconPath() {
		return iconPath;
	}


	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
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
