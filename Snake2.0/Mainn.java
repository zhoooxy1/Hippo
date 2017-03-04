
public class Mainn {

	public static void main(String[] args) {
		Snake snake = new Snake(2,8,DIR.R);
		Grid grid = new Grid(snake);
		snake.addObserver(grid);
		new Thread(snake).start();
		
	}

}
