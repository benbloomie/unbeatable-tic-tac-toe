package TicTacToe;

import java.util.ArrayList;

public class Game {
    // private attributes to control the game flow
    protected Board board;
    protected Player player1;
    protected Player player2;
    private int boardSize;
    protected ArrayList<Integer> filledPositions;

    // constructor to initialize game components and players
    public Game(Player player1, Player player2, int boardSize) {
        this.board = new Board(boardSize); 
        this.player1 = player1;
        this.player2 = player2;
        this.filledPositions = new ArrayList<>();
        this.boardSize = board.getSize();
    }

    // runs the main game flow
    public void playGame() {
        board.resetBoard();     // cleans the game board
        filledPositions.clear();    // resets the used positions

        displayMessage();
        int numOfTurns = 0; // intializes int variable to remember what turn the game is currently on
        
        while (numOfTurns < (boardSize * boardSize)) {
            Player currentPlayer = checkPlayer(numOfTurns); // creates a new player object depending on the turn number
            String currentName = currentPlayer.getName();   // calls instance method to get name of player whose turn it currently is
            int positionSelected = currentPlayer.takeTurn(board, filledPositions, boardSize);    // calls the takeTurn method to have the player take their turn
            placeSymbol(positionSelected, currentPlayer);   // places the marker based on the return position, assigned to positionSelected
            board.displayGameBoard();   // displays updated board after each turn

            // checks to see if someone has won the game, and breaks out of the loop if there is a winner
            if (board.checkForWinner(currentPlayer)) {
                System.out.println("Congratulations " + currentName + ", you won!");
                break;
            }
            numOfTurns++;   // increments number of turns after each player places their symbol

            // checks if there is a winner for a full board, and if none is present, indicate it is a draw
            if (filledPositions.size() == (boardSize * boardSize) && !board.checkForWinner(player1) && !board.checkForWinner(player2)) {
                System.out.println("It's a draw!"); 
            }
        }
    }

    // method to display the initial welcoming message to user
    public void displayMessage() {
        System.out.println("\nHere is an overview of the board:");
        board.displayGameBoard();   
        board.resetBoard(); 
    }

    // helper method to determine the current player based on the turn number
    protected Player checkPlayer(int turn) {
        // returns player1 for all even values of current turn
        if (turn % 2 == 0) {
            return player1;
        }
        // returns player2 for all odd values of current turn
        else {
            return player2;
        }
    }

    // helper method to place a symbol on the board
    protected void placeSymbol(int position, Player player) {
        char symbol = player.getSymbol();
        int rowOfInput = (position - 1) / boardSize;  // uses integer division to calculate the value of the row 
        int columnOfInput = (position - 1) % boardSize;  // uses the remainder to determine the column
        board.placeMarker(symbol, rowOfInput, columnOfInput); // calls the setter method to update the game board
    }
}