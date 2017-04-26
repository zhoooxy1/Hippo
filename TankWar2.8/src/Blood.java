import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blood {
	int x,y,w,h;
	int step = 0;
	public boolean Live = true;
	
	private int[][] pos = {{400,500},{400,500},{400,500},{420,500},
			{440,500},{440,520},{440,520},{440,520},{420,520},{420,520},{420,520}};
	
	public Blood(){
		x = pos[0][0];
		y = pos[0][1];
		w = 20;
		h = 20;
	}
	
	public void draw(Graphics g){
		if(!Live) return ;
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, w, h);
		move();
	}

	public void move(){
		step ++;
		if(step == pos.length){
			step = 0;
		}
		x = pos[step][0];
		y = pos[step][1];
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
	
	
	
}
