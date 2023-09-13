import java.util.LinkedList;
import java.util.Queue;
// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
public class SnakeAndLadderBFS {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        boolean dir = true;  // move left to right
        int r = n-1; int c = 0;
        for(int i=0; i<n*n; i++)
        {
            if(board[r][c] == -1)
                arr[i] = board[r][c];
            else
                arr[i] = board[r][c]-1;
            if(dir)
            {
                c++;
                if(c == n) {
                    r--;
                    c = n-1;
                    dir = false;
                }
            }else
            {
                c--;
                if( c == -1){
                    r--;
                    c = 0;
                    dir = true;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int moves = 0;
        q.add(0);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++)
            {
                int currIdx = q.poll();
                for(int j=1; j<=6; j++){
                    int newIdx = currIdx + j;
                    if(newIdx == arr.length -1 || arr[newIdx] == arr.length-1) return moves+1;
                    if(arr[newIdx] != -2)
                    {
                        if(arr[newIdx] == -1)
                        {
                            q.add(newIdx);
                        }else{
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx] = -2;
                    }

                }
            }
            moves++;
        }
        return -1;
    }
}