import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tank {
	private static final int XSPEED = 5;
	private static final int YSPEED = 5;
	int x;
	int y;
	
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	
	List<Missile> missiles = new ArrayList<Missile>();
	
	
	private boolean  bL = false ,bR = false, bU = false, bD = false;
	private DIR TankDir = DIR.STOP;
	private DIR ptDir = DIR.D;
	TankClient tc = null;
	boolean Live = true;
	
	int Life = 100;
	
	public int getLife() {
		return Life;
	}
	
	public void setLife(int life) {
		Life = life;
	}

	private static Random r = new Random();
	private int step = r.nextInt(12) + 3;


	public boolean GOOD   ;
	
	
	int oldx,oldy;
	
	Tank(int x,int y,boolean GOOD){
		this.x = x;
		this.y = y;

		this.oldx = x;
		this.oldy = y;
		this.GOOD = GOOD;
	}
	

	Tank(int x,int y,boolean GOOD,DIR TankDir,TankClient tc){
		this(x,y,GOOD);
		this.TankDir = TankDir;
		this.tc = tc;
	}
	
	public void draw(Graphics g) {
		if(!Live) { 
			if(GOOD){
			return;
		}
		else if(!GOOD){
					tc.enemyTanks.remove(this);
					return;
				}
			}
		
		if(GOOD == true) g.setColor(Color.ORANGE);
		else g.setColor(Color.PINK);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		bb.draw(g);
		move();
		
		switch(ptDir){
		case L:
			g.drawLine( x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT/2);
			break;
		case R:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH, y+Tank.HEIGHT/2);
			break;
		case U:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH/2, y);
			break;
		case D:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH/2, y+Tank.HEIGHT);
			break;
		case LU:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y);
			break;
		case RU:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH, y);
			break;
		case LD:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x, y+Tank.HEIGHT);
			break;
		case RD:
			g.drawLine(x+Tank.WIDTH/2, y+Tank.HEIGHT/2, x+Tank.WIDTH, y+Tank.HEIGHT);
			break;
		case STOP:
			break;
		}
		
		
		
		
	}
	
	private void move(){
		oldx = x;
		oldy = y;
		switch(TankDir){
		case L:
			x -= XSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		
		if(TankDir != DIR.STOP){
			ptDir = TankDir;
		}
		
		if(x < 0) x = 0;
		if(y < 30) y = 30;
		if(x > TankClient.WIDTH - Tank.WIDTH ) x = TankClient.WIDTH - Tank.WIDTH ;
		if(y > TankClient.HEIGHT - Tank.HEIGHT) y =TankClient.HEIGHT - Tank.HEIGHT;
		
		if(!GOOD){
			DIR[] dir = DIR.values();
			if(step == 0){
				step = r.nextInt(12) + 3;
				int i = r.nextInt(dir.length);
				TankDir = dir[i];
			}
			step --;
			if(r.nextInt(30)>=29)fire();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_F2:
			if(!this.Live){
				this.Live = true;
				this.setLife(100);
			}
		case KeyEvent.VK_LEFT:
			bL = true;
			break;  
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
	}
		TankDir();
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_SPACE:
			SuperFire();
			break;
		}
		TankDir();
	}

	
	
	private Missile fire() {
		if(!Live) return null;
		int x = this.x + WIDTH/2 - Missile.width/2;
		int y = this.y + HEIGHT/2 - Missile.height/2;
		Missile m = new Missile(x,y,ptDir,this.GOOD,this.tc);
		tc.missiles.add(m);
		return m;
	}
	
	public Missile fire(DIR dir){
		if(!Live) return null;
		int x = this.x + WIDTH/2 - Missile.width/2;
		int y = this.y + HEIGHT/2 - Missile.height/2;
		Missile m = new Missile(x,y,dir,this.GOOD,this.tc);
		tc.missiles.add(m);
		return m;
		
	}
	public void SuperFire(){
		DIR[] dir = DIR.values();
		for(int i=0; i<8; i++){
			fire(dir[i]);
		}
	}

	private void TankDir(){
		if(bL && !bR && !bU && !bD )TankDir = DIR.L;
		else if(!bL && bR && !bU && !bD )TankDir = DIR.R;
		else if(!bL && !bR &&  bU && !bD )TankDir = DIR.U;
		else if(!bL && !bR && !bU &&  bD )TankDir = DIR.D;
		else if(bL && !bR &&  bU && !bD )TankDir = DIR.LU;
		else if(bL && !bR && !bU &&  bD )TankDir = DIR.LD;
		else if(!bL && bR &&  bU && !bD )TankDir = DIR.RU;
		else if(!bL && bR && !bU &&  bD )TankDir = DIR.RD;
		else if(!bL && !bR && !bU && !bD )TankDir = DIR.STOP;
		
		
		}


	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean isLive() {
		return Live;
	}


	public void setLive(boolean live) {
		Live = live;
	}
	
	public boolean TouchWall(Wall w){
		if(this.getRect().intersects(w.getRect())){
			this.stay();
			return true;
		}
		return false;
	}
	
	public boolean TouchBlood(Blood b){
		if(this.getRect().intersects(b.getRect())){
			b.Live = false;
			this.setLife(100);
			return true;
		}
		return false;
		
	}


	private void stay() {
		x = oldx;
		y = oldy;
		
	}
	
	public boolean TouchTank(List<Tank> tanks){
		for(int i=0; i<tanks.size(); i++){
			Tank t = tanks.get(i);
			if(t != this)
			if(this.getRect().intersects(t.getRect()) && this.Live 
					&& t.isLive()){
				t.stay();
				this.stay();
				return true;
			}
		}
		return false;
	}
	
	private BloodBar bb = new BloodBar();
	private class BloodBar{
		public void draw(Graphics g){
			g.setColor(Color.BLACK);
			g.drawRect(x, y-10, WIDTH, 10);
			g.setColor(Color.RED);
			int w = WIDTH * Life/100;
			g.fillRect(x, y-10, w, 10);
		}
	}
	
	
}

