package src.edu.rpi.cs.csci4960.s21.javatan;



public class Player {
    private ArrayList<ResourceCard> resourceCards;
    private ArrayList<DevelopmentCard> developmentCards;
    private boolean hasLongestRoad;
    private boolean hasLargestArmy;
    private int victoryPoints;
    // Color will be assigned
    private PlayerColor color;

    public Player(PlayerColor color) {
        this.color = color;
    }

    public void addResourceCard(ResourceCard card) {

    }

    public void addDevelopmentCard(DevelopmentCard card) {

    }

    // Return ture if the card existed and was removed, false otherwise
    public boolean removeResourceCard(ResourceCard card) {

    }
    public boolean removeDevelopmentCard(DevelopmentCard card) {

    }

    public ResourceCard removeRandomCard() {

    }

}