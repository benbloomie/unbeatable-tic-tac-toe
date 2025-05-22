package TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner numberSelector = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!"); 

        int[] players = getPlayers(numberSelector);
        char[] symbols = {'X', 'O'};
        numberSelector.nextLine();  // clears the scanner aftering receiving
 
        Player player1 = createPlayer(players[0], symbols[0], numberSelector, 1);
        Player player2 = createPlayer(players[1], symbols[1], numberSelector, 2);

        boolean playAgain = true;
        while (playAgain) {
            int boardSize = getBoardSize(numberSelector);
            Game ticTacToe = new Game(player1, player2, boardSize);
            ticTacToe.playGame();

            playAgain = playAgainPrompt(numberSelector);
        }

        numberSelector.close();
    }

    // method to prompt the user to play again
    public static boolean playAgainPrompt(Scanner userInput) {
        System.out.println("Would you like to play again (Y/N): ");
        while (true) {
            String result = userInput.next();

            if (result.toUpperCase().equals("N")) {
                System.out.println("Thank you for playing! Please play again soon :)");
                return false;
            }
            else if (result.toUpperCase().equals("Y")) {
                return true;
            }
            else {
                System.out.println("Please enter either Y or N");
            }
        }
    }

    // method to get the board size from user input
    public static int getBoardSize(Scanner numberSelector) {
        // loops until valid input is received
        while (true) {
            System.out.print("Please enter a size for the board (between 3 and 20): ");
            try {
                int boardSize = numberSelector.nextInt();
                // ensures board size is a valid choice
                if (boardSize >= 3 && boardSize <= 20) {
                    return boardSize;
                }
                else {
                    System.out.println("Invalid input. Please enter a number between 3 and 20 (inclusive).");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                numberSelector.next();  // clears the scanner for invalid input
            }
        }
    }

    // method allow the user to chose their player options
    public static int[] getPlayers(Scanner numberSelector) {
        // outputs possible player options
        System.out.println("Here are the possible player options:");
        System.out.println("    1: Human");
        System.out.println("    2: Computer (Easy)");
        System.out.println("    3: Computer (Hard)");
    
        // intializes arrays for players symbols and number selection
        char[] symbols = {'X', 'O'};    
        int[] playerNumbers = new int[2]; 
    
        // iterates through the loop twice to get both player numbers
        for (int i = 0; i < symbols.length; i++) {
            // loops until the user enters a valid number 
            while (true) {
                System.out.print("Please make your selection for Player " + (i + 1) + " (symbol: " + symbols[i] + "): ");
                // gets integer input from the user, and ensure that it is a valid value
                try {
                    int playerNumber = numberSelector.nextInt();
                    if (playerNumber >= 1 && playerNumber <= 3) {
                        playerNumbers[i] = playerNumber;    // appends the users choice to the player number array
                        break; // exit the loop for this player once valid input is given
                    } 
                    else {
                        System.out.println("Invalid Input. Please select an option between 1 and 3.");
                    }
                } 
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    numberSelector.next();  // clears the scanner for invalid input
                }
            }
        }
        return playerNumbers;
    }

    // method to create the player object based on the user selection
    public static Player createPlayer(int playerNumber, char symbol, Scanner inputScanner, int playerNumbers) {
        // assigns a default Player object, and declares a name variable
        Player player = new HumanPlayer("DEFAULT", 'N');
        String name;
        // compares cases for player numbers
        switch(playerNumber) {
            // creates a HumnaPlayer object for input of 1
            case 1:
                System.out.print("Please enter the name of Player " + playerNumbers + " (Human): ");
                name = inputScanner.nextLine();
                player = new HumanPlayer(name, symbol);
                break; 
            // creates a ComputerPlayer object for an input of 2
            case 2:
                System.out.print("Please enter the name of Player " + playerNumbers + " (Computer): ");
                name = inputScanner.nextLine();
                player = new ComputerPlayer(name, symbol);
                break;
            // creates a ComputerPlayer object for an input of 3
            case 3:
                System.out.print("Please enter the name of Player " + playerNumbers + " (Computer): ");
                name = inputScanner.nextLine();
                player = new SmartComputerPlayer(name, symbol);
                break;
        }
        return player;
    }
}