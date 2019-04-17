package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * GUI for chess panel. TO be called by main menu.
 * @author Parker
 *
 */
public class ChessGUI extends JFrame implements ActionListener {
	/**
	 * Chess gui id.
	 */
	private static final long serialVersionUID = -8253661587267331327L;
	/**
	 * Item for returning to main menu.
	 */
	private JMenuItem mainMenu;
	/**
	 * Strings for player names.
	 */
	private String n1, n2;
	/**
	 * Construtor for chess frame.
	 * @param name1 player 1's name.
	 * @param name2 player 2's name.
	 */
	public ChessGUI(final String name1, final String name2, final boolean isHost, final Socket commsSock ) {
		JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitGame;
        JMenuItem newGame;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creates and adds menu options to file menu which is then added to menu bar
        fileMenu = new JMenu("File");
        quitGame = new JMenuItem("Quit");
        newGame = new JMenuItem("New Game");
        mainMenu = new JMenuItem("Main Menu");

        fileMenu.add(newGame);
        fileMenu.add(mainMenu);
        fileMenu.add(quitGame);
        mainMenu.addActionListener(this);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);
		n1 = name1;
		n2 = name2;

		if (isHost)
            setTitle("Chess Host (White)");
		else
		    setTitle("Chess Client (Black)");

        add(new View(quitGame, newGame, name1, name2, isHost, commsSock));

	    pack();
		setSize(800, 800);
		setVisible(true);
	}

	@Override
	public void actionPerformed(final ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == mainMenu) {
			dispose();
			new mainmenu.MenuGUI(n1, n2);
		}
	}
}
