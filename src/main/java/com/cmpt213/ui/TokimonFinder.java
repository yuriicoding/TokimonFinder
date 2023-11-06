package com.cmpt213.ui;

import java.util.Scanner;
import com.cmpt213.gameLogic.GameGrid;
import com.cmpt213.gameLogic.Spell;

/**
 * The main class for the Tokimon finder game, responsible for handling user input and game logic.
 */
public class TokimonFinder {
    public static void main(String[] args) {
        // Parse command line arguments and set game options
        int numToki = 10; // Default number of Tokimons
        int numFoki = 5; // Default number of Fokimons
        int totalTokiFoki = 0;
        int row = -1;
        int column = -1;
        boolean cheatMode = false;

        for (String arg : args) {
            if (arg.startsWith("--numToki=")) {
                try {
                    int numTokiVal = Integer.parseInt(arg.substring(10));
                    totalTokiFoki = totalTokiFoki + numTokiVal;
                    if (numTokiVal < 5 || totalTokiFoki > 100) {
                        System.out.println("Invalid number of Tokimons. Using default value.");
                    } else {
                        numToki = numTokiVal;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number of Tokimons. Using default value.");
                }
            } else if (arg.startsWith("--numFoki=")) {
                try {
                    int numFokiVal = Integer.parseInt(arg.substring(10));
                    totalTokiFoki = totalTokiFoki + numFokiVal;
                    if (numFokiVal < 5 || totalTokiFoki > 100) {
                        System.out.println("Invalid number of Tokimons. Using default value.");
                    } else {
                        numFoki = numFokiVal;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number of Fokimons. Using default value.");
                }
            } else if (arg.equals("--cheat")) {
                cheatMode = true;
            }
        }

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (row < 0 || row > 9 || column < 0 || column > 9) {
            System.out.println("Enter your starting position in format 'A2' (letter from A to J and number from 0 to 9):");
            System.out.print("Chosen position: ");
            String userInput = scanner.nextLine();

            // Make sure the input is in the correct format
            if (userInput.length() == 2) {
                char letter = userInput.charAt(0);
                int number = Character.getNumericValue(userInput.charAt(1));

                // Check if the letter is in the range A to J
                if (letter >= 'A' && letter <= 'J') {
                    row = letter - 'A'; // Convert letter to a number from 0 to 9

                    // Check if the number is in the range 0 to 9
                    if (number >= 0 && number <= 9) {
                        column = number; // No need for conversion for the column

                        // You now have the row and column as separate int variables
                        System.out.println("Row: " + row);
                        System.out.println("Column: " + column);
                    } else {
                        System.out.println("Invalid column number. Please enter a number from 0 to 9.");
                    }
                } else {
                    System.out.println("Invalid letter. Please enter a letter from A to J.");
                }
            } else {
                System.out.println("Invalid input format. Please enter a letter from A to J and a number from 0 to 9 (e.g., 'A3').");
            }
        }

        // Create a spell for the player
        Spell spell = new Spell();

        // Create the game grid
        GameGrid gameGrid = new GameGrid(numToki, numFoki);
        gameGrid.initializeGrid(row, column);

        if (cheatMode) {
            DisplayGrid.displayCheatersGrid(gameGrid, spell);
        }

        // Game loop
        while (true) {
            DisplayGrid.display(gameGrid, spell);

            if (gameGrid.isGameWon()) {
                System.out.println("Congratulations! You've found all the Tokimons. You win!");
                DisplayGrid.displayCheatersGrid(gameGrid, spell);
                break;
            }

            if (gameGrid.isGameLost()) {
                System.out.println("Game over! You lost.");
                break;
            }

            System.out.print("Enter your move (W/A/S/D to move, 1 to use a spell, Q to quit): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                System.out.println("You quit the game. Thanks for playing!");
                break;
            } else if (input.equals("1")) {

                int spellChosen;

                while (true) {
                    System.out.println("Which spell do you wanna use, little wizard?");
                    System.out.println("1 to reveal random Tokimon.");
                    System.out.println("2 to reveal random Fokimon.");
                    System.out.println("3 to teleport to a new location.");
                    System.out.print("Number of your spell: ");
                    if (scanner.hasNextInt()) {
                        spellChosen = scanner.nextInt();
                        if (spellChosen >= 1 && spellChosen <= 3) {
                            break; // Valid choice, exit the loop
                        }
                    }

                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    scanner.nextLine(); // Consume the invalid input
                }

                // Now you have a valid choice in the 'spellChosen' variable
                System.out.println("You chose spell: " + spellChosen);
                Spell.useSpell(spellChosen, gameGrid, spell);

            } else if (input.matches("[WASD]")) {
                gameGrid.movePlayer(input);
            } else {
                System.out.println("Invalid input. Please enter a valid move (W/A/S/D), 1 for a spell, or Q to quit.");
            }
        }

        // Close the scanner
        scanner.close();
    }
}
