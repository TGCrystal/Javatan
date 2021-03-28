package src.edu.rpi.cs.csci4960.s21.javatan;

/**
           0,0    0,1    0,2
        1,0    1,1    1,2    1,3
    2,0    2,1    2,2     2,3   2,4
        3,0    3,1    3,2    3,3
           4,0    4,1    4,2
*/
public class Board {
    private Tile[][] tiles;
    private Road[][] roads;
    private Building[][] buildings;
    private int robberRow;
    private int robberColumn;


    // Assume fixed size for now
    public Board() {

    }
}