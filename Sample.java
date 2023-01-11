Problem-909 (Snakes and ladders)
TC-O(n*n)

Solution-
  
class Solution
{
    public int snakesAndLadders(int[][] board)
    {
        if(board.length==0 || board==null) return 0;
        int n=board.length;
        int[] grid= new int[n*n];
        Queue<Integer> q = new LinkedList<>();
        int row=n-1;
        int col=0;
        int index=0,even=0,moves=0;
        while(index<n*n)
        {
            if(board[row][col]==-1)
            {
                grid[index]=-1;
            }
            else
            {
                grid[index]=board[row][col]-1;
            }
            index++;
            if(even%2==0)
            {
                col++;
                if(col==n)
                {
                    col--;
                    row--;
                    even++;
                }
            }
            else
            {
                col--;
                if(col==-1)
                {
                    col++;
                    row--;
                    even++;
                }
            }
        }
        q.add(0);
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int i=0; i<size;i++)
            {
                int curr=q.poll();
                if(curr==n*n-1)
                {
                    return moves;
                }
                for(int j=1;j<=6;j++)
                {
                    int baby=curr+j;
                    if(baby> n*n-1)
                    {
                        continue;
                    }
                    if(grid[baby]==-1)
                    {
                        q.add(baby);
                        grid[baby]=-2;
                    }
                    else if(grid[baby]>0)
                    {
                        q.add(grid[baby]);
                        grid[baby]=-2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
    
}













Problem-529 (Minesweeper)
TC and SC-O(n*m)

Solution-
  
class Solution {
    int[][] dirs = {{-1,0},{0,-1},{-1,-1},{1,1},{-1,1},{1,-1},{0,1},{1,0}};
    public char[][] updateBoard(char[][] board, int[] click)
    {
        if(board == null || board.length == 0) return board;
        int m = board.length;
        int n = board[0].length;
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        board[click[0]][click[1]] = 'B';
        q.add(click);
        while(!q.isEmpty())
        {
            int[] curr = q.poll();
            int mines = neighborMines(curr,board, m,n);
            if(mines>0)
            {
                board[curr[0]][curr[1]] = (char)(mines+'0');
            }
            else
            {
                for(int[] dir: dirs)
                {
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc] == 'E'){
                    board[nr][nc]='B';
                    q.add(new int[]{nr,nc});
                }
            }
        }
            
    }
        return board;
}
    
    private int neighborMines(int[] curr, char[][] board, int m, int n)
    {
        int count = 0;
        for(int[] dir: dirs)
        {
                int nr = curr[0]+dir[0];
                int nc = curr[1]+dir[1];
                if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc] == 'M')
                {
                    count++;
                }
        }
        return count;
    }
}
