package chess;

/**
 * The model for the chess game. All moves are executed through 
 * this class.
 * @author Alec
 *
 */
public class Model {
	/**
	 * The chess board.
	 */
	private Piece[][] board;
	/** 
	 * Player 1.
	 */
	private Player player1;
	/**
	 * Player 2.
	 */
	private Player player2;
	/**
	 * The current player, who's turn it is.
	 */
	private Player currentPlayer;
	
	/**
	 * Model constructor. Creates the pieces and board.
	 * @param name1 player 1's name.
	 * @param name2 player 2's name.
	 */
	public Model(final String name1, final String name2) {
		board = new Piece[8][8];
		
		player1 = new Player(name1, Team.WHITE);
		player2 = new Player(name2, Team.BLACK);
		currentPlayer = player1;
		
		board[0][0] = new Rook(Team.BLACK);
		board[0][1] = new Knight(Team.BLACK);
		board[0][2] = new Bishop(Team.BLACK);
		board[0][3] = new Queen(Team.BLACK);
		board[0][4] = new King(Team.BLACK);
		board[0][5] = new Bishop(Team.BLACK);
		board[0][6] = new Knight(Team.BLACK);
		board[0][7] = new Rook(Team.BLACK);
		board[7][0] = new Rook(Team.WHITE);
		board[7][1] = new Knight(Team.WHITE);
		board[7][2] = new Bishop(Team.WHITE);
		board[7][3] = new King(Team.WHITE);
		board[7][4] = new Queen(Team.WHITE);
		board[7][5] = new Bishop(Team.WHITE);
		board[7][6] = new Knight(Team.WHITE);
		board[7][7] = new Rook(Team.WHITE);
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(Team.BLACK);
			board[6][i] = new Pawn(Team.WHITE);
		}
	}
	
	/**
	 * Gets the current player, who's turn it is.
	 * @return the current player
	 */
	public Player currentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Determines and switches to the next player.
	 */
	public void nextTurn() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
	}
	
	/**
	 * Clears the board.
	 */
	public void wipeBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
	}
	
	/**
	 * Moves the chess piece.
	 * @param move the move the piece will make
	 */
	public void move(final Move move) {
		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
	}

	/**
	 * Determines if a move is valid.
	 * @param move the move the piece made
	 * @return True if the movie is valid, false if not
	 */
	public boolean isValidMove(final Move move) {
		return board[move.fromRow][move.fromColumn].isValidMove(move, board);
	}
	
	/**
	 * Get the piece at a given location.
	 * @param row the piece's row
	 * @param column the piece's column
	 * @return the piece at the row,column or null if none
	 */
	public Piece pieceAt(final int row, final int column) {
		if (board[row][column] == null) {
			return null;
		} else {
			return board[row][column];
		}
	}
	
	/**
	 * Determines if the game has been won.
	 * @return True if it has been won, false if not
	 */
	public boolean isWinner() {
		boolean black = false;
		boolean white = false;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] != null && board[row][col].team() == "BLACK" 
						&& board[row][col].type() == "King") {
					black = true;
				}
				if (board[row][col] != null && board[row][col].team() == "WHITE" 
						&& board[row][col].type() == "King") {
					white = true;
				}
			}
		}
		return !(black && white);
	}
}
