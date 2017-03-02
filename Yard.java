import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Yard extends JFrame {

	Image offScreenImage = null;

	Snake snake = new Snake(this);
	Egg egg = new Egg();
	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	
	
	public void launch(){
		this.setSize(ROWS*BLOCK_SIZE, BLOCK_SIZE*COLS);
		this.setLocation(1000,300);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
			
		});
		new Thread(new PanitThread()).start();
		this.setVisible(true);
	}
	@Override
	
	
	public void paint(Graphics g) {
		
		if(offScreenImage == null) {
			offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		}
		Graphics gOff = offScreenImage.getGraphics();
		gOff.setColor(gOff.getColor());
		gOff.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS* BLOCK_SIZE);
		super.paint(gOff);
		gOff.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		gOff.setColor(Color.DARK_GRAY);
		//»­³öºáÏß
		for(int i=1; i<ROWS; i++) {
			gOff.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		for(int i=1; i<COLS; i++) {
			gOff.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * ROWS);
		}
		snake.eat(egg);
		snake.draw(gOff);
		egg.draw(gOff);
		this.addKeyListener(new KeyMonitor());
		g.drawImage(offScreenImage, 0, 0,  null);
	}
	
	
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			
			
			snake.keyPressed(e);
		}
		
	}
	
	
	public class PanitThread implements Runnable{

		@Override
		public void run() {
			while(true) {
				repaint();
				try{
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	public static void main (String args[]){
		new Yard().launch();
	}

}

