import java.util.LinkedList;
import java.util.Queue;
//Time Complexity : O(N*N)
//Space Complexity : O(N*N)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Reverse the board for easy index tracking. Then push the current square and
 * current moves to the queue. While queue is not empty, pop each array from the
 * queue and find the next possible move by adding 1 to 6 to the current number
 * and check if the next is crossing n*n. If crossed, return send element from
 * array + 1. If not, then mark the next as visited and push it to the queue
 * incrementing the moves by 1.
 *
 */
public class SnakesAndLadders {
	class Solution {
	    public int snakesAndLadders(int[][] board) {
	        int n = board.length;
	        reverse(board);
	        boolean[] visited = new boolean[n*n+1];
	        Queue<int[]> queue = new LinkedList<>();
	        queue.offer(new int[]{1, 0});
	        visited[1] = true;

	        while(!queue.isEmpty()) {
	            int[] curr = queue.poll();
	            for(int j=1; j<=6; j++) {
	                int next = curr[0] + j;
	                int[] p = coordinate(next, n);
	                if(board[p[0]][p[1]] != -1) {
	                    next = board[p[0]][p[1]];
	                }
	                if(next == n*n) {
	                    return curr[1] + 1;
	                }
	                if(!visited[next]) {
	                    visited[next] = true;
	                    queue.offer(new int[]{next, curr[1] + 1});
	                }
	            }
	        }

	        return -1;
	    }

	    public int[] coordinate(int sq, int n) {
	        int row = (sq - 1) / n;
	        int col = (sq - 1) % n;
	        if(row % 2 != 0) {
	            col = n - 1 - col;
	        }
	        return new int[]{row, col};
	    }

	    public void reverse(int[][] board) {
	        int l = 0, r = board.length-1;
	        while(l < r) {
	            int[] temp = board[l];
	            board[l] = board[r];
	            board[r] = temp;
	            l++;
	            r--;
	        }
	    }
	}
