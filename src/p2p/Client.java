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
    PrintWriter outStream;
    BufferedReader inStream;
    String inputLine;

    InputStreamReader isr;

    public Client(String hostName, int portNumber) throws Exception {
        echoSocket = new Socket(hostName, portNumber);
        outStream = new PrintWriter(echoSocket.getOutputStream(), true);
        inStream = new BufferedReader(
                new InputStreamReader(echoSocket.getInputStream()));
        new chess.ChessGUI(null, this);
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
            Move move = new Move();
            String[] strArr = inStream.readLine().split(":", 4);
            move.fromRow = Integer.parseInt(strArr[0]);
            move.fromColumn = Integer.parseInt(strArr[1]);
            move.toRow = Integer.parseInt(strArr[2]);
            move.toColumn = Integer.parseInt(strArr[3]);
            return move;
        } catch (Exception e) {
            System.out.print("Receive Move Error");
        }
        return null;
    }

    public void close() throws Exception {
        // close IO streams, then socket
        System.err.println("Closing connection with client");
        echoSocket.close();
    }
}