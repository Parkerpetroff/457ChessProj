package chess;

//import java.util.ArrayList;

/**
 * Class for the individual players.
 * @author Alec
 *
 */
public class Player {
	/**
	 * The player's name.
	 */
	private String name;
	/**
	 * The team the player is on.
	 */
	private Team team;
	/**
	 * The score.
	 */
	//release 2
	//private int score;
	/**
	 * What pieces the player has captured.
	 */
	//might not use
	//private ArrayList<Piece> capturedPieces = new ArrayList<Piece>();
	/**
	 * The last move the player made.
	 */
	//might not use
	//private Move move;
	
	/**
	 * Player constructor.
	 * @param name player's name
	 * @param team team the player is on.
	 */
	Player(final String name, final Team team) {
		this.name = name;
		this.team = team;
	}
	
	/**
	 * Get the player's name.
	 * @return player's name
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Get the player's team.
	 * @return name of team (black or white)
	 */
	public String team() {
		return team.toString();
	}
}
