package mainmenu;

import p2p.Server;
import p2p.Client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main menu class.
 * @author Parker and Randy
 */
public class MenuGUI extends JFrame implements ActionListener, Runnable {

	/** Menu ID. */
	private static final long serialVersionUID = 8921269292088829426L;

	/** Screen. */
	private JPanel screen;

	/** button for hosting game. */
	private JButton hostButton;

	/** button for joining game. */
	private JButton joinButton;

	/** button for exiting. */
	private JButton exit;

	/** constraints for gridbag formatting. */
	private GridBagConstraints con;

	/** Player names. */
	private String name1, name2;

	/** Panel for Host */
	private HostGUI host;

	/** Panel for Join */
	private JoinGUI join;
	/**
	 * Menu constructor.
	 * @param n1 name for player 1.
	 * @param n2 name for player 2.
	 */
	public MenuGUI(final String n1, final String n2) {
		screen = new JPanel();
		screen.setLayout(new GridBagLayout());
		con = new GridBagConstraints();

		name1 = n1;
		name2 = n2;
		con.gridx = 0;
		con.gridy = 0;
		con.ipady = 100;
		con.gridwidth = 2;
		addButtons();
		add(screen);
		setTitle("P2P Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,800);
		setVisible(true);
	}

	/**
	 * Add the buttons to the screen.
	 */
	private void addButtons() {
		con.ipadx = 0;
		con.ipady = 0;
		con.gridwidth = 1;
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridy++;

		con.gridx = 0;
		hostButton = new JButton("Host Game");
		hostButton.addActionListener(this);
		hostButton.setFont(new Font("Arial", Font.BOLD, 60));
		screen.add(hostButton, con);
		
		con.gridx++;
		joinButton = new JButton("Join Game");
		joinButton.addActionListener(this);
		joinButton.setFont(new Font("Arial", Font.BOLD, 60));
		screen.add(joinButton, con);

		con.gridy++;
		con.gridx = 0;
		con.gridwidth = 2;
		exit = new JButton("Exit");
		exit.setFont(new Font("Arial", Font.BOLD, 60));
		exit.addActionListener(this);
		screen.add(exit, con);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == exit) {
			dispose();
		}

		if (e.getSource() == hostButton) {
			this.remove(screen);
			host = new HostGUI();
			add(host);
			revalidate();
			repaint();
		try{
			Server s = new Server();
			s.start();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
			//new chess.ChessGUI(name1, name2);

		}
		
		if (e.getSource() == joinButton) {
			this.remove(screen);
			join = new JoinGUI();
			add(join);
			revalidate();
			repaint();
		}
	}
	@Override
	public void run(){

	}
	/**
	 * drive for GUI().
	 * @param args for main
	 */
	public static void main(final String[] args) {
		new MenuGUI("Player 1 ", "Player 2");
	}
}


