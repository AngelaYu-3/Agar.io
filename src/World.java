import java.awt.Rectangle;

public class World {
	private int wx = -500;
    private int wy = -500;
    private int width = 2000;
	Rectangle world = new Rectangle(wx, wy, width, width);
	
	public int getWx() {
	    return wx;
	}
	
	public void setWx(int wx) {
	    this.wx = wx;	
	}
	    
	public int getWy() {
	  return wy;
	}
	
	public void setWy(int wy) {
	    this.wy = wy;	
	}
	    
	public int getWidth() {
	  return width;
	}

}
