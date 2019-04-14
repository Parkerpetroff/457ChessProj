/**********************************************************************
 * Filename: Client
 *
 * Authors: Alec Betancourt, Parker Petroff, and Randy Nguyen
 **********************************************************************/

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private String hostName;
    private int portNumber;
    private PrintWriter out;
    private BufferedReader in;
    private FileOutputStream fileOut;
    private PrintWriter fileWrite;
    private boolean exists;
    private int status;
    private DataInputStream din;
    private String UserName;

    public Client() {
        // Something goes here?

    }

    public void setUserName(String s) {
        UserName = s;
    }

    public int connect(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        // Create a socket that connects to the above host and port number
        try {
            socket = new Socket(this.hostName, this.portNumber);
            // Create a PrintWriter from the socket's output stream
            // Use the autoFlush option
            out = new PrintWriter(socket.getOutputStream(), true);
            // Create a BufferedReader from the socket's input stream
            //https://stackoverflow.com/questions/48266026/socket-java-client-python-server
            din = new DataInputStream(socket.getInputStream());
            out.println("CONNECT " + hostName + " " + portNumber + " " + UserName);
            String s = din.readUTF();
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("error");
            return -1;
        }
        return 0;
    }

    public int list() {
        try {
            out.println("list");
            String s = null;
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("No connection");
            return -1;
        }
        return 0;
    }

    public int send (String com) {
        try {
            out.println(com);
            System.out.println("Sent Command: " + com);
            String s = din.readUTF();
            while(din.readUTF()!=null) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println("Send Error");
            return -1;
        }
        return 0;
    }

    public int close() {
        try {
            out.print("QUIT");
            out.close();
            socket.close();
            return 0;
        } catch (IOException e) {
            System.out.println("No connection to close");
            return -1;
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Scanner scnr = new Scanner(System.in);

        int status;

        //System.out.print("Enter your username: ");
        //String name = scnr.nextLine();
        // TODO Remove fixed name
        client.setUserName("RANDY");

        while (true) {

            String cmd = scnr.nextLine();
            String[] commands = cmd.split(" ");

            if (commands.length < 1 || commands.length > 3)
                System.out.println("Invalid arguments");

            else {

                String command = commands[0].toUpperCase();
                switch (command) {
                    case "CONNECT":
                        if (commands.length != 3)
                            System.out.println("Invalid arguments");
                        else {
                            try {
                                int pNum = Integer.parseInt(commands[2]);
                                status = client.connect(commands[1], pNum);
                                if (status == 0)
                                    System.out.println("Connection Successful");
                            } catch (Exception e) {
                                System.out.print("Could not convert port to number");
                            }
                        }
                        break;

                    case "LIST":
                        client.list();
                        break;

                    case "QUIT":
                        status = client.close();
                        break;

                    case "HELP":
                        System.out.println("--- HELP MENU ---\n");
                        System.out.println("CONNECT <server name/IP address> <server port>");
                        System.out.println("LIST");
                        System.out.println("QUIT\n");
                        break;

                    default:
                        client.send(command);
                }
            }
        }
    }
}

