package com.bsg.sudokurest.dto;

public class GameResponse {

    private int[] puzzle;
    private int[] solvedGame;

    public int[] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[] puzzle) {
        this.puzzle = puzzle;
    }

    public int[] getSolvedGame() {
        return solvedGame;
    }

    public void setSolvedGame(int[] solvedGame) {
        this.solvedGame = solvedGame;
    }
}
