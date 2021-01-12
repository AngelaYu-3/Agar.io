import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Cell { 
    private int vx, vy;
    private int mass;
    private int rad;
    private int x, y;
    private int cx, cy;
    private int radToVelocity = 150;
    private Color color;
    private boolean isAlive;
    
    Font font2 = new Font("Courier New", 1, 15);
    
    //constructor
    public Cell(int x, int y, int rad) {
        this.x = y;
        this.y = y;
        this.rad = rad;
        this.mass = (int) (Math.PI * rad * rad);
        cx = x + rad;
        cy = y + rad;
        
        int r = (int)(Math.random() * 255);
	    int b = (int)(Math.random() * 255);
	    int g = (int)(Math.random() * 255);
	     
	    color = new Color(r, b, g); 
    }
    
    public void paint(Graphics g) {
    	update();
    	g.setColor(color);
    	g.fillOval(x, y, rad * 2, rad * 2);  
    	
    	/*g.setFont(font2);
    	g.setColor(Color.black);
    	g.drawString("YOU",cx, cy);*/
    }
    
    //anything that updates variable of this object
    public void update() {
        x += vx;
        y += vy;
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
