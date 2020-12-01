//Time Complexity-O(n^2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        int minLevel=0;
        if(board==null||board.length==0||board[0].length==0)
        {
            return minLevel;
        }
        int n=board.length;
        int m=board[0].length;
        int even=0;
        int i=n-1;
        int index=0;
        int j=0;
        int[]move=new int[n*m];
        while(i>=0 && j>=0)
        {
            if(board[i][j]==-1)
            {
                move[index]=-1;
            }
            else
            {
                move[index]=board[i][j]-1;
            }
            if(even%2==0)
            {
                j++;
            }
            else{
                j--;
            }
            if(j>=m)
            {
                even=even+1;
                j=j-1;
                i=i-1;
            }
            else if(j<0)
            {
                even++;
                i--;
                j++;
            }
            index++;
        }
        Queue<Integer>queue=new LinkedList();
        queue.add(0);
        move[0]=-2;
        while(!queue.isEmpty())
        {
            int size=queue.size();
            for(int y=0;y<size;y++)
            {
                int cur=queue.poll();
                if(cur==n*m-1)
                {
                    return minLevel;
                }
                for(int x=1;x<=6;x++)
                {
                    int nextMove=cur+x;
                    if(nextMove<n*m && move[nextMove]!=-2)
                    {
                        if(move[nextMove]==-1)
                        {
                        queue.add(nextMove);
                        }
                    
                    else
                      {
                        queue.add(move[nextMove]);
                      }
                      move[nextMove]=-2;
                    }
                }
            }
            minLevel++;
        }
        return -1;
    }
}