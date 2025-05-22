package TicTacToe;

public class Board {
    // private attributes to store board information
    protected char[][] gameBoard;
    private int boardSize;
    
    // constructor to initialize the board size and generate the game board
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.gameBoard = new char[boardSize][boardSize];
        generateGameBoard();
    }
    
    // getter method to gain access to the size of the board
    public int getSize() {
        return boardSize;
    }

    // getter method to gain access to the board
    public char[][] getGameBoard() {
        return gameBoard;
    }

    // method to display the current state of the game board
    public void displayGameBoard() {
        // iterates through each row and column in the tic tac toe board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // if we have reached the last element in the column, don't print a vertical line
                if (j == boardSize - 1) {
                    System.out.println(" " + gameBoard[i][j]);
                }
                // standard printing of the table
                else {
                    System.out.print(" " + gameBoard[i][j] + " " + "|");
                }
            }
            // only print a horizontal line if there is a row that follows
            if (i < boardSize - 1) {
                // uses a StringBuffer to create horizontal line to allow for an adaptive way of printing
                StringBuffer horizontalLine = new StringBuffer();
                // loops through number of rows
                for (int j = 0; j < boardSize; j++) {
                    // for last element, only add 3 occurences of ---
                    if (j == boardSize - 1) {
                        horizontalLine.append("---");
                    }
                    // elsewise, add 4 occurences
                    else {
                        horizontalLine.append("----");

                    }
                }
                System.out.println(horizontalLine.toString());
            }
        }
    }

    // method to reset the game board
    public void resetBoard() {
        generateGameBoard();   // private method is called within the public method
    }

    // helper method to generate an empty game board
    protected void generateGameBoard() {
        // iterates through each row and column in the tic tac toe board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // sets each value to be an empty character
                gameBoard[i][j] = ' ';
            }
        }
    }

    // method to place the player's symbol on the board
    public void placeMarker(char symbol, int row, int column) {
        gameBoard[row][column] = symbol;    // updates the gameboard with the symbol of the current player
    }

    // method to check if a winner is present
    protected boolean checkForWinner(Player player) {
        char symbol = player.getSymbol();
        if (rowWinningCondition(symbol) || columnWinningCondition(symbol) || negDiagonalWinningCondition(symbol) || posDiagonalWinningCondition(symbol)) {
            return true;
        }
        else {
            return false;
        }
    }

    // method to check the winning condition along the rows
    protected boolean rowWinningCondition(char symbol) {
        // iterates through each row in the board
        for (int i = 0; i < boardSize; i++) {
            int symbolCounter = 0;  // resets condition variable after each iteration
            // iterates through each column in each row
            for (int j = 0; j < boardSize; j++) {
                // if an element in the row is the given symbol, increment the counter
                if (gameBoard[i][j] == symbol) {
                    symbolCounter++;
                }
            }
            // if the number of equal symbols is the size of the board, return true
            if (symbolCounter == boardSize) {
                return true;
            }
        }
        return false;   // returns false if no winning condition is found
    }

    // method to check the winning condition along the columns
    protected boolean columnWinningCondition(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            int symbolCounter = 0;
            for (int j = 0; j < boardSize; j++) {
                // if an element in the column is the given symbol, increment the counter          
                if (gameBoard[j][i] == symbol) {
                    symbolCounter++;
                }
            }
            if (symbolCounter == boardSize) {
                return true;
            }
        }
        return false;   
    }

    // method to check the winning condition along the positive diagonal
    protected boolean posDiagonalWinningCondition(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            // checks the diagonal elements along the positive sloped line
            if (gameBoard[boardSize - 1 - i][i] != symbol) {
                return false;   // returns false if not all elements match
            }
        }
        return true;
    }

    // method to check the winning condition along the negative diagonal
    protected boolean negDiagonalWinningCondition(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            // checks the diagonal elements along the negative sloped line
            if (gameBoard[i][i] != symbol) {
                return false;   
            }
        }
        return true ;
    }
}