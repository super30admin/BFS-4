/*
 * TC = O(N*N)
 * SC = O(N*N)
 * 
 * Approach: we first flatten the board. Then we perform BFS to each and every index. 
 * Level order traversal is done to make sure we have the maximum covered when performing BFS.
 */

import java.util.*;
public class SnakesAndLadders
{
    public static int compute(int[][] board)
    {
        if(board == null || board.length == 0) return 0;

        int n = board.length;

        int[] moves = new int[n*n];

        int r = n-1;
        int c = 0;
        int even = 0;
        int idx  = 0;
        while(r>=0 && c>=0)
        {
            if(board[r][c] == -1)
            {
                moves[idx] = -1;
            }
            else{
                moves[idx] = board[r][c] -1;
            }
            idx++;

            if(even%2 == 0)
            {
                c++;
                if(c == n)
                {
                    even++;
                    r--;
                    c--;
                }
            }
            else{
                c--;
                if(c==-1)
                {
                    r--;
                    c++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2;
        int count = 0;

        while(!q.isEmpty())
        {
            int size = q.size();

            for(int i =0;i<size;i++){
                
            int curr = q.poll();
            if(curr == n*n - 1) return count;
            for(int j = 1;j<=6;j++)
            {
                int child = curr + j;
                if(child>=n*n) continue;
                if(moves[child]!=-2)
                {
                    if(moves[child]==-1)
                    {
                        q.add(child);
                    }
                    else{
                        q.add(moves[child]);
                        
                    }
                    moves[child]=-2;
                }
            }
        }
        count++;
        }
        return count;
    }

    public static void main(String args[])
    {
        int[][] board = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        System.out.println(compute(board));
    }
}