import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Driver extends JPanel implements ActionListener, MouseMotionListener, KeyListener {
	
	ArrayList<Enemy> enemies;  
	ArrayList<Food> foodBank;
	Enemy e;
	Timer t;
	World w;
	Cell player;
	Font font1 = new Font("Courier New", 2, 100);
	Font font2 = new Font("Courier New", 1, 15);
	Font font3 = new Font("Courier New", 1, 50);
	ArrayList<Cell> cells = new ArrayList<Cell>();
	boolean isWinner, isLoser;
	
	/*TODO:
	 * add split functionality
	 */

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawRect(w.getWx(), w.getWy(), w.getWidth(), w.getWidth());
		
		//gridlines
		g.setColor(Color.LIGHT_GRAY);
		int x = w.getWx() + 100;
		int y1 = w.getWy();
		int y2 = w.getWy() + w.getWidth();
		
		for(int i = 0; i < 19; i++ ) {
		     g.drawLine(x, y1, x, y2);	
		     x += 100;
		}
		
		int x1 = w.getWx();
		int x2 = w.getWx() + w.getWidth();
		int y = w.getWy() + 100;
		
		for(int i = 0; i < 19; i++ ) {
		     g.drawLine(x1, y, x2, y);	
		     y += 100;
		}
		
		//enemy collisions and removing smaller enemy
		for(Enemy e: enemies) {
			if(e.enemyCollision(enemies, e, w)) break;
		}
		
		for(Cell c: cells) {
			for(int i = 0; i < enemies.size(); i++) {
				if(enemies.get(i).isCollidingP(c) && c.getAlive()) {
					if(enemies.get(i).getRad() > c.getRad()) {
						
						enemies.get(i).updateSize(c.getMass(), w);
						c.setRad(0);
						c.setAlive(false);
						break;
					}
					
					if(enemies.get(i).getRad() < c.getRad() && c.getAlive()) {
						c.updateSize(enemies.get(i).getMass(), w);
						enemies.remove(i);
						break;
					}
				}
			}
		}
		
		//food enemy collisions
		for(Enemy e: enemies) {
		    for(int i = 0; i < foodBank.size(); i++) {
		    	if(foodBank.get(i).isCollidingE(e) && !isWinner && !isLoser) {
				    e.updateSize(foodBank.get(i).getMass(), w);	
				    foodBank.remove(i);
				    break;
		    	}
		    }
		}
		
		//food player collisions
		for(Cell c: cells) {
			for(int i = 0; i < foodBank.size(); i++) {
				if(foodBank.get(i).isCollidingP(c) && !isWinner && !isLoser) {
				    c.updateSize(foodBank.get(i).getMass(), w);
				    foodBank.remove(i);
				}
			}
			
		}
		
		
		//adding food 
		if(foodBank.size() != 500) {
			for(int i = foodBank.size(); i < 500; i++) {
				foodBank.add(new Food(w));
			}
		}
		
		//painting enemies, food, and player
		for(Food f: foodBank) {
			f.paint(g, cells.get(0), w);
		}	
		for(Enemy e: enemies) {
			e.paint(g, cells.get(0), w); 
		}
		
		for(Cell c: cells) {
			if(c.getAlive()) c.paint(g, w);
		}
        
        g.setFont(font2);
    	g.setColor(Color.black);
    	g.drawString("Enemies Alive: " + enemies.size(), 10 , 10);
    	g.drawString("Current Mass: " + cells.get(0).getMass(), 10, 23);
    	
    	if(enemies.size() == 0) {
    		g.setFont(font1);
    		g.setColor(Color.black);
        	g.drawString("You Won!", 150, 200);
        	isWinner = true;
        	
        	g.setFont(font3);
        	g.drawString("Final Mass: " + cells.get(0).getMass(), 130, 270);
    	}
    	
    	if(isLoser()) {
    		g.setFont(font1);
    		g.setColor(Color.black);
        	g.drawString("You Lost!", 150, 200);
        	isLoser = true;
        	
        	g.setFont(font3);
        	g.drawString("Final Mass: " + cells.get(0).getMass(), 130, 270);
    	}
    }
        

	
	public Driver() {
		enemies = new ArrayList<Enemy>();
		foodBank = new ArrayList<Food>();
		cells.add(new Cell(400, 300, 30));
		w = new World();
		isWinner = false;
		isLoser = false;
	
		for(int i = 0; i < 50; i++) {
			enemies.add(new Enemy(w));
		}
		
		for(int i = 0; i < 200; i++) {
			foodBank.add(new Food(w));
		}
		
		
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800, 600);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	
		frame.addMouseMotionListener(this);
		frame.addKeyListener(this);
		t = new Timer(17, this); //choose swing library for import
		t.start();
	}
	

	public static void main(String[] arg) {
		Driver drive = new Driver();
	}
	
	public boolean isLoser() {
		for(int i = 0; i < cells.size(); i++) {
			if(cells.get(i).getAlive()) return false;
		}
		
		return true;
	}


	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!isLoser) {
			int i = 0;
			while(!cells.get(i).getAlive()) {
				i++;
			}
			
			cells.get(i).move(e.getX(), e.getY(), w);	
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint(); //Timer will invoke this method which then refreshes the screen for the "animation"
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 32 && cells.get(0).getMass() > 10000) {
			cells.add(cells.get(0).split());
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
