// Time complexity: O(m * n)
// Space Complexity: O(m * n)

class Solution {
    public int m;
    public int n;
    int[][] dirs = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return null;
        m = board.length;
        n = board[0].length;
        int i=click[0], j=click[1];
        if(board[i][j]=='M'){
            board[i][j]='X';
            return board;
        }
        dfs(board, i, j);
        return board;
    }
    
    private void dfs(char[][] board, int i, int j)
    {
        if(i<0 || i>=board.length || j<0 ||j>=board[0].length|| board[i][j] !='E')
            return;
        int mines = getMines(board, i, j);
        if(mines==0)
        {
            board[i][j]='B';
            for(int[] dir: dirs)
            {
                int r=dir[0]+i;
                int c=dir[1]+j;
                dfs(board, r, c);
            }
        }
        else
            board[i][j]=(char)(mines+'0');
    }
    
    private int getMines(char[][] board, int i, int j)
    {
        int count=0;
        for(int[] dir: dirs)
        {
            int r=dir[0]+i;
            int c=dir[1]+j;
            if(r<0 || r>=board.length || c<0 ||c>=board[0].length)
                continue;
            if(board[r][c]=='M')    count++;
        }
        return count;
        
    }
}