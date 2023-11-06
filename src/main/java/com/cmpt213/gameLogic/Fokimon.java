package com.cmpt213.gameLogic;

/**
 * The `Fokimon` class represents a Fokimon cell in the game grid. It is a specific cell type that extends the abstract
 * `Cell` class. This class provides the character symbol 'X' to represent a Fokimon.
 */
public class Fokimon extends Cell {

    /**
     * Retrieves the character symbol 'X' associated with this Fokimon cell.
     *
     * @return The character symbol 'X' representing a Fokimon.
     */
    @Override
    public char getSymbol() {
        return 'X'; // Symbol representing a Fokimon
    }
}


