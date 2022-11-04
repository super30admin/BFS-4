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
        Queue<int[]>q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty())
        {
            int[] curr = q.poll();
            int count = countMines(board,curr[0],curr[1]);
            if(count == 0)//process babies
            {
              for(int[] dir : dirs)
              {
                  int r = dir[0] + curr[0];
                  int c = dir[1] + curr[1];
                  if(r>=0 && c>=0 && r<n && c<m && board[r][c] == 'E')
                  {
                      board[r][c] = 'B';
                      q.add(new int[]{r,c});
                  }
              }
            }
            else
            {
                board[curr[0]][curr[1]] = (char) (count + '0');
            }


        }
        return board;

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