package edu.rpi.cs.csci4960.s21.javatan;

import java.net.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* The client used to send and receive game info to and from the server
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Client extends Thread {
    private Socket socket = null;
    private Scanner getKey = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private Player player;
    private boolean stop;
    private Board localBoardCopy;
    private PlayerColor thisPlayerColor;
    ExecutorService executorService;
    private Boolean hasFreeSettlement = true;
    private Boolean hasFreeRoad = true;

    /**
    * Sole constructor for Client, sets the players color to BLUE and creates a new Board instance
    */
    public Client() {
        super();
        localBoardCopy = new Board(true);
        thisPlayerColor = PlayerColor.BLUE;
        player = new Player(thisPlayerColor, true);
    }

    /**
    * Attempts to connect to the server with the serverName and port stored by the class
    *
    * @param ip the ip of the server to connect to
    * @param port the port of the server to connect to
    */
    public void connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            Message message = (Message) in.readObject();
            player = message.getPlayer();
            localBoardCopy = message.getBoard();
            thisPlayerColor = player.getPlayerColor();
            executorService = Executors.newCachedThreadPool();
            executorService.execute(this);
            System.out.println("my color is " + playerColorToStringColor(player.getPlayerColor()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Gets the player associated with this client
    *
    * @return the player associated with this client
    */
    public Player getPlayer() {
        return player;
    }

    /**
    * Sets the player to associate with this client
    *
    * @param player the player to associate with this client
    */
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Message message = (Message) in.readObject();
                if (message != null) {
                    if (message.getBoard() != null)
                        localBoardCopy = message.getBoard();
                    System.out.println(this.player.getPlayerColor().toString()+":Client received new board");
                    // TODO: how to refresh gui
                }
            } catch (Exception e) {
                
            } finally {
                getKey.close();
                try {
                    out.close();
                } catch (Exception e) {}
            }
        }
    }

    private String playerColorToStringColor(PlayerColor color) {
        switch (color) {
            case RED:
                return "red";
            case ORANGE:
                return "orange";
            case WHITE:
                return "white";
            case BLUE:
                return "blue";
            default:
                return "black";
        }
    }

    /**
     * Handles this player clicking a settlement. Buys the settlment if it's currently unowned,
     * has a valid placement, and this player has the resources.
     * Upgrades the settlement if this player currently owns it.
     *
     * @param row Row of settlement in question
     * @param col Col of settlement in question
     */
    public void handleThisPlayerClickingSettlement(int row, int col) {
        System.out.println("Received click from settlement " + row + "," + col);
        Building building = localBoardCopy.getBuilding(row, col);
        if (building.getOwner() == PlayerColor.NONE)
            addThisPlayerSettlement(row, col);
        if (building.getOwner() == thisPlayerColor)
            upgradeThisPlayerSettlementToCity(row, col);
    }

    /**
     * Handles this player clicking a road. Buys the road if it's currently unowned, has a
     * valid placement, and this player has the resources.
     *
     * @param row Row of settlement in question
     * @param col Col of settlement in question
     */
    public void handleThisPlayerClickingRoad(int row, int col) {
        Road road = localBoardCopy.getRoad(row, col);
        if (road.getOwner() == PlayerColor.NONE)
            setThisPlayerRoad(row, col);
    }

    /**
    * Rolls the dice, sends the result to the server, and receives the results to add resources
    */
    public void rollDiceAndDistributeResources() {
        Random random = new Random();
        int roll = random.nextInt(12)+1; //Dice sum can be between 2 and 12, inclusive
        // TODO: Send roll to server
        ArrayList<Tuple<ResourceCard, PlayerColor>> list = 
            localBoardCopy.processTurn(roll);
        player.addThisPlayersResources(list);
    }

    //#region Private helpers to manage roads and settlements

    /**
     * Adds the road of another player
     *
     * @param row   Row of road in question
     * @param col   Col of road in question
     * @param color Player to give the road to
     */
    private void addOtherPlayerRoad(int row, int col, PlayerColor color) {
        if (localBoardCopy.addRoad(row, col, color))
            GUI.setColorOfRoadGUI(row, col, playerColorToStringColor(color));
    }

    /**
     * Checks to see if this player can buy the road. If so, buys it and then
     * sends this to server.
     *
     * @param row Row of road in question
     * @param col Col of road in question
     */
    private void setThisPlayerRoad(int row, int col) {
       if ( (player.tryBuyRoad(false) || hasFreeRoad) && localBoardCopy.addRoad(row, col, thisPlayerColor)) {
            // TODO: Send to server
            player.tryBuyRoad(true);
            GUI.setColorOfRoadGUI(row, col, playerColorToStringColor(thisPlayerColor));
            if (hasFreeRoad) hasFreeRoad = false;
       }
    }

    /**
     * Adds the settlement of another player.
     *
     * @param row   Row of settlement in question
     * @param col   Col of settlement in question
     * @param color Player to give the settlement to
     */
    private void addOtherPlayerSettlement(int row, int col, PlayerColor color) {
        if (localBoardCopy.addBuilding(row, col, color))
            GUI.setColorOfSettlement(row, col, playerColorToStringColor(color));
    }

    /**
     * Adds the settlement of this player. Checks for correct placement and resources,
     * then sends this to the server.
     *
     * @param row Row of the settlement in question
     * @param col Col of the settlement in question
     */
    private void addThisPlayerSettlement(int row, int col) {

        if (player.tryBuySettlement(false) && 
            (localBoardCopy.addBuilding(row, col, thisPlayerColor)) || 
                (hasFreeSettlement && localBoardCopy.addBuildingInit(row, col, thisPlayerColor))) {
            // TODO: Send to server
            player.tryBuySettlement(true);
            if (hasFreeSettlement) hasFreeSettlement = false;
            GUI.setColorOfSettlement(row, col, playerColorToStringColor(thisPlayerColor));
        }

    }

    /**
     * Upgrades the settlement of another player to a city.
     *
     * @param row   Row of the settlement in question
     * @param col   Col of the settlement in question
     * @param color Color of the player
     */
    private void upgradeOtherPlayerSettlementToCity(int row, int col, PlayerColor color) {
        if (localBoardCopy.upgradeBuilding(row, col)) {
            GUI.setColorOfSettlement(row, col, playerColorToStringColor(color));
        }
    }

    /**
     * Upgrades the settlement of this player to a city
     *
     * @param row Row of the settlement in question
     * @param col Col of the settlement in question
     */
    private void upgradeThisPlayerSettlementToCity(int row, int col) {
        if (player.tryBuyCity(false) && localBoardCopy.upgradeBuilding(row, col)) {
            // TODO: Send to server
            player.tryBuyCity(true);
            GUI.setColorOfSettlement(row, col, playerColorToStringColor(thisPlayerColor));
        }
    }
    //#endregion

    /**
    * Sends a message
    *
    * @param msg the Message to send
    * @return true if the message is successfully sent, false otherwise
    */
    public boolean sendMessage(Message msg) {
        try {
            out.writeObject(msg);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
