    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/minesweeper/
    Time Complexity for operators : o(n*m) .. size of matrix
    Extra Space Complexity for operators : o(n*m) for recursive stack
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. 
                    A) Check if click itself is on Mine. If it is then change it to X and return the board.
                    B) If not then  do  the dfs traversal on the  8 directions  starting from given position of click.
                    C) First getthen count of mine by  using getMine. It will check 8 directions on the  current  position
                       and return the numbetr of mines around.
                    D) Now, if mine == 0 then do the dfs  call again in 8 directions and change it to 'B'
                       else, add the number of mines on that position and dont do dfs call.
                    F) At the end, return board.
    */ 

class Solution {
    
    int directions[][] = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board == null || board.length == 0 || board[0].length == 0)
            return board;
        
        int x = click[0];
        int y = click[1];
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }
        
        dfs(board, x, y);
        
        return board;
    }
    
    public void dfs(char[][] board, int i, int j){
        
        int mine = getMine(board, i, j);
        
        if(mine == 0){
            board[i][j] = 'B';
            
            for(int dirs[] : directions){
                int r = i + dirs[0];
                int c = j + dirs[1];
                
                if(r>=0 && r< board.length && c >= 0 && c < board[0].length && board[r][c] == 'E'){
                    dfs(board,r,c);
                }
            }
            
        }else{
           board[i][j] = (char) (mine + '0'); 
        }
        
    }
    
    private int getMine(char[][] board, int i, int j){
        int mine = 0;
        
        for(int dirs[] : directions){
            int r = i + dirs[0];
            int c = j + dirs[1];
            
            if(r>=0 && r< board.length && c >= 0 && c < board[0].length && board[r][c] == 'M'){
                mine++;
            }
        }
        return mine;
    }
}