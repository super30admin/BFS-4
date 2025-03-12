/**
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * BFS approach
 */

public class MineSweeper {
    int m, n;
    int dirs[][];

    // ----------------------------- DFS Approach ---------------------------
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) {
            return board;
        }

        m = board.length;
        n = board[0].length;
        dirs = new int[][] {
            {-1, 0}, // U
            {1, 0}, // D
            {0, -1}, // L
            {0, 1}, // R
            {-1, -1}, // UL
            {1, - 1}, // DL
            {-1, 1}, // UR
            {1, 1} // DR
        };

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click);
        return board;
    }

    private void dfs(char[][] board, int[] click) {
        int row = click[0];
        int col = click[1];
        // base
        if (row < 0 || row == m || col < 0 || col == n || board[row][col] != 'E'){
            return;
        }
        // logic
        int countMines = countMines(board, click);
        if (countMines == 0) {
            board[row][col] = 'B';
            for (int[] dir: dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];
                dfs (board, new int[]{nr, nc});
            }
        } else {
            board[row][col] = (char)(countMines + '0');
        }
    }

    private int countMines(char[][] board, int[] click) {
        int count = 0;
        for (int[] dir: dirs) {
            int nr = click[0] + dir[0];
            int nc = click[1] + dir[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }

        return count;
    }


    // ---------------------------- BFS approach ----------------------------

    public static void main(String[] args) {
        // Your code here
    }
}