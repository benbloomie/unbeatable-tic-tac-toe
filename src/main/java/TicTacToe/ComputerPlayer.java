package TicTacToe;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player{
    private Random randomNumberGenerator;   // random number generator to choose the computers position

    // constructor to initialize computer player's name, game symbol, and random number generator
    public ComputerPlayer(String usersName, char gameSymbol) {
        super(usersName, gameSymbol);   // calls the super class constructore to initialize player attributes
        randomNumberGenerator = new Random();
    }

    // overridden method to take turn; logic for computer player
    @Override
    public int takeTurn(Board board, ArrayList<Integer> filledPositions, int boardSize) {
        int positionSelected;   // stores randomly generated position
        // loops until the randomly generated position has not already been used
        while (true) {
            positionSelected = randomNumberGenerator.nextInt(boardSize * boardSize) + 1;    // generates a random number between 1 and 9
            // checks if position has already been used 
            if (!filledPositions.contains(positionSelected)) {
                filledPositions.add(positionSelected);  // adds new position if not within the list already
                System.out.println(getName() + " chose position " + positionSelected + ".");  // outputs a message to indicates computers placement
                return positionSelected;
            }
        } 
    }
}
