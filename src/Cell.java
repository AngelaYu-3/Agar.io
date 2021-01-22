import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Cell { 
    private int vx, vy;
    private int mass;
    private int rad;
    private int x, y;
    private int cx, cy;
    private int xSign, ySign;
    private double angle, v;
    private int radToVelocity = 300;
    private int wx = -500;
    private int wy = -500;
    private int width = 2000;
	Rectangle world = new Rectangle(wx, wy, width, width);;
    private Color color;
    private boolean isAlive;
    
    Font font2 = new Font("Courier New", 1, 16);

    
    //constructor
    public Cell(int x, int y, int rad) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.mass = (int) (Math.PI * rad * rad);
    }
    
    public void paint(Graphics g, boolean isLoser) {
    	g.setColor(color);
    	g.fillOval(x - rad, y - rad, rad * 2, rad * 2);  
    	
    	if(rad != 0) {
    		g.setFont(font2);
        	g.setColor(Color.black);
        	g.drawString("YOU",x-15, y);
    	}
    	
    	if(x - rad <= wx && xSign > 0 || (x - rad + (2*rad)) >= wx + width && xSign < 0) {
    	    vx = 0;	
    	}else {
    		vx = (int)(v * Math.cos(angle) * xSign);
    	}
    	
    	if(y - rad <= wy && ySign > 0 || (y - rad + (2*rad)) >= wy + width && ySign < 0) {
    		vy = 0;
    	}else {
    		vy = (int)(v * Math.sin(angle) * ySign);
    	}
 
    	    
    	wx += vx;
    	wy += vy;	
    }
    
    public void updateSize(int mass) {
    	if(this.mass < 40000) {
			 this.mass += mass;
		     rad = (int)Math.sqrt(this.mass/Math.PI);
		     cx = x + rad;
		     cy = y + rad;	 
		}
    }
    
    public Cell split(int x, int y) {
    	if(this.mass > 25000) {
    		double newMass = this.mass * 0.25;
    		int newRad = (int)(Math.sqrt(newMass/Math.PI));
    		this.mass *= 0.75;
    		rad = (int)Math.sqrt(this.mass/Math.PI);
		    cx = x + rad;
		    cy = y + rad;	
		    return new Cell(x,y,newRad);
    	}return null;
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
        	
        	if(x - rad <= wx && xSign > 0 || (x - rad + (2*rad)) >= wx + width && xSign < 0) {
        	    vx = 0;	
        	}
        	if(y - rad <= wy && ySign > 0 || (y - rad + (2*rad)) >= wy + width && ySign < 0) {
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
    
    public int getY() {
    	return y;
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
    
    public void setColor(Color color) {
    	this.color = color;
    }

	public int getMass() {
		// TODO Auto-generated method stub
		return mass;
	}
	
}
