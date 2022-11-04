class Solution {
    //tc-o(m*n) sc-o(m*n)
    int[][]dirs;int n;int m;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        n = board.length;
        m = board[0].length;
        dirs = new int[][]{{0,1},{0,-1},{-1,0},{1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0],click[1]);

        return board;
    }

    private void dfs(char[][] board,int i, int j)
    {
        //base case
        if(i== n || j == m || i<0 || j < 0 || board[i][j] == 'B') return;


        //logic
        board[i][j] = 'B';
        int count = countMines(board, i, j);
        if(count == 0)
        {
        for(int[] dir : dirs)
        {
            int r = dir[0] + i;
            int c = dir[1] + j;
            dfs(board, r ,c);
        }
        }
        else
        {
         board[i][j] = (char)(count + '0');
        }

    }

    private int countMines(char[][]board,int i,int j)
    {
      int count =0;
      for(int[] dir : dirs)
      {
          int r = dir[0] + i;
          int c = dir[1] + j;

          if(r>=0 && c>=0 && r<n && c<m && board[r][c] == 'M') count++;
      }
      return count;
    }
}