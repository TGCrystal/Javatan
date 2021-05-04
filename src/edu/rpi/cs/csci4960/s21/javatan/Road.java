package edu.rpi.cs.csci4960.s21.javatan;

import java.io.Serializable;

enum PortType {NONE, BRICK, LUMBER, ORE, GRAIN, WOOL, THREE};


/**
* A class used to store information about a road. The owner of the road and whether there
* is a port connected to it is stored.
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Road implements Serializable {
    /**
    * Represents the owner of this road, PlayerColor.NONE if not yet built
    */
    private PlayerColor ownership;
    /**
    * Represents the type of port that goes along this road, PortType.NONE for none
    */
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
