// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//529. Minesweeper
//https://leetcode.com/problems/minesweeper/

class Solution {
    // DFS
    // time: O(n*n)
    // space: O(n*n)

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

/*
class Solution {
    // BFS
    // time: O(n*n)
    // space: O(n*n)
    public char[][] updateBoard(char[][] board, int[] click) {

        // Queue
        Queue<int[]> q = new LinkedList<>();
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 },
                { 1, 1 } };

        if (m == 0)
            return board;

        q.add(new int[] { click[0], click[1] });

        while (!q.isEmpty()) {

            int countMine = 0;
            int[] temp = q.poll();

            int i = temp[0];
            int j = temp[1];

            if (board[i][j] == 'M') {
                board[i][j] = 'X';
                return board;
            }

            for (int[] dir : dirs) { // Checking for mines
                int row = i + dir[0];
                int col = j + dir[1];
                if ((row >= 0 && col >= 0 && row < m && col < n) && (board[row][col] == 'M')) {
                    countMine++;
                }
            }

            if (countMine > 0)
                board[i][j] = (char) (countMine + '0');
            else if (countMine == 0) {
                board[i][j] = 'B';
                for (int[] dir : dirs) {

                    int row = i + dir[0];
                    int col = j + dir[1];
                    if ((row >= 0 && col >= 0 && row < m && col < n) && (board[row][col] == 'E')) {
                        board[row][col] = 'B';
                        q.add(new int[] { row, col });
                    }

                }
            }

        }
        return board;

    }
}
*/
