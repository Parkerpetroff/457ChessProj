package p2p;

/**********************************************************************
 * Filename: Client
 *
 * Authors: Alec Betancourt, Parker Petroff, and Randy Nguyen
 **********************************************************************/

import java.io.*;
import java.net.*;

import chess.Move;

public class Client {

	Socket echoSocket;
	PrintWriter out;
	BufferedReader in;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	
    public Client(String hostName, int portNumber) throws Exception {
    	echoSocket = new Socket(hostName, portNumber);
        new chess.ChessGUI("Player 1", "Player 2",false,echoSocket);
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
        echoSocket.close();
    }
}