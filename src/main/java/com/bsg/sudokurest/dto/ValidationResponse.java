package com.bsg.sudokurest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents the result of a Sudoku solution validation.")
public class ValidationResponse {

    @Schema(description = "Indicates whether the Sudoku solution is valid.")
    private boolean valid;

    @Schema(description = "Provides additional information about the validation result.")
    private String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
