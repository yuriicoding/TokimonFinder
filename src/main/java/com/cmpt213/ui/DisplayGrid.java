package com.cmpt213.ui;

import com.cmpt213.gameLogic.Cell;
import com.cmpt213.gameLogic.Fokimon;
import com.cmpt213.gameLogic.GameGrid;
import com.cmpt213.gameLogic.Position;
import com.cmpt213.gameLogic.Spell;
import com.cmpt213.gameLogic.Tokimon;

/**
 * This class is responsible for displaying the game grid and relevant information to the user.
 */
public class DisplayGrid {

    /**
     * Displays the solution grid in "cheat mode" showing the actual location of Tokimons and Fokimons.
     * @param gameGrid The GameGrid object that contains the game's state.
     * @param spell The Spell object used by the player.
     */
    public static void displayCheatersGrid(GameGrid gameGrid, Spell spell) {
        Cell[][] grid = gameGrid.getGrid();

        System.out.println("Solution Grid:");
        System.out.print("   ");
        for (int column_header = 0; column_header < 10; column_header++) {
            System.out.print(column_header + "  ");
        }
        System.out.println();

        for (int row = 0; row < 10; row++) {
            System.out.print((char) ('A' + row) + "  ");
            for (int col = 0; col < 10; col++) {
                if (grid[row][col] instanceof Tokimon) {
                    System.out.print("$  "); // Tokimon
                } else if (grid[row][col] instanceof Fokimon) {
                    System.out.print("X  "); // Fokimon
                } else {
                    System.out.print("~  "); // Empty cell
                }
            }
            System.out.println();
        }
    }

    /**
     * Displays the current game grid along with player's position and relevant game information.
     * @param gameGrid The GameGrid object that contains the game's state.
     * @param spell The Spell object used by the player.
     */
    public static void display(GameGrid gameGrid, Spell spell) {
        Position playerPosition = gameGrid.getPlayerPosition();
        Cell[][] grid = gameGrid.getGrid();
        boolean[][] visited = gameGrid.getVisited();

        int remainingSpells = spell.getRemainingSpells();
        int numToki = gameGrid.getNumToki();
        int collectedToki = gameGrid.getCollectedToki();

        System.out.println("Game Grid:");
        System.out.print("   ");
        for (int column_header = 0; column_header < 10; column_header++) {
            System.out.print(column_header + "  ");
        }
        System.out.println();

        for (int row = 0; row < 10; row++) {
            System.out.print((char) ('A' + row) + "  ");
            for (int col = 0; col < 10; col++) {
                if (playerPosition.getRow() == row && playerPosition.getCol() == col) {
                    if (grid[row][col] instanceof Tokimon) {
                        System.out.print("@$ "); // Tokimon
                    } else if (grid[row][col] instanceof Fokimon) {
                        System.out.print("@X "); // Fokimon
                    } else {
                        System.out.print("@  "); // Empty cell
                    }
                } else if (visited[row][col]) {
                    if (grid[row][col] instanceof Tokimon) {
                        System.out.print("$  "); // Tokimon
                    } else if (grid[row][col] instanceof Fokimon) {
                        System.out.print("X  "); // Fokimon
                    } else {
                        System.out.print("   "); // Empty cell
                    }
                } else {
                    System.out.print("~  "); // Unvisited cell
                }
            }
            System.out.println();
        }

        System.out.println("Spells: " + remainingSpells);
        System.out.println("Tokimons Found: " + collectedToki);
        System.out.println("Tokimons Remaining: " + (numToki - collectedToki));
    }
}
