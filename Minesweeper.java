// O(n^2) time and space

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int click_row = click[0];
        int click_col = click[1];
        //game is over
        if (board[click_row][click_col] == 'M') {
            board[click_row][click_col] = 'X';
            return board;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];

        return revealSquare(board, click_row, click_col, visited);
    }

    private char[][] revealSquare(char[][] board, int click_row, int click_col, boolean[][] visited ) {
        if (visited[click_row][click_col]) {
            return board;
        }
        int mines = numberOfMines(board, click_row, click_col);
        if (mines > 0) {
            board[click_row][click_col] = (char) (mines + '0');
        } else if (board[click_row][click_col] == 'E') {
            board[click_row][click_col] = 'B';
        }
        visited[click_row][click_col] = true;
        for (int row = -1; row < 2; row++) {
            for (int col = -1; col < 2; col++) {
                int x = click_row + row;
                int y = click_col + col;
                if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                    continue;
                }
                if (board[click_row][click_col] == 'B') {
                    revealSquare(board, x, y, visited);
                }
            }
        }
        return board;
    }

    private int numberOfMines(char board[][], int click_row, int click_col ) {
        int mines = 0;
        for (int row = -1; row < 2; row++) {
            for (int col = -1; col < 2; col++) {
                int x = click_row + row;
                int y = click_col + col;
                if (row == 0 && col == 0) {
                    continue;
                }
                if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                    continue;
                }
                if (board[x][y] == 'M') {
                    mines++;
                }
            }
        }
        return mines;
    }
}