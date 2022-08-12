// Time Complexity : O(n*n)
// Space Complexity : O(n*n)

class Solution {
    int m;
    int n;
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {

        dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };
        m = board.length;
        n = board[0].length;

        if (m == 0)
            return board;

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dfs(board, click[0], click[1]);

        return board;
    }

    private void dfs(char[][] board, int i, int j) {
        // base
        if ((i < 0 || j < 0 || i > m - 1 || j > n - 1) || (board[i][j] == 'B'))
            return;

        // logic
        int mines = countM(board, i, j);
        if (mines > 0) {
            board[i][j] = (char) (mines + '0');
            return;
        } else if (mines == 0) {

            board[i][j] = 'B';
            for (int[] dir : dirs) {

                int row = i + dir[0];
                int col = j + dir[1];
                dfs(board, row, col);
            }

        }

    }

    private int countM(char[][] board, int i, int j) {
        int countMine = 0;
        for (int[] dir : dirs) { // Checking for mines
            int row = i + dir[0];
            int col = j + dir[1];
            if ((row >= 0 && col >= 0 && row < m && col < n) && (board[row][col] == 'M')) {
                countMine++;
            }
        }
        return countMine;
    }
}