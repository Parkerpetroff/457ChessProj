package chess;

/**
 * Class for queen chess piece.
 * @author Alec
 *
 */
public class Queen extends Piece {
	/**
	 * Queen constructor.
	 * @param team the team the piece belongs to.
	 */
	public Queen(final Team team) {
		super(team);
	}
	
	/**
	 * Returns the name of the chess piece.
	 * @return name of chess piece
	 */
	public String type() {
		return "Queen";
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
		
		//check final space that piece will occupy
		if (board[move.toRow][move.toColumn] == null 
				|| !(board[move.toRow][move.toColumn].team().equals(team()))) {
            valid = true;
		}
		
		//check if not moving like rook or bishop
		if ((move.toRow != move.fromRow && move.toColumn != move.fromColumn)
			&& (Math.abs(move.toRow - move.fromRow) != Math.abs(move.toColumn - move.fromColumn))) {
        	return false;
        }
		
		//check intermediate spaces if moving multiple spaces
  		if (Math.abs(move.toRow - move.fromRow) > 1 || Math.abs(move.toColumn - move.fromColumn) > 1) {
  			int rowOffset = 0, colOffset = 0;
  			//check that you're moving in that direction to avoid / by zero exception
  			if (Math.abs(move.toRow - move.fromRow) >= 1) {
  				rowOffset = (move.toRow - move.fromRow) / Math.abs(move.toRow - move.fromRow);
  			}
  			if (Math.abs(move.toColumn - move.fromColumn) >= 1) {
  				colOffset = (move.toColumn - move.fromColumn) 
  						/ Math.abs(move.toColumn - move.fromColumn);
  			}
  			for (int i = 1; i < Math.abs(move.fromRow - move.toRow)
				|| i < Math.abs(move.fromColumn - move.toColumn); i++) {
  				if (board[move.fromRow + (i * rowOffset)][move.fromColumn + (i * colOffset)] == null) {
                      valid = true;
          		} else {
          			return false;
          		}
  			}
  		}
		return valid;
	}
}
