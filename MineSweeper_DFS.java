/*
 * Time complexity : O(m*n)
 * Space Complexity : O(1)
 * 
 */

class Solution {
    
    int dirs[][] = {{-1,-1}, {-1,0}, {-1,1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    int m;
    int n;
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }
        
        m = board.length;
        n = board[0].length;
        
        int i = click[0];
        int j = click[1];
        
        if(board[i][j] == 'M'){
            board[i][j] = 'X';
            return board;
        }
        
        dfs(board, i, j);
        return board;
    }
    
    private int getMines(char[][] board, int i, int j){
        
        int count = 0;
        
        for(int dir[] : dirs){
            int row = dir[0] + i;
            int col = dir[1] + j;
            
            if(row < 0 || row >= board.length || col < 0 || col >= n){
                continue;
            }
            
            if(board[row][col] == 'M'){
                count++;
            }
        }
        
        return count;
    }
    
    private void dfs(char[][] board, int i, int j){
        
        
        //base case
        if(board[i][j] != 'E'){
            return;
        }
        
        //Logic
        int mines = getMines(board, i, j);
        
        if(mines == 0){
            
            board[i][j] = 'B';
            for(int dir[] : dirs){
                int row = dir[0] + i;
                int col = dir[1] + j;
            
                if(row < 0 || row >= board.length || col < 0 || col >= n){
                    continue;
                }
                
                dfs(board, row, col);
            }
        }else{
            board[i][j] = (char) (mines + '0');
        }
    }
}