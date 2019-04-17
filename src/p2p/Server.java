package p2p;

/**********************************************************************
 * Filename: Server
 *
 * Authors: Alec Betancourt, Parker Petroff, and Randy Nguyen
 **********************************************************************/

import java.io.*;
import java.net.*;

import chess.Move;

public class Server implements Runnable {
    int port;
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    ObjectOutputStream outStream;
    ObjectInputStream inStream;
    Thread server;

    public void createServer() throws Exception {

        // create socket
        port = 2222;
        serverSocket = new ServerSocket(port);
        System.err.println("Started server on port " + port);

        clientSocket = serverSocket.accept();
        System.err.println("Accepted connection from client");
        new chess.ChessGUI("Player 1", "Player 2", true, clientSocket);

    }

    @Override
    public void run() {
        try {
            createServer();
        } catch (Exception ex) {
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
