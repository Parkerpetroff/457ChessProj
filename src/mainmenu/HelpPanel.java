package mainmenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel displayed as the help screen.
 * @author Parker
 *
 */
public class HelpPanel extends JPanel implements ActionListener {
	
	/**
	 * ID for help Panel.
	 */
	private static final long serialVersionUID = -6980233742847123020L;
	/**
	 * Message for top of the screen.
	 */
	private JLabel message;
	/**
	 * Button for chess help screen.
	 */
	private JButton chess;
	/**
	 * Button to exit the help screen.
	 */
	private JButton exit;
	/**
	 * Whether or not the user wants to exit.
	 */
	private boolean exitStatus;
	/**
	 * Icon for help.
	 */
	private ImageIcon helpMenu;

	/**
	 * Constraints for GridBag formating.
	 */
	private GridBagConstraints con;
	
	/**
	 * Creates the help panel with buttons.
	 * @param chessLogo logo for chess button
	 * @param ret Jbutton to return to main menu.
	 */
	public HelpPanel(ImageIcon chessLogo, final JButton ret) {
		setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		exit = ret;
		addButtons();

		chess.setIcon(chessLogo);
		exitStatus = false;
	}
	/**
	 * Get he exit status.
	 * @return True if exit is pressed, false if nt
	 */
	public boolean getExit() {
		return exitStatus;
	}
	
	/**
	 * Add buttons to the panel.
	 */
	public void addButtons() {
		helpMenu = new ImageIcon("src/MainMenu/helpMenuLogo.png");
		message = new JLabel();
		message.setIcon(helpMenu);
		
		con.gridx = 0;
		con.gridy = 0;
		con.gridwidth = 2;
		add(message, con);
		con.fill = GridBagConstraints.HORIZONTAL;
		
		con.gridwidth = 1;
		con.gridy++;

		chess = new JButton();
		chess.addActionListener(this);
		add(chess, con);

		con.gridy++;
		con.gridwidth = 2;
		add(exit, con);
	}

	/**
	 * Remove the buttons from the panel.
	 */
	public void removeButtons() {
		remove(message);
		remove(chess);
		revalidate();
		repaint();
	}
	
	/**
	 * Add the help panel for the corresponding game.
	 * @param str the name of the game user wants help on.
	 */
	public void addHelp(final String str) {
		con.gridx = 0;
		con.gridy = 0;
		if (str.equals("chess")) {
			JLabel h = new JLabel();
			h.setIcon(chess.getIcon());				
			add(h, con);
			con.gridy++;
		}
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == chess) {
			removeButtons();
			addHelp("chess");
		}
	}
}
