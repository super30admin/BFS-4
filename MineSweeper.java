class MineSweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        int m= board.length;
        int n = board[0].length;
        if(board[click[0]][click[1]]=='M')
        {
            board[click[0]][click[1]] ='X';
            return board;
        }
         Queue<int[]> q = new LinkedList<>();
         q.add(click);
         board[click[0]][click[1]] ='B';
         while(!q.isEmpty())
         {
            int[] cur = q.poll();
            int count = countMines(board,cur[0],cur[1],dirs);
            if(count==0)
            {
            for(int[] dir:dirs)
            {
                int nr = dir[0]+ cur[0];
                 int nc= dir[1]+cur[1];
                if(nr>=0 && nc>=0 && nr<board.length && nc<board[0].length && board[nr][nc]=='E')
                {
                    q.add(new int[]{nr,nc});
                    board[nr][nc] ='B';
                }
            }
            }
            else{
                board[cur[0]][cur[1]] = (char) ('0' + count);
            }
         }
         return board;
    }

    private int countMines(char[][] board, int i,int j,int[][] dirs)
    {
        int cnt=0;
        for(int[] dir:dirs)
        {
            int nr = dir[0]+i;
            int nc= dir[1]+j;

            if(nr>=0 && nc>=0 && nr<board.length && nc<board[0].length && board[nr][nc]=='M')
            cnt++;
        }
        return cnt;
    }
}

/*
 Time Complexity: O(m * n) where m is the number of rows and n is the number of columns.

Space Complexity: O(m * n) for the BFS queue and auxiliary space.
 */