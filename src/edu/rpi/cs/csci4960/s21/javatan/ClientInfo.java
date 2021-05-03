package edu.rpi.cs.csci4960.s21.javatan;


/**
 * Storing client information for future use
 */

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientInfo {

    private Player player;
    private Socket client;
    private ObjectOutputStream out;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
}
