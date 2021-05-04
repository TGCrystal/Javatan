package edu.rpi.cs.csci4960.s21.javatan;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Stores client information for future use
 *
 * @author Chuanfeng Xiong
 * @author Chris Lamberston
 * @author Ruben McWilliams
 * @author Trevor Crystal
 */
public class ClientInfo {

    private Player player;
    private Socket client;
    private ObjectOutputStream out;

    /**
    * Gets the player associated with this client
    *
    * @return the player associated with this client
    */
    public Player getPlayer() {
        return player;
    }

    /**
    * Sets the player associated with this client
    *
    * @param player the player to associate with this client
    */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
    * Gets the client associated with this client
    *
    * @return the client associated with this client
    */
    public Socket getClient() {
        return client;
    }

    /**
    * Sets the client associated with this client
    *
    * @param client the client to associate with this client
    */
    public void setClient(Socket client) {
        this.client = client;
    }

    /**
    * Gets the output stream associated with this client
    *
    * @return the output stream associated with this client
    */
    public ObjectOutputStream getOut() {
        return out;
    }

    /**
    * Sets the output stream associated with this client
    *
    * @param out the output stream to associate with this client
    */
    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
}
