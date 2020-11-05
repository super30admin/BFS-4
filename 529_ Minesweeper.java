    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/minesweeper/
    Time Complexity for operators : o(n^n) .. n is the length of the string
    Extra Space Complexity for operators : o(n) for (List<String> path) without recursive stack
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. 
                    A) 


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