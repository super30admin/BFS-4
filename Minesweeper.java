//Time Complexity: O(n*m*8) -> O(n*m)
//Space Complexity: O(n)
// DFS
public class Minesweeper {
    int m,n;
    int dirs[][];
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null)
            return null;
        
        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{0,1}, {0, -1}, {1, 0}, {-1, 0}, {-1,-1}, {1, 1}, {-1, 1}, {1, -1}};
        
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int []> q = new LinkedList<>();
        q.offer(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty())
        {
            int curr[] = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if(count > 0)
            {
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
            else
            {
                for(int dir[] : dirs)
                {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E')
                    {
                        board[r][c] = 'B';
                        q.offer(new int[]{r,c});
                    }
                }
            }
        }
        
        return board;
    }
    
    private int countMines(char[][] board, int r, int c)
    {
        int count = 0;
        for(int dir[]: dirs)
        {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M')
            {
               count++;
            }
        }
        return count;
    }
}

//Time Complexity: O(n*m*8) -> O(n*m)
//Space Complexity: O(n)
// DFS
public class Minesweeper {
    int m,n;
    int dirs[][];
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null)
            return null;
        
        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{0,1}, {0, -1}, {1, 0}, {-1, 0}, {-1,-1}, {1, 1}, {-1, 1}, {1, -1}};
        
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click[0], click[1]);
        
        return board;
    }
    
    private void dfs(char[][] board, int r, int c)
    {
        // base
        if(r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'E')
        {
           return;
        }
        
        // logic
        // visited
        board[r][c] = 'B';
        int count = countMines(board, r, c);
        if(count > 0)
        {
            board[r][c] = (char)(count + '0');
        }
        else
        {
            for(int dir[]: dirs)
            {
                int nr = r + dir[0];
                int nc = c + dir[1];
                dfs(board, nr, nc);
            }
        }
    }
    
    private int countMines(char[][] board, int r, int c)
    {
        int count = 0;
        for(int dir[]: dirs)
        {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M')
            {
               count++;
            }
        }
        return count;
    }
}
