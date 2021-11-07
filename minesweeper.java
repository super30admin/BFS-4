// Time Complexity : O(m x n)
// Space Complexity : O(m x n)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// DFS approach
class Solution {
    int [][] dirs;
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        // edge case
        if(board == null || board.length == 0) return board;
        
        dirs = new int[][]{
            {0, 1}, {0, -1}, {1, 1}, {1, -1},
            {1, 0}, {-1, 0}, {-1,-1}, {-1, 1}
        };
        m = board.length; n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }
    
    private void dfs(char[][] board, int i, int j){
        // base
        if(i < 0 || j < 0 || i == m || j == n || board[i][j] != 'E') return;
        // logic
        board[i][j] = 'B';
        int countMines = countMines(board, i, j);
        if(countMines>0){
            board[i][j] = (char)(countMines + '0');
        } else{
            for(int [] dir: dirs){
                int r = dir[0] + i;
                int c = dir[1] + j;
                dfs(board, r, c);
            }
        }
    }
    
    public int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir: dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}

// *****************************************
// Time Complexity : O(m x n)
// Space Complexity : O(m x n)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// BFS approach
class Solution {
    int [][] dirs;
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        // edge case
        if(board == null || board.length == 0) return board;
        
        dirs = new int[][]{
            {0, 1}, {0, -1}, {1, 1}, {1, -1},
            {1, 0}, {-1, 0}, {-1,-1}, {-1, 1}
        };
        m = board.length; n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int countMines = countMines(board, curr[0], curr[1]);
            if(countMines > 0){
                board[curr[0]][curr[1]] = (char)(countMines + '0');
            } else{
                for(int[] dir: dirs){
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'E'){
                        q.add(new int[] {r,c});
                        board[r][c] = 'B';
                    }
                }
            }
        }
        return board;
    }
    public int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir: dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}