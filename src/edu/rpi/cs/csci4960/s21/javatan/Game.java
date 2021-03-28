package src.edu.rpi.cs.csci4960.s21.javatan;

public class Game {
    private Board board;
    private PlayerColor longestRoad;
    private PlayerColor largestArmy;
    // Use order of players joining as which player is which
    private ArrayList<PlayerColor> players;
    private int currentPlayerIndex;
}