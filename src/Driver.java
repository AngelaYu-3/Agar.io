import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Driver extends JPanel implements ActionListener, KeyListener, MouseMotionListener {
	
	ArrayList<Enemy> enemies;  
	ArrayList<Food> foodBank;
	Timer t;
	Cell player;
	Font font1 = new Font("Courier New", 1, 100);
	Font font2 = new Font("Courier New", 1, 15);
	Font font3 = new Font("Courier New", 1, 50);
	long startTime, endTime;
	int time = 0;
	boolean isWinner, isLoser;
	
	/*TODO:
	 * fix border collision--based on movement of border (will need to work on collision in terms of player!!)
	 * get food to only spawn in border
	 * fix max size of enemies
	 * 
	 * split??
	 */

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawRect(player.getWx(), player.getWy(), player.getWidth(), player.getWidth());
		
		
		//enemy collisions and removing smaller enemy
		for(Enemy e: enemies) {
			if(e.enemyCollision(enemies, e, player)) break;
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).isCollidingP(player)) {
				if(enemies.get(i).getRad() > player.getRad()) {
					enemies.get(i).updateSize(player.getMass(), player);
					player.setRad(0);
					isLoser = true;
					break;
				}
				
				if(enemies.get(i).getRad() < player.getRad()) {
					player.updateSize(enemies.get(i).getMass());
					enemies.remove(i);
					break;
				}
			}
		}
		
		//food enemy collisions
		for(Enemy e: enemies) {
		    for(int i = 0; i < foodBank.size(); i++) {
		    	if(foodBank.get(i).isCollidingE(e) && !isWinner && !isLoser) {
				    e.updateSize(foodBank.get(i).getMass(), player);	
				    foodBank.remove(i);
				    break;
		    	}
		    }
		}
		
		//food player collisions
		for(int i = 0; i < foodBank.size(); i++) {
			if(foodBank.get(i).isCollidingP(player) && !isWinner && !isLoser) {
			    player.updateSize(foodBank.get(i).getMass() + 10);
			    foodBank.remove(i);
			}
		}
		
		//adding food 
		if(foodBank.size() != 500) {
			for(int i = foodBank.size(); i < 500; i++) {
				foodBank.add(new Food(player));
			}
		}
		
		//painting enemies, food, and player
		for(Food f: foodBank) {
			f.paint(g, player);
		}	
		for(Enemy e: enemies) {
			e.paint(g, player); 
		}
        player.paint(g, isLoser);
        
        g.setFont(font2);
    	g.setColor(Color.black);
    	g.drawString("Enemies Alive: " + enemies.size(), 10 , 10);
    	
    	if(enemies.size() == 0) {
    		g.setFont(font1);
    		g.setColor(Color.black);
        	g.drawString("You Won!", 150, 200);
        	isWinner = true;
        	
        	g.setFont(font3);
        	g.drawString("Final Mass: " + player.getMass(), 130, 270);
    	}
    	
    	if(isLoser) {
    		g.setFont(font1);
    		g.setColor(Color.black);
        	g.drawString("You Lost!", 150, 200);
        	
        	g.setFont(font3);
        	g.drawString("Final Mass: " + player.getMass(), 130, 270);
    	}
    }
        

	
	public Driver() {
		enemies = new ArrayList<Enemy>();
		foodBank = new ArrayList<Food>();
		player = new Cell(400, 300, 30);
		isWinner = false;
		isLoser = false;
	
		for(int i = 0; i < 50; i++) {
			enemies.add(new Enemy(player));
		}
		
		for(int i = 0; i < 200; i++) {
			foodBank.add(new Food(player));
		}
		
		
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addKeyListener(this);

	
		frame.addMouseMotionListener(this);
		t = new Timer(17, this); //choose swing library for import
		t.start();
	}
	

	public static void main(String[] arg) {
		Driver drive = new Driver();
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Mouse moved! X: " +  e.getX() + " Y: " + e.getY());
		player.move(e.getX(), e.getY());
	}


	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint(); //Timer will invoke this method which then refreshes the screen for the "animation"
		
	}
}
