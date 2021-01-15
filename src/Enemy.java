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
	 private int radToVelX, radToVelY;
	 private Color color;
	 double randX = Math.random(), randY = Math.random();

	 
	 public Enemy() {
		 rad = (int)(Math.random() * 40 + 10);
		 mass = (int)(Math.PI * rad * rad);
	    
	     x = (int)(Math.random() * 2500 - 500);
	     y = (int)(Math.random() * 2500 - 500);
	     
	     radToVelX = (int)(Math.random() * 5 + 150);
	     if(randX < 0.5) {
			 vx = (int)(radToVelX/rad); 
		 }else {
			 vx = (int)(-radToVelX/rad); 
		 }
	     
	     radToVelY = (int)(Math.random() * 5 + 150);
	     if(randY < 0.5) {
			 vy = (int)(radToVelY/rad); 
		 }else {
			 vy = (int)(-radToVelY/rad); 
		 }
	     
	     cx = x + rad;
	     cy = y + rad;
	     
	     int r = (int)(Math.random() * 255);
	     int b = (int)(Math.random() * 255);
	     int g = (int)(Math.random() * 255);
	     
	     color = new Color(r, b, g); 
	 }
	 
	 public boolean enemyCollision(ArrayList<Enemy> enemies, Enemy e) {
	     for(int i = 0; i < enemies.size(); i++) {
			 if(e.isColliding(enemies.get(i)) && e.getRad() > enemies.get(i).getRad()) {
			    e.updateSize(enemies.get(i).getMass());
			    enemies.remove(i);
			    return true;
			 }
	     } 
	     return false;
		 
	 }
	 
	 //collision code
	 public boolean isColliding(Enemy en2) {
		double minDist = rad + en2.rad; 
		double distance = Math.sqrt(Math.pow(cx - en2.cx, 2) + Math.pow(cy - en2.cy, 2));
		
		if(distance > minDist/2) {
			return false;
		}return true;
	 }
	 
	 public void updateSize(int mass) {
		 if(mass != 500) {
			 this.mass += (int)((mass));
		     rad = (int)Math.sqrt(this.mass/Math.PI); 
		     cx = x + rad;
		     cy = y + rad;	 
		 }
	     
	 }
	 
	 public void paint(Graphics g, Cell p) {
		 update(p);
		 g.setColor(color);
		 g.fillOval(x, y, rad * 2, rad * 2);
			
		 //have enemy object bounce off world rectangle borders
		 if(x == p.getWx() || x == (p.getWx() + p.getWidth())) {
			 vx = -vx;
		 }
		 if(y == p.getWy() || y == (p.getWy() + p.getWidth())) {
			 vy = -vy;
		 } 
	 }
	 
	 public void update(Cell p) {
		 radToVelX = (int)(Math.random() * 5 + 150);
	     if(randX < 0.5) {
			 vx = (int)(radToVelX/rad); 
		 }else {
			 vx = (int)(-radToVelX/rad); 
		 }
	     
	     radToVelY = (int)(Math.random() * 5 + 150);
	     if(randY < 0.5) {
			 vy = (int)(radToVelY/rad); 
		 }else {
			 vy = (int)(-radToVelY/rad); 
		 }
		 
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
