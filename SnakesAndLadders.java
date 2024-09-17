// Time complexity: O(m * n)  where m and n are no. of rows and cols in board respectively.
// Space Complexity: O(m * n)

class Solution {

    public int snakesAndLadders(int[][] board) {
        if (board==null && board.length == 0)
            return 0;
        int N = board.length * board[0].length;
        int r=board.length;
        int c=board[0].length;
        int[] moves = new int[N];
        int i=r-1;
        int j=0;
        int idx=0;
        int even=0;
        
        //process -> convert 2D array to 1D
        while(i>=0 && j>=0)
        {
            if(board[i][j]==-1) 
                moves[idx]=-1;
            else
                moves[idx]=board[i][j] -1;
            idx++;
            if(even%2==0)
                j++;
            else
                j--;
            if(j>=c)
            {
                i--;
                even++;
                j--;
            }
            else if(j<0)
            {
                i--;
                even++;
                j++;
            }
        }
        int minMove=0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0]=-2;    //mark it visited
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int l=0; l<size; l++)
            {
                int curr = q.poll();
                if(curr==N-1) return minMove; //reached final
                for(int k=1; k<7; k++)
                {
                    int next = k+curr;
                    if(next<N && moves[next]!=-2)
                    {
                        if(moves[next] > -1)    q.add(moves[next]); //you reached a snake or a ladder
                        else    
                        {
                            q.add(next);
                        }
                        moves[next]=-2;
                    }
                }
            }
            minMove++; 
        }
        return -1;
    }
}