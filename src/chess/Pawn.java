package chess;

/**
 * Class for the pawn chess piece.
 * @author Alec
 *
 */
public class Pawn extends Piece {
	/**
	 * Pawn constructor.
	 * @param team the team the piece belongs to
	 */
	public Pawn(final Team team) {
		super(team);
	}
	
	/**
	 * Get the name of the piece.
	 * @return name of piece "Pawn".
	 */
	public String type() {
		return "Pawn";
	}
	
	/**
	 * Whether or not the last move was valid.
	 * @param move the move that was made
	 * @param board the current board
	 * @return True if the move is valid false if not
	 */
	public boolean isValidMove(final Move move, final Piece[][] board) {
		boolean valid = false;
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		//if moving vertically
		if (move.fromColumn == move.toColumn && board[move.toRow][move.toColumn] == null) {
			//move one space
			if ((board[move.fromRow][move.fromColumn].team() == "BLACK" 
					&& move.fromRow + 1 == move.toRow)
					|| (board[move.fromRow][move.fromColumn].team() == "WHITE" 
				&& move.fromRow - 1 == move.toRow)) {
				valid = true;
			}
			//piece can move 2 spaces if first move
			if ((board[move.fromRow][move.fromColumn].team() == "BLACK" 
					&& move.fromRow == 1 && move.toRow == 3) 
					|| (board[move.fromRow][move.fromColumn].team() == "WHITE" 
					&& move.fromRow == 6 && move.toRow == 4)) {
				//if (board[(move.fromRow + move.toRow) / 2][move.fromColumn] == null) {
				if (board[(move.fromRow / 2) + (move.toRow / 2) 
				          + ((move.fromRow % 2 + move.toRow % 2) / 2)]
					[move.fromColumn] == null) {
					valid = true;
				}
			}
		}
		//if move diagonally if and only if capture
		if (Math.abs(move.toRow - move.fromRow) == 1 && Math.abs(move.toColumn - move.fromColumn) == 1
			&& board[move.toRow][move.toColumn] != null 
			&& !(board[move.toRow][move.toColumn].team().equals(team()))) {
			if (board[move.fromRow][move.fromColumn].team() == "BLACK" && move.fromRow + 1 == move.toRow) {
				valid = true;
			}
			if (board[move.fromRow][move.fromColumn].team() == "WHITE" && move.fromRow - 1 == move.toRow) {
				valid = true;
			}
		}
		return valid;
	}
}
