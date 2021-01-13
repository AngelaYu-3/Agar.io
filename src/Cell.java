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
	    cx = x + rad/2;
	    cy = y + rad/2;
	    
	    color = new Color(51, 204, 204); 
    }
    
    public void paint(Graphics g) {
    	update();
    	g.setColor(color);
    	g.fillOval(x, y, rad * 2, rad * 2);  
    	
    	g.setFont(font2);
    	g.setColor(Color.black);
    	g.drawString("YOU",x + 15, y + 30);
    	//System.out.println("cx: " + cx + "cy: " + cy);
    }
    
    //anything that updates variable of this object
    public void update() {
    	//vx = (int)(radToVelocity/rad);
    	//vy = (int)(radToVelocity/rad);
        x += vx;
        y += vy;
    }
    
    public void updateSize(int mass) {
    	this.mass += (int)((mass));
    	rad = (int)Math.sqrt(this.mass/Math.PI);
    }
    
    public void move(int mx, int my) {
    	double xDist = (mx - cx);
    	double yDist = (cy-my);
        double angle = Math.atan(yDist/xDist);
        System.out.println("mx " + mx + " my " + my + " cx " + cx + " cy " + cy);
        System.out.println("yDist " + (cy - my));
        //System.out.println("xDist: " + xDist + " yDist: " + yDist + " angle: " + angle);
        double v = Math.sqrt((2* Math.pow(radToVelocity/rad, 2)));
        int vx = (int)(v * Math.acos(angle));
        int vy = (int)(v * Math.asin(angle));
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
    
    public int getCx() {
    	return cx;
    }
    
    public int getCy() {
    	return cy;
    }
}
