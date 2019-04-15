package p2p;

/**********************************************************************
 * Filename: Server
 *
 * Authors: Alec Betancourt, Parker Petroff, and Randy Nguyen
 **********************************************************************/

import mainmenu.HostGUI;

import java.io.*;
import java.net.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws Exception {

        // create socket
        int port = 2222;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        // repeatedly wait for connections, and process
        while (true) {

            // a "blocking" call which waits until a connection is requested
            Socket clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

            // open up IO streams
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	
            // waits for data and reads it in until connection dies
            // readLine() blocks until the server receives a new line from client
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
        	    out.println(inputLine);
            }

            // close IO streams, then socket
            System.err.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();
        }
        //when you quit
        //serverSocket.close();
    }
}
