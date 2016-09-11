package com.revinate.minesweeper;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void smallBoardTest() throws Exception {
        Board board = new Board("1,1 2,2");

        /*
         * X = not revealed
         * M = mine
         * number = revealed
         *
         * X X X X X
         * X M X X X
         * X X M X X
         * X X X X X
         * X X X X X
         */

        assertThat(board.reveal(0, 0), is(true));
        assertThat(board.isRevealed(0, 0), is(true));
        assertThat(board.getNumberOfSurroundingMines(0, 0), is(1));

        /*
         * 1 X X X X
         * X M X X X
         * X X M X X
         * X X X X X
         * X X X X X
         */

        assertThat(board.isRevealed(1, 2), is(false));
        assertThat(board.reveal(1, 2), is(true));
        assertThat(board.isRevealed(1, 2), is(true));
        assertThat(board.getNumberOfSurroundingMines(1, 2), is(2));

        /*
         * 1 X X X X
         * X M 2 X X
         * X X M X X
         * X X X X X
         * X X X X X
         */

        assertThat(board.reveal(0, 3), is(true));
        assertThat(board.isRevealed(0, 2), is(true));
        assertThat(board.isRevealed(0, 3), is(true));
        assertThat(board.isRevealed(0, 4), is(true));
        assertThat(board.isRevealed(1, 2), is(true));
        assertThat(board.isRevealed(1, 3), is(true));
        assertThat(board.isRevealed(1, 4), is(true));
        assertThat(board.isRevealed(2, 0), is(true));
        assertThat(board.isRevealed(2, 1), is(true));
        assertThat(board.isRevealed(2, 2), is(false));
        assertThat(board.isRevealed(2, 3), is(true));
        assertThat(board.isRevealed(2, 4), is(true));

        /*
         * 1 X 1 0 0
         * X M 2 1 0
         * 1 2 M 1 0
         * 0 1 1 1 0
         * 0 0 0 0 0
         */

        assertThat(board.reveal(1, 1), is(false));
    }
}
