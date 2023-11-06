package com.cmpt213.gameLogic;

/**
 * The `Cell` class represents a basic cell in a game grid. This is an abstract class that defines a contract for 
 * deriving specific cell types. Subclasses are expected to implement the `getSymbol` method to return the character
 * symbol representing the specific cell type.
 */
public abstract class Cell {

    /**
     * Retrieves the character symbol associated with this cell. Subclasses must implement this method to provide the
     * appropriate character symbol for their specific cell type.
     *
     * @return The character symbol representing this cell type.
     */
    public abstract char getSymbol();
}


