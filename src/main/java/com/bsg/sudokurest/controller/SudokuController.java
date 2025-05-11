package com.bsg.sudokurest.controller;

import com.bsg.sudokurest.dto.GameResponse;
import com.bsg.sudokurest.service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @GetMapping("/generate")
    public ResponseEntity<GameResponse> generateGame(@RequestParam(defaultValue = "false", required = false) boolean includeFullGame) {
        return ResponseEntity.ok(sudokuService.generateSudoku(includeFullGame));
    }


}
