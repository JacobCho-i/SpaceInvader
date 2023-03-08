import java.awt.Rectangle;
public class PlayerProjectile extends Rectangle{
	int speed;
	boolean valid = true;
	public PlayerProjectile(int width, int height, int xx, int yy, int speed) {
		super(0,0,width,height);
		x = xx;
		y = yy;
		this.speed = speed;
	}
	public void move() {
		translate(0,-speed);
	}
	
	public void hit() {
		valid = false;
	}
	
	public boolean laser() {
		return valid;
	}
	
	
}
