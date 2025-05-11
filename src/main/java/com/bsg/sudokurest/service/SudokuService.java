package com.bsg.sudokurest.service;

import com.bsg.sudokurest.dto.GameResponse;
import com.bsg.sudokurest.dto.ValidationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.bsg.sudokurest.util.MatrixUtil.*;

@Service
public class SudokuService {

    private static final int BOARD_SIZE = 9;
    private static final int SECTION_SIZE = 3;

    public GameResponse generateSudoku(boolean includeSolution) {
        var response = new GameResponse();
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

        fillBoard(board);

        if (includeSolution) {
            response.setSolvedGame(matrixToArray(deepCopy(board, BOARD_SIZE)));
        }

        removeCells(board, 40);
        response.setPuzzle(matrixToArray(deepCopy(board, BOARD_SIZE)));

        return response;
    }

    public ValidationResponse validateSolution(int[] solution) {
        if (solution.length < BOARD_SIZE * BOARD_SIZE) {
            var response = new ValidationResponse();
            response.setValid(false);
            response.setMessage("The solution array must contain exactly 81 values between 1 and 9");
            return response;
        }

        boolean containsZero = Arrays.stream(solution)
                .filter(v -> v == 0)
                .findAny()
                .isPresent();

        if (containsZero) {
            var response = new ValidationResponse();
            response.setValid(false);
            response.setMessage("The solution contains empty spaces");
            return response;
        }

        int[][] currentBoard = arrayToMatrix(solution, BOARD_SIZE);
        boolean isValid = true;

        for (int r = 0; r < BOARD_SIZE; r++) {
            if (!isValid) {
                break;
            }

            for (int c = 0; c < BOARD_SIZE; c++) {
                if (!isValid) {
                    break;
                }

                isValid = isValid(currentBoard, r, c, currentBoard[r][c], true);
            }
        }

        var response = new ValidationResponse();
        response.setValid(isValid);
        response.setMessage(!isValid ? "Invalid solution" : "");
        return response;
    }

    private void removeCells(int[][] board, int cellsToRemove) {
        List<int[]> positions = new ArrayList<>();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                positions.add(new int[]{row, col});
            }
        }

        Collections.shuffle(positions);
        int removed = 0;

        for (int[] pos : positions) {
            if (removed >= cellsToRemove) {
                break;
            }

            int row = pos[0];
            int col = pos[1];

            if (board[row][col] == 0) {
                continue;
            }

            int backup = board[row][col];
            board[row][col] = 0;

            int qntSolutions = countSolutions(deepCopy(board, BOARD_SIZE), 0);
            if (qntSolutions == 1) {
                removed++;
            } else {
                board[row][col] = backup;
            }
        }
    }

    private boolean fillBoard(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {

                if (board[row][col] != 0) {
                    continue;
                }

                var numbers = generateShuffledNumbers();
                for (int number : numbers) {
                    if (!isValid(board, row, col, number, false)) {
                        continue;
                    }

                    board[row][col] = number;

                    if (fillBoard(board)) {
                        return true;
                    }

                    board[row][col] = 0;
                }
                return false;
            }
        }

        return true;
    }

    private List<Integer> generateShuffledNumbers() {
        List<Integer> numbers = IntStream.range(1, BOARD_SIZE + 1)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);
        return numbers;
    }

    private boolean isValid(int[][] board, int row, int col, int number, boolean ignoreSelf) {

        // Verifica todas as colunas da linha atual
        for (int c = 0; c < BOARD_SIZE; c++) {
            if (ignoreSelf && c == col) {
                continue;
            }

            if (board[row][c] == number) {
                return false;
            }
        }

        // Verifica todas as linhas da coluna atual
        for (int r = 0; r < BOARD_SIZE; r++) {
            if (ignoreSelf && r == row) {
                continue;
            }

            if (board[r][col] == number) {
                return false;
            }
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        // Verifica se existe um número na sessão (bloco 3x3) atual
        for (int i = 0; i < SECTION_SIZE; i++) {
            for (int j = 0; j < SECTION_SIZE; j++) {
                int r = startRow + i;
                int c = startCol + j;

                if (ignoreSelf && c == col && r == row) {
                    continue;
                }

                if (board[r][c] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private int countSolutions(int[][] board, int count) {
        if (count > 1) {
            return count;
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] != 0) {
                    continue;
                }

                for (int num = 1; num <= BOARD_SIZE; num++) {
                    if (!isValid(board, row, col, num, false)) {
                        continue;
                    }

                    board[row][col] = num;
                    count = countSolutions(board, count);
                    board[row][col] = 0;

                    if (count > 1) {
                        return count;
                    }
                }
                return count;
            }
        }

        return count + 1;
    }

}
