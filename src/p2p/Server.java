package p2p;

/**********************************************************************
 * Filename: Server

 *
 * Authors: Alec Betancourt, Parker Petroff, and Randy Nguyen
 **********************************************************************/

import java.io.*;
import java.net.*;
import chess.Move;

public class Server {
	int port;
	ServerSocket serverSocket;
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	String inputLine;
	
    public Server() throws Exception {
    	// create socket
        port = 2222;
        serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);
        
        clientSocket = serverSocket.accept();
        System.err.println("Accepted connection from client");

        // open up IO streams
    	outStream = new ObjectOutputStream(clientSocket.getOutputStream());
    	inStream = new ObjectInputStream(clientSocket.getInputStream());
    	
        // repeatedly wait for connections, and process
        /*
        while (true) {
            // a "blocking" call which waits until a connection is requested
            clientSocket = serverSocket.accept();
            System.err.println("Accepted connection from client");

            // open up IO streams
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	
        	outStream = new ObjectOutputStream(clientSocket.getOutputStream());
        	inStream = new ObjectInputStream(clientSocket.getInputStream());
        	
            // waits for data and reads it in until connection dies
            // readLine() blocks until the server receives a new line from client
            while ((inputLine = in.readLine()) != null) {
            	System.out.println("Received message: " + inputLine + " from " + clientSocket.toString());
        	    out.println(inputLine);
            }
        }
        */
    }
    
    public void send(Move move) throws Exception {
    	outStream.writeObject(move);
    }
    
    public Move receive() throws Exception {
    	return (Move) inStream.readObject();
    }
    
    public void close() throws Exception {
    	// close IO streams, then socket
        System.err.println("Closing connection with client");
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    
}
