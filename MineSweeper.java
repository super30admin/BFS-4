class Solution {
    //Time O(m*n)
    //Space O(m*n)
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
        {
            return board;
        }
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int temp = reveal(board , click[0] , click[1]);
        if( temp > 0)
        {
            board[click[0]][click[1]] = (char)(temp + '0');
            return board;
        }
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[] {click[0] , click[1]});
        board[click[0]][click[1]] = 'B';
        while(!Q.isEmpty())
        {
            int[] ar = Q.poll();
            for(int[] dir : dirs)
            {
                int i = dir[0] + ar[0];
                int j = dir[1] + ar[1];
                if(i >=0 && i< board.length && j >=0 && j < board[0].length && board[i][j] == 'E')
                {
                    int count = reveal(board , i , j );
                    if(count > 0) board[i][j] = (char)(count + '0');
                    else 
                    {
                        board[i][j] = 'B';
                        Q.add(new int[]{i,j});
                    }
                }
            }
        }
        return board;
    }
    private int reveal(char[][] board , int row , int col)
    {
        int count = 0;
        for(int[] dir : dirs)
        {
            int i = dir[0] + row;
            int j = dir[1] + col;
            if(i >=0 && i< board.length && j >=0 && j < board[0].length && board[i][j] == 'M')
            {
                count++;
            }
        }
        return count;
    }
}