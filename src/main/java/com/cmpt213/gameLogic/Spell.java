package com.cmpt213.gameLogic;

import java.util.Random;

/**
 * The `Spell` class represents the spells that the player can use in the Tokimon finder game.
 */
public class Spell {
    private static Random random;
    private static int remainingSpells = 3;

    /**
     * Constructs a `Spell` object and initializes the random number generator.
     */
    public Spell() {
        random = new Random();
    }

    /**
     * Uses a spell based on the specified spell number to interact with the game grid.
     *
     * @param spellNumber The number representing the type of spell to use:
     *                    1 for Tokimon spell, 2 for Fokimon spell, or 3 for Teleportation spell.
     * @param gameGrid   The `GameGrid` object representing the game grid.
     * @param spell      The `Spell` object to use for the spell.
     */
    public static void useSpell(int spellNumber, GameGrid gameGrid, Spell spell) {
        if (remainingSpells > 0) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);

            // Tokimon spell
            if (spellNumber == 1) {
                while (true) {
                    Cell cell = gameGrid.getCell(row, col);

                    if (!gameGrid.isVisited(row, col)) {
                        if (cell instanceof Tokimon) {
                            gameGrid.setVisited(row, col);
                            gameGrid.collctedTokiIncrease();
                            break; // Exit the inner loop when Tokimon is found
                        }
                    }

                    // Move to the next cell
                    col = (col + 1) % 10;
                    if (col == 9) {
                        row = (row + 1) % 10; // If we reach the last column, move to the next row
                    }
                }
            }
            // Fokimon spell
            else if (spellNumber == 2) {
                while (true) {
                    Cell cell = gameGrid.getCell(row, col);

                    if (!gameGrid.isVisited(row, col)) {
                        if (cell instanceof Fokimon) {
                            gameGrid.setVisited(row, col);
                            break; // Exit the inner loop when Fokimon is found
                        }
                    }

                    // Move to the next cell
                    col = (col + 1) % 10;
                    if (col == 9) {
                        row = (row + 1) % 10; // If we reach the last column, move to the next row
                    }
                }
            }
            // Teleportation spell
            else if (spellNumber == 3) {
                gameGrid.teleportPlayer(row, col);
            }
            remainingSpells--;
        }
    }

    /**
     * Retrieves the number of remaining spells that the player can use.
     *
     * @return The remaining number of spells.
     */
    public int getRemainingSpells() {
        return remainingSpells;
    }

    /**
     * Sets the remaining number of spells that the player can use.
     *
     * @param spellsNum The number of spells to set as remaining.
     */
    public void setRemainingSpells(int spellsNum) {
        remainingSpells = spellsNum;
    }
}
