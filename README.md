# Sudoku API

## Description

This API allows for the generation of Sudoku boards and solution validation.

## Endpoints

### 1. Generate Sudoku

- **URL:** `/sudoku/generate`
- **Method:** GET
- **Optional Parameter:**
  - `includeSolution` (boolean): If set to `true`, returns the complete board without empty spaces.
- **Example Call:**

#### Without full game (default)
Both of the following will work:
```
GET /sudoku/generate
GET /sudoku/generate?includeSolution=false
```

```json
{
  "puzzle": [ 8, 0, 9, 0, 0, 5, 0, 0, 3, 2, 4, 7, 9, 0, 3, 5, 8, 6, 0, 0, 3, 0, 7, 0, 1, 0, 2, 6, 2, 0, 4, 3, 9, 7, 1, 0, 0, 3, 0, 0, 0, 0, 2, 6, 0, 1, 0, 0, 2, 5, 6, 0, 4, 0, 4, 0, 1, 3, 0, 0, 8, 5, 0, 0, 0, 2, 0, 0, 8, 0, 3, 0, 0, 0, 6, 5, 0, 0, 0, 0, 0 ],
  "fullGame": null
}
```

#### Including full game
```
GET /sudoku/generate?includeSolution=true
```
    
```json
{
  "puzzle": [ 8, 0, 9, 0, 0, 5, 0, 0, 3, 2, 4, 7, 9, 0, 3, 5, 8, 6, 0, 0, 3, 0, 7, 0, 1, 0, 2, 6, 2, 0, 4, 3, 9, 7, 1, 0, 0, 3, 0, 0, 0, 0, 2, 6, 0, 1, 0, 0, 2, 5, 6, 0, 4, 0, 4, 0, 1, 3, 0, 0, 8, 5, 0, 0, 0, 2, 0, 0, 8, 0, 3, 0, 0, 0, 6, 5, 0, 0, 0, 0, 0 ],
  "fullGame": [ 8, 1, 9, 6, 2, 5, 4, 7, 3, 2, 4, 7, 9, 1, 3, 5, 8, 6, 5, 6, 3, 8, 7, 4, 1, 9, 2, 6, 2, 5, 4, 3, 9, 7, 1, 8, 9, 3, 4, 7, 8, 1, 2, 6, 5, 1, 7, 8, 2, 5, 6, 3, 4, 9, 4, 9, 1, 3, 6, 2, 8, 5, 7, 7, 5, 2, 1, 9, 8, 6, 3, 4, 3, 8, 6, 5, 4, 7, 9, 2, 1 ]
}
```

### 2. Validate Solution

- **URL:** `/sudoku/validate`
- **Method:** POST
- **Request Body:**

  ```json
  {
    "solution": [ 8, 1, 9, 6, 2, 5, 4, 7, 3, 2, 4, 7, 9, 1, 3, 5, 8, 6, 5, 6, 3, 8, 7, 4, 1, 9, 2, 6, 2, 5, 4, 3, 9, 7, 1, 8, 9, 3, 4, 7, 8, 1, 2, 6, 5, 1, 7, 8, 2, 5, 6, 3, 4, 9, 4, 9, 1, 3, 6, 2, 8, 5, 7, 7, 5, 2, 1, 9, 8, 6, 3, 4, 3, 8, 6, 5, 4, 7, 9, 2, 1 ]
  }
  ```
* **Response:**

  ```json
  {
    "valid": true,
    "message": ""
  }
  ```
  
## Links
- Generate puzzle: https://sudokuapi-production.up.railway.app/sudoku/generate
- Validation: http://sudokuapi-production.up.railway.app/sudoku/validation
- Documentation: http://sudokuapi-production.up.railway.app/swagger-ui.html