package chess;

/**
 * Class that handles logic and attributes for an instance of the Knight class.
 * @author William
 *
 */
public class Knight extends Piece {
	/**
	 * Knight constructor.
	 * @param team the team the piece belongs to
	 */
	public Knight(final Team team) {
		super(team);
	}
	
	/**
	 * Returns the name of the chess piece.
	 * @return name of chess piece
	 */
	public String type() {
		return "Knight";
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
		
		//check if knight is moving in "L" shape as well as the final space that the piece will occupy
		if ((Math.abs(move.toRow - move.fromRow) == 1 && Math.abs(move.toColumn - move.fromColumn) == 2)
			|| (Math.abs(move.toRow - move.fromRow) == 2 && Math.abs(move.toColumn - move.fromColumn) == 1)
			&& (board[move.toRow][move.toColumn] == null
			|| !(board[move.toRow][move.toColumn].team().equals(team())))) {
			return true;
		}
		
		return false;
	}
}
