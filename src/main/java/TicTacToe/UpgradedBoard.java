package TicTacToe;

public class UpgradedBoard extends Board {
    private int winningCondition; // number of consecutive markers needed to win
    private char[][] gameBoard;
    private int boardSize;

    // constructor to initialize board with size and winning condition
    public UpgradedBoard(int boardSize, int winningCondition) {
        super(boardSize); // calls super constructor to initialize the grid size
        this.winningCondition = winningCondition;
        this.boardSize = boardSize;
        gameBoard = getGameBoard();
    }

    // overrides the super method to check for a winner with custom winning condition 
    @Override
    protected boolean checkForWinner(Player player) {
        char symbol = player.getSymbol();
        if (rowWinningCondition(symbol) || columnWinningCondition(symbol) || posDiagonalWinningCondition(symbol) || negDiagonalWinningCondition(symbol)) {
            return true;
        }
        else {
            return false;
        }
    }

    // overrides the super method to check row for a winner for M consecutive markers
    @Override
    protected boolean rowWinningCondition(char symbol) {
        // iterates through each row in the board
        for (int i = 0; i < boardSize; i++) {
            int symbolCounter = 0;  // resets condition variable after each iteration
            // iterates through each column in each row
            for (int j = 0; j < boardSize; j++) {
                // compares each symbol in the row
                if (gameBoard[i][j] == symbol) {
                    symbolCounter++;    // increments the counter for every symbol found
                } 
                else {
                    symbolCounter = 0; // resets the counter if the symbol doesn't match
                }
                // if the number of equal symbols in the row matches the condition, return true
                if (symbolCounter == winningCondition) {
                    return true;
                }
            }
        }
        return false;   // returns false if no winning condition is found
    }

    // overrides the super method to check columns for a winner for M consecutive markers
    @Override
    protected boolean columnWinningCondition(char symbol) {
        for (int i = 0; i < boardSize; i++) {
            int symbolCounter = 0;
            for (int j = 0; j < boardSize; j++) {
                // compares each symbol in the column
                if (gameBoard[j][i] == symbol) {
                    symbolCounter++;
                } 
                else {
                    symbolCounter = 0;
                }
                // if the number of equal symbols in the column matches the condition, return true
                if (symbolCounter == winningCondition) {
                    return true;
                }
            }
        }
        return false;
    }

    // overrides the super method to check the positive diagonal for a winner for M consecutive markers
    @Override
    protected boolean posDiagonalWinningCondition(char symbol) {
        // iterates through each row in the board
        for (int i = 0; i < boardSize; i++) {
            // iterates through each column in each row
            for (int j = 0; j < boardSize; j++) {
                // if the current element matches the symbol, check all the diagonals
                if (gameBoard[i][j] == symbol) {
                    int symbolCounter = 0;
                    // check the positive diagonal 
                    for (int k = 0; k < winningCondition; k++) {
                        // ensures we don't check an element that is out of bounds
                        if ((i + k < boardSize) && (j + k < boardSize) && (gameBoard[i + k][j + k] == symbol)) {
                            symbolCounter++;    // increments the count for each element found on the positive diagonal
                        } 
                        // if the diagonal has an empty position, or position is out of the range
                        else {
                            break; 
                        }
                    }
                    // if the number of consecutive symbols matches the winning condition
                    if (symbolCounter == winningCondition) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // overrides the super method to check the negative diagonal for a winner for M consecutive markers
    @Override
    protected boolean negDiagonalWinningCondition(char symbol) {
        // iterates through each row in the board
        for (int i = 0; i < boardSize; i++) {
            // iterates through each column in each row
            for (int j = 0; j < boardSize; j++) {
                // if the current element matches the symbol, check diagonals
                if (gameBoard[i][j] == symbol) {
                    int symbolCounter = 0;
                    // check the negative diagonal 
                    for (int k = 0; k < winningCondition; k++) {
                        // ensures we don't check an element that is out of bounds
                        if ((i + k < boardSize) && (j - k >= 0) && (gameBoard[i + k][j - k] == symbol)) {
                            symbolCounter++;    // increments the count for each element found on the negative diagonal
                        } 
                        // if the diagonal has an empty position, or position is out of the range
                        else {
                            break; 
                        }
                    }
                    // if the number of consecutive symbols matches the winning condition
                    if (symbolCounter == winningCondition) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}