package edu.rpi.cs.csci4960.s21.javatan;

enum PortType {NONE, BRICK, LUMBER, ORE, GRAIN, WOOL, THREE};

public class Road {
    private PlayerColor ownership;
    private final PortType port;

    /**
    * Creates a new road with no owner and no port
    */
    public Road() {
        ownership = PlayerColor.NONE;
        port = PortType.NONE;
    }

    /**
    * Creates a new road with no owner and the given port type
    *
    * @param port the type of port to associate with this road
    */
    public Road(PortType port) {
        ownership = PlayerColor.NONE;
        this.port = port;
    }

    /**
    * Sets the owner of this road
    *
    * @param newOwner the new owner of this road
    */
    public void setOwner(PlayerColor newOwner) {
        ownership = newOwner;
    }

    /**
    * Gets the color of the owner of this road
    *
    * @return the color of the owner of this road
    */
    public PlayerColor getOwner() {
        return ownership;
    }

    /**
    * Gets the type of this port
    *
    * @return the type of this port
    */
    public PortType getPort() {
        return port;
    }
}