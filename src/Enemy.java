import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy {
    
	 private int vx, vy;
	 private int mass;
	 private int rad;
	 private int x, y;
	 private int cx, cy;
	 private int radToVel = 150;
	 private Color color;
	 double randX = Math.random(), randY = Math.random();

	 
	 public Enemy(Cell p) {
		 rad = (int)(Math.random() * 20 + 10);
		 mass = (int)(Math.PI * rad * rad);
	     
		 x = (int)(Math.random() * 1700 + p.getWx()+30);
		 y = (int)(Math.random() * 1700 + p.getWy()+30);
	     
	     if(randX < 0.5) {
			 vx = (int)(radToVel/rad); 
		 }else {
			 vx = (int)(-radToVel/rad); 
		 }
	     
	     if(randY < 0.5) {
			 vy = (int)(radToVel/rad); 
		 }else {
			 vy = (int)(-radToVel/rad); 
		 }
	     
	     cx = x + rad;
	     cy = y + rad;
	     
	     int r = (int)(Math.random() * 255);
	     int b = (int)(Math.random() * 255);
	     int g = (int)(Math.random() * 255);
	     
	     color = new Color(r, b, g); 
	 }
	 
	 public boolean enemyCollision(ArrayList<Enemy> enemies, Enemy e, Cell p) {
	     for(int i = 0; i < enemies.size(); i++) {
			 if(e.isCollidingE(enemies.get(i)) && e.getRad() > enemies.get(i).getRad()) {
			    e.updateSize(enemies.get(i).getMass(), p);
			    enemies.remove(i);
			    return true;
			 }
	     } 
	     return false;
		 
	 }
	 
	 //collision code
	 public boolean isCollidingE(Enemy en2) {
		double minDist = rad + en2.rad; 
		double distance = Math.sqrt(Math.pow(cx - en2.cx, 2) + Math.pow(cy - en2.cy, 2));
		
		if(distance > minDist/2) {
			return false;
		}return true;
	 }
	 
	 public boolean isCollidingP(Cell p) {
	    	double minDist = rad + p.getRad();
	        double distance = Math.sqrt(Math.pow(cx - p.getCx(), 2) + Math.pow(cy - p.getCy(), 2));
			
			if(distance > minDist) {
				return false;
			}return true;
	 }
	 
	 public void updateSize(int mass, Cell p) {
		 if(mass < 25000) {
			 this.mass += (int)((mass));
			 if(this.mass > 10000) this.mass = 10000;
		     rad = (int)Math.sqrt(this.mass/Math.PI); 
		     cx = x + rad;
		     cy = y + rad;	 
		 }
		 
		 //added to account for when enemies both collide and reach border 
		 while(x + (2*rad) >= (p.getWx() + p.getWidth())) {
	    	 x -= 10;
	     }
		 while(y + (2*rad) >= (p.getWy() + p.getWidth())) {
	    	 y -= 10;
	     }
	     
	 }
	 
	 public void paint(Graphics g, Cell p) {
		 
	
		 //have enemy object bounce off world rectangle borders
		 if(x <= p.getWx() || x+(2*rad) >= (p.getWx() + p.getWidth())) {
			 vx = -vx;
		 }
		 if(y <= p.getWy() || y+(2*rad) >= (p.getWy() + p.getWidth())) {
			 vy = -vy;
		 } 
		 
		 update(p);
		 g.setColor(color);
		 g.fillOval(x, y, rad * 2, rad * 2);
		 
	 }
	 
	 public void update(Cell p) {		 
		 x += (vx + p.getVx());
	     y += (vy + p.getVy());
		 cx += (vx + p.getVx());
	     cy += (vy + p.getVy());
	 }
	 
	 public int getRad() {
		 return rad;
	 }
	 
	 public void setRad(int rad) {
		 this.rad = rad;
	 }
	 
	 public int getCx() {
		 return cx;
	 }
	 
	 public int getCy() {
		 return cy;
	 }
	 
	 public int getMass() {
		 return mass;
	 }
}
