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
    private int mx, my;
    private int cx, cy;
    private int xSign, ySign;
    private double angle, v;
    private int radToVelocity = 300;
    private Color color;
    private boolean isAlive;
    
    Font font2 = new Font("Courier New", 1, 16);

    
    //constructor
    public Cell(int x, int y, int rad) {
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.mass = (int) (Math.PI * rad * rad);
        isAlive = true;
        color = new Color(20, 100, 250);
    }
    
    public void paint(Graphics g, World w) {
    	g.setColor(color);
    	g.fillOval(x - rad, y - rad, rad * 2, rad * 2);
    
    	if(rad != 0) {
    		g.setFont(font2);
        	g.setColor(Color.black);
        	g.drawString("YOU",x-15, y);
    	}
    	
    	if(x - rad <= w.getWx() && xSign > 0 || (x - rad + (2*rad)) >= w.getWx() + w.getWidth() && xSign < 0) {
    	    vx = 0;	
    	}else {
    		vx = (int)(v * Math.cos(angle) * xSign);
    	}
    	
    	if(y - rad <= w.getWy() && ySign > 0 || (y - rad + (2*rad)) >= w.getWy() + w.getWidth() && ySign < 0) {
    		vy = 0;
    	}else {
    		vy = (int)(v * Math.sin(angle) * ySign);
    	}
    
    	w.setWx(w.getWx() + vx);
    	w.setWy(w.getWy() + vy);	
    }
    
    public void updateSize(int mass, World w) {
    	if(this.mass < 40000) {
			 this.mass += mass;
		     rad = (int)Math.sqrt(this.mass/Math.PI);
		     cx = x + rad;
		     cy = y + rad;	 
		}
    	
    	 while(x + (2*rad) >= (w.getWx() + w.getWidth())) {
	    	 x -= 10;
	     }
		 while(y + (2*rad) >= (w.getWy() + w.getWidth())) {
	    	 y -= 10;
	     }
		 
		 while(x <= w.getWx()) {
			 x += 10;
		 }
		 
		 while(y <= w.getWy()) {
			 y += 10;
		 }
    }
    
    public Cell split() {
    	int xC;
    	int yC;
        double newMass = this.mass * 0.25;
        int newRad = (int)(Math.sqrt(newMass/Math.PI));
    	mass *= 0.75;
    	rad = (int)Math.sqrt(mass/Math.PI);	
    	
    	int rand = (int)(Math.random());
    	if(rand == 1) {
    		xC = x - rad - 20;
    	}else {
    		xC = x + rad + 20;
    	}
    	
    	rand = (int)(Math.random() * 2*rad - rad);
		return new Cell(mx,my + rand,newRad);
    }
    
    public void move(int mx, int my, World w) {
    	this.mx = mx;
    	this.my = my;
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
        	
        	if(x - rad <= w.getWx() && xSign > 0 || (x - rad + (2*rad)) >= w.getWx() + w.getWidth() && xSign < 0) {
        	    vx = 0;	
        	}
        	if(y - rad <= w.getWy() && ySign > 0 || (y - rad + (2*rad)) >= w.getWy() + w.getWidth() && ySign < 0) {
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
	
	public boolean getAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
