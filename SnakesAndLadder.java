
// Time Complexity : O(n^2) where n is the number of cells in the board
// Space Complexity : O(n^2) where n is the number of cells in the board
// Did this code successfully run on Leetcode :   yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Approach: We can use BFS to find the shortest path from 1 to n*n.
// We can use a queue to store the current position and the number of moves taken to reach that position.
// We can use a boolean array to keep track of the visited positions.
// We can use a for loop to iterate through the possible dice rolls (1-6) and calculate the next position.
// If the next position is greater than n*n, we can break the loop.
// If the next position is not visited, we can mark it as visited and add it to the queue.
// If the next position is a snake or ladder, we can add the destination position to the queue instead of the next position.
// We can continue this process until we reach n*n or the queue is empty.   

import java.util.*;

class Solution {

    public int snakesAndLadders(int[][] board) {

        int n = board.length;

        int target = n * n;

        boolean[] visited = new boolean[target + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int curr = queue.poll();

                if (curr == target) {
                    return moves;
                }

                for (int dice = 1; dice <= 6; dice++) {

                    int next = curr + dice;

                    if (next > target) break;

                    int[] pos = getCoordinates(next, n);
                    int r = pos[0];
                    int c = pos[1];

                    if (visited[next]) continue;
                    visited[next] = true;

                    if (board[r][c] != -1) {
                        queue.offer(board[r][c]);
                    } else {
                        queue.offer(next);
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    private int[] getCoordinates(int num, int n) {

        int rowFromBottom = (num - 1) / n;
        int col = (num - 1) % n;

        int r = n - 1 - rowFromBottom;

        if (rowFromBottom % 2 == 1) {
            col = n - 1 - col;
        }

        return new int[]{r, col};
    }
}