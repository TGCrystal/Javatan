package edu.rpi.cs.csci4960.s21.javatan;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *Server receive connection request and store information of every client, then keep listening
 * and sending message(message.java) to communicate with client
 * See MessageReceiveListener.java for more detail
 *
 * @author Chuanfeng Xiong
 * @author Chris Lamberston
 * @author Ruben McWilliams
 * @author Trevor Crystal
 */
public class Server extends Thread {

    private GUI gui;
    private ServerSocket serverSocket = null;
    private Boolean stop = false;
    private Map<String, ClientInfo> clients;
    private Game game;
    //A new function to manage thread pool
    ExecutorService executorService;

    /**
    * The sole constructor for Server
    *
    * @param gui the gui to associate with this server instance
    * @param game the game instance to associate with this server instance
    */
    public Server(GUI gui, Game game) {
        this.gui = gui;
        this.game = game;
        executorService = Executors.newCachedThreadPool();
    }

    /**
    * Starts the server
    *
    * @param ip the ip for the server to use
    * @param port the port for the server to use
    */
    public void startServer(String ip, int port) {
        try {
            this.serverSocket = new ServerSocket(port, 0, InetAddress.getByName(ip));
            executorService.execute(this);
        } catch (IOException e) {
            // this.gui.writeOutputMessage(" - The Server could not be opened!");
            return;
        }
    }

    /**
    * Run the run
    */
    public void run() {
        clients = new HashMap<>();
        int count = 0;
        // Enter the main loops
        while (!stop) {
            if (count > 3) {
                continue;
            }
            // Get four client trying to connect
            try {
                System.out.println("Server start; wait for a client");
                //Wait for connection
                Socket client = serverSocket.accept();
                //Get outputaStream when connection is done
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                /**
                 * Get information of clients and keep them
                 */
                Player player = new Player(game.addPlayer());
                ClientInfo clientInfo = new ClientInfo();
                clientInfo.setPlayer(player);
                clientInfo.setClient(client);
                clientInfo.setOut(out);
                clients.put(player.getPlayerColor().toString(), clientInfo);

                //Outprint
                String outputMessage = " - client " + client.getInetAddress() + ":" + client.getPort() + " join the game!";
                // this.gui.writeOutputMessage(outputMessage);
                System.out.println(outputMessage);

                //Send color they are assigned to player
                Message message = new Message(player.getPlayerColor());
                message.setCurrentPlayer(game.getCurrentPlayer());
                message.setPlayer(player);
                message.setBoard(game.getBoard());
                out.writeObject(message);
                out.flush();

                //Start a new thread, which receive and unpack message sent from each client
                MessageReceiveListener receive = new MessageReceiveListener(this, clientInfo, this.gui, this.game);
                executorService.execute(receive);

                count++;
            } catch (IOException e) {
                System.out.println("Could not get a client.");
                // this.gui.writeOutputMessage(" - Could not open output stream!");
                return;
            }

        }
    }


    /**
     * Send out message to every client if no player color is specified
     * Otherwise just send it to a certain Client
     * @param msg message that needs to be sent to client
     * @return true if the message sends without error, false otherwise
     */
    public boolean
    sendMessage(Message msg) {
        if (msg.getPlayerColor() == PlayerColor.NONE) {
            Collection<ClientInfo> collection = clients.values();
            Iterator<ClientInfo> iterable = collection.iterator();
            while (iterable.hasNext()) {
                try {
                    iterable.next().getOut().writeObject(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            ClientInfo clientInfo = clients.get(msg.getPlayerColor().toString());
            try {
                clientInfo.getOut().writeObject(msg);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
    * Stops the server
    */
    public void stopServer() {
        this.stop = stop;
    }


}
