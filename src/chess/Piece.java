package chess;

/**
 * Abstract class that all piece's will inherit.
 * @author Alec
 *
 */
public abstract class Piece {
	//private Player player;
	/**
	 * The team that the piece belongs to.
	 */
	private Team team;

	/**
	 * Piece constructor.
	 * @param team the team the piece belongs to.
	 */
	public Piece(final Team team) {
		this.team = team;
	}

	/**
	 * Get the name of the piece.
	 * @return name of piece
	 */
	public abstract String type();

	/**
	 * Get the team the piece belongs to.
	 * @return team name
	 */
	public String team() {
		return team.toString();
	}
	/**
	 * Determines if the piece's move was valid.
	 * @param move the attempted move
	 * @param board the current board
	 * @return True if the move is valid, false if not
	 */
	public boolean isValidMove(final Move move, final Piece[][] board) {
		return !((board[move.toRow][move.toColumn] != null
				&& board[move.toRow][move.toColumn].team().equals(team.toString()))
				|| (move.fromRow == move.toRow && move.fromColumn == move.toColumn));
	}
}
