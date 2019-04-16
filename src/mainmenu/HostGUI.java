package mainmenu;

import chess.Move;
import p2p.Server;

import java.awt.*;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

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

    private String MyIP;


    /**
     * Creates the help panel with buttons.
     */
    public HostGUI() {
        setLayout(new GridBagLayout());
        setLayout(new GridBagLayout());
        con = new GridBagConstraints();
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("System IP Address : " +
                    (localhost.getHostAddress()).trim());
            MyIP = localhost.getHostAddress().trim();
        }catch(Exception e) {
            System.out.println("error");
        }

      addLabels();
    }

    /**
     * Add labels to the panel.
     */
    public void addLabels() {

        messageLabel = new JLabel("Give this to your friend!");
        ipLabel = new JLabel("IP Address: " + MyIP);
        portLabel = new JLabel("Port #: 2222");
        try {

        }catch(Exception e){

        }

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

//        try {
//            String[] args = {"9000"};
//            Server.main(args);
//        } catch (IOException error) {
//            System.out.print("Server Creation Error");
//        }
    }
}
