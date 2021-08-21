// Time Complexity : O(m * n)
// Space Complexity : O(m * n)

class Solution {
    int m, n;
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return board;
        m = board.length;
        n = board[0].length;
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][]{{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
        dfs(board, click[0], click[1]);
        return board;
    }
    
    private void dfs(char[][] board, int r, int c){
        //base
        if(r < 0 || c < 0 || r == m || c == n || board[r][c] != 'E')
            return;
        //logic
        board[r][c] = 'B';
        int mines = getMines(board, r, c);
        if(mines != 0) {
            board[r][c] = (char)(mines + '0');
        }
        else {
            for(int[] dir: dirs){
                int i = dir[0] + r;
                int j = dir[1] + c;
                dfs(board, i, j);
            }
        }
    }
    
    private int getMines(char[][] board, int r, int c) {
        int count = 0;
        for(int[] dir: dirs){
            int i = dir[0] + r;
            int j = dir[1] + c;
            if(i >= 0 && j >= 0 && i < m && j < n && board[i][j] == 'M')
                count++;
        }
        return count;
    }
}