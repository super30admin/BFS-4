import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int snakesAndLadders(int[][] board) {
        //edge case
        if(board == null || board.length == 0)
            return 0;
        int m = board.length, n = board[0].length;
        int idx = 0;
        int[] moves  = new int[board.length * board[0].length];
        //flattening the array
        int i = m-1;int j =0, even =0;
        int min = 0;
        while( i >= 0 && j >=0)
        {
            if(board[i][j] == -1)
            {
                moves[idx] = -1;
            }else
            {
                moves[idx] = board[i][j] -1;
            }
            idx++;
            if(even % 2 == 0)
                j++;
            else
                j--;
            //move i and j
            if( j  >= n)
            {
                j--;
                i--;
                even++;
            }else if(j < 0)
            {
                j++;
                i--;
                even++;
            }
        }
        //queue to do breadth first traversal and process the elements while checking the min number of levels needed to reach the destination
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        moves[0] = -2;
        int len= board.length * board[0].length;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int l = 0; l < size; l++){
                int curr = queue.poll();
                if(curr == len -1)
                    return min;
                for(int k=1;k<7;k++)
                {
                    int child = curr + k;
                    if(child < len && moves[child] != -2)
                    {
                        if(moves[child] > -1){
                            queue.add(moves[child]);
                        } else {
                            queue.add(child);
                        }
                        moves[child] = -2;
                    }
                }
            }
            min++;
        }
        return -1;
    }
}
//Time Complexity : O(m * n)
//Space Complexity : O(m * n) for the moves array
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this :