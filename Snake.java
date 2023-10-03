/**
 * The Snake is a singly linked list with snake behaviors.
 * 
 * @author Atul Ummaneni
 * @since May 14, 2022
 */
public class Snake extends SinglyLinkedList<Coordinate> {

	// Fields

	public Snake(Coordinate location) {
		clear();
		int col = location.getColumn();
		int row = location.getRow();
		for (int i = 0; i < 5; i++) {
			add(new Coordinate(row + i, col));
		}
	}

	public Snake(int i, int j) {
		for (int k = i; k < i + 4; k++) {
			this.add(new Coordinate(k + 1, j));
		}

	}

}