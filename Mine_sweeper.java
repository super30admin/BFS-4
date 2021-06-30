// Time Complexity : O(M*N)
// Space Complexity : O(M*N) 
// Did this code successfully run on Leetcode : Yes




class Solution {
         
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        
        int row = board.length;
        int col = board[0].length;
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
             return board;
            } 

        int[] dx = {-1, -1, -1, 0,  0,  1, 1, 1};
        int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};
        
        dfs_Visit(click[0], click[1], board, dx, dy);
        
        return board;
    }
     
    private void dfs_Visit(int row, int col, char[][] board, int[] dx, int[] dy){
              
             int count = countMines(row, col, board, dx, dy);
             if(count == 0){
                 board[row][col] = 'B';
           
                for(int k=0; k<8; k++){                
                    int x = row + dx[k];
                    int y = col + dy[k];
                        
                    if(isValid(x, y, board) && board[x][y] == 'E'){
                      
                        dfs_Visit(x, y, board, dx, dy);
                     }                                  
                   }
                }else{
                     board[row][col] = (char)(count + '0');
                 }
         }

       
                
    private boolean isValid(int x, int y, char[][] board){
        return x>=0 && x<board.length && y>=0 && y<board[0].length;
    } 
    
   
    private int countMines(int x, int y, char[][] board, int[] dx, int[] dy){
        int count = 0;
        
        for(int i=0; i<8; i++){
            int new_x = x + dx[i];
            int new_y = y + dy[i];
            
            if(isValid(new_x, new_y, board) && board[new_x][new_y] == 'M')
                count++;
        }
        
        return count;
    }
}

