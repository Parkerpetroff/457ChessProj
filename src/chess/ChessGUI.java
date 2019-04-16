package chess;

import p2p.Client;
import p2p.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	 * @param server server socket
	 * @param client client socket
	 */
	public ChessGUI(Server server, Client client) {
		JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitGame;
        JMenuItem newGame;


      //  JFrame frame = new JFrame("Chess");
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
        setTitle("Chess");
		add(new View(quitGame, newGame, server, client));
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
