//Time: O(MN)
//Space: O(MN)

class minesweep {
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return board;
        
        m = board.length;
        n = board[0].length;
        dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {1, 1}, {-1,-1}, {1, -1}, {-1, 1}};
        
        //If the revealed tile is a mine, game over
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        

        dfs(board, click[0], click[1]);
        
        return board;
    }
    
    private void dfs(char[][] board, int i, int j){
        //base
        if(i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'E')
            return;
        
        //logic
        //change current tile to B
        board[i][j] = 'B';
        //get the number of mines
        int mines = getMines(board, i, j);

        //if there are surrounding mines, then we just change tile to number of mines
        if(mines != 0){
            board[i][j] = (char) (mines + '0');
        }
        //we process all surrounding mines to reveal them with number of their surrounding mines
        else{
            for(int[] dir: dirs){
                int nr = dir[0] + i;
                int nc = dir[1] + j;
                dfs(board, nr, nc);
            }          
        }

    }
    
    
    private int getMines(char[][] board, int i , int j){
        int mines = 0;
        for(int[] dir: dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M'){
                mines++;
            }
        }
        
        return mines;
    }
}