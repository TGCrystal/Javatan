package edu.rpi.cs.csci4960.s21.javatan;

/**
 * Unpack message and complete request by using function from Game.java
 * Send update message out
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;

public class MessageReceiveListener extends Thread {

    private ObjectInputStream in;
    private boolean stop = false;
    private GUI gui;
    private Game game;
    private ClientInfo clientInfo;
    private Server server;

    public MessageReceiveListener(Server server,ClientInfo clientInfo, GUI gui, Game game) {
        this.clientInfo = clientInfo;
        this.gui = gui;
        this.game = game;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            this.in = new ObjectInputStream(clientInfo.getClient().getInputStream());
            // Enter process loop
            while (!stop) {
                // Open the InputStream

                Message msg = (Message) this.in.readObject();
                if (msg != null && (msg.getPlayerColor() == clientInfo.getPlayer().getPlayerColor())) {
                    String actionCode = msg.getAction().getActionCode();
                    //Construct a message that need to be sent to each Client
                    Message outMessage = new Message(PlayerColor.NONE);
                    switch (actionCode) {
                        case "1": {
                            int row = msg.getAction().getRow();
                            int col = msg.getAction().getCol();
                            this.game.getBoard().addBuilding(row, col, msg.getPlayerColor());
                            outMessage.setBoard(this.game.getBoard());

                            break;
                        }
                        case "2": {
                            int row = msg.getAction().getRow();
                            int col = msg.getAction().getCol();
                            this.game.getBoard().upgradeBuilding(row, col);
                            outMessage.setBoard(this.game.getBoard());
                            break;
                        }
                        case "3": {
                            // TODO:Dice
                            break;
                        }
                        case "4": {
                            System.out.println("Server received addRoad request from Client");
                            int row = msg.getAction().getRow();
                            int col = msg.getAction().getCol();
                            this.game.getBoard().addRoad(row, col, msg.getPlayerColor());
                            outMessage.setBoard(this.game.getBoard());
                            System.out.println("New Board sent to each Client");
                            break;
                        }
                        case "0": {
                            game.nextTurn();
                            outMessage.setCurrentPlayer(game.getCurrentPlayer());
                            server.sendMessage(outMessage);
                            break;
                        }
                    }
                    server.sendMessage(outMessage);
                }
            }
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
