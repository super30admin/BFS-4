// Time Complexity : o(n*n)
// Space Complexity : o(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    public int snakesAndLadders(int[][] board) {
      if(board.length == 0 || board == null) 
            return 0;
        
        Queue<Integer> queue = new LinkedList<>();
        int[] moves = flattenBoard(board);
        
        queue.add(1);
        moves[1]=-2;
         int min = 0;
        int n = board.length;
        while(!queue.isEmpty())
        {
            int sz=queue.size();
             
            for(int l=0;l<sz;l++)
            {
                int curr=queue.poll();
                
                if(curr==n*n)
                    return min;
                for(int j=1;j<7;j++)
                {
                    int child=curr+j;
                    
                    if(child>n*n)
                        break;
                    
                    if(moves[child]!=-2)
                    {
                        if(moves[child]==-1)
                        {
                             queue.add(child);
                        }
                        else
                        {
                            queue.add(moves[child]);
                        }
                        moves[child]=-2;
                    }
                }
            }
            min++;
        }
        return -1;
    }
    
    private int[] flattenBoard(int[][] board)
    {
        int n=board.length;
        int[] moves=new int[n*n+1];
        
        int i=n-1;
        int j=0;
        int idx=1;
        int even=0;
        
        while(i>=0 && j<n)
        {
            moves[idx]=board[i][j];
            idx++;
            
            if(even%2==0)
            {
                j++;
                if(j==n)   
                {
                    i--;
                    even++;
                   j--;
                    
                }
            }
            else
            {
                j--;
                
                if(j==-1)
                {
                    i--;
                    even++;
                    j++;
                }
            }
        }
        return moves;
    }
}