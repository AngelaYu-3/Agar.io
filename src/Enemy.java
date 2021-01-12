import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
    
	 private int vx, vy;
	 private int mass;
	 private int rad;
	 private int x, y;
	 private int cx, cy;
	 private int radToVelocity;
	 private Color color;
	 Rectangle world = new Rectangle(-500, -500, 2000, 2000);
	 double randX = Math.random(), randY = Math.random();

	 
	 public Enemy() {
		 rad = (int)(Math.random() * 50 + 20);
		 mass = (int)(Math.PI * rad * rad);
	    
	     x = (int)(Math.random() * 2500 - 500);
	     y = (int)(Math.random() * 2500 - 500);
	     
	     radToVelocity = (int)(Math.random() * 10 + 150);
	     if(randX < 0.5) {
			 vx = (int)(radToVelocity/rad); 
		 }else {
			 vx = (int)(-radToVelocity/rad); 
		 }
	     
	     radToVelocity = (int)(Math.random() * 10 + 150);
	     if(randY < 0.5) {
			 vy = (int)(radToVelocity/rad); 
		 }else {
			 vy = (int)(-radToVelocity/rad); 
		 }
	     
	     cx = x + rad;
	     cy = y + rad;
	     
	     int r = (int)(Math.random() * 255);
	     int b = (int)(Math.random() * 255);
	     int g = (int)(Math.random() * 255);
	     
	     color = new Color(r, b, g); 
	 }
	 
	 //collision code
	 public boolean isColliding(Enemy en2) {
		double minDist = rad + en2.rad; 
		double distance = Math.sqrt(Math.pow(cx - en2.cx, 2) + Math.pow(cy - en2.cy, 2));
		
		if(distance > minDist/2) {
			return false;
		}return true;
	 }
	 
	 public void updateSize(Enemy e) {
	     mass += (e.mass /5);
	     rad = (int)Math.sqrt(mass/Math.PI);
	     cx = x + rad;
	     cy = y + rad;
	 }
	 
	 public void paint(Graphics g) {
		 update();
		 g.setColor(color);
		 g.fillOval(x, y, rad * 2, rad * 2);
		 
		 //have enemy object bounce off world rectangle borders
		 if(x <= world.getMinX() || x >= world.getMaxX()) {
			 vx = -vx;
		 }
		 if(y <= world.getMinY() || y >= world.getMaxY()) {
			 vy = -vy;
		 }
	 }
	 
	 public void update() {
    
		 if(Math.abs(vx) > 1) {
			 if(randX < 0.5) {
				 vx = (int)(radToVelocity/rad); 
			 }else {
				 vx = (int)(-radToVelocity/rad); 
			 }
		 }
		 
		 if(Math.abs(vy) > 1) {
			 if(randY < 0.5) {
				 vy = (int)(radToVelocity/rad); 
			 }else {
				 vy = (int)(-radToVelocity/rad); 
			 }
			 
		 }
		 
		 x += vx;
		 y += vy;
		 cx += vx;
	     cy += vy;
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
}
