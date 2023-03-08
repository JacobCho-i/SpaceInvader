import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.geom.*;

//import randomGame.GDV5;

import java.util.Timer;
import java.util.TimerTask;

//ultiamte 
//collsion


public class SpaceInvader extends GDV5 {
	
	static Ship player = new Ship();
	Ship playerSprite = new Ship(20,20);
	ArrayList<PlayerProjectile> projectiles = new ArrayList<PlayerProjectile>();
	ArrayList<EnemyProjectile> eProjectiles = new ArrayList<EnemyProjectile>();
	int timer = 0;
	int shootingTimer = 0;
	boolean shotReady = true;
	int selected = 1;
	int hp = 3;
	// 1 = start, 2 = how to, 3 = game, 4 = end page
	int gamemode = 1;
	int score = 0;
	int powerup = 3;
	int wave = 1;
	int stage = 1;
	int pattern = 0;
	int att = 5;
	int enemytimer = 0;
	private static Image backgroundImage1 = null;
	private static Image backgroundImage2 = null;
	private static Image playerSpritee = null;
	private static Image sunSprite = null;
	private static Image pattern1 = null;
	private static Image pattern2 = null;
	private static Image pattern3 = null;
	private static Image pattern4 = null;
	private static Image shield = null;
	private static Image pProjectile = null;
	private static Image marsSprite = null;
	private static Image meteorSprite = null;
	private static Image satSprite = null;
	private static Image laserSprite = null;
	ArrayList<EnemyShip> enemies = new ArrayList<EnemyShip>();
	BufferedImage bi = new BufferedImage(35, 20, BufferedImage.TYPE_INT_ARGB);
	private Timer p1timer;
	private TimerTask p1timeTask;
	private int p1time = 0;
	private Timer ltimer;
	private TimerTask lTask;
	private int ltime = 0;
	private int p2time = 0;
	private int p3time = 0;
	Ellipse2D ult = new Ellipse2D.Double(100, 100, 80, 80);
	boolean ultActivated = false;
	boolean ultOn = true; 
	int ultTimer = 0;
	int ultTimer1 = 0;
	private Timer ultTimerr;
	private TimerTask ultTask;
	public static void main(String[] args) {
		SpaceInvader game = new SpaceInvader();
		player.locate(445, 700);
		try {
			backgroundImage1 = new ImageIcon(new URL("https://i.imgur.com/88k1trA.gif")).getImage();
			backgroundImage2 = new ImageIcon(new URL("https://img.freepik.com/free-vector/gradient-universe-background_23-2149635763.jpg")).getImage();
			playerSpritee = new ImageIcon(new URL("https://i.imgur.com/4TAnMB4.gif")).getImage();
			
			sunSprite = new ImageIcon(new URL("https://i.imgur.com/S97xoiz.gif")).getImage();
			
			pattern1 = new ImageIcon(new URL("https://i.imgur.com/VqyrGDX.png")).getImage();
			pattern2 = new ImageIcon(new URL("https://i.imgur.com/9nN0n7z.gif")).getImage();
			pattern3 = new ImageIcon(new URL("https://i.imgur.com/0qT4c7c.png")).getImage();
			pattern4 = new ImageIcon(new URL("https://i.imgur.com/KWh2HZe.png")).getImage();
			
			shield  = new ImageIcon(new URL("https://i.imgur.com/PuDxvEC.png")).getImage();
			
			pProjectile  = new ImageIcon(new URL("https://imgur.com/WVY6E3G.png")).getImage();
			
			marsSprite  = new ImageIcon(new URL("https://i.imgur.com/3JOzWyk.gif")).getImage();
			
			meteorSprite  = new ImageIcon(new URL("https://i.imgur.com/gXAQOAD.png")).getImage();
			
			satSprite  = new ImageIcon(new URL("https://i.imgur.com/2XYbQwK.gif")).getImage();
			
			laserSprite = new ImageIcon(new URL("https://i.imgur.com/bjvEX8i.gif")).getImage();
			//
			//
		} catch (IOException e) {
            System.out.println("Something went wrong, sorry:" + e.toString());
            e.printStackTrace();
        }
		game.start();
	}
	
	public void gameScreenObserver() {
		if (GDV5.KeysPressed[KeyEvent.VK_DOWN] && selected == 1) {
			selected = 2;
		}
		if (GDV5.KeysPressed[KeyEvent.VK_UP] && selected == 2) {
			selected = 1;
		}
	}
	
	public void controllerObserver() {
		if (GDV5.KeysPressed[KeyEvent.VK_W]) {
			player.moveAt(0,-1);
		}
		if (GDV5.KeysPressed[KeyEvent.VK_S]) {
			player.moveAt(0,1);
		}
		if (GDV5.KeysPressed[KeyEvent.VK_A]) {
			player.moveAt(-1,0);
		}
		if (GDV5.KeysPressed[KeyEvent.VK_D]) {
			player.moveAt(1,0);
		}
		if (GDV5.KeysPressed[KeyEvent.VK_SPACE] && shotReady) {
			PlayerProjectile laser = new PlayerProjectile(5,20,player.x + 3,player.y-10, 10);
			projectiles.add(laser);
			shootingTimer = 0;
			shotReady = false;
		}
	}
	public void shotgun(int type) {
		
		if (type == 1) {
			EnemyProjectile n = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,15);
			EnemyProjectile n1 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,15);
			EnemyProjectile n2 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,18);
			EnemyProjectile n3 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,18);
			EnemyProjectile n4 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,15);
			
			n1.setdx(0 + Math.random() * 0.4 - 0.2);
			n4.setdx(0.33 +  Math.random() * 0.4 - 0.2);
			n.setdx(-0.33+ Math.random() * 0.4 - 0.2);
			n2.setdx(0.75+ Math.random() * 0.4 - 0.2);
			n3.setdx(-0.75+ Math.random() * 0.4 - 0.2);
			n1.setdy(1);
			n2.setdy(0.25);
			n3.setdy(0.25);
			n4.setdy(0.66);
			n.setdy(0.66);
			n.setid(1);
			n2.setid(1);
			n3.setid(1);
			n4.setid(1);
			n1.setid(1);
			eProjectiles.add(n);
			eProjectiles.add(n2);
			eProjectiles.add(n3);
			eProjectiles.add(n4);
			eProjectiles.add(n1);
			
		}
		if (type == 2) {
			EnemyProjectile n = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,15);
			EnemyProjectile n2 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,15);
			EnemyProjectile n3 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,15);
			EnemyProjectile n4 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,15);
			EnemyProjectile n5 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,17);
			EnemyProjectile n6 = new EnemyProjectile(20,20,enemies.get(0).x+100,enemies.get(0).y+200,17);
			n4.setdx(0.25+ 0.01 * Math.random() * 0.4 - 0.2);
			n.setdx(-0.25+ 0.01 *  Math.random() * 0.4 - 0.2);
			n2.setdx(0.66+ 0.01 *  Math.random() * 0.4 - 0.2);
			n3.setdx(-0.66+ 0.01 *  Math.random() * 0.4 - 0.2);
			n2.setdy(0.33);
			n3.setdy(0.33);
			n5.setdx(0.44+ 0.01 * Math.random() * 0.4 - 0.2);
			n6.setdx(-0.44+ 0.01 *  Math.random() * 0.4 - 0.2);
			n5.setdy(0.55);
			n6.setdy(0.55);
			n4.setdy(0.75);
			n.setdy(0.75);
			n.setid(1);
			n2.setid(1);
			n3.setid(1);
			n4.setid(1);
			n5.setid(1);
			n6.setid(1);
			eProjectiles.add(n);
			eProjectiles.add(n2);
			eProjectiles.add(n3);
			eProjectiles.add(n4);
			eProjectiles.add(n5);
			eProjectiles.add(n6);
		}
		
	}
	public void enemyBehavior() {
		// 1st boss, 1 = shot gun, 2 = homing, 3 = locked 
		if (wave == 1) {
			if (enemytimer % 180 == 0)  {
				if (enemies.get(0).getHp() > 0) {
					EnemyProjectile n = new EnemyProjectile(30,30,enemies.get(0).x+10,enemies.get(0).y+10, 1);
					n.setdx(0.015 * (player.getX() - n.getX()));
					n.setdy(0.015 * (player.getY() - n.getY()));
					n.setid(5);
					eProjectiles.add(n);
				}
				
				if (enemies.get(1).getHp() > 0) {
					EnemyProjectile n1 = new EnemyProjectile(30,30,enemies.get(1).x+10,enemies.get(1).y+10, 1);
					n1.setdx(0.015 * (player.getX() - n1.getX()));
					n1.setdy(0.015 * (player.getY() - n1.getY()));
					n1.setid(5);
					eProjectiles.add(n1);
				}
				
			}
			
		}
		if (wave == 2) {
			if (enemytimer % 75 == 0) {
				EnemyProjectile n = new EnemyProjectile(20,750,player.x + 10 + (int) Math.random() * 50 + 1,enemies.get(0).y+10, 1, false, false);
				eProjectiles.add(n);
				ltime = 0;
				ltimer = new Timer();
				lTask = new TimerTask() {
					@Override 
					public void run() {
						if (ltime < 12) {
							ltime++;
							if (ltime == 4) {
								n.turnActive();
							}
						} 
						else {
							n.hit();
							ltime = 0;
							cancel();
						}
					}
				};
				
				ltimer.schedule(lTask, 0, 100);
			}
		}
		if (wave == 3) {
			if (enemytimer % 180 == 0)  {
				if (enemies.get(0).getHp() > 0) {
					EnemyProjectile n = new EnemyProjectile(20,20,enemies.get(0).x+10,enemies.get(0).y+10, 1);
					n.setdx(0.010 * (player.getX() - n.getX()));
					n.setdy(0.010 * (player.getY() - n.getY()));
					n.setid(5);
					eProjectiles.add(n);
					EnemyProjectile nn = new EnemyProjectile(20,20,enemies.get(0).x+10,enemies.get(0).y+10, 1);
					nn.setdx(0.010 * (player.getX() - nn.getX()) - 1);
					nn.setdy(0.012 * (player.getY() - nn.getY()));
					nn.setid(5);
					eProjectiles.add(nn);
					EnemyProjectile nnn = new EnemyProjectile(20,20,enemies.get(0).x+10,enemies.get(0).y+10, 1);
					nnn.setdx(0.010 * (player.getX() - nnn.getX()) + 1);
					nnn.setdy(0.012 * (player.getY() - nnn.getY()));
					nnn.setid(5);
					eProjectiles.add(nnn);
				}
				if (enemies.get(1).getHp() > 0) {
					EnemyProjectile n1 = new EnemyProjectile(20,20,enemies.get(1).x+10,enemies.get(1).y+10, 1);
					n1.setdx(0.010 * (player.getX() - n1.getX()));
					n1.setdy(0.010 * (player.getY() - n1.getY()));
					n1.setid(5);
					eProjectiles.add(n1);
					EnemyProjectile nn1 = new EnemyProjectile(20,20,enemies.get(1).x+10,enemies.get(1).y+10, 1);
					nn1.setdx(0.010 * (player.getX() - nn1.getX()) - 1);
					nn1.setdy(0.012 * (player.getY() - nn1.getY()));
					nn1.setid(5);
					eProjectiles.add(nn1);
					EnemyProjectile nnn1 = new EnemyProjectile(20,20,enemies.get(1).x+10,enemies.get(1).y+10, 1);
					nnn1.setdx(0.010 * (player.getX() - nnn1.getX()) + 1);
					nnn1.setdy(0.012 * (player.getY() - nnn1.getY()));
					nnn1.setid(5);
					eProjectiles.add(nnn1);
				}
				if (enemies.get(2).getHp() > 0) {
					EnemyProjectile n2 = new EnemyProjectile(20,20,enemies.get(2).x+10,enemies.get(2).y+10, 1);
					n2.setdx(0.010 * (player.getX() - n2.getX()));
					n2.setdy(0.010 * (player.getY() - n2.getY()));
					n2.setid(5);
					eProjectiles.add(n2);
					EnemyProjectile nn2 = new EnemyProjectile(20,20,enemies.get(2).x+10,enemies.get(2).y+10, 1);
					nn2.setdx(0.010 * (player.getX() - nn2.getX()) - 1);
					nn2.setdy(0.012 * (player.getY() - nn2.getY()));
					nn2.setid(5);
					eProjectiles.add(nn2);
					EnemyProjectile nnn2 = new EnemyProjectile(20,20,enemies.get(2).x+10,enemies.get(2).y+10, 1);
					nnn2.setdx(0.010 * (player.getX() - nnn2.getX()) + 1);
					nnn2.setdy(0.012 * (player.getY() - nnn2.getY()));
					nnn2.setid(5);
					eProjectiles.add(nnn2);
				}
				
				
				
			}
		}
		if (stage == 1 && wave == 4) {
			if (enemytimer % 420 == 0) {
				//int pattern = (int) Math.random() * 5; 
				
				switch (pattern % 4) {
				case(0):
					eProjectiles = new ArrayList<EnemyProjectile>();
					p1time = 0;
					p1timer = new Timer();
					TimerTask p2timeTask = new TimerTask() {
						
						@Override 
						public void run() {
							if (p1time < 5) {
								if (p1time % 2 == 0) {
									shotgun(1);
								}
								if (p1time % 2 == 1) {
									shotgun(2);
								}
								p1time++;
							} 
							else {
								System.out.println("timer stoped");
								cancel();
							}
						}
					};

					p1timeTask = new TimerTask() {
						@Override 
						public void run() {
							if (p1time < 5) {
								if (p1time % 2 == 0) {
									shotgun(1);
								}
								if (p1time % 2 == 1) {
									shotgun(2);
								}
								p1time++;
							} 
							else {
								System.out.println("timer stoped");
								p1time = 0;
								p1timer.schedule(p2timeTask, 1000, 300);
								cancel();
							}
						}
					};
					
					p1timer.schedule(p1timeTask, 0, 300);
					break;
				case(1):
					p2time = 0;
					eProjectiles = new ArrayList<EnemyProjectile>();
					p1timeTask = new TimerTask() {
						@Override 
						public void run() {
							if (p2time < 9) {
								if (p2time % 2 == 1) {
									EnemyProjectile n = new EnemyProjectile(40,40,200,400,3,true);
									n.setid(2);
									eProjectiles.add(n);
								} else {
									EnemyProjectile n = new EnemyProjectile(40,40,660,400, 3,true);
									n.setid(2);
									eProjectiles.add(n);
								}
								
								p2time++;
							} 
							else {
								System.out.println("timer stoped");
								p2time = 0;
								cancel();
							}
						}
					};
					
					p1timer.schedule(p1timeTask, 0, 500);
					break;
				case(2):
					eProjectiles = new ArrayList<EnemyProjectile>();
					p1timeTask = new TimerTask() {
					@Override 
					public void run() {
						if (p3time < 5) {
							EnemyProjectile n = new EnemyProjectile(30,30,enemies.get(0).x+100,enemies.get(0).y+200, 1);
							EnemyProjectile n1 = new EnemyProjectile(30,30,enemies.get(0).x+100,enemies.get(0).y+200, 2);
							EnemyProjectile n2 = new EnemyProjectile(30,30,enemies.get(0).x+100,enemies.get(0).y+200, 3);
							EnemyProjectile n3 = new EnemyProjectile(30,30,enemies.get(0).x+100,enemies.get(0).y+200, 4);
							EnemyProjectile n4 = new EnemyProjectile(30,30,enemies.get(0).x+100,enemies.get(0).y+200, 5);
							EnemyProjectile n5 = new EnemyProjectile(30,30,enemies.get(0).x+100,enemies.get(0).y+200, 6);
							EnemyProjectile n6 = new EnemyProjectile(30,30,enemies.get(0).x+100,enemies.get(0).y+200, 8);
							n.setdx(0.015 * (player.getX() - n.getX()));
							n.setdy(0.015 * (player.getY() - n.getY()));
							n1.setdx(0.015 * (player.getX() - n.getX()));
							n1.setdy(0.015 * (player.getY() - n.getY()));
							n2.setdx(0.015 * (player.getX() - n.getX()));
							n2.setdy(0.015 * (player.getY() - n.getY()));
							n3.setdx(0.015 * (player.getX() - n.getX()));
							n3.setdy(0.015 * (player.getY() - n.getY()));
							n4.setdx(0.015 * (player.getX() - n.getX()));
							n4.setdy(0.015 * (player.getY() - n.getY()));
							n5.setdx(0.015 * (player.getX() - n.getX()));
							n5.setdy(0.015 * (player.getY() - n.getY()));
							n6.setdx(0.015 * (player.getX() - n.getX()));
							n6.setdy(0.015 * (player.getY() - n.getY()));
							n.setid(3);
							n1.setid(3);
							n2.setid(3);
							n3.setid(3);
							n4.setid(3);
							n5.setid(3);
							n6.setid(3);
							eProjectiles.add(n);
							eProjectiles.add(n1);
							eProjectiles.add(n2);
							eProjectiles.add(n3);
							eProjectiles.add(n4);
							eProjectiles.add(n5);
							eProjectiles.add(n6);
							p3time++;
							
							
						} 
						else {
							System.out.println("timer stoped");
							p3time = 0;
							cancel();
						}
					}
				};
				
				p1timer.schedule(p1timeTask, 0, 500);
					break;
				case(3):
					p2time = 0;
				eProjectiles = new ArrayList<EnemyProjectile>();
				p1timeTask = new TimerTask() {
					@Override 
					public void run() {
						if (p2time < 24) {
							if (p2time % 12 == 6) {
								EnemyProjectile n = new EnemyProjectile(300,300,enemies.get(0).x-50,enemies.get(0).y,5);
								EnemyProjectile n1 = new EnemyProjectile(300,300,enemies.get(0).x-50,enemies.get(0).y,5);
								n1.setdx(-1);
								n1.setdy(1.71);
								n.setdx(1);
								n.setdy(1.71);
								n.setid(4);
								n1.setid(4);
								eProjectiles.add(n1);
								eProjectiles.add(n);
							} 
							if (p2time % 12 == 0) {
								EnemyProjectile n = new EnemyProjectile(300,300,enemies.get(0).x-50,enemies.get(0).y, 5);
								n.setdy(2);
								n.setid(4);
								eProjectiles.add(n);
							}
							
							p2time++;
						} 
						else {
							System.out.println("timer stoped");
							p2time = 0;
							cancel();
						}
					}
				};
				
				p1timer.schedule(p1timeTask, 0, 250);
					break;
				case(4):
					break;
				}
				pattern++;
			}
		}
	}
	
	public void laserObserver() {
		for (EnemyShip enemy: enemies) {
			for (PlayerProjectile laser: projectiles) {
				hit(enemy, laser);
			}
		}
	}

	public void hit(EnemyShip enemy, PlayerProjectile laser) {
		if (enemy.getHp() <= 0) {
			return;
		}
		if (!(enemy.intersects(laser))) {
			return;
		}
		if (laser.laser()) {
			enemy.hit(att);
			laser.hit();
		}
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (gamemode == 1) {
			gameScreenObserver();
			if (GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
				if (selected == 1) {
					//here
					player.locate(445, 700);
					timer = 0;
					shootingTimer = 0;
					shotReady = true;
					stage = 1;
					wave = 1;
					hp = 3;
					score = 0;
					powerup = 3;
					eProjectiles = new ArrayList<EnemyProjectile>();
					projectiles = new ArrayList<PlayerProjectile>();
					enemies = new ArrayList<EnemyShip>();
					EnemyShip enemy1 = new EnemyShip(40, 40, 50);
					EnemyShip enemy2 = new EnemyShip(40, 40, 50);
					enemy1.locate(GDV5.getMaxWindowX()/4, 100);
					enemy2.locate(3 * GDV5.getMaxWindowX()/4, 100);
					enemies.add(enemy1);
					enemies.add(enemy2);
					gamemode = 3;
				} else {
					gamemode = 2;
				}
				
			}
		}
		if (gamemode == 2) {
			if (GDV5.KeysPressed[KeyEvent.VK_BACK_SPACE]) {
				gamemode = 1;
			}
		}
		if (gamemode == 3) {
			timer++;
			enemytimer++;
			if (ultActivated) {
				ult.setFrame(ult.getX()-4.5, ult.getY()-4.5, ult.getHeight()+9, ult.getWidth()+9);
			}
			
			if (shootingTimer < 15) {
				shootingTimer++;
			}
			if (shootingTimer == 15) {
				shotReady = true;
			}
			if (hp <= 0) {
				gamemode = 4;
			}
			
			if (GDV5.KeysPressed[KeyEvent.VK_E] && ultOn) {
				ultOn = false;
				ult.setFrame(player.x-50, player.y-50, 100, 100);
				ultActivated = true;
				powerup--;
				ultTask = new TimerTask() {
					@Override 
					public void run() {
						if (ultTimer1 < 5) {
							System.out.println("sec " + ultTimer1);
							ultTimer1++;
							if (ultTimer1 == 3) {
								ultActivated = false;
								ult = new Ellipse2D.Double(100, 100, 80, 80);
							}
						} 
						else {
							System.out.println("ult ready");
							ultTimer1 = 0;
							ultOn = true;
							cancel();
						}
					}
				};
				ultTimerr = new Timer();
				ultTimerr.schedule(ultTask, 0, 1000);
				
			}
			playerSprite.locate(player.x - 5, player.y - 5);
			controllerObserver();
			laserObserver();
			for (PlayerProjectile pro: projectiles) {
				pro.move();
			}
			for (EnemyProjectile e: eProjectiles) {
				e.moveAt();
			}
			
			for (int i = 0; i < eProjectiles.size(); i++) {
				if (wave == 2) {
					if(i < eProjectiles.size()) {
						EnemyProjectile e = eProjectiles.get(i);
						if (e.isActive()) {
							if (e.intersects(player)) {
								if (e.laser()) {
									e.hit();
									hp -= 1;
								}
							}
							if (ult.intersects(e) && ultActivated) {
								if (e.laser()) {
									e.hit();
								}
							}
						}
						
					}
				} else {
					if(i < eProjectiles.size()) {
						EnemyProjectile e = eProjectiles.get(i);
						if (e.intersects(player)) {
							if (e.laser()) {
								e.hit();
								hp -= 1;
							}
						}
						if (ult.intersects(e) && ultActivated) {
							if (e.laser()) {
								e.hit();
							}
						}
					}
				}
				
				
				
			}
			
			for (int i = 0; i < eProjectiles.size(); i++) {
				if(i < eProjectiles.size()) {
					EnemyProjectile e = eProjectiles.get(i);
					if (e.gethoming()) {
						e.setdx(0.015 * (player.getX() - e.getX()));
						e.setdy(0.015 * (player.getY() - e.getY()));
					}
				}
			}
			
			if (wave == 1 && enemies.get(0).getHp() <= 0 && enemies.get(1).getHp() <= 0) {
				EnemyShip enemy1 = new EnemyShip(50, 50, 150);
				enemy1.locate(GDV5.getMaxWindowX()/2-25, 100);
				enemies = new ArrayList<EnemyShip>();
				enemies.add(enemy1);
				
				projectiles = new ArrayList<PlayerProjectile>();
				eProjectiles = new ArrayList<EnemyProjectile>();
				score += 1000;
				enemytimer= 1;
				wave += 1;
			}
			if (wave == 2 && enemies.get(0).getHp() <= 0) {
				EnemyShip enemy1 = new EnemyShip(30, 30, 40);
				EnemyShip enemy2 = new EnemyShip(30, 30, 40);
				EnemyShip enemy3 = new EnemyShip(30, 30, 40);
				enemy1.locate(GDV5.getMaxWindowX()/4-15, 100);
				enemy2.locate(GDV5.getMaxWindowX()/2-15, 100);
				enemy3.locate(3 * GDV5.getMaxWindowX()/4-15, 100);
				enemies = new ArrayList<EnemyShip>();
				enemies.add(enemy1);
				enemies.add(enemy2);
				enemies.add(enemy3);
				projectiles = new ArrayList<PlayerProjectile>();
				eProjectiles = new ArrayList<EnemyProjectile>();
				wave += 1;
				score += 1000;
			}
			if (wave == 3 && enemies.get(0).getHp() <= 0 && enemies.get(1).getHp() <= 0 && enemies.get(2).getHp() <= 0) {
				EnemyShip enemy1 = new EnemyShip(200, 200, 300);
				enemy1.locate(GDV5.getMaxWindowX()/2 - 100, 0);
				enemies = new ArrayList<EnemyShip>();
				enemies.add(enemy1);
				wave += 1;
				projectiles = new ArrayList<PlayerProjectile>();
				eProjectiles = new ArrayList<EnemyProjectile>();
				enemytimer = 0;
				score += 1000;
			}
			if (wave == 4 && enemies.get(0).getHp() <= 0) {
				score += 1000;
				gamemode = 4;
			}
			enemyBehavior();
		}
		if (gamemode == 4 && GDV5.KeysPressed[KeyEvent.VK_R]) {
			gamemode = 1;
		}
	}
	

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		super.paint(win);
		if (gamemode == 1) {
			
			win.setColor(Color.white);
			win.setFont(new Font("Georgia", Font.PLAIN, 50));
			win.drawString("Space", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Space")/2, GDV5.getMaxWindowY()/2 - 150);
			win.drawString("Invader", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Breakout")/2, GDV5.getMaxWindowY()/2 - 100);
			win.setFont(new Font("Kai", Font.PLAIN, 20));
			if (selected == 1) {
				win.drawString("How to Play", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("How to play")/2, GDV5.getMaxWindowY() - 250);
				win.setFont(new Font("Kai", Font.BOLD, 25));
				win.drawString("Play game", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Play game")/2, GDV5.getMaxWindowY() - 300);
			}
			if (selected == 2) {
				win.drawString("Play game", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Play game")/2, GDV5.getMaxWindowY() - 300);
				win.setFont(new Font("Kai", Font.BOLD, 25));
				win.drawString("How to Play", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("How to play")/2, GDV5.getMaxWindowY() - 250);
			}
			
		}
		if (gamemode == 2) {
			win.setColor(Color.WHITE);
			win.setFont(new Font("Helvetica", Font.PLAIN, 20));
			win.drawString("How to Play", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("How to play")/2, 50);
			win.setFont(new Font("Helvetica", Font.PLAIN, 30));
			win.drawString("Controls", 160, 200);
			win.setStroke(new BasicStroke(4));
			win.fillRect(125, 250, 50, 50);
			win.fillRect(190, 250, 50, 50);
			win.fillRect(255, 250, 50, 50);
	        win.setColor(Color.BLACK);
	        win.drawString("←", 135, 280);
			win.drawString("→", 200, 280);
			win.drawString("↑", 260, 280);
			win.setFont(new Font("Helvetica", Font.PLAIN, 10));
			win.drawString("Backspace", 55, 540);
			win.setFont(new Font("Helvetica", Font.PLAIN, 20));
			win.drawString("Go Back", 55, 520);
			win.setColor(Color.WHITE);
			win.drawString("Spacebar - attack, E - ultimate", GDV5.getMaxWindowX()/2 + 100, 250);
			win.drawString("     Defeat bosses to win!     ", GDV5.getMaxWindowX()/2 + 100, 280);
			win.drawString("  Press Backspace to go back   ", GDV5.getMaxWindowX()/2 + 100, 310);
		}
		
		if (gamemode == 4) {
			if (score == 4000) {
				win.setColor(Color.white);
				win.setFont(new Font("Georgia", Font.PLAIN, 50));
				win.drawString("You", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Wou")/2, GDV5.getMaxWindowY()/2 - 150);
				win.drawString("Win!", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Win!")/2, GDV5.getMaxWindowY()/2 - 100);
				win.setFont(new Font("Georgia", Font.PLAIN, 25));
				win.drawString("Press R to play again", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Press R to play again")/2, GDV5.getMaxWindowY()/2 - 50);
			} else {
				win.setColor(Color.white);
				win.setFont(new Font("Georgia", Font.PLAIN, 50));
				win.drawString("Game", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Game")/2, GDV5.getMaxWindowY()/2 - 150);
				win.drawString("Over", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Over")/2, GDV5.getMaxWindowY()/2 - 100);
				win.setFont(new Font("Georgia", Font.PLAIN, 25));
				win.drawString("Press R to play again", GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("Press R to play again")/2, GDV5.getMaxWindowY()/2 - 50);
			}
			
		}
		if (gamemode == 3) {
			win.drawImage(backgroundImage2, 0, 0, 900, 800, getBackground(), getFocusCycleRootAncestor());
			win.setColor(Color.white);
			//AffineTransform old = new AffineTransform(win.getTransform());
			//win.rotate(Math.toRadians(20), 10, 10);
			if (ultActivated) {
				win.drawImage(shield, (int) ult.getX(), (int) ult.getY(), (int) ult.getHeight(), (int)ult.getWidth(), null, getFocusCycleRootAncestor());
			}
			//win.fill(playerSprite);
			win.drawImage(playerSpritee, playerSprite.x, playerSprite.y, 20, 20, null, getFocusCycleRootAncestor());
			//win.setTransform(old);
			win.setFont(new Font("Kai", Font.PLAIN, 20));
			win.drawString("hp:", 40, 730);
			
			
			if (hp >= 3) {
				win.fillRect(120, 745, 30, 30);
			}
			if (hp >= 2) {
				win.fillRect(80, 745, 30, 30);
			}
			if (hp >= 1) {
				win.fillRect(40, 745, 30, 30);
			}
			win.drawString("ultimate:", GDV5.getMaxWindowX() - win.getFontMetrics().stringWidth("power up:  ") - 45, 730);
			if (powerup >= 3) {
				win.fillRect(GDV5.getMaxWindowX() - 70, 745, 30, 30);
			}
			if (powerup >= 2) {
				win.fillRect(GDV5.getMaxWindowX() - 110, 745, 30, 30);
			}
			if (powerup >= 1) {
				win.fillRect(GDV5.getMaxWindowX() - 150, 745, 30, 30);
			}
			win.setColor(Color.red);
			for (PlayerProjectile pro: projectiles) {
				if (pro.laser()) {
					win.drawImage(pProjectile, pro.x-3, pro.y-5, 10, 30, null, getFocusCycleRootAncestor());
				}
				
			}
			
			for (int i = 0; i < eProjectiles.size(); i++) {
				if(i < eProjectiles.size()) {
					EnemyProjectile e = eProjectiles.get(i);
					if (e.laser()) {
						
						if (wave == 1 || wave == 3) {
							win.drawImage(meteorSprite, e.x-5, e.y-5, 30, 30, null, getFocusCycleRootAncestor());
						}
						else if (wave == 2) {
							win.drawImage(laserSprite, e.x, e.y, 20, 750, null, getFocusCycleRootAncestor());
						}
						else if (e.getid() == 1) {
							win.drawImage(pattern1, e.x-5, e.y-5, 30, 30, null, getFocusCycleRootAncestor());
							}
						else if (e.getid() == 2) {
							win.drawImage(pattern4, e.x, e.y, 40, 40, null, getFocusCycleRootAncestor());
						}
						else if (e.getid() == 3) {
							win.drawImage(pattern3, e.x, e.y, 30, 30, null, getFocusCycleRootAncestor());
						}
						else if (e.getid() == 4) {
							win.drawImage(pattern2, e.x-25, e.y-25, 350, 350, null, getFocusCycleRootAncestor());
						} else {
							win.fill(e);
						}
						
						
						
					}
				}
			}
			
			for (EnemyShip enemy: enemies) {
				if (enemy.getHp() > 0) {
					if (stage == 1 && wave == 1) {
						win.drawImage(backgroundImage1, enemy.x, enemy.y, 40, 40, null, getFocusCycleRootAncestor());
					} 
					else if (wave == 2) {
						win.drawImage(satSprite, enemy.x, enemy.y, 50, 50, null, getFocusCycleRootAncestor());
					}
					else if (wave == 3) {
						win.drawImage(marsSprite, enemy.x, enemy.y, 30, 30, null, getFocusCycleRootAncestor());
					}
					else if (stage == 1 && wave == 4) {
						win.drawImage(sunSprite, enemy.x-50, enemy.y, 300, 200, null, getFocusCycleRootAncestor());
						win.setColor(Color.red);
						win.drawRect(70, 45, (int) (660 * enemy.hp / 300), 30);
						win.fillRect(70, 45, (int) (660 * enemy.hp / 300), 30);
					}
					else {
						win.fill(enemy);
					}
				}
			}
			win.setColor(Color.white);
			win.drawString("score: " + score, GDV5.getMaxWindowX()/2 - win.getFontMetrics().stringWidth("score: " + score)/2, 50);
			
		}
		
	}
	
}
