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
	Font font2 = new Font("Courier New", 1, 15);
	
	/*TODO:
	 * randomize movement of enemies more
	 * get "YOU" text working
	 * get food working (inheritance?--make more organized)
	 * mouse movement
	 */

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		if(foodBank.size() != 300) {
			for(int i = foodBank.size(); i < 300; i++) {
				foodBank.add(new Food());
			}
		}
		
		//painting enemies and food
		for(Enemy e: enemies) {
			e.paint(g); 	
		}
		for(Food f: foodBank) {
			f.paint(g);
		}
		
		//checking for enemy collisions and removing smaller enemy
		for(Enemy e: enemies) {
			if(e.enemyCollision(enemies, e)) break;
		}
		
		//checking for food collisions and removing food
		for(Enemy e: enemies) {
		    for(int i = 0; i < foodBank.size(); i++) {
		    	if(foodBank.get(i).isColliding(e)) {
				    e.updateSize(foodBank.get(i).getMass());	
				    foodBank.remove(i);
				    break;
		    	}
		    }
		}
		
        //painting player
        player.paint(g);
        
        g.setFont(font2);
    	g.setColor(Color.black);
    	g.drawString("Enemies Alive: " + enemies.size(), 10 , 10);
    }
        

	
	public Driver() {
		enemies = new ArrayList<Enemy>();
		foodBank = new ArrayList<Food>();
		player = new Cell(400, 300, 30);
	
		for(int i = 0; i < 125; i++) {
			enemies.add(new Enemy());
		}
		
		for(int i = 0; i < 300; i++) {
			foodBank.add(new Food());
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
