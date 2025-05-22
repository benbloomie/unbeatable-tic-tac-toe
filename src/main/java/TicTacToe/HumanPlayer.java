package TicTacToe;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player{
    Scanner userInput;  // scanner to get user input from human players

    // constructor to initialize human player's name, game symbol, and input scanner
    public HumanPlayer(String usersName, char gameSymbol) {
        super(usersName, gameSymbol);   // calls the super class constructore to initialize player attributes
        userInput = new Scanner(System.in);
    }

    // overridden method to take turn; logic for human player
    @Override
    public int takeTurn(Board board, ArrayList<Integer> filledPositions, int boardSize) {
        int rowInput, columnInput;   // initializes row and column variables to store input from the user
        System.out.println(getName() + ", it is your turn!");
        System.out.print("Please enter a position in the format of (row, column): ");
    
        // loops until a valid position is entered
        while (true) { 
            try {
                // calls scanner to get input from the user
                String input = userInput.nextLine(); 
                // ensures proper formatting of user input
                if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')') {
                    String rowsAndColumns = input.substring(1, input.length() - 1); // extracts contents inside of the parentheses 
                    int commaLocation = rowsAndColumns.indexOf(','); // finds comma in rowsAndColumns
    
                    // checks if a comma exists to divide the row and column before proceeding
                    if (commaLocation == -1) {
                        throw new InputMismatchException("Invalid input. Please ensure that a comma divides the row and column values.");
                    }
    
                    // extracts values for row and column and parses it from a string to an intger
                    String rowInputString = rowsAndColumns.substring(0, commaLocation).trim();
                    String columnInputString = rowsAndColumns.substring(commaLocation + 1).trim(); // starts at commaLocation + 1 to exclude comma
                    try {
                        rowInput = Integer.parseInt(rowInputString);
                        columnInput = Integer.parseInt(columnInputString);
                    // catches exception if conversion fails
                    } 
                    catch (NumberFormatException e) {
                        throw new InputMismatchException("Invalid input. Please enter valid numeric values for row and column.");
                    }
    
                    // ensures that row and column values are in the valid range
                    if (rowInput < 1 || rowInput > boardSize || columnInput < 1 || columnInput > boardSize) {
                        throw new IndexOutOfBoundsException("Invalid position. Please enter a number between 1 and " + boardSize + " for both row and column.");
                    }
    
                    int positionInput = (rowInput - 1) * boardSize + columnInput; // converts row, column to a board position (1-9)
    
                    // checks if input has already been used before returning the position number
                    if (filledPositions.contains(positionInput)) {
                        System.out.println("Position has already been used. Please try again.");
                    }
                    else {
                        filledPositions.add(positionInput); // adds new position if not within list already
                        return positionInput;   
                    }
                }
                else {
                    throw new InputMismatchException("Invalid input. Please enter two numbers in the format: (row, column)");
                }
            }
            // catches all exceptions and prints the corresponding output
            catch(IndexOutOfBoundsException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }    
}