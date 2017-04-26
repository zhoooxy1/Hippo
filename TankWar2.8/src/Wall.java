import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {

	int x,y,w,h;
	TankClient tc;
	public Wall(int x, int y, int w, int h, TankClient tc) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(x, y, w, h);
	}
	
	
}
