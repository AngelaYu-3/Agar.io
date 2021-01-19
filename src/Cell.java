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
    private int xSign, ySign;
    private double angle, v;
    private int radToVelocity = 250;
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
	    cx = x + rad;
	    cy = y + rad;
	    
	    color = new Color(51, 204, 204); 
    }
    
    public void paint(Graphics g, boolean isLoser) {
    	g.setColor(color);
    	g.fillOval(x, y, rad * 2, rad * 2);  
    	
    	if(rad != 0) {
    		g.setFont(font2);
        	g.setColor(Color.black);
        	g.drawString("YOU",cx-15, cy);
    	}
    	
    	if(x <= wx && xSign > 0 || (x + (2*rad)) >= wx + width && xSign < 0) {
    	    vx = 0;	
    	}else {
    		vx = (int)(v * Math.cos(angle) * xSign);
    	}
    	
    	if(y <= wy && ySign > 0 || (y + (2*rad)) >= wy + width && ySign < 0) {
    		vy = 0;
    	}else {
    		vy = (int)(v * Math.sin(angle) * ySign);
    	}
 
    	    
    	wx += vx;
    	wy += vy;	
    }
    
    public void updateSize(int mass) {
    	if(mass < 25000) {
			 this.mass += mass;
		     rad = (int)Math.sqrt(this.mass/Math.PI);
		     cx = x + rad;
		     cy = y + rad;	 
		 }
    	
    	//added to account for when player both collides w/ enemy/food and reaches border
    	//can be improved--not very smooth
    	while(x + (2*rad) >= (wx + width)) {
	    	 x--;
	    	 cx--;
	     }
		while(y + (2*rad) >= (wy + width)) {
	    	 y--;
	    	 cy--;
	    }
    }
    
    public void split() {
    	//when mouse clicked and mass > 2500 can split 25% and 75%? up to size 1000 (shoot fast and direct?)
    }
    
    public void move(int mx, int my) {
    	if(rad != 0) {
        	double xDist = mx - cx;
        	double yDist = my-cy;
        	
        	xSign = (xDist < 0) ? 1: -1;
        	ySign = (yDist < 0) ? 1: -1;
        	
        	angle = Math.abs(Math.atan(yDist/xDist));
            v = Math.sqrt((2* Math.pow(radToVelocity/rad, 2)));
            vx = (int)(v * Math.cos(angle) * xSign);
            vy = (int)(v * Math.sin(angle) * ySign);
        	
        	if(((mx < 460 && mx > 400) && (my < 360 && my > 300))) {
        		vx = 0;
        		vy = 0;
        	}
        	
        	if(x <= wx && xSign > 0 || (x + (2*rad)) >= wx + width && xSign < 0) {
        	    vx = 0;	
        	}
        	if(y <= wy && ySign > 0 || (y + (2*rad)) >= wy + width && ySign < 0) {
        		vy = 0;
        	}
        	
        	
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
    
    public int getRad() {
    	return rad;
    }
    
    public void setRad(int rad) {
    	this.rad = rad;
    }

	public int getMass() {
		// TODO Auto-generated method stub
		return mass;
	}
	
}
