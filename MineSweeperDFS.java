// Time Complexity : O(m*n)  -> 8 times O(m*n) (for all 8 directions)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes

public class MineSweeperDFS {
    int[][] dirs;
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,-1},{-1,1},{1,1},{-1,-1}};

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board,click[0],click[1]);
        return board;
    }

    private void dfs(char[][] board, int i, int j){
        //base
        if(i<0 || j<0 || i==m || j==n || board[i][j] != 'E' ) return;

        //logic
        board[i][j] = 'B';
        int count = countMines(board, i, j);
        if(count == 0)
        {
            for(int[] dir: dirs){
                int r = i+dir[0];
                int c = j+dir[1];
                dfs(board,r,c);
            }
        }else{
            board[i][j] =(char)(count+'0');
        }

    }
    private int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir: dirs){
            int r = i+dir[0];
            int c = j+dir[1];
            if(r>=0 && r<m && c>=0 && c< n && board[r][c]=='M') count++;
        }
        return count;
    }
}