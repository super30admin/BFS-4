class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0)
            return board;
        helper(board,click);
        return board;
    }
    
    private void helper(char[][]board, int[]click)
    {  
        int i = click[0];
        int j = click[1];
        int[][] dirs = {{0,1}, {0,-1}, {1,0},{-1,0},{1,1},{1,-1}, {-1,1},{-1,-1}};
        if(board[i][j]=='M')
        {
            board[i][j]='X';
            return;
        }
        if(board[i][j]=='E')
        {
            int count =0;
            
            for(int [] d:dirs)
            {
                int newi = i+d[0];
                int newj = j+d[1];
                
                if(newi>=0 && newi<board.length && newj>=0 && newj<board[0].length)
                {
                    if(board[newi][newj]=='M')
                    {   
                        count++;  
                    }
                        
                }  
            }
             if(count>0)
                {
                    board[i][j]= (char)(count+'0');
                }
                else
                { 
                    
               for(int [] d:dirs)
                { board[i][j]='B';
                   int newi = i+d[0];
                   int newj = j+d[1];
                
                   if(newi>=0 && newi<board.length && newj>=0 && newj<board[0].length)
                     {
                    
                        helper(board, new int[]{newi,newj});
               
                     }  
                }
                        
                }
        }
        
    }
}