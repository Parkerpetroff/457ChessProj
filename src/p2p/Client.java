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
	int port;
	Socket echoSocket;
	PrintWriter out;
	BufferedReader in;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	String inputLine;
	
    public Client(String hostName, int portNumber) throws Exception {
    	echoSocket = new Socket(hostName, portNumber);
    	outStream = new ObjectOutputStream(echoSocket.getOutputStream());
    	inStream = new ObjectInputStream(echoSocket.getInputStream());
    	/*
    	try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
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
        echoSocket.close();
    }
}