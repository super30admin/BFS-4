package BFS-4;
// TC: O(m*n)
// SC: O(m*n)
public class Problem1 {
    int m,n;
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.m = board.length;
        this.n = board[0].length;
        this.dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1} ,{1,1}, {1,-1},{-1,1},{-1,-1}};
        int r = click[0];
        int c = click[1];
        if(board[r][c] == 'M'){
            board[r][c] = 'X';
            return board;
        }
        dfs(board, r,c);
        return board;
    }

    private void dfs(char[][] board, int i, int j){
        //base case
        if(i<0 || j < 0 || i==m || j ==n || board[i][j]!='E'){
            return;
        }
        //logic
        board[i][j] = 'B';
        int mineCount = countMines(board, i, j);
        if(mineCount>0){
            board[i][j] = (char) (mineCount+'0');
        }else{
            for(int[] dir: dirs){
                int nr = i+dir[0];
                int nc = j+dir[1];
                dfs(board,nr,nc);
            }
        }
    }

    private int countMines(char[][] board, int i, int j){
        int count=0;
        for(int[] dir: dirs){
            int nr = i+dir[0];
            int nc = j+dir[1];
            if(nr<m && nc < n && nr>=0 && nc >=0 && board[nr][nc]=='M'){
                count++;
            }
        }
        return count;
    }
}
