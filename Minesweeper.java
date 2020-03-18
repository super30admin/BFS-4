//TC - O(m*n)
//SC - O(m*n)
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int[]> q = new LinkedList();
        q.add(click);
        
        board[click[0]][click[1]] = '#';
        
        int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}, {1,-1}, {-1,1}, {1,1}, {-1,-1}};
        
        while(!q.isEmpty())
        {
            int[] curr = q.poll();
            
            int cnt = 0;
            
            for(int[] dir : dirs)
            {
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];
                
                if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') ++cnt;
            }
            
            if(cnt > 0)
            {
                board[curr[0]][curr[1]] = (char)(cnt + '0');
            }
            else
            {
                board[curr[0]][curr[1]] = 'B';
            }
            
            if(board[curr[0]][curr[1]] == 'B')
            {
                for(int[] dir : dirs)
                {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];

                    if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E')
                    {
                        board[r][c] = '#';
                        q.add(new int[]{r,c});
                    }
                }
            }
            
        }
        
        return board;
    }
}
