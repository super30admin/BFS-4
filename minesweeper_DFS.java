// Time Complexity : O(m*n) where m and n are the dimensions of the given board
// Space Complexity : O(m*n) where m and n are the dimensions of the given board
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class minesweeper_DFS {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{1,1},{-1,1},{1,-1}};
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) return board;
        m = board.length;
        n = board[0].length;
        int i = click[0], j = click[1];
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }
        dfs(board, i, j);
        return board;
    }
    private void dfs(char[][] board, int i, int j) {
        // base case
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'E') return;
        // logic
        int mines = getMines(board, i, j);
        if (mines == 0) {
            board[i][j] = 'B';
            for (int[] dir : dirs) {
                int r = dir[0] + i;
                int c = dir[1] + j;
                dfs(board, r, c);
            }
        } else {
            board[i][j] = (char) (mines + '0');
        }
    }
    private int getMines(char[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') {
                count++;   
            }
        }
        return count;
    }
}