class Solution {
    // Approach: DFS
    // TC: O(M*N)
    // SC: O(M*N)
    
    public char[][] updateBoard(char[][] board, int[] click) {
        // Base case
        if(board == null || board.length == 0 || board[0].length == 0)
            return board;
        
        // Checking for the min at the click position
        int x = click[0], y = click[1];
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }
        
        // Traversing the baord
        dfs(board, x, y);
        
        return board;
    }
    
    int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
    
    public void dfs(char[][] board, int x, int y){
        
        int mines = getMines(board, x, y);
        
        // If no mine found, then traversing all directions at that x,y position
        if(mines == 0){
            board[x][y] = 'B';      
           
            for(int[] dir : directions){
                int i = x + dir[0];
                int j = y + dir[1];
            
                if(i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 'E'){
                    dfs(board, i, j);
                }
            }
        } else 
            board[x][y] = (char)(mines + '0');          // If mine found, updating the count
    }
    
    
    // Method to find number of mines at a given position
    public int getMines(char[][] board, int x, int y){
        int i = 0, j = 0, mines = 0;
        for(int[] dir : directions){
            i = x + dir[0];
            j = y + dir[1];
            
            if(i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 'M'){
                mines++;
            }
        }
        return mines;
    }
}