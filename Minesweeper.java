class Minesweeper {
    
    // Time Complexity: O(n * m)
    // Space Complexity: (n * m)
    
    private int[][] dirs = {{1,0},{0,1},{0,-1}, {-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0)
            return board;
        int i = click[0];
        int j = click[1];
        
        if(board[i][j] == 'M'){
            board[i][j] = 'X';
            return board;
        }
        
        dfs(board, i, j);
        return board;
    }
    
    private void dfs(char[][] board, int i, int j){
        int mines = getMines(board, i, j);
        if(mines == 0){
            board[i][j] = 'B';
            for(int [] dir : dirs){
                int row = i + dir[0];
                int col = j + dir[1];
                
                if(row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == 'E')
                    dfs(board, row, col);
            }
        }else
            board[i][j] = (char)(mines + '0');
    }
    
    private int getMines(char[][] board, int i, int j){
        int mines = 0;
        for(int [] dir : dirs){
            int row = i + dir[0];
            int col = j + dir[1];

            if(row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == 'M')
                mines++;
        }
        return mines;
    }
}

