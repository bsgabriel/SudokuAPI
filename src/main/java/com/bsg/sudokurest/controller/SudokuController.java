package com.bsg.sudokurest.controller;

import com.bsg.sudokurest.dto.GameResponse;
import com.bsg.sudokurest.dto.ValidationResponse;
import com.bsg.sudokurest.service.SudokuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @GetMapping("/generate")
    @Operation(description = "Returns a Sudoku puzzle. 0 indicates an empty space.")
    public ResponseEntity<GameResponse> generateGame(@Parameter(description = "Indicates whether the solved game should be returned along with the puzzle.")
                                                     @RequestParam(defaultValue = "false", required = false) boolean includeSolution) {
        return ResponseEntity.ok(sudokuService.generateSudoku(includeSolution));
    }

    @PostMapping("/validate")
    @Operation(description = "Validates a given solution.")
    public ResponseEntity<ValidationResponse> validateSolution(@Parameter(description = "User's solution. It should contain exactly 81 items.")
                                                               @RequestBody int[] solution) {
        return ResponseEntity.ok(sudokuService.validateSolution(solution));
    }

}

