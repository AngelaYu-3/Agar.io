import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Food { 
    private int vx, vy;
    private int mass;
    private int rad;
    private int x, y;
    private int cx, cy;
    private int radToVelocity = 150;
    private Color color;
    private boolean isAlive;
    
    //constructor
    public Food() {
    	x = (int)(Math.random() * 2500 - 500);
	    y = (int)(Math.random() * 2500 - 500);
        rad = 10;
        cx = x + rad;
        cy = y + rad;
        mass = (int) (Math.PI * rad * rad);
	     
	    color = new Color(0, 255, 0); 
    }
    
    public void paint(Graphics g) {
    	update();
    	g.setColor(color);
    	g.fillOval(x, y, rad * 2, rad * 2);  
    }
    
    //anything that updates variable of this object
    public void update() {
        x += vx;
        y += vy;
    }
    
    public boolean isColliding(Food food) {
		double minDist = rad + food.rad; 
		double distance = Math.sqrt(Math.pow(cx - food.cx, 2) + Math.pow(cy - food.cy, 2));
		
		if(distance > minDist/2) {
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
}
