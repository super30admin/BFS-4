//TC: O(mXn)
//SC: O(mXn)
class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0) return board;
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        m =board.length;
        n =board[0].length;
        dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1},{-1,1},{-1,-1},{1,-1},{1,1}};

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty())
        {
            int[] curr = q.poll();
            int bombCount = countBomb(board,curr);
            if(bombCount==0)
            {
                for(int[] dir : dirs)
                {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r>=0&&c>=0&&r<m && c<n && board[r][c]=='E')
                    {
                        board[r][c] = 'B';
                        q.add(new int[]{r,c});
                    }
                }
            }
            else
            {
                board[curr[0]][curr[1]] = (char)(bombCount+'0');
            }
        }
        return board;
    }

    public int countBomb(char[][] board,int[] curr){
        int count = 0;

        for(int[] dir : dirs)
        {
            int r = dir[0] + curr[0];
            int c = dir[1] + curr[1];
            if(r>=0&&c>=0&&r<m && c<n && board[r][c]=='M')
            {
                count++;
            }
        }
        return count;
    }
}