import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Food { 
    private int vx, vy;
    private int mass;
    private int rad;
    private int x, y;
    private int cx, cy;
    private Color color;
    private boolean isAlive;
    
    //constructor
    public Food(Cell p) {
    	x = (int)(Math.random() * 2000 + p.getWx());
	    y = (int)(Math.random() * 2000 + p.getWy()+15);
        rad = 2;
        cx = x + rad;
        cy = y + rad;
        mass = (int) (Math.PI * rad * rad);
	     
	    color = new Color(100, 200, 0); 
    }
    
    public void paint(Graphics g, Cell p) {
    	update(p);
    	
    	if(x > p.getWx() && x < p.getWx()+p.getWidth() && y > p.getWy() && y < p.getWy()+p.getWidth()) {
    		g.setColor(color);
        	g.fillOval(x, y, rad * 2, rad * 2); 
    	}
    	 
    }
    
    //anything that updates variable of this object
    public void update(Cell p) {
        x += (vx + p.getVx());
        y += (vy + p.getVy());
        cx += (vx + p.getVx());
        cy += (vy + p.getVy());
        
    }
    
    public boolean isCollidingE(Enemy e) {
		double minDist = rad + e.getRad(); 
		double distance = Math.sqrt(Math.pow(cx - e.getCx(), 2) + Math.pow(cy - e.getCy(), 2));
		
		if(distance > minDist) {
			return false;
		}return true;
	 }
    
    public boolean isCollidingP(Cell p) {
    	double minDist = rad + p.getRad();
        double distance = Math.sqrt(Math.pow(cx - p.getX(), 2) + Math.pow(cy - p.getY(), 2));
		
		if(distance > minDist) {
			return false;
		}return true;
    }
    
    //mass to radius helper method
    public int getX() {
    	return x;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public int getVx() {
    	return vx;
    }
    
    public void setVx(int vx) {
    	this.vx = vx;
    }
    public int getVy() {
    	return vy;
    }
    
    public void setVy(int vy) {
    	this.vy = vy;
    }
    
    public int getMass() {
    	return mass;
    }

	public void updateSize(Enemy e) {
		// TODO Auto-generated method stub
	}
}
