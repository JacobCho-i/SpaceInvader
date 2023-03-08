import java.awt.Rectangle;
public class Ship extends Rectangle{

	int dx;
	int dy;
	public Ship() {
		super(0,0,10,10);
	}
	
	public Ship(int width, int height) {
		super(0,0,width,height);
	}
	
	public void move() {
		translate(dx*5,dy*5);
	}
	public void moveAt(int dxx, int dyy) {
		translate(dxx*3,dyy*3);
	}
	
	public void locate(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	public void setDY(int dyy) {
		dy = dyy;
	}
	
	public void setDX(int dxx) {
		dx = dxx;
	}
}
