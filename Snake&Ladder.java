// Time Complexity : O(m^2)
// Space Complexity :O(m^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    public int snakesAndLadders(int[][] board) {
        //edge case
        if(board == null || board.length==0) return 0;
        int m = board.length;
        int [] flattenBoard = new int[m*m];
        int i = m-1;
        int j = 0;
        int k =0;
        boolean flag = false;
        //preProcessing
        while(i>=0 && j >=0)
        {
            if(board[i][j]==-1)
            {
                flattenBoard[k++] =-1;
            }
            else
            {
                flattenBoard[k++] = board[i][j]-1;
            }
            if(!flag)
            {
                j++;
            }
            else
            {
                j--;
            }
            if(j>=m)
            {
                i--;
                j--;
                flag = true;
            }
            if(j<0)
            {
                i--;
                j++;
                flag = false;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int minSteps =0;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int x =0; x<size;x++)
            {
                int index = q.poll();
                if(index == (flattenBoard.length-1)) return minSteps;
                for(int y = 1;y <7;y++)
                {
                    int child = index + y;
                    if(child < flattenBoard.length && flattenBoard[child]!=-2)
                    {
                             if(flattenBoard[child] ==-1)
                            {
                                q.add(child);
                            }
                            else
                            {
                                q.add(flattenBoard[child]);
                            }
                        flattenBoard[child] =-2;
                    }
                }
                
            }
            minSteps++;
                       
        }
        return -1;
        
    }
}