import java.awt.Rectangle;
public class EnemyShip extends Rectangle{
	int dx;
	int dy;
	int hp;
	public EnemyShip() {
		super(0,0,20,20);
	}
	
	public EnemyShip(int width, int height, int hp) {
		super(0,0,width,height);
		this.hp = hp;
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
	
	public void hit(int damage) {
		hp -= damage;
	}
	
	public int getHp() {
		return hp;
	}
}
