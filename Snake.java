import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	int x,y;
	Node head= null;
	Node tail = null;
	int size = 0;
	private Yard y1;
	int w = Yard.BLOCK_SIZE;
	
	Node n = new Node(20,20,Dir.L);
	
	public Snake(Yard y1){
		head = n;
		tail = n;
		size = 1;
		this.y1 = y1;
	}
	
	
	public void addToTail(){
		Node node = null;
		switch(tail.dir){
		case L :
			node = new Node(tail.row , tail.col +1,tail.dir);
			break;
		case R :
			node = new Node(tail.row , tail.col-1,tail.dir);
			break;
		case U :
			node = new Node(tail.row-1, tail.col,tail.dir);
			break;
		case D :
			node = new Node(tail.row+1, tail.col,tail.dir);
			break;
		}
		tail.next = node;
		node.prev = tail;
		tail = node;
		size ++;
	}
	
	public void addToHead(){
		Node node = null;
		switch(head.dir){
		case L :
			node = new Node(head.row , head.col -1,head.dir);
			break;
		case R :
			node = new Node(head.row , head.col+1,head.dir);
			break;
		case U :
			node = new Node(head.row-1, head.col,head.dir);
			break;
		case D :
			node = new Node(head.row+1, head.col,head.dir);
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		size ++;
	}
	
	public void draw(Graphics g){

		if(size <=0 ) return;
		move();
		for(Node n = head; n != null; n = n.next){
			
		n.draw(g);
		}
	
	}
	
	private void move(){
		addToHead();
		deleteFromTail();
	}
	
	
	public  Rectangle getRec() {
		return new Rectangle(head.col*w, head.row*w,w,w);
	}
	
	
	public void eat(Egg e){
		if(this.getRec().intersects(e.getRect())){
			e.reAppear();
		this.addToHead();
		}
	}
	
	
	
	
	
	
	
	
	 private void deleteFromTail() {
		 if(size == 0) return;
		 
		 tail = tail.prev;
		 tail.next = null;
		  
		
	}















	class Node{
		Node next;
		int row,col;
		Dir dir = Dir.L;
		Node prev = null;
		
		Node(int row, int col, Dir dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		
		void draw(Graphics g){
			g.setColor(Color.red);
			g.fillOval(w*col,w*row,w,w);

		}
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT :
			if(head.dir != Dir.R)
				head.dir = Dir.L;
			break;
		case KeyEvent.VK_UP :
			if(head.dir != Dir.D)
				head.dir = Dir.U;
			break;
		case KeyEvent.VK_RIGHT :
			if(head.dir != Dir.L)
				head.dir = Dir.R;
			break;
		case KeyEvent.VK_DOWN :
			if(head.dir != Dir.U)
				head.dir = Dir.D;
			break; 
		}
	}
}






