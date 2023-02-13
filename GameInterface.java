import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
public class GameInterface {   
	//Where our custom panel is defined
	@SuppressWarnings("serial")
	public static class MyPanel extends JPanel {
		public MyPanel() {
	    }
	    public Image loadImage(String filename) {
	    	Toolkit tk = Toolkit.getDefaultToolkit();
	    	Image img = tk.getImage(filename);
	    	MediaTracker mt = new MediaTracker(this);
	    	mt.addImage(img, 0);
	    	try {
	    		mt.waitForAll();
	    	} catch (InterruptedException ie) {}
	    	return img;
	    }
	    
	    //All objects we need to draw on the panel
	    Image backgroundImage = loadImage("backgroundImage.jpg");
	    Image playerModel = loadImage("playerModel.png");
	    Image playerModelStraight = playerModel;
	    Image playerModelLeft = loadImage("playerModelLeft.png");
	    Image playerModelRight = loadImage("playerModelRight.png");
	    Image enemyModel = loadImage("enemyModel.png");
	    Player player = new Player();
	    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	    int distance = 0;
	    int dangerLevel = 1;
	    String wTutorial = "W = Up";
	    String aTutorial = "A = Left";
	    String sTutorial = "S = Down";
	    String dTutorial = "D = Right";
	    String shieldTutorial1 = "";
	    String shieldTutorial2 = "";
	    String endText1 = "";
	    String endText2 = "";
	    int lives = 3;
	    
	    //Here is where all the drawing on screen is done
	    @Override
	    public void paintComponent(Graphics g) {
	    	super.paintComponent(g);
	    	g.drawImage(backgroundImage, 0, 0, 816, 939, this);
	    	g.drawImage(playerModel, player.getXPosition(), player.getYPosition(),
    				player.getXSize(), player.getYSize(), this);
	    	for (int i = 0; i < enemyList.size(); i++) {
	    		Enemy currEnemy = enemyList.get(i);
	    		g.drawImage(enemyModel, currEnemy.getXPosition(), currEnemy.getYPosition(),
	    					currEnemy.getXSize(), currEnemy.getXSize(), this);
	    	}
	    	g.setColor(new Color(107, 52, 235));
	    	g.setFont(new Font("SansSerif", Font.BOLD, 35));
	    	g.drawString("" + distance + "m", 675, 30);
	    	
	    	if (lives == 3)
	    		g.setColor(new Color(66, 245, 215));
	    	else if (lives == 2)
	    		g.setColor(new Color(200, 245, 66));
	    	else if (lives == 1)
	    		g.setColor(new Color(245, 167, 66));
	    	else if (lives == 0)
	    		g.setColor(new Color(245, 66, 66));
	    	g.drawString("Shields: " + lives, 300, 875);
	    	
	    	g.setColor(Color.RED);
	    	g.drawString(endText1, 250, 400);
	    	g.drawString(endText2, 100, 430);
	    	
	    	g.setColor(new Color(235, 52, 216));
	    	g.setFont(new Font("SansSerif", Font.BOLD, 25));
	    	g.drawString(wTutorial, 10, 30);
	    	g.drawString(aTutorial, 10, 60);
	    	g.drawString(sTutorial, 10, 90);
	    	g.drawString(dTutorial, 10, 120);
	    	g.drawString(shieldTutorial1, 10, 30);
	    	g.drawString(shieldTutorial2, 10, 60);
	    	
	    	if (dangerLevel == 1) 
	    		g.setColor(new Color(107, 52, 235));
	    	else if (dangerLevel == 2)
	    		g.setColor(new Color(52, 119, 235));
	    	else if (dangerLevel == 3)
	    		g.setColor(new Color(52, 235, 219));
	    	else if (dangerLevel == 3)
	    		g.setColor(new Color(52, 235, 128));
	    	else if (dangerLevel == 4)
	    		g.setColor(new Color(88, 235, 52));
	    	else if (dangerLevel == 5)
	    		g.setColor(new Color(177, 235, 52));
	    	else if (dangerLevel == 6)
	    		g.setColor(new Color(235, 232, 52));
	    	else if (dangerLevel == 7)
	    		g.setColor(new Color(235, 152, 52));
	    	else if (dangerLevel == 8)
	    		g.setColor(new Color(235, 52, 52));
	    	else if (dangerLevel == 9) {
	    		if ((distance / 10) % 2 == 0)
	    			g.setColor(new Color(235, 52, 52));
	    		if ((distance / 10) % 2 == 1)
	    			g.setColor(new Color(235, 152, 52));
	    	}
	    	g.setFont(new Font("SansSerif", Font.BOLD, 20));
	    	g.drawString("Danger level: " + dangerLevel, 625, 875);
		}
	}
	
	//Main Method
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Space Race");
		MyPanel panel = new MyPanel();
		Player player = panel.player;
		ArrayList<Enemy> enemyList = panel.enemyList;
		
		
		panel.setBounds(0, 0, 816, 939);
		panel.repaint();
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(new Point(10, 10));
		frame.setSize(new Dimension(800 + 16, 900 + 39));
		frame.setPreferredSize(new Dimension(800 + 16, 900 + 39));
		frame.setResizable(false);
		frame.setIconImage(panel.playerModel);
		frame.setVisible(true);
		
		
		class MovementListener extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_W) 
					player.setYVelocity(-10);
				if (event.getKeyCode() == KeyEvent.VK_A)
					player.setXVelocity(-10);
				if (event.getKeyCode() == KeyEvent.VK_S)
					player.setYVelocity(10);
				if (event.getKeyCode() == KeyEvent.VK_D)
					player.setXVelocity(10);
			}	
			
			@Override
			public void keyReleased(KeyEvent event) {
				switch(event.getKeyCode()) {
				case (KeyEvent.VK_W): player.setYVelocity(0);
				break;
				case (KeyEvent.VK_A): player.setXVelocity(0);
				break;
				case (KeyEvent.VK_S): player.setYVelocity(0);
				break;
				case (KeyEvent.VK_D): player.setXVelocity(0);
				break;
				case (KeyEvent.VK_ESCAPE): System.exit(1);
				break;
				default: break;
				}
			}
		}
		panel.requestFocusInWindow();
		panel.addKeyListener(new MovementListener());
		
		//Game updater: updates all info then repaints the panel,
		//also detecting if the player lost or not and carrying out the specified actions
		ActionListener updater = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (player.willEscapeTop()) {
					player.update();
					player.setYPosition(0);
				}
				else if (player.willEscapeBottom()) {
					player.update();
					player.setYPosition(900 - 75);
				}
				else if (player.willEscapeLeft()) {
					player.update();
					player.setXPosition(0);
				}
				else if (player.willEscapeRight()) {
					player.update();
					player.setXPosition(800 - 75);
				}
				else
					player.update();
				
				for (int i = 0; i < enemyList.size(); i++) {
					if (enemyList.get(i).willEscape()) {
						enemyList.remove(i);
					}
					else
						enemyList.get(i).update();
				}
				panel.distance++;
				panel.repaint();
				
				//Detects if player has lost, giving end text then closing the program
				for (int i = 0; i < enemyList.size(); i++) {
					if (player.colidesWith(enemyList.get(i))) {
						enemyList.remove(i);
						if (panel.lives > 0) {
							panel.lives--;
							player.setInvincible(true);
							ActionListener damageBuffer = new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									player.setInvincible(false);
								}
							};
							Timer damageBufferer = new Timer(1, damageBuffer);
							damageBufferer.setInitialDelay(2500);
							damageBufferer.setRepeats(false);
							damageBufferer.start();
						}
						else {
							final int distance = panel.distance;
							panel.endText1 = "You made it " + distance + "m.";
							panel.endText2 = "To try again run the program again.";
							panel.playerModel = null;
							panel.playerModelStraight = null;
							panel.playerModelLeft = null;
							panel.playerModelRight = null;
							panel.player.setInvincible(true);
							//One time delay to exit the game after end text is shown
							ActionListener gameExit = new ActionListener() {
								public void actionPerformed(ActionEvent event) {
									System.exit(0);
								}
							};
							Timer gameExiter = new Timer(1, gameExit);
							gameExiter.setInitialDelay(5000);
							gameExiter.setRepeats(false);
							gameExiter.start();
						}
					}
				}
				
				//Regenerates shields every 1000m
				if (panel.distance % 1000 == 0 && panel.lives < 3)
					panel.lives++;
				//Sets danger level to 9 after 7,500m
				if (panel.distance % 7500 == 0 && panel.distance != 0) 
					panel.dangerLevel = 9;
				//Set correct player model
				if (panel.player.getXVelocity() > 0)
					panel.playerModel = panel.playerModelRight;
				if (panel.player.getXVelocity() < 0)
					panel.playerModel = panel.playerModelLeft;
				if (panel.player.getXVelocity() == 0)
					panel.playerModel = panel.playerModelStraight;
				
			}
		};
		Timer gameUpdater = new Timer(30, updater);
		gameUpdater.start();
		
		//Enemy Spawner: adds enemies to the enemyList at a variable rate
		ActionListener enemySpawn = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				enemyList.add(new Enemy());
			}
		};
		Timer enemySpawner = new Timer(900, enemySpawn);
		enemySpawner.setInitialDelay(10000);
		enemySpawner.start();
		
		//Danger Incrementer: Decreases delay of enemy spawner every 30s
		ActionListener dangerIncrement = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (enemySpawner.getDelay() > 200) {
					enemySpawner.setDelay(enemySpawner.getDelay() - 100);
					panel.dangerLevel++;
				}
				else if (panel.dangerLevel == 9)
					enemySpawner.setDelay(150);
			}
		};
		Timer dangerIncrementer = new Timer(30000, dangerIncrement);
		dangerIncrementer.start();
		
		//Tutorial Remover: Sets the tutorial text to empty, essentially erasing it and
		//displaying next tutorial
		ActionListener tutorialRemove1 = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.wTutorial = "";
				panel.aTutorial = "";
				panel.sTutorial = "";
				panel.dTutorial = "";
				panel.shieldTutorial1 = "Your shields protect you, and will"; 
				panel.shieldTutorial2 = "regenerate every 1000m. Good Luck!";
			}
		};
		Timer tutorialRemover1 = new Timer(100, tutorialRemove1);
		tutorialRemover1.setInitialDelay(10000);
		tutorialRemover1.setRepeats(false);
		tutorialRemover1.start();
		
		//Removes the next tutorial message
		ActionListener tutorialRemove2 = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.shieldTutorial1 = "";
				panel.shieldTutorial2 = "";
			}
		};
		Timer tutorialRemover2 = new Timer(100, tutorialRemove2);
		tutorialRemover2.setInitialDelay(20000);
		tutorialRemover2.setRepeats(false);
		tutorialRemover2.start();
	}
}
