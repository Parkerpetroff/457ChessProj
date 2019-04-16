package p2p;

/**********************************************************************
 * Filename: Server

 *
 * Authors: Alec Betancourt, Parker Petroff, and Randy Nguyen
 **********************************************************************/

import java.io.*;
import java.net.*;
import chess.Move;

public class Server implements Runnable{
	int port;
	ServerSocket serverSocket;
	Socket clientSocket;
    PrintWriter outStream;
    BufferedReader inStream;
	String inputLine;
	Thread server;

   public void createServer() throws Exception {

    	// create socket
        port = 2222;
        serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);
        
        clientSocket = serverSocket.accept();
        System.err.println("Accepted connection from client");

        // open up IO streams
    	outStream = new PrintWriter(clientSocket.getOutputStream(), true);
    	inStream = new BufferedReader(
                   new InputStreamReader(clientSocket.getInputStream()));
    	new chess.ChessGUI(this, null);
    }
    @Override
    public void run(){
       try {
           createServer();
       }
       catch (Exception ex){
           System.out.println(ex.getMessage());
       }
    }
    public void start() {
        System.out.println("Thread started");
        if (server == null) {
            server = new Thread(this);
            server.start();
        }
    }
    
    public void send(Move move) {
       try {
           outStream.println(move.toString());
       } catch (Exception e) {
           System.out.print("Send Move Error");
       }
    }
    
    public Move receive() {
       try {
           //return inStream.readLine();
           Move move = new Move();
           String[] strArr = inStream.readLine().split(":", 4);
           move.fromRow = Integer.parseInt(strArr[0]);
           move.fromColumn = Integer.parseInt(strArr[1]);
           move.toRow = Integer.parseInt(strArr[2]);
           move.toColumn = Integer.parseInt(strArr[3]);
       } catch (Exception e) {
           System.out.print("Receive Move Error");
       }
       return null;
    }
    
    public void close() throws Exception {
    	// close IO streams, then socket
        System.err.println("Closing connection with client");
        clientSocket.close();
        serverSocket.close();
    }
    
}
