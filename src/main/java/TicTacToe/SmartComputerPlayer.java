package TicTacToe;

import java.util.ArrayList;
import java.util.Random;

public class SmartComputerPlayer extends Player {
    private Random randomNumberGenerator;   // random number generator to choose the computers position
    private char computerSymbol;
    // constructor to initialize computer player's name, game symbol, and random number generator
    public SmartComputerPlayer(String usersName, char gameSymbol) {
        super(usersName, gameSymbol);   // calls the super class constructore to initialize player attributes
        computerSymbol = getSymbol();
        randomNumberGenerator = new Random();
    }

    // overridden method to take turn; logic for computer player
    @Override
    public int takeTurn(Board board, ArrayList<Integer> filledPositions, int boardSize) {
        char[][] gameBoard = board.getGameBoard();
        boardSize = gameBoard.length;
        char otherSymbol = getOtherSymbol();

        // condition 1: check for winning move
        int winningMove = findWinningMove(gameBoard, computerSymbol, boardSize);   // calls function to check if computer can win the game
        // if a winning position is found, return the position to the calling function
        if (winningMove != -1) {   
            System.out.println(getName() + " chose position " + winningMove + ".");  // outputs a message to indicates computers placement
            filledPositions.add(winningMove);
            return winningMove;
        }

        // condition 2: check to block oponent
        int blockingMove = findBlockingMove(gameBoard, otherSymbol, boardSize);    // SAME AS WINNING MOVE BUT != ' ' && != computerSymbol
        // if a blocking position is found, return the position to the calling function
        if (blockingMove != -1) {
            System.out.println(getName() + " chose position " + blockingMove + ".");  // outputs a message to indicates computers placement
            filledPositions.add(blockingMove);
            return blockingMove;
        }

        // condition 3: play in the middle of the board
        if (gameBoard[boardSize / 2][boardSize / 2] == ' ') {
            int positionSelected =  (boardSize / 2) * boardSize + (boardSize / 2) + 1;
            System.out.println(getName() + " chose position " + positionSelected + ".");  // outputs a message to indicates computers placement
            filledPositions.add(positionSelected);
            return positionSelected;   // calculates and returns middle position
        }

        // condition 3: play in the corners of the board
        int[][] corners = { {0,0} , {boardSize - 1, 0}, {0, boardSize - 1}, {boardSize - 1, boardSize - 1} };   // creates an array of each corner position
        // iterates through each corner positition
        for (int[] corner: corners) {
            // if a corner position is empty, calculate that position as an integer, and return it
            if (gameBoard[corner[0]][corner[1]] == ' ') {
                int positionSelected = corner[0] * boardSize + corner[1] + 1;
                System.out.println(getName() + " chose position " + positionSelected + ".");  // outputs a message to indicates computers placement
                filledPositions.add(positionSelected);
                return positionSelected;
            }
        }

        // if all other conditions do not pass
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

    // method to determine the symbol of the player
    private char getOtherSymbol() {
        // assigns the opposite symbol to the other player based on the symbol of the smart computer
        if (computerSymbol == 'X') {
            return 'O';
        }
        else {
            return 'X';
        }
    }

    // method to analyze the board to find if a winning move is present
    private int findWinningMove(char[][] gameBoard, char symbol, int boardSize) {
        // checks for winning availabiltiy along the rows
        for (int i = 0; i < boardSize; i++) {
            // tracks the number of symbol occurences, and empty spots
            int symbolCounter = 0;
            int emptySpot = -1; 
            // iterates through each column in the board
            for (int j = 0; j < boardSize; j++) {
                // increments the symbol counter if it is found
                if (gameBoard[i][j] == symbol) {
                    symbolCounter++;
                // calculates the board position if an empty element is found
                } 
                else if (gameBoard[i][j] == ' ') {
                    emptySpot = i * boardSize + j + 1; 
                }
            }
            // if there is only 1 open space along the row, and the rest are of the symbol, return that position
            if (symbolCounter == boardSize - 1 && emptySpot != -1) {
                return emptySpot;
            }
        }

        // checks for winning availabiltiy along the columns
        for (int i = 0; i < boardSize; i++) {
            // tracks the number of symbol occurences, and empty spots
            int symbolCounter = 0;
            int emptySpot = -1; 
            // iterates through each column in the board
            for (int j = 0; j < boardSize; j++) {
                // increments the symbol counter if it is found
                if (gameBoard[j][i] == symbol) {
                    symbolCounter++;
                // calculates the board position if an empty element is found
                } 
                else if (gameBoard[j][i] == ' ') {
                    emptySpot = j * boardSize + i + 1; 
                }
            }
            // if there is only 1 open space along the column, and the rest are of the symbol, return that position
            if (symbolCounter == boardSize - 1 && emptySpot != -1) {
                return emptySpot;
            }
        }

        // checks for winning availabiltiy along the negative slope diagonal
        int negDiagonalCounter = 0;
        int negDiagonalEmptySpot = -1;
        // iterates through each element along the negative diagonal slope
        for (int i = 0; i < boardSize; i++) {
            // if the symbol exists at the position, incremenet the counter
            if (gameBoard[i][i] == symbol) {
                negDiagonalCounter++;
            } 
            // calculates the board position if an empty element is found
            else if (gameBoard[i][i] == ' ') {
                negDiagonalEmptySpot = i * boardSize + i + 1;
            }
        }
        // if there is only 1 open space along the neg diagonal, and the rest are of the symbol, return that position
        if (negDiagonalCounter == boardSize - 1 && negDiagonalEmptySpot != -1) {
            return negDiagonalEmptySpot;
        }

        // Check negative diagonal for a winning move
        int posDiagonalCounter = 0;
        int posDiagonalEmptySpot = -1;
        // iterates through each element along the negative diagonal slope
        for (int i = 0; i < boardSize; i++) {
            // if the symbol exists at the position, incremenet the counter
            if (gameBoard[boardSize - 1 - i][i] == symbol) {
                posDiagonalCounter++;
            } 
            // calculates the board position if an empty element is found
            else if (gameBoard[boardSize - 1 - i][i] == ' ') {
                posDiagonalEmptySpot = (boardSize - 1 - i) * boardSize + i + 1;
            }
        }
        // if there is only 1 open space along the neg diagonal, and the rest are of the symbol, return that position
        if (posDiagonalCounter == boardSize - 1 && posDiagonalEmptySpot != -1) {
            return posDiagonalEmptySpot;
        }
        return -1; // returns -1 if no winning move found
    }

    // method to analyze the board to find if a blocking move is present
    private int findBlockingMove(char[][] gameBoard, char symbol, int boardSize) {
        // determines the blocking position by finding the winning move for the other player
        return findWinningMove(gameBoard, symbol, boardSize);
    } 
}