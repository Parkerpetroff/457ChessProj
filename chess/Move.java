package chess;

/**
 * Class for keeping track of where the chess piece moves.
 * @author Alec
 *
 */
public class Move {
	/**
	 * Move data for rows and columns.
	 */
	public int fromRow, fromColumn, toRow, toColumn;

	/**
	 * Move constructor.
	 */
	public Move() {
		fromRow = -1;
		fromColumn = -1;
		toRow = -1;
		toColumn = -1;
	}

	/**
	 * Get the message for the move location.
	 * @return move location message
	 */
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", "
				+ "toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}
}
