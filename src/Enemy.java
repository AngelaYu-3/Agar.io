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

	 
	 public Enemy(World w) {
		 rad = (int)(Math.random() * 20 + 10);
		 mass = (int)(Math.PI * rad * rad);
	     
		 x = (int)(Math.random() * 1500 + w.getWx()+30);
		 y = (int)(Math.random() * 1500 + w.getWy()+30);
	     
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
	 
	 public boolean enemyCollision(ArrayList<Enemy> enemies, Enemy e, World w) {
	     for(int i = 0; i < enemies.size(); i++) {
			 if(e.isCollidingE(enemies.get(i)) && e.getRad() > enemies.get(i).getRad()) {
			    e.updateSize(enemies.get(i).getMass(), w);
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
	        double distance = Math.sqrt(Math.pow(cx - p.getX(), 2) + Math.pow(cy - p.getY(), 2));
			
			if(distance > minDist) {
				return false;
			}return true;
	 }
	 
	 public void updateSize(int mass, World w) {
		 if(mass < 20000) {
			 this.mass += (int)((mass));
			 if(this.mass > 20000) this.mass = 20000;
		     rad = (int)Math.sqrt(this.mass/Math.PI); 
		     cx = x + rad;
		     cy = y + rad;	 
		 }
		 
		 //added to account for when enemies both collide and reach border 
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
	 
	 public void paint(Graphics g, Cell p, World w) {
		 
		 //have enemy object bounce off world rectangle borders
		 if(x <= w.getWx() || x+(2*rad) >= (w.getWx() + w.getWidth())) {
			 vx = -vx;
		 }
		 if(y <= w.getWy() || y+(2*rad) >= (w.getWy() + w.getWidth())) {
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
	 
	 public int getVx() {
		 return vx;
	 }
	 
	 public int getVy() {
		 return vy;
	 }
	 
	 public int getMass() {
		 return mass;
	 }
	 
	 public Color getColor() {
		 return color;
	 }
	 
	 public void setColor(Color color) {
		 this.color = color;
	 }
}
