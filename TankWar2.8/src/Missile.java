import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
 
public class Missile {

	int x,y;
	DIR dir;
	private boolean live = true;
	TankClient tc = null;
	private boolean good = true;


	public static final int width = 10;
	public static final int height = 10;
	private static final int XSPEED = 8;
	private static final int YSPEED = 8;
/*
	public Missile(int x, int y,Tank.DIR dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
		
	}
	*/
	public Missile(int x, int y,DIR dir,boolean GOOD,TankClient tc) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tc = tc;
		this.good = GOOD;
	}
	
	public void draw(Graphics g){
		if(!live){
			tc.missiles.remove(this);
		return;}
		if(good) g.setColor(Color.MAGENTA);
		else
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x, y, width, height);
		move();
	}

	public void move() {
		switch(dir){
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

		}
		
		if(x < 0 || y < 0 || x > TankClient.WIDTH || y > TankClient.HEIGHT){
			live  = false;
		}
	}
	
	
	public boolean isLive() {
		return live;
	}

	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	boolean hitTank(Tank t){
		if(this.getRect().intersects(t.getRect()) && t.isLive() && this.isLive() && this.good != t.GOOD){
			
			t.setLife(t.getLife()-10);
			if(t.getLife() == 0) t.setLive(false);
		
			this.live = false;
			Explor e = new Explor(t.x, t.y, tc);
			tc.explor.add(e);
			return true;
		}
		return false;
	}
	
	public boolean hitsTank(List<Tank> tanks){
		for(int i=0; i<tanks.size(); i++){
			Tank t = tanks.get(i);
			if(hitTank(t)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hitWall(Wall w){
			if(this.getRect().intersects(w.getRect())){
				this.live = false;
				return true;
			}
			return false;
	}
}
