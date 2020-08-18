// Time Complexity : O(m * n) --> where m & n are the lengths of input matrix
// Space Complexity : O(m * n)
// Did this code successfully run on Leetcode (529): Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

// DFS Solution
class Solution {
    int dirs[][];
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) { 
        dirs = new int[][] {{0,-1}, {0,1}, {-1,0}, {1,0}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
        m = board.length; n = board[0].length;
        int r = click[0]; int c = click[1]; 
        
        // edge case
        if (board == null || board.length == 0) return board;
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        
        dfs(board, r, c);
        return board;
    }
    
    private void dfs(char[][] board, int i, int j) {
        // base case
         if (i < 0 || i == m || j < 0 || j == n || board[i][j] != 'E') return;
        
        // logic
        board[i][j] = 'B';
        int mines = getMines(board, i, j);
        if (mines == 0) { // then only process the childrens
            for (int dir[] : dirs) {
                int r = i + dir[0];
                int c = j + dir[1];
                dfs(board, r, c);
            }
        }
        else board[i][j] = (char) (mines + '0');
    }
    
    private int getMines(char[][] board, int i, int j) {
        int count = 0;
        for (int dir[] : dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if (r < m && r >= 0 && c < n && c >= 0 && board[r][c] == 'M') count++;
        }
        return count;
    }
}