package edu.rpi.cs.csci4960.s21.javatan;


/**
 * For communication between Client and Server
 */

import java.io.Serializable;

public class Message implements Serializable {

    private PlayerColor playerColor;
    private Player player;
    private Action action;
    private Board board;
    private PlayerColor currentPlayer;

    public Message(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
