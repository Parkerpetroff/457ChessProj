package chess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 * GUI for chess panel. TO be called by main menu.
 * @author Parker
 *
 */
public class ClientChessGUI extends JFrame implements ActionListener {
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
	public ClientChessGUI(final String name1, final String name2, final boolean isHost, final Socket echoSocket) {
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
		n1 = name1;
		n2 = name2;
        setTitle("Chess");

        add(new ClientView(quitGame, newGame, name1, name2));

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
