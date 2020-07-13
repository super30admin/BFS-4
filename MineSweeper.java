// Time Complexity : O(mn) where m is hte number of rows and n is the number of columns
// Space Complexity : O(1) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : To handle the case where the number of mines occur at the cells.
/* Your code here along with comments explaining your approach: Either BFS or DFS works. count the number of mines you have in the board. If the
click is on a mine M, game is over. Else based on the count of nunmber of mines, if it is equal to 0, then we simply traverse over the E cells and
convert them to B. If the number of mines are >= 1, then we take care on how many mines are there and we display the number on the cell. We need
to take care we only replace the E cells with B. We should not touch any other cell.
*/
// APPROACH 1: USE BFS
class Solution {
    int[][] dirs = new int[][]{{0,1},{1,0},{-1,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(click == null || click.length == 0) return board;
        Queue<int[]> q = new LinkedList<>();
        int r = click[0], c = click[1];
        q.add(click);
        if(board[r][c] == 'M') {                                                                    // Base case on clicking M
            board[r][c] = 'X';                                                                              // Game over, play a better game
            return board;
        } 
        board[r][c] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            r = curr[0];
            c = curr[1];
            int mines = getMines(board, r, c);
            if(mines == 0){                                                                                             // No Mines are present
                for(int[] dir : dirs){
                    int i = dir[0] + curr[0];
                    int j = dir[1] + curr[1];
     if(i >= 0 && j >= 0 && i < board.length && j < board[0].length && board[i][j] == 'E'){                                     // Search for E cells
                            q.add(new int[]{i,j});
                            board[i][j] = 'B';                                                              // Convert the E cells into B cells
                    } 
                } 
                }else {
                        board[r][c] =(char)(mines + '0');                                                           // Convert the cells into the number of mines you can find
                       
                }   
            } 
        return board;
    }
    private int getMines(char[][] board, int i, int j){                                                                     // Count the number of mines you can find
        int count  = 0;
        for(int[] dir : dirs){
            int r = i + dir[0];
            int c = j + dir[1];                                                                                         // Search for the M
             if(r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'M') {
              count++;                                                                                                  // Count the number of M
                }
        }
        return count;
    }
}

// APPROACH 2 : USE DFS
class Solution {
    int[][] dirs = new int[][]{{0,1},{1,0},{-1,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(click == null || click.length == 0) return board;
        Queue<int[]> q = new LinkedList<>();
        int r = click[0], c = click[1];
        q.add(click);
        if(board[r][c] == 'M') {
            board[r][c] = 'X';                                                                                      // Edge case, game over
            return board;
        } 
        board[r][c] = 'B';                                                      
        dfs(board, r, c);                                                                                               // Start exploring
        return board;
    }
    
    private void dfs(char[][] board, int k, int l){
        int mines = getMines(board, k, l);
        if(mines == 0){                                                                                             // No mines are present
            for(int[] dir: dirs){
                int i = dir[0] + k;
                int j = dir[1] + l;
     if(i >= 0 && j >=0 && i < board.length && j < board[0].length && board[i][j] == 'E'){                                          // Find E cells
         board[i][j] = 'B';                                                                                         // Convert E cells into B
         dfs(board, i, j);                                                                                          // Do a DFS from Bth cell
        }
    }
    } else {
            board[k][l] = (char) (mines + '0');                                                                 // Mines are >= 1, replace the number on the board
        }
    }
    private int getMines(char[][] board, int i, int j){
        int count  = 0;
        for(int[] dir : dirs){                                                                                      
            int r = i + dir[0];
            int c = j + dir[1];
             if(r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'M') {                                        // If you find M
              count++;                                                                                          // Count the number of M's
                }
        }
        return count;
    }
}