// Time Complexity : O(M*N) M: rows N:columns
// Space Complexity : O(M*N) queue size
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
 /* 
 BFS
 1. Do BFS when cell not revealed.
 2. Mark the cell as revealed before insertion in queue to avoid repition of cells in queue.
 3. Then again modify the cell while polling based on getMines value
 */
class Solution {
    int m; int n;
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length == 0) return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1},{-1,1},{1,1},{1,-1},{-1,-1}};
        m = board.length; n = board[0].length;
        Queue<int[]> q = new LinkedList<int[]>();
        board[click[0]][click[1]] = 'B';
        q.add(click);
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0]; int c = curr[1];
            int mines = getMines(board,curr);
            if(mines == 0){
                for(int[] dir : dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc]=='E'){
                        board[nr][nc] = 'B';
                        q.add(new int[] {nr,nc});
                    }
                }
            }
            else{
                board[r][c] = (char)(mines + '0');
            } 
        }
        return board;
    }
    
    private int getMines(char[][] board, int[] index){
        int count =0;
        for(int[] dir : dirs){
            int r = index[0] + dir[0];
            int c = index[1] + dir[1];
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}



// Time Complexity : O(M*N) M: rows N:columns
// Space Complexity : O(M*N) recursive stack size
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
 /* 
 DFS
 1. Do DFS when cell not revealed.
 2. Mark the cell as revealed before start of base case to avoid repition of cells in recursion.
 3. Then again modify the cell in main logic based on getMines value
 */
class Solution {
    int m; int n;
    int[][] dirs;
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length == 0) return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][] {{1,0},{0,1},{-1,0},{0,-1},{-1,1},{1,1},{1,-1},{-1,-1}};
        m = board.length; n = board[0].length;
        dfs(board, click);
        return board;
    }
    
    private void dfs(char[][] board, int[] index){
        int r = index[0]; int c = index[1];
        if(r < 0 || r == m || c < 0 || c == n || board[r][c] != 'E') return;
        board[r][c] = 'B';
        int mines = getMines(board, index);
        if(mines == 0){
            for(int[] dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                dfs(board, new int[] {nr,nc});
            }
        }
        else{
            board[r][c] = (char)(mines + '0');
        }
    }
    
    private int getMines(char[][] board, int[] index){
        int count =0;
        for(int[] dir : dirs){
            int r = index[0] + dir[0];
            int c = index[1] + dir[1];
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}