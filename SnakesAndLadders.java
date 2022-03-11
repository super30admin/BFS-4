package bfs4;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
	//BFS
	//Time Complexity : O(n^2), where n is the length of board
	//Space Complexity : O(n^2)
	//Did this code successfully run on Leetcode : Yes
	//Any problem you faced while coding this : No
	public int snakesAndLadders(int[][] board) {
        // null
        if(board == null || board.length == 0)
            return 0;
        int n = board.length * board.length;
        int[] moves = new int[n];
        boolean direction = true;
        int i = board.length - 1;
        int j = 0;
        
        int idx = 0;
        while(idx < moves.length) {
            if(board[i][j] == -1)
                moves[idx] = -1;
            else
                moves[idx] = board[i][j] - 1;
            idx++;
            
            if(direction) {
                j++;
                if(j == board.length) {
                    i--;
                    j--;
                    direction = false;
                }
            } else {
                j--;
                if(j == -1) {
                    i--;
                    j++;
                    direction = true;
                }
            }
        }
        
        int steps = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        moves[0] = -2;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int l=0; l<size; l++) {
                int curr = q.poll();
                if(curr == n - 1)
                    return steps;
                for(int k=1; k<=6; k++) {
                    int in = curr + k;
                    if(in >= n)
                        break;
                    if(moves[in] != -2) {
                        if(moves[in] == -1)
                            q.offer(in);
                        else
                            q.offer(moves[in]);
                        moves[in] = -2;
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}
