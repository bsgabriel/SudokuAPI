package com.bsg.sudokurest.dto;

public class GameResponse {

    private int[] puzzle;
    private int[] fullGame;

    public int[] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(int[] puzzle) {
        this.puzzle = puzzle;
    }

    public int[] getFullGame() {
        return fullGame;
    }

    public void setFullGame(int[] fullGame) {
        this.fullGame = fullGame;
    }
}
