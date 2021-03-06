package chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * JPanel for the chess game.
 *
 * @author Parker
 */
public class View extends JPanel implements Runnable {
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
    private boolean isTurn;

    private Socket comms;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private waitForTurn w;
    private Thread t;

    @Override
    public void run() {

    }

    /**
     * Creates the board and all pieces/icons.
     *
     * @param pquitGame quit game menu item.
     * @param pnewGame  new game menu item
     * @param pName1    player 1's name.
     * @param pName2    player 2's name.
     */
    public View(final JMenuItem pquitGame, final JMenuItem pnewGame, final String pName1, final String pName2, boolean isHost, final Socket comms) {
        name1 = pName1;
        name2 = pName2;
        model = new Model(name1, name2);
        board = new JButton[8][8];
        move = new Move();
        newGame = pnewGame;
        quitGame = pquitGame;
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
        //host will go first
        isTurn = isHost;
        setLayout(new GridLayout(8, 8));
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
        this.comms = comms;
        try {
            outStream = new ObjectOutputStream(comms.getOutputStream());
            inStream = new ObjectInputStream(comms.getInputStream());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        updateBoard();
        try {
            w = new waitForTurn();
            w.start();
        } catch (Exception ex) {

        }
    }

    private class waitForTurn implements Runnable {
        public void waitTurn() {
            try {
                while (inStream.available() != 0) {

                }
                Move oMove = (Move) inStream.readObject();

                if (oMove == null) {
                    System.out.println(oMove.toString());
                } else {
                    System.out.println(oMove.toString());
                    model.move(oMove);
                    System.out.println("Valid " + oMove);
                    if (model.isWinner()) {
                        model.wipeBoard();
                        JOptionPane.showMessageDialog(null,
                                model.currentPlayer().name()
                                        + " has won!");
                    }
                    updateBoard();
                    model.nextTurn();
                    isTurn = true;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                waitTurn();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        public void start() {
            t = new Thread(this);
            t.start();
        }
    }

    /**
     * Listener for board buttons.
     *
     * @author Alec
     */
    private class ButtonListener implements ActionListener {
        /**
         * Respond to button clicks.
         *
         * @param event what was pressed.
         */
        public void actionPerformed(final ActionEvent event) {
            boolean success = false;
            Object source = event.getSource();
            if (isTurn) {
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
                                    try {
                                        outStream.reset();
                                        outStream.writeObject(move);
                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    success = true;
                                    move = new Move();
                                    System.out.println("Valid " + move);
                                    if (model.isWinner()) {
                                        model.wipeBoard();
                                        JOptionPane.showMessageDialog(null,
                                                model.currentPlayer().name()
                                                        + " has won!");

                                    }
                                    model.nextTurn();
                                    isTurn = false;
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
            } else {
                JOptionPane.showMessageDialog(null,
                        "Not your turn");
            }
            //reset model if new game option is selected
            if (source == newGame) {
                //leave for now
                //model = new Model(name1, name2, isHost);
            }

            //quit game if menu option was selected
            if (source == quitGame) {
                //either exit to main menu or exit jvm completely
                System.exit(0);
            }
            updateBoard();
            if (success) {
                try {
                    w = new waitForTurn();
                    w.start();

                } catch (Exception ex) {

                }

            }

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
