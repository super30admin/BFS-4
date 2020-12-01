//Time Complexity-O(n*m)
//Space Complexity-O(n*m

class Solution {
    int[][] dirs={{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null||board.length==0||board[0].length==0)
        {
            return board;
        }
        int i=click[0];
        int j=click[1];
        if(board[i][j]=='M')
        {
            board[i][j]='X';
            return board;
        }
        dfs(i,j,board);
        return board;
    }
    private void dfs(int i,int j,char[][]board)
    {
        int mine=countmine(board,i,j);
        if(mine==0)
        {
            board[i][j]='B';
            for(int[]dir:dirs)
            {
                int r=i+dir[0];
                int c=j+dir[1];
                if(r>=0&&r<board.length&&c>=0&&c<board[0].length&&board[r][c]=='E')
                {
                    dfs(r,c,board);
                }
            }
        }
        else{
            board[i][j]=(char)(mine+'0');
        }
    }
    private int countmine(char[][]board,int i,int j)
    {
        int mineCount=0;
        for(int[]dir:dirs)
        {
            int r=i+dir[0];
                int c=j+dir[1];
                if(r>=0&&r<board.length&&c>=0&&c<board[0].length&&board[r][c]=='M')
                {
                    mineCount++;
                }
        }
        return mineCount;        
    }
}