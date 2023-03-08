import java.awt.Rectangle;
public class EnemyProjectile extends Rectangle{
	int speed;
	int id;
	double dx;
	double dy;
	boolean valid = true;
	boolean ishoming;
	boolean active;
	public EnemyProjectile(int width, int height, int xx, int yy, int speed) {
		super(0,0,width,height);
		x = xx;
		y = yy;
		this.speed = speed;
	}
	public EnemyProjectile(int width, int height, int xx, int yy, int speed, boolean homing) {
		super(0,0,width,height);
		x = xx;
		y = yy;
		ishoming = homing;
		this.speed = speed;
	}
	public EnemyProjectile(int width, int height, int xx, int yy, int speed, boolean homing, boolean active) {
		super(0,0,width,height);
		x = xx;
		y = yy;
		ishoming = homing;
		this.speed = speed;
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void turnActive() {
		active = true;
	}
	public void move() {
		translate(0,-speed);
	}
	
	public void setdx(double num) {
		dx = num;
	}
	public void setdy(double num) {
		dy = num;
	}
	
	//1~4 - boss 1 pattern
	public void setid(int num) {
		id = num;
	}
	public int getid() {
		return id;
	}
	public void moveAt() {
		translate((int) (speed * dx), (int) (speed * dy));
	}
	
	public boolean gethoming() {
		return ishoming;
	}
	public void hit() {
		valid = false;
	}
	
	public boolean laser() {
		return valid;
	}
}
