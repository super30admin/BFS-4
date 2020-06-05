// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadder {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0){
            return 0;
        }

        int r = board.length;
        int c = board[0].length;

        int[] moves = new int[r * c];

        // flag to flatten the list
        int even = 0;

        // starting point
        int i = r-1;
        int j = 0;

        int index = 0;

        while(i >= 0 && j >= 0){
            if(board[i][j] == -1){
                moves[index] = -1;
            } else {
                moves[index] = board[i][j] - 1;
            }

            index++;

            if(even % 2 == 0){
                j++;
            } else {
                j--;
            }

            if(j >= c){
                i--;
                even++;
                j--;
            }

            if(j < 0){
                i--;
                even++;
                j++;
            }
        }

        int minMoves = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2;

        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                int curr = q.poll();
                if(curr == r*c - 1){
                    return minMoves;
                }

                for(int l=1; l<=6; l++){
                    int child = curr + l;
                    if(child < r*c && moves[child] != -2){
                        if(moves[child] == -1){
                            q.add(child);
                        } else {
                            q.add(moves[child]);
                        }

                        moves[child] = -2;
                    }

                }
            }
            minMoves++;
        }
        return -1;
    }
}
