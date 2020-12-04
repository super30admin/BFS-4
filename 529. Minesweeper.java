class Solution {//Time and space of O(MN)
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        
        int row = click[0];
        int col = click[1];
        //base 
        if(board[row][col] == 'M'){
            board[row][col] = 'X';
            return board;
        }
        dfs(board,row,col);
        
        return board;
    }
    public void dfs(char[][] board, int i, int j){
        //Check if the i and j are valid
        if(i<0 || i>=board.length || j< 0 || j>= board[0].length || board[i][j] != 'E'){
            return;
        }
        int mines = getMines(board ,i,j);
        
        if(mines == 0){
            board[i][j] = 'B';
            for(int[] dir:dirs){
              int newRow = i + dir[0];
              int newCol = j + dir[1];
              dfs(board,newRow, newCol);
         }
        }else{
            board[i][j] = (char)(mines+'0') ;
        }  
    }
    public int getMines(char[][] board ,int i, int j){
        int count = 0;
        for(int[] dir:dirs){
              int newRow = i + dir[0];
              int newCol = j + dir[1];
              if(newRow<0 || newRow>=board.length || newCol< 0 || newCol>= board[0].length){
                  continue;
              }
            if(board[newRow][newCol] == 'M') count++;
         }
        return count;
    }
}