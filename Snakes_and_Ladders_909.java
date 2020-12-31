//Time Complexity : O(r*r)
//Space Complexity : O(r*r)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : None

package com.s30.satish;

import java.util.LinkedList;
import java.util.Queue;

class Snakes_and_Ladders_909 {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0)
            return 0;
        int r = board.length;
        int[] moves = new int[r * r];
        int index = 0;
        int i = r-1;
        int j = 0;
        int even = 0;
        int minMoves = 0;
        Queue<Integer> q = new LinkedList<>();
        
        while(i >= 0 && j >= 0)
        {
            if(board[i][j] == -1)
                moves[index] = -1;
            else
                moves[index] = board[i][j] - 1;
            index++;
            
            if(even % 2 == 0)
                j++;
            else
                j--;
            if(j == r)
            {
                i--;
                j--;
                even++;
            }
            if(j < 0)
            {
                i--;
                j++;
                even++;
            }
        }
        
        q.add(0);
        moves[0] = -2;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int a = 0; a < size; a++)
            {
                int currNum = q.poll();
                if(currNum == moves.length - 1)
                    return minMoves;
                for(int b = 1; b <= 6; b++)
                {
                    int child = currNum + b;
                    if(child < moves.length && moves[child] != -2)
                    {
                        if(moves[child] == -1)
                            q.add(child);
                        else
                            q.add(moves[child]);
                        moves[child] = -2;
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
}
