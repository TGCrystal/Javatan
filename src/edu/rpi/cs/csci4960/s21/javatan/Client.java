package edu.rpi.cs.csci4960.s21.javatan;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public Player getPlayer() {
        return player;
    }

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
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
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
//        if ( player.tryBuyRoad(false) && localBoardCopy.addRoad(row, col, thisPlayerColor)) {
        // TODO: Send to
        Action action = new Action();
        action.setActionCode("4");
        action.setRow(row);
        action.setCol(col);
        Message message = new Message(this.player.getPlayerColor());
        message.setAction(action);
        this.sendMessage(message);
        player.tryBuyRoad(true);
//            GUI.setColorOfRoadGUI(row, col, playerColorToStringColor(thisPlayerColor));
//        }
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
        if (player.tryBuySettlement(false) && localBoardCopy.addBuilding(row, col, thisPlayerColor)) {
            // TODO: Send to server
            player.tryBuySettlement(true);
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
