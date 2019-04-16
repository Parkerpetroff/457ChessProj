package chess;

import p2p.Client;
import p2p.Server;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * JPanel for the chess game.
 * @author Parker
 *
 */
public class View extends JPanel {
	/**
	 * View ID.
	 */
	private static final long serialVersionUID = -2223695223616731728L;
	/**
	 * The model for the chess game.
	 */
	private Model model;
	/**
	 * The board for the game.
	 */
	private JButton[][] board;
	/**
	 * The current move.
	 */
	private Move move;
	/**
	 * Menu item to create new game.
	 */
	private JMenuItem newGame;
    /**
     * Menu item to quit game.
     */
	private JMenuItem quitGame;
	/**
	 * All of the chess piece icons.
	 */
	private ImageIcon pawnIconW, pawnIconB, rookIconW, 
	rookIconB, knightIconW, knightIconB, bishopIconW, bishopIconB,
	queenIconW, queenIconB, kingIconW, kingIconB;
	/**
	 * Brown used for background tiles.
	 */
	private static final Color BROWN = new Color(205, 133, 63);
	/**
	 * Tan used for background tiles.
	 */
	private static final Color TAN = new Color(210, 180, 140);
	/**
	 * Player names.
	 */
	private String name1, name2;

	Server server;
	Client client;

	//will likely be deleted in favor of master UI in release 2
	//add new game/quit function in menu options
	/**
	 * Creates the JFrame and chess panel.
	 * @param args for main
	 */
	public static void main(final String[] args) {
		JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitGame;
        JMenuItem newGame;

        JFrame frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creates and adds menu options to file menu which is then added to menu bar
        fileMenu = new JMenu("File");
        quitGame = new JMenuItem("Quit");
        newGame = new JMenuItem("New Game");

        fileMenu.add(newGame);
        fileMenu.add(quitGame);
        menus = new JMenuBar();
        frame.setJMenuBar(menus);
        menus.add(fileMenu);
		
//		frame.add(new View(quitGame, newGame, "Player 1", "Player 2"));
		frame.add(new View(args[0]));
	    frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	/**
	 * Creates the board and all pieces/icons.
	 * 
	 * @param pquitGame quit game menu item.
	 * @param pnewGame new game menu item
	 * @param pName1 player 1's name.
	 * @param pName2 player 2's name.
	 */
	/*public View(final JMenuItem pquitGame, final JMenuItem pnewGame, final String pName1, final String pName2) {
		TODO
		public View(final JMenuItem pquitGame, final JMenuItem pnewGame, Socket sock) {
		if (sock == server) model = new Model("WHITE");
		else if (sock == client) model = new Model("BLACK");


		board = new JButton[8][8];
		move = new Move();
		newGame = pnewGame;
        quitGame = pquitGame;
		loadIcons();
		setLayout(new GridLayout(8, 8));
		createBoard();
		updateBoard();

		if (sock == client) {
			eMove = sock.receive(move);
			model.move(eMove);
		or
			model.move(sock.receive(move));
		}

		name1 = pName1;
		name2 = pName2;
		model = new Model(name1, name2);
		board = new JButton[8][8];
		move = new Move();
		newGame = pnewGame;
        quitGame = pquitGame;
		loadIcons();

		setLayout(new GridLayout(8, 8));
		createBoard();
		updateBoard();
	}*/

	public View(String sock) {
		if (sock.equals("Server")) model = new Model("WHITE");
		else if (sock.equals("Client")) model = new Model("BLACK");


		board = new JButton[8][8];
		move = new Move();
		loadIcons();
		setLayout(new GridLayout(8, 8));
		createBoard();
		updateBoard();

//		if (sock == client) {
//			eMove = sock.receive(move);
//			model.move(eMove);
//			// or
//			model.move(sock.receive(move));
//		}
	}

	public View(Server sock) {
		server = sock;
		model = new Model("WHITE");

		board = new JButton[8][8];
		move = new Move();
		loadIcons();
		setLayout(new GridLayout(8, 8));
		createBoard();
		updateBoard();
	}

	public View(Client sock) {
		client = sock;
		model = new Model("BLACK");

		board = new JButton[8][8];
		move = new Move();
		loadIcons();
		setLayout(new GridLayout(8, 8));
		createBoard();
		updateBoard();
		try {
			model.move(client.receive());
		} catch (Exception e) {
			System.out.print("Could Not Receive Move");
		}
	}



	/**
	 * Loads images to chess pieces
	 */
	private void loadIcons() {
		pawnIconW = new ImageIcon("src/chess/pawnIconW.png");
		pawnIconB = new ImageIcon("src/chess/pawnIconB.png");
		rookIconW = new ImageIcon("src/chess/rookIconW.png");
		rookIconB = new ImageIcon("src/chess/rookIconB.png");
		knightIconW = new ImageIcon("src/chess/knightIconW.png");
		knightIconB = new ImageIcon("src/chess/KnightIconB.png");
		bishopIconW = new ImageIcon("src/chess/bishopIconW.png");
		bishopIconB = new ImageIcon("src/chess/bishopIconB.png");
		queenIconW = new ImageIcon("src/chess/queenIconW.png");
		queenIconB = new ImageIcon("src/chess/queenIconB.png");
		kingIconW = new ImageIcon("src/chess/kingIconW.png");
		kingIconB = new ImageIcon("src/chess/kingIconB.png");
	}

	/**
	 * Creates all tiles on board and adds button listener
	 */
	private void createBoard() {
		ButtonListener listener = new ButtonListener();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col] = new JButton();
				if ((row + col) % 2 == 0) {
	                board[row][col].setBackground(BROWN);
	            } else {
	                board[row][col].setBackground(TAN);
	            }
				board[row][col].addActionListener(listener);
				add(board[row][col]);
			}
		}

		//add listeners to menu items
		quitGame.addActionListener(listener);
		newGame.addActionListener(listener);
	}

	/**
	 * Listener for board buttons.
	 * @author Alec
	 *
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Respond to button clicks.
		 * @param event what was pressed.
		 */
		public void actionPerformed(final ActionEvent event) {
			Object source = event.getSource();
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (board[row][col] == source) {
						if (move.fromRow == -1 && model.pieceAt(row, col) != null
							&& model.pieceAt(row, col).team().
							equals(model.currentPlayer().team())) {
							System.out.print("Piece selected: " 
							+ model.pieceAt(row, col).team() + " ");
							System.out.println(model.pieceAt(row, col).type());
							move.fromRow = row;
							move.fromColumn = col;
						} else if (move.fromRow != -1) {
							move.toRow = row;
							move.toColumn = col;
							System.out.println(move);
							if (move.toRow == move.fromRow && move.toColumn 
									== move.fromColumn) {
								move = new Move();
							} else if (model.isValidMove(move)) {
								model.move(move);
								// TODO sock.send(move);
								move = new Move();
								System.out.println("Valid " + move);
								if (model.isWinner()) {
									// break;
								} // TODO else {sock.receive(move);}

							} else {
								JOptionPane.showMessageDialog(null,
										"This is not a valid move");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"This is not a valid selection");
						}
					}
				}
			}

			if (model.isWinner()) {
				model.wipeBoard();
				JOptionPane.showMessageDialog(null,
						model.currentPlayer().name()
								+ " has won!");
			}

			//reset model if new game option is selected
            if (source == newGame) {    
            	model = new Model("White");
            }
            
            //quit game if menu option was selected
            if (source == quitGame) {
                //either exit to main menu or exit jvm completely
            	System.exit(0);
            }
			updateBoard();
		}
	}
	
	/**
	 * Update the look of the board after a move.
	 */
	private void updateBoard() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (model.pieceAt(row, col) != null && model.pieceAt(row, col).team() == "BLACK") {
					switch (model.pieceAt(row, col).type()) {
					case "Pawn":
						board[row][col].setIcon(pawnIconB);
						break;
					case "Rook":
						board[row][col].setIcon(rookIconB);
						break;
					case "Knight":
						board[row][col].setIcon(knightIconB);
						break;
					case "Bishop":
						board[row][col].setIcon(bishopIconB);
						break;
					case "Queen":
						board[row][col].setIcon(queenIconB);
						break;
					case "King":
						board[row][col].setIcon(kingIconB);
						break;
					default:
						break;
					}
				} else if (model.pieceAt(row, col) != null 
						&& model.pieceAt(row, col).team() == "WHITE") {
					switch (model.pieceAt(row, col).type()) {
					case "Pawn":
						board[row][col].setIcon(pawnIconW);
						break;
					case "Rook":
						board[row][col].setIcon(rookIconW);
						break;
					case "Knight":
						board[row][col].setIcon(knightIconW);
						break;
					case "Bishop":
						board[row][col].setIcon(bishopIconW);
						break;
					case "Queen":
						board[row][col].setIcon(queenIconW);
						break;
					case "King":
						board[row][col].setIcon(kingIconW);
						break;
					default:
						break;
					}
				} else {
					board[row][col].setIcon(null);
				}
			}
		}		
	}
}
