class Solution {
    int[][] dirs; int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length==0) return board;
        dirs = new int[][]{{0,1}, {0, -1}, {-1, 0}, {1,0}, {-1,-1}, {1,1}, {-1, 1}, {1, -1}};
        m = board.length; n = board[0].length;
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click[0], click[1]);
        
        return board;
    }
    
    private void dfs(char[][]board, int i, int j) {
        if(i<0 || i>=m || j<0 || j>= n || board[i][j] != 'E') return;
        
        board[i][j] = 'B';
        int countmines = countMines(board, i, j);
        if(countmines>0) {
            board[i][j] = (char)(countmines +'0');
        } else {
            for(int[] dir: dirs){
                int r = dir[0] + i;
                int c = dir[1] + j;
                dfs(board, r, c);
            }
        }
    }
    
    private int countMines(char[][]board, int i, int j) {
        int count = 0;
        for(int [] dir: dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r>= 0 && r<m && c>=0 && c<n && board[r][c]=='M') count++;
        }
        
        return count;
    }
}