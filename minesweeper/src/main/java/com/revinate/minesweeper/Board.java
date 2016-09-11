package com.revinate.minesweeper;

import java.util.Arrays;

public class Board {
    // -1 - not revealed, -2 - mine, number >= 0 - revealed
    private static final int N = 30;
    private static final int[] offset = {-1, 0, 1};
    int[][] board;

    /**
     * Creates a game board with a input with the following format
     * <p>
     * x1,y1 x2,y2 x3,y3
     * <p>
     * Example: 2,2 4,3 6,4
     */
    public Board(String coordinates) {
        board = new int[N][N];
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
        String[] strs = coordinates.split(" ");
        for (String str : strs) {
            String[] xy = str.split(",");
            int x = Integer.parseInt(xy[0]), y = Integer.parseInt(xy[1]);
            board[x][y] = -2;
        }
    }

    /**
     * Reveals a specific point.
     *
     * @param x The x position
     * @param y The y position
     * @return false if the point contains a mine
     */
    public boolean reveal(int x, int y) {
        if (board[x][y] == -2) return false;
        int numOfSurroundingMines = getNumberOfSurroundingMines(x, y);
        board[x][y] = numOfSurroundingMines;
        if (numOfSurroundingMines == 0) {
            for (int i : offset) {
                for (int j : offset) {
                    int newX = x + i, newY = y + j;
                    if (newX < 0 || newY < 0 || newX >= N || newY >= N || board[newX][newY] != -1) continue;
                    board[newX][newY] = getNumberOfSurroundingMines(newX, newY);
                    if (getNumberOfSurroundingMines(newX, newY) == 0) reveal(newX, newY);
                }
            }
        } 
        return true;
    }

    /**
     * Checks if the current point has been revealed already.
     *
     * @param x The x position
     * @param y The y position
     * @return true if the point has been revealed
     */
    public boolean isRevealed(int x, int y) {
        return board[x][y] >= 0;
    }

    /**
     * Gets the number of mines surrounding this specific coordinate.
     *
     * @param x The x position
     * @param y The y position
     * @return The number of mines in this position or -1 if the position itself is a mine
     */
    public int getNumberOfSurroundingMines(int x, int y) {
        if (board[x][y] == -2) return -1;
        int cnt = 0;
        for (int i : offset) {
            for (int j : offset) {
                int newX = x + i, newY = y + j;
                if (newX < 0 || newY < 0 || newX >= N || newY >= N) continue;
                if (board[newX][newY] == -2) cnt++;
            }
        }
        return cnt;
    }
}
