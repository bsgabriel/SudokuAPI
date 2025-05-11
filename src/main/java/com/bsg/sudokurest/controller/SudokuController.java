package com.bsg.sudokurest.controller;

import com.bsg.sudokurest.dto.GameResponse;
import com.bsg.sudokurest.dto.ValidationResponse;
import com.bsg.sudokurest.service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @GetMapping("/generate")
    public ResponseEntity<GameResponse> generateGame(@RequestParam(defaultValue = "false", required = false) boolean includeFullGame) {
        return ResponseEntity.ok(sudokuService.generateSudoku(includeFullGame));
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidationResponse> validateSolution(@RequestBody int[] solution) {
        return ResponseEntity.ok(sudokuService.validateSolution(solution));
    }


}
