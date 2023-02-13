//Holds all the info for enemies, while also randomizing the values for every new enemy
public class Enemy {
	private int xPosition;
	private int yPosition;
	private int xVelocity;
	private int yVelocity;
	private int xSize;
	private int ySize;
	
	//Creates new enemy with randomized values
	public Enemy() {
		xPosition = (int)(Math.random() * 806);
		yPosition = 0;
		xVelocity = (int)(Math.random() * 20 - 10);
		yVelocity = (int)(Math.random() * 10 + 10);
		xSize = 100;
		ySize = 100;
	}
	
	//Returns true if enemy will escape the panel in the next frame of time
	public boolean willEscape() {
		if (xPosition + xVelocity < 0 || 816 < xPosition + xSize + xVelocity ||
			yPosition + yVelocity < 0 || 939 < yPosition + ySize + yVelocity)
			return true;
		else
			return false;
	}
	//Updates enemy into next frame of time
	public void update() {
		xPosition += xVelocity;
		yPosition += yVelocity;
	}
	
	//All getter and setter methods
	public int getXPosition() {
		return xPosition;
	}
	public int getYPosition() {
		return yPosition;
	}
	public int getXVelocity() {
		return xVelocity;
	}
	public int getYVelocity() {
		return yVelocity;
	}
	public int getXSize() {
		return xSize;
	}
	public int getYSize() {
		return ySize;
	}
	
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public void setXVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}
	public void setYVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}
	public void setXSize(int xSize) {
		this.xSize = xSize;
	}
	public void setYBox(int ySize) {
		this.ySize = ySize;
	}
}