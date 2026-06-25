// Time Complexity : o(n^2) where n is the number of cells in the board
// Space Complexity : o(n^2) where n is the number of cells in the board
// Did this code successfully run on Leetcode :     yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Approach: We can use BFS to find the shortest path from 1 to n*n. 
// We can use a queue to store the current position and the number of moves taken to reach that position.
//  We can use a boolean array to keep track of the visited positions.
//  We can use a for loop to iterate through the possible dice rolls (1-6) and calculate the next position. 
// If the next position is greater than n*n, we can break the loop.
//  If the next position is not visited, we can mark it as visited and add it to the queue.
// If the next position is a snake or ladder, we can add the destination position to the queue instead of the next position.
//  We can continue this process until we reach n*n or the queue is empty.


import java.util.*;

class Solution {

    int[][] dirs = {
        {-1,-1}, {-1,0}, {-1,1},
        {0,-1},          {0,1},
        {1,-1},  {1,0},  {1,1}
    };

    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {

        m = board.length;
        n = board[0].length;

        int row = click[0];
        int col = click[1];

        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{row, col});
        board[row][col] = 'B';

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];

            int mines = countMines(board, r, c);

            if (mines > 0) {
                board[r][c] = (char) (mines + '0');
            } else {

                for (int k = 0; k < 8; k++) {

                    int nr = r + dirs[k][0];
                    int nc = c + dirs[k][1];

                    if (nr >= 0 && nc >= 0 &&
                        nr < m && nc < n &&
                        board[nr][nc] == 'E') {

                        queue.offer(new int[]{nr, nc});
                        board[nr][nc] = 'B'; // mark visited
                    }
                }
            }
        }

        return board;
    }

    private int countMines(char[][] board, int row, int col) {

        int count = 0;

        for (int k = 0; k < 8; k++) {

            int r = row + dirs[k][0];
            int c = col + dirs[k][1];

            if (r >= 0 && c >= 0 &&
                r < m && c < n &&
                board[r][c] == 'M') {
                count++;
            }
        }

        return count;
    }
}