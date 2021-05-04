package edu.rpi.cs.csci4960.s21.javatan;



import java.io.Serializable;

/**
 * For communication between Client and Server
 *
 * @author Chuanfeng Xiong
 * @author Chris Lamberston
 * @author Ruben McWilliams
 * @author Trevor Crystal
 */
public class Message implements Serializable {

    /**
    * The color of the player who is sending this message
    */
    private PlayerColor playerColor;
    /**
    * The player who is sending this message
    */
    private Player player;
    /**
    * The action this player is taking
    */
    private Action action;
    /**
    * The board representing the game state
    */
    private Board board;
    /**
    * The player whose turn it is
    */
    private PlayerColor currentPlayer;

    /**
    * The sole constructor for Message, sets the color of the player sending the message
    *
    * @param playerColor the color of the player sending this message
    */
    public Message(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
    * Gets the player sending this message
    *
    * @return the player sending the message
    */
    public Player getPlayer() {
        return player;
    }

    /**
    * Sets the player sending this message
    *
    * @param player the player sending this message
    */
    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
    * Gets the color of the player sending this message
    *
    * @return the color of the player sending the message
    */
    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    /**
    * Sets the color of the player sending this message
    *
    * @param playerColor the color of the player sending this message
    */
    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
    * Gets the Action performed that this message is relaying
    *
    * @return the Action this message is sending
    */
    public Action getAction() {
        return action;
    }

    /**
    * Sets the Action for this message to send
    *
    * @param action the Action for this message to send
    */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
    * Gets the board data contained in this message
    *
    * @return the Board state in this message
    */
    public Board getBoard() {
        return board;
    }

    /**
    * Sets the board data to be contained in this message
    *
    * @param board the Board state to send with this message
    */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
    * Gets the current player
    *
    * @return the current player
    */
    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    /**
    * Sets the current player
    *
    * @param currecntPlayer the current player
    */
    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
