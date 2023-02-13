//Holds all the player's info
public class Player {
	private int xPosition;
	private int yPosition;
	private int xVelocity;
	private int yVelocity;
	private int xSize;
	private int ySize;
	private boolean invincible;
	
	//Creates new player with specified size and position
	public Player() {
		xPosition = 325;
		yPosition = 700;
		xVelocity = 0;
		yVelocity = 0;
		xSize = 100;
		ySize = 110;
		invincible = false;
	}
	
	//Returns if the playerModel contains the middle of a specified enemy model
	//p.s. I only use the middle of the enemy model because using all of the enemy model
	//causes the enemies to kill you from very far away
	public boolean colidesWith(Enemy enemy) {
		if (invincible)
			return false;
		for (int i = 0; i <= xSize; i++) {
			for (int j = 0; j <= ySize; j++) {
				if(enemy.getXPosition() + 50 == xPosition + i &&
				   enemy.getYPosition() + 50 == yPosition + j)
				   return true;
			}
		}
		return false;
	}
	
	//Updates player info into next frame of time
	public void update() {
		xPosition += xVelocity;
		yPosition += yVelocity;
	}
	
	//More descriptive than willEscape method in Enemy class, allows for keeping player 
	//inside panel no matter where they are
	public boolean willEscapeLeft() {
		if (xPosition + xVelocity < 0)
			return true;
		else
			return false;
	}
	public boolean willEscapeRight() {
		if (816 < xPosition + xVelocity + xSize)
			return true;
		else
			return false;
	}
	public boolean willEscapeTop() {
		if (yPosition + yVelocity < 0)
			return true;
		else
			return false;
	}
	public boolean willEscapeBottom() {
		if (939 < yPosition + yVelocity + ySize)
			return true;
		else
			return false;
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
	public boolean isInvincible() {
		return invincible;
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
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}
}
