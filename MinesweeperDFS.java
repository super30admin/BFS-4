//TC : O(m*n) - m and n are number of rows and columns
//SC : O(1)
class Solution {
    int[][] dirs= {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{-1,-1},{1,-1}};
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return null;
        m = board.length;
        n = board[0].length;
        
        
        
        int i=click[0],j = click[1];
        
        if(board[i][j] == 'M'){
             board[i][j] = 'X';
            return board;
        }
           
        
        dfs(board,i,j);
        
        return board;
        
    }
    
    private void dfs(char[][] board, int i,int j){
        if(i<0 || i>=m || j<0 || j>=n || board[i][j] != 'E')
            return;
        
        int nearbyMines = getMines(board,i,j);
        if(nearbyMines == 0){
             board[i][j] = 'B';
            for(int[] dir : dirs){
                int r = i+dir[0];
                int c = j+dir[1];
                dfs(board,r,c);

            }
        }
        else{
             board[i][j] = (char) (nearbyMines + '0');
            return;
        }
       
        
    }
    
    private int getMines(char[][] board, int i , int j){
        int count = 0;
        for(int[] dir : dirs){
            int r = i+dir[0];
            int c = j+dir[1];
            if(r>=0 && r < m && c >=0 && c <n && board[r][c] == 'M')
                count++;
        }
            
        return count++;
    }
}