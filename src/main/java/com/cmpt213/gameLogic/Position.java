package com.cmpt213.gameLogic;

import java.util.Objects;

/**
 * The `Position` class represents a position within the game grid. It is used to specify the row and column of a cell
 * on the grid.
 */
public class Position {
    private int row;
    private int col;

    /**
     * Constructs a new `Position` object with the specified row and column values.
     *
     * @param row The row value.
     * @param col The column value.
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Retrieves the row value of this position.
     *
     * @return The row value.
     */
    public int getRow() {
        return row;
    }

    /**
     * Retrieves the column value of this position.
     *
     * @return The column value.
     */
    public int getCol() {
        return col;
    }

    /**
     * Moves the position one step up.
     *
     * @return A new `Position` object representing the position after moving up.
     */
    public Position moveUp() {
        return new Position(row - 1, col);
    }

    /**
     * Moves the position one step down.
     *
     * @return A new `Position` object representing the position after moving down.
     */
    public Position moveDown() {
        return new Position(row + 1, col);
    }

    /**
     * Moves the position one step left.
     *
     * @return A new `Position` object representing the position after moving left.
     */
    public Position moveLeft() {
        return new Position(row, col - 1);
    }

    /**
     * Moves the position one step right.
     *
     * @return A new `Position` object representing the position after moving right.
     */
    public Position moveRight() {
        return new Position(row, col + 1);
    }

    /**
     * Checks if this `Position` object is equal to another object.
     *
     * @param o The object to compare.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    /**
     * Computes the hash code for this `Position` object.
     *
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Converts this `Position` object to a string representation.
     *
     * @return A string representation in the format "(row, col)".
     */
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    /**
     * Teleports the position to the specified row and column values.
     *
     * @param rowVal    The new row value for the position.
     * @param columnVal The new column value for the position.
     */
    public void teleport(int rowVal, int columnVal) {
        row = rowVal;
        col = columnVal;
    }
}
