import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explor {
	int x,y;
	int[] diameter = { 20, 20, 30, 30, 30, 50, 50, 30, 30, 30, 30, 20, 10};
	int step = 0; 
	boolean Live = true;
	TankClient tc;
	
	public Explor(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	private static Image[] imgs = null;
	static {
		imgs = new Image[]{
		tk.getImage(Explor.class.getClassLoader().getResource("images/0.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/1.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/2.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/3.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/4.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/5.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/6.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/7.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/8.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/9.gif")),
		tk.getImage(Explor.class.getClassLoader().getResource("images/10.gif")),
	};
	}
	
	
	
	
	private static boolean init = false;
	
	
	
	public void draw(Graphics g){
		if(!Live) return;
		if(step == diameter.length){
			Live = false;
			step = 0;
		}
		if(step == imgs.length) {
			Live = false;
			step = 0;
			return;
		}
		
		g.drawImage(imgs[step], x, y, null);
		
		step ++;
	
}
}
