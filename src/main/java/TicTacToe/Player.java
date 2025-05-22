package TicTacToe;

import java.util.ArrayList;

public abstract class Player {
    // private variables to store the information of each player
    private String usersName;
    private char gameSymbol;

    // constructor to initialize player's name and game symbol
    public Player(String usersName, char gameSymbol) {
        this.usersName = usersName;
        this.gameSymbol = gameSymbol;
    }

    // getter method to get the players name
    public String getName() {
        return usersName;
    }

    // getter method to get the players symbol
    public char getSymbol() {
        return gameSymbol;
    } 

    // abstract method for taking a turn; implemented for each subclass/type of player
    public abstract int takeTurn(Board board, ArrayList<Integer> filledPositions, int boardSize);
}
