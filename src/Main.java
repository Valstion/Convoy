public class Main {
    private int[][] board;
    private int size;

    public Main(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    public void setCellState(int row, int col, int state) {
        board[row][col] = state;
    }

    public void nextGeneration() {
        int[][] nextBoard = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int liveNeighbors = countLiveNeighbors(row, col);

                if (board[row][col] == 1) {
                    // Cell is alive
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        // Any live cell with fewer than two live neighbors dies
                        // Any live cell with more than three live neighbors dies
                        nextBoard[row][col] = 0;
                    } else {
                        // Any live cell with two or three live neighbors survives
                        nextBoard[row][col] = 1;
                    }
                } else {
                    // Cell is dead
                    if (liveNeighbors == 3) {
                        // Any dead cell with exactly three live neighbors becomes alive
                        nextBoard[row][col] = 1;
                    }
                }
            }
        }

        board = nextBoard;
    }

    private int countLiveNeighbors(int row, int col) {
        int liveNeighbors = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
                    liveNeighbors += board[newRow][newCol];
                }
            }
        }

        return liveNeighbors;
    }

    public void printBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col] == 1 ? "■ " : "□ ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main game = new Main(10);

        // Set initial cell states (example pattern)
        game.setCellState(1, 5, 1);
        game.setCellState(5, 6, 1);
        game.setCellState(5, 4, 1);
        game.setCellState(1, 5, 1);
        game.setCellState(6, 4, 1);

        // Run the game for 10 generations
        for (int i = 0; i < 100; i++) {
            System.out.println("Generation " + (i + 1));
            game.printBoard();
            game.nextGeneration();
            System.out.println();
        }
    }
}