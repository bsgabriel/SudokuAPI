package com.bsg.sudokurest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Contains the generated puzzle and the solved game if requested.")
public class GameResponse {

    @Schema(description = "The puzzle to be solved.")
    private int[] puzzle;

    @Schema(description = "The solved puzzle. It's only present if the 'includeFullGame' is set to true.")
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
