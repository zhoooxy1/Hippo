import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {
/*/
 *熟悉配置文件的使用，
 *properties类
 *Singleton模式
 *单个实例
 *举单个的实例的设计模式
 *
 *在propertyMng类中private构造方法，
 *使得其他类不能构造，只能读，不能改
 */

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	Image offScreenImage = null;
	Tank myTank = new Tank(50,50,true,DIR.STOP,this);
	
	List<Missile> missiles = new ArrayList<Missile>();
	Missile m = null;
	List<Explor> explor = new ArrayList<Explor>();
	List<Tank> enemyTanks = new ArrayList<Tank>();
	Wall w1 = new Wall(150, 150, 30, 300, this);
	Wall w2 = new Wall(400, 350, 300, 30, this);
	Explor e;
	
	Blood b = new Blood();
	public void paint(Graphics g) {
		
		g.setColor(Color.black);
		g.drawString("Missiles Count :" + missiles.size(), 10, 50);
		g.drawString("MyTank   Life  :" + myTank.getLife(), 10, 90);
		g.drawString("enemyTanks Count :" + enemyTanks.size(), 10, 70);
		
		if(enemyTanks.size() <= 3){
			for(int i=0; i<5; i++){
				Tank a = new Tank(400+40*(1+i), 500, false, DIR.D, this);
				enemyTanks.add(0, a);;
			}
		}
		
		
		
		for(int i=0; i<missiles.size(); i++){
			m = missiles.get(i);
			m.hitWall(w1);
			m.hitWall(w2);
			m.hitTank(myTank);
			m.hitsTank(enemyTanks);
			
			/*if(!m.isLive()){
				missiles.remove(i);
			}*/
			
			m.move();
			m.draw(g);
		}
		for(int i=0; i<enemyTanks.size(); i++){
			Tank t = enemyTanks.get(i);
			t.TouchWall(w1);
			t.TouchWall(w2);
			t.TouchTank(enemyTanks);
			t.draw(g);
		}
		for(int i=0; i<explor.size(); i++){
			e = explor.get(i);
			e.draw(g);
		}
		
		w1.draw(g);
		w2.draw(g);
		myTank.draw(g);
		myTank.TouchBlood(b);
		b.draw(g);
		
	}

	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(WIDTH, HEIGHT);
		}
		Graphics gOff = offScreenImage.getGraphics();
		gOff.setColor(Color.LIGHT_GRAY);
		gOff.fillRect(0, 0, WIDTH, HEIGHT);
		paint(gOff);
		
		g.drawImage(offScreenImage, 0, 0, null);
		
	}

	public void launchFrame(){
		
		for(int i=0; i<Integer.parseInt(propertyMng.getProperty("enemyTanksCount")); i++){
			Tank a = new Tank(200+40*(1+i), 200, false, DIR.D, this);
			enemyTanks.add(a);
		}
		this.setLocation(800,300);
		this.setSize(WIDTH, HEIGHT);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setTitle("TankWar");
		this.setResizable(false);
		this.setBackground(Color.LIGHT_GRAY);
		this.setVisible(true);
		this.addKeyListener(new KeyMonitor());
		new Thread(new PaintThread()).start();
	}
	
	
	private class PaintThread implements Runnable {

		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
	}
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		
	}
	public static void main(String[] args) {
		new TankClient().launchFrame();
		
	}
}
