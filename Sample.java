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
