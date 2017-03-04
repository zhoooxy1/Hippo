
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;

public class Snake extends Observable implements Runnable {
	
	static LinkedList<Node> snake = new LinkedList<>();
	static boolean[][] s;
	
	int w = Grid.SIZE;
	static int x,y;
	static DIR dir;
	static Node head,EggNode;
	
	static int min = 1;
	static int max = Grid.ROW-1;

	static int Eggx,Eggy;
	
	 Random r = new Random();

	 boolean paused = false; 
	
	Iterator<Node> it;
	private Node Egg;

	boolean running = false; 
	public Snake(int x,int y,DIR dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		init();
	}
	
	public void init(){
		
		s = new boolean[max][];
    for (int i = 0; i < max; ++i) {
        s[i] = new boolean[max];
        Arrays.fill(s[i], false);
    }
    
	snake.add(new Node(x,y,dir));
	head = snake.getFirst();
	Egg = createEgg();
	s[x][y] = true;
	s[Egg.x][Egg.y] = true;
	}
	
	public  boolean move(){
		Node head = snake.getFirst();
		int xx = head.x;
		int yy = head.y;
		DIR d = head.dir;
		
		switch (d){
		case L:
			xx--;
			break;
		case R:
			xx++;
			break;
		case U:
			yy--;
			break;
		case D:
			yy++;
			break;
		}
		
		if( xx >= min && xx <= max && yy >= min && yy <= max){
			if(s[xx][yy]){
				if(Egg.getX() == xx &&  Egg.getY() == yy){
					snake.addFirst(new Node(xx,yy,d));
					s[Egg.getX()][Egg.getY()]=false;
					Egg = createEgg();
					s[Egg.getX()][Egg.getY()] = true;
					return true;
				}
				else{ 
					return false;
				}
			}
			else{
				snake.addFirst(new Node(xx,yy,d));
				s[snake.getFirst().getX()][snake.getFirst().getY()] = true;
				Node n = snake.removeLast();
				s[n.getX()][n.getY()] = false;
				return true;
			}
			}
		return false;
		}
	
		
	public  Node createEgg() {
		do{
		Eggx = min + r.nextInt(max - min);
		Eggy = min + r.nextInt(max - min);
		}while(s[x][y]);
		 return  EggNode = new Node (Eggx,Eggy,DIR.L);
	}


	public void keyPressed(KeyEvent e){
		head = snake.getFirst();
		int key = e.getKeyCode();
		if(running);
		switch(key){
		case KeyEvent.VK_UP:
			if(head.dir != DIR.D)
				head.dir = DIR.U;
			break;
		case KeyEvent.VK_DOWN:
			if(head.dir != DIR.U)
				head.dir = DIR.D;
			break;
		case KeyEvent.VK_LEFT:
			if(head.dir != DIR.R)
				head.dir= DIR.L;
			break;
		case KeyEvent.VK_RIGHT:
			if(head.dir != DIR.L)
				head.dir = DIR.R;
			break;
		}
	}
	

	@Override
	public void run() {
        running = true;
		while(true){
			try{
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		if (!paused) {
            if (move()) {
                setChanged();           
                notifyObservers();
            } else {
                JOptionPane.showMessageDialog(null,
                        "you failed",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
		running = false;
    }
	}
}
	
	


