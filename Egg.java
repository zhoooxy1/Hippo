import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;



public class Egg {
	
	static int col;
	static int row;
	int w = Yard.BLOCK_SIZE;
	static Random r = new Random();
	
	public Egg(int col, int row) {
		this.col = col;
		this.row = row;
	}
	
	public Egg() {
		this(r.nextInt(Yard.ROWS),r.nextInt(Yard.COLS));
	}
	
	public void draw (Graphics g){
		g.setColor(Color.red);
		g.fillOval(w*col,w*row,w,w);
	}
	
	public Rectangle getRect(){
		return new Rectangle (this.col*w,this.row*w,w,w);
	}
	
	public void reAppear(){
		this.col = r.nextInt(Yard.COLS);
		this.row = r.nextInt(Yard.ROWS);
	}

	
}
