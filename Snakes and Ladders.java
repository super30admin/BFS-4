//time complexity:O(m*n)
//space complexity:O(m*n)

class Solution {
    public int snakesAndLadders(int[][] board) {
        //edge
        if(board==null || board.length==0) return 0;
        int m=board.length;
        int minMoves=0;
        int moves[]=new int[m*m];
        Queue<Integer>q=new LinkedList<>();
        int i=m-1;
        int j=0; int idx=0; int isEven=0;
        while(i>=0&&j>=0)
        {
            if(board[i][j]==-1)
            {
                moves[idx]=-1;
            }
            else
            {
                moves[idx]=board[i][j]-1;
            }
            idx++;
            if(isEven%2==0)
            {
                j++;
            }
            else
            {
                j--;
            }
            if(j<0)
            {
                i--;j++;isEven++;
            }
            else if(j==m)
            {
                i--;j--;isEven++;
            }
        }
        System.out.print(Arrays.toString(moves));
        q.add(0);
        moves[0]=-3;
        while(!q.isEmpty())
        {
            int size=q.size();
            for(int k=0;k<size;k++)
            {
                int curr=q.poll();
                if(curr==moves.length-1)
                {
                    return minMoves;
                }
                for(int l=1;l<=6;l++)
                {
                    int baby=curr+l;
                    if(baby <=moves.length-1&& moves[baby]!=-3 )
                    {
                        if(moves[baby]==-1)
                        {
                             q.add(baby);
                        }
                       
                    else 
                    {
                        q.add(moves[baby]);
                    }
                        moves[baby]=-3;
                    }
                }
            }
            minMoves++;
        }
        return -1;
        
    }
}
