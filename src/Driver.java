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

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//checking for collisions and removing smaller enemy
		//painting enemies
		int index = -1;
		for(Enemy e: enemies) {
			e.paint(g); 
			for(int i = 0; i < enemies.size(); i++) {
    	        if(e.isColliding(enemies.get(i)) && e.getRad() > enemies.get(i).getRad()) {
    	        		e.updateSize(enemies.get(i));
    	        		index = i;	
    	        		break;
    	        }
    	    }
		}
		if(index != -1) {
			enemies.remove(index);
		}
		
		for(Food f: foodBank) {
			f.paint(g); 
		}
        //painting player
        player.paint(g);
         
    }
        

	
	public Driver() {
		enemies = new ArrayList<Enemy>();
		foodBank = new ArrayList<Food>();
		player = new Cell(400, 300, 30);
	
		for(int i = 0; i < 125; i++) {
			enemies.add(new Enemy());
		}
		
		for(int i = 0; i < 200; i++) {
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
