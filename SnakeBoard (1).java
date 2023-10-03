/**
 * Creates the board and places the Snake and 
 * the target on the board.
 *
 * @author Atul Ummaneni
 * @since 13 May 2022
 */
public class SnakeBoard {

	/* fields */
	private char[][] board;
	private int height, width; // The 2D array to hold the board

	/* Constructor */
	public SnakeBoard(int height, int width) {
		board = new char[height][width];
		this.height = height;
		this.width = width;
	}

	/**
	 * Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		/* implement here */
		System.out.print("+ ");
		for (int i = 0; i < width; i++) {
			System.out.print("- ");
		}
		System.out.print("+");
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = ' ';
			}
		}
		
		ListNode<Coordinate> head = snake.get(0); // used to traverse through the snake
		board[head.getValue().getRow()][head.getValue().getColumn()] = '@'; // places the head of the snake on the board
		head = head.getNext();
		while (head != null) // traverses through the snake
		{
			board[head.getValue().getRow()][head.getValue().getColumn()] = '*';
			head = head.getNext();
		}
		board[target.getRow()][target.getColumn()] = '+'; // places the target

		for (int row = 0; row < board.length; row++) // prints the board
		{
			for (int col = 0; col < board[0].length; col++) {
				if(col==0)System.out.print("| ");
				System.out.print(board[row][col] + " ");
				if(col==board[0].length-1)System.out.print("|");
			}
			System.out.println();
		}
		System.out.print("+ ");
		for (int i = 0; i < width; i++) {
			System.out.print("- ");
		}
		System.out.print("+");
		System.out.println();
	}

	/* Helper methods go here */

	/* Accessor methods */

	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/

	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}