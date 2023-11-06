package com.cmpt213.gameLogic;

import java.util.Random;

/**
 * The `GameGrid` class represents the game grid for the Tokimon finder game. It manages the grid cells, player's
 * position, and game state.
 */
public class GameGrid {
    private Cell[][] grid;
    private boolean[][] visited;
    private int numToki;
    private int numFoki;
    private int collectedToki;
    private Position playerPosition;

    /**
     * Constructs a new `GameGrid` with the specified number of Tokimons and Fokimons.
     *
     * @param numToki The number of Tokimons on the grid.
     * @param numFoki The number of Fokimons on the grid.
     */
    public GameGrid(int numToki, int numFoki) {
        this.numToki = numToki;
        this.numFoki = numFoki;
        this.grid = new Cell[10][10];
        this.visited = new boolean[10][10];
        this.collectedToki = 0;
    }

    /**
     * Retrieves the game grid.
     *
     * @return The 2D array representing the game grid.
     */
    public Cell[][] getGrid() {
        return grid;
    }

    /**
     * Retrieves the visited cells array.
     *
     * @return The 2D array representing visited cells.
     */
    public boolean[][] getVisited() {
        return visited;
    }

    /**
     * Retrieves the total number of Tokimons in the game.
     *
     * @return The number of Tokimons.
     */
    public int getNumToki() {
        return numToki;
    }

    /**
     * Retrieves the number of Tokimons collected by the player.
     *
     * @return The number of collected Tokimons.
     */
    public int getCollectedToki() {
        return collectedToki;
    }

    /**
     * Increases the count of collected Tokimons by 1.
     */
    public void collctedTokiIncrease() {
        collectedToki++;
    }

    /**
     * Retrieves the player's current position on the grid.
     *
     * @return The `Position` object representing the player's position.
     */
    public Position getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Initializes the game grid by randomly placing Tokimons, Fokimons, and empty cells, and sets the player's initial position.
     *
     * @param playerInitialRow    The initial row for the player.
     * @param playerInitialColumn The initial column for the player.
     */
    public void initializeGrid(int playerInitialRow, int playerInitialColumn) {
        Random random = new Random();
        this.playerPosition = new Position(playerInitialRow, playerInitialColumn);

        for (int i = 0; i < numToki; i++) {
            int row, col;
            do {
                row = random.nextInt(10);
                col = random.nextInt(10);
            } while (grid[row][col] != null);
            grid[row][col] = new Tokimon();
        }

        for (int i = 0; i < numFoki; i++) {
            int row, col;
            do {
                row = random.nextInt(10);
                col = random.nextInt(10);
            } while (grid[row][col] != null);
            grid[row][col] = new Fokimon();
        }
        
        if (!visited[playerPosition.getRow()][playerPosition.getCol()])
        {
            visited[playerPosition.getRow()][playerPosition.getCol()] = true;
            if (grid[playerPosition.getRow()][playerPosition.getCol()] instanceof Tokimon) {
                // Player finds a Tokimon
                collectedToki++;
            }
        }
    }

    /**
     * Moves the player in the specified direction and updates the player's position and game state.
     *
     * @param direction The direction in which the player should move ("W" for up, "A" for left, "S" for down, "D" for right).
     */
    public void movePlayer(String direction) {
        Position newPosition = playerPosition;

        switch (direction) {
            case "W":
                if (newPosition.getRow() - 1 > -1) {
                    newPosition = playerPosition.moveUp();
                } else {
                    System.out.println("Impossible to go there!");
                }
                break;
            case "A":
                if (newPosition.getCol() - 1 > -1) {
                    newPosition = playerPosition.moveLeft();
                } else {
                    System.out.println("Impossible to go there!");
                }
                break;
            case "S":
                if (newPosition.getRow() + 1 < 10) {
                    newPosition = playerPosition.moveDown();
                } else {
                    System.out.println("Impossible to go there!");
                }
                break;
            case "D":
                if (newPosition.getCol() + 1 < 10) {
                    newPosition = playerPosition.moveRight();
                } else {
                    System.out.println("Impossible to go there!");
                }
                break;
        }
        playerPosition = newPosition;

        if (!visited[newPosition.getRow()][newPosition.getCol()]) {
            visited[newPosition.getRow()][newPosition.getCol()] = true;
            if (grid[newPosition.getRow()][newPosition.getCol()] instanceof Tokimon) {
                // Player finds a Tokimon
                collectedToki++;
            }
        }
    }

    /**
     * Teleports the player to the specified row and column, updating the player's position and game state.
     *
     * @param row    The new row for the player.
     * @param column The new column for the player.
     */
    public void teleportPlayer(int row, int column) {
        playerPosition.teleport(row, column);
        if (!visited[playerPosition.getRow()][playerPosition.getCol()]) {
            visited[playerPosition.getRow()][playerPosition.getCol()] = true;
            if (grid[playerPosition.getRow()][playerPosition.getCol()] instanceof Tokimon) {
                // Player finds a Tokimon
                collectedToki++;
            }
        }
    }

    /**
     * Checks if a specific cell on the grid has been visited by the player.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return `true` if the cell has been visited, `false` otherwise.
     */
    public boolean isVisited(int row, int col) {
        return visited[row][col];
    }

    /**
     * Marks a specific cell on the grid as visited.
     *
     * @param row The row of the cell to mark.
     * @param col The column of the cell to mark.
     */
    public void setVisited(int row, int col) {
        visited[row][col] = true;
    }

    /**
     * Retrieves the cell at the specified row and column on the grid.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return The `Cell` object representing the cell at the specified location.
     */
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Checks if the game has been won by the player.
     *
     * @return `true` if the player has collected all Tokimons, `false` otherwise.
     */
    public boolean isGameWon() {
        return collectedToki == numToki;
    }

    /**
     * Checks if the game has been lost by the player.
     *
     * @return `true` if the player has landed on a Fokimon cell, `false` otherwise.
     */
    public boolean isGameLost() {
        return grid[playerPosition.getRow()][playerPosition.getCol()] instanceof Fokimon;
    }
}
