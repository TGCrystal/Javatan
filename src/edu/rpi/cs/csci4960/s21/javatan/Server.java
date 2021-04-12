package edu.rpi.cs.csci4960.s21.javatan;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
public class Server {
    private int port;

    public void server() throws IOException {
        //Create a server
        System.out.println("Server start; wait for a client");
        PrintWriter out = null;
        Scanner getClient = null;
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(this.port);
            /**
             * start server
             */
            client = server.accept();
            System.out.println(client.getInetAddress() + " has been connected");

            out = new PrintWriter(client.getOutputStream());
            out.flush();
            getClient = new Scanner(client.getInputStream());

            while (getClient.hasNextLine()) {
                String returnMsg = null;
                String tmp = getClient.nextLine();
                if ("exit".equals(tmp)) {
                    //exit means game over
                    break;
                } else if ("start".equals(tmp)) {
                    /**
                     * new game started; initializes all game states
                     */
                } else {
                    int clientInput = Integer.parseInt(tmp);
                    /**
                     * the game middle output; used it to do some computations
                     */
                }
                out.println(returnMsg);
                out.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.close();
            getClient.close();
            server.close();
        }
    }
}
