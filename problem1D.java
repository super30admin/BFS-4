class minesWeeper {
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        
        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click[0], click[1]);
        return board;
    }
    
    private void dfs(char[][] board, int i, int j){
        if(i < 0 || i == m || j < 0 || j == n || board[i][j] != 'E'){
            return;
        }
        board[i][j] = 'B';
        int count = countMines(board, i, j);
        if(count > 0){
                board[i][j] = (char) (count + '0');
            }
            else{
                for(int[] dir : dirs){
                    int r = i + dir[0];
                    int c = j + dir[1];
                    dfs(board, r, c);
                }
            }
    }
    
    private int countMines(char[][] board, int r, int c){
        int count = 0;
        for(int[] dir : dirs){
            int i = r + dir[0];
            int j = c + dir[1];
            if(i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'M'){
                count++;
            }
        }
        return count;
    }
}

//time complexity O(m * n)
//space complexity O(m * n) where m and n is the length of board's row & column