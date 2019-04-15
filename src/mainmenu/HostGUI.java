package mainmenu;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel displayed as the host screen.
 * @author Randy
 */
public class HostGUI extends JPanel{

    /** Message for screen.*/
    private JLabel messageLabel;

    /** Message for IP. */
    private JLabel ipLabel;

    /** Message for port number. */
    private JLabel portLabel;

    /** Constraints for GridBag formating. */
    private GridBagConstraints con;

    /**
     * Creates the help panel with buttons.
     */
    public HostGUI() {
        setLayout(new GridBagLayout());
        setLayout(new GridBagLayout());
        con = new GridBagConstraints();
        addLabels();
    }

    /**
     * Add labels to the panel.
     */
    public void addLabels() {

        messageLabel = new JLabel("Give this to your friend!");
        ipLabel = new JLabel("IP Addy Here!");
        portLabel = new JLabel("Port Num Here!");

        messageLabel.setFont(new Font("Arial", Font.BOLD, 30));
        ipLabel.setFont(new Font("Arial", Font.BOLD, 30));
        portLabel.setFont(new Font("Arial", Font.BOLD, 30));

        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        add(messageLabel, con);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 2;
        add(ipLabel, con);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 2;
        add(portLabel, con);
        con.fill = GridBagConstraints.HORIZONTAL;
    }
}
