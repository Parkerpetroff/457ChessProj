package mainmenu;

import p2p.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Panel displayed as the join screen.
 * @author Randy
 */
public class JoinGUI extends JPanel implements ActionListener {

    /** Message for screen.*/
    private JLabel messageLabel;

    /** Message for IP. */
    private JLabel ipLabel;

    /** Message for port number. */
    private JLabel portLabel;

    /** Text field for IP. */
    private JTextField ipField;

    /** Text field for port number. */
    private JTextField portField;

    /** Button to connect */
    private JButton connectButton;

    /** String for IP address */
    private String IP;

    /** Integer for port number. */
    private int PortNum;

    /** Constraints for GridBag formating. */
    private GridBagConstraints con;

    /**
     * Creates the help panel with buttons.
     */
    public JoinGUI() {
        setLayout(new GridBagLayout());
        setLayout(new GridBagLayout());
        con = new GridBagConstraints();
        addLabels();
        addTextFields();
        addButton();
    }

    /**
     * Add labels to the panel.
     */
    public void addLabels() {

        messageLabel = new JLabel("Enter Host's information");
        ipLabel = new JLabel("IP:");
        portLabel = new JLabel("Port #:");

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
        con.gridwidth = 1;
        add(ipLabel, con);
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 1;
        add(portLabel, con);
        con.fill = GridBagConstraints.HORIZONTAL;
    }

    private void addTextFields() {

        ipField = new JTextField();
        portField = new JTextField();

        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.HORIZONTAL;
        add(ipField, con);
        con.gridx = 1;
        con.gridy = 2;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.HORIZONTAL;
        add(portField, con);
    }

    private void addButton() {
        connectButton = new JButton("Connect");
        connectButton.setFont(new Font("Arial", Font.BOLD, 30));
        con.gridx = 1;
        con.gridy = 3;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.HORIZONTAL;
        connectButton.addActionListener(this);
        add(connectButton, con);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == connectButton) {
            try {
                IP = ipField.getText();
                PortNum = Integer.parseInt(portField.getText());
                new Client(IP,PortNum);
                System.out.println("CONNECT" + " " + IP + " " + PortNum);

            } catch (Exception error) {
                System.out.println("Error with input");
            }
        }
    }

}