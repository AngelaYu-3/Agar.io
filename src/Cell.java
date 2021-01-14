import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cell { 
    private int vx, vy;
    private int mass;
    private int rad;
    private int x, y;
    private int cx, cy;
    private int radToVelocity = 150;
    private int wx, wy, width;
	Rectangle world;
    private Color color;
    private boolean isAlive;
    
    Font font2 = new Font("Courier New", 1, 15);
    
    //constructor
    public Cell(int x, int y, int rad) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.mass = (int) (Math.PI * rad * rad);
        wx = -500;
        wy = -500;
        width = 2000;
        world = new Rectangle(wx, wy, width, width);
	    cx = x + rad - 13;
	    cy = y + rad;
	    
	    color = new Color(51, 204, 204); 
    }
    
    public void paint(Graphics g) {
    	g.setColor(color);
    	g.fillOval(x, y, rad * 2, rad * 2);  
    	
    	g.setFont(font2);
    	g.setColor(Color.black);
    	g.drawString("YOU",cx, cy);
    	
    	wx += vx;
		wy += vy;
    }
    
    public void updateSize(int mass) {
    	if(mass != 2000) {
			 this.mass += (int)((mass));
		     rad = (int)Math.sqrt(this.mass/Math.PI);
		     cx = x + rad;
		     cy = y + rad;	 
		 }
    }
    
    public void move(int mx, int my) {
    	int xSign, ySign;
    	double xDist = mx - cx;
    	double yDist = my-cy;
    	
    	xSign = (xDist < 0) ? 1: -1;
    	ySign = (yDist < 0) ? 1: -1;
    	
    	if(Math.sqrt((Math.pow(xDist, 2) + Math.pow(yDist, 2))) > 500 || ((mx < 460 && mx > 400) && (my < 360 && my > 300))) {
    		vx = 0;
    		vy = 0;
    	}else {
    		double angle = Math.abs(Math.atan(yDist/xDist));
            double v = Math.sqrt((2* Math.pow(radToVelocity/rad, 2)));
            vx = (int)(v * Math.cos(angle) * xSign);
            vy = (int)(v * Math.sin(angle) * ySign);
    	}
    	
    	
        
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
    
    public int getWx() {
    	return wx;
    }
    
    public int getWy() {
    	return wy;
    }
    
    public int getWidth() {
    	return width;
    }
}
