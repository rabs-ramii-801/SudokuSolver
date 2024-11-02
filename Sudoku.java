class Sudoku {
    private int[][] sudoku;
    private static final int UNASSIGNED = 0;

    // Constructors to initialize the Sudoku grid
    public Sudoku() {
        sudoku = new int[9][9];
    }

    public Sudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    // Checks if a number is present in the specified row
    private boolean containsInRow(int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    // Checks if a number is present in the specified column
    private boolean containsInCol(int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    // Checks if a number is present in the 3x3 box
    private boolean containsInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (sudoku[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if it is safe to assign the number to the cell at (row, col)
    private boolean isAllowed(int row, int col, int number) {
        return !(containsInRow(row, number) || containsInCol(col, number) || containsInBox(row, col, number));
    }

    // Solves the Sudoku puzzle using backtracking
    public boolean solveSudoku() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (sudoku[row][col] == UNASSIGNED) {
                    for (int number = 1; number <= 9; number++) {
                        if (isAllowed(row, col, number)) {
                            sudoku[row][col] = number;
                            if (solveSudoku()) {
                                return true;
                            } else {
                                sudoku[row][col] = UNASSIGNED;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Displays the current state of the Sudoku puzzle
    public void displaySudoku() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Sample Sudoku puzzle (0 represents an unassigned cell)
        int[][] puzzle = {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };

        // Create a Sudoku instance with the puzzle
        Sudoku sudoku = new Sudoku(puzzle);

        System.out.println("Original Sudoku puzzle:");
        sudoku.displaySudoku();

        // Attempt to solve the puzzle
        if (sudoku.solveSudoku()) {
            System.out.println("\nSolved Sudoku puzzle:");
            sudoku.displaySudoku();
        } else {
            System.out.println("\nNo solution exists for the given Sudoku puzzle.");
        }
    }
}
