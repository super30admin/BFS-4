class Solution {
    private static final int[][] DIRS = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},          {0, 1},
        {1, -1},  {1, 0}, {1, 1}
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];

        if (board[row][col] == 'M') {
            board[row][col] = 'X';
        } else {
            dfs(board, row, col);
        }

        return board;
    }

    private void dfs(char[][] board, int row, int col) {
        if (!inBounds(board, row, col) || board[row][col] != 'E') return;

        int count = countMines(board, row, col);
        if (count > 0) {
            board[row][col] = (char) (count + '0');
        } else {
            board[row][col] = 'B';
            for (int[] dir : DIRS) {
                dfs(board, row + dir[0], col + dir[1]);
            }
        }
    }

    private boolean inBounds(char[][] board, int r, int c) {
        return r >= 0 && c >= 0 && r < board.length && c < board[0].length;
    }

    private int countMines(char[][] board, int r, int c) {
        int count = 0;
        for (int[] dir : DIRS) {
            int nr = r + dir[0], nc = c + dir[1];
            if (inBounds(board, nr, nc) && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }
}
