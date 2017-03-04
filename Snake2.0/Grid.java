import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;


public class Grid extends JFrame implements Observer{
	public static final int ROW = 25;
	public static final int COL = 25;
	public static final int SIZE = 15;
	
	Node head;
	
	Image offScreenImage = null;
	private Snake snake;
	
	
	public Grid(Snake snake){
		this.snake = snake ;
		
		this.setTitle("Ã∞≥‘…ﬂ2.0");
		this.setBounds(0, 0, COL*SIZE, ROW*SIZE);
		this.setLocation(1000, 300);
		this.addWindowListener(new WindowAdapter (){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);

	}
	
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
		}
		
	}
	
	 public void repaint(){

		Graphics g = getGraphics();
		
		LinkedList<Node> na = snake.snake;
		g.setColor(Color.PINK);
		g.fillRect(0, 0, COL*SIZE, ROW*SIZE);
		Iterator<Node> it = na.iterator();
		while (it.hasNext()) {
            Node n = (Node) it.next();
    		g.setColor(Color.DARK_GRAY);
    		g.fillOval(SIZE *n.x,SIZE *n.y, SIZE , SIZE );
        }
		 g.setColor(Color.RED);
	        Node n = snake.EggNode;
	        g.fillOval(SIZE *n.x,SIZE *n.y, SIZE , SIZE );
		
	}

	 public void update(Observable o, Object arg) {
	        repaint();
	    }

}
