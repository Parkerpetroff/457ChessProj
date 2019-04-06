package chess;

/**
 * Class for king chess piece.
 * @author Alec
 *
 */
public class King extends Piece {
	/**
	 * King constructor.
	 * @param team the team the piece belongs to
	 */
	public King(final Team team) {
		super(team);
	}
	
	/**
	 * Returns the name of the chess piece.
	 * @return name of chess piece
	 */
	public String type() {
		return "King";
	}
	
	/**
	 * Whether or not the last move was valid.
	 * @param move the move that was made
	 * @param board the current board
	 * @return True if the move is valid false if not
	 */
	public boolean isValidMove(final Move move, final Piece[][] board) {
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		//check that king is only moving one space in any direction
		if (Math.abs(move.toRow - move.fromRow) <= 1 && Math.abs(move.toColumn - move.fromColumn) <= 1
			&& (board[move.toRow][move.toColumn] == null
			|| !(board[move.toRow][move.toColumn].team().equals(team())))) {
			return true;
		}
		return false;
	}
}
