// Approach: Create a flattened array of all the indexes on board. Do BFS, add all the idx + 6
// numbered indexes in the queue but only once. Make the idx = -2 to avoid repetition in the
// flattened array. Increase No. of moves on each step and return when destination block is
// reached.
// Time: O(n^2) for BFS on n x n matrix
// Space: O(n^2) for the new arr[] created

import java.util.*;

class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        boolean dirRight = true;
        int i = n-1, j = 0;
        int idx = 0; // index of the flattened array

        // create a flattened array
        while (idx < n*n) {
            if (board[i][j] == -1) {
                arr[idx] = -1;
            }
            else {
                arr[idx] = board[i][j] - 1;
            }
            idx++;

            if (dirRight) {
                j++;
                if (j == n) {
                    i--;
                    dirRight = false;
                    j--;
                }
            }
            else {
                j--;
                if (j < 0) {
                    i--;
                    dirRight = true;
                    j++;
                }
            }
        }

        // Do BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0); arr[0] = -2;
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int k = 0; k<size; k++) {
                int currIdx = q.poll();

                // roll the dice
                for (j = 1; j<=6; j++) {
                    int newIdx = currIdx + j;
                    if (newIdx == n*n - 1) return moves + 1;

                    if (arr[newIdx] != -2) {        // not already visited
                        if (arr[newIdx] == -1) {
                            q.add(newIdx);
                        }
                        else {
                            if (arr[newIdx] == n*n - 1) return moves + 1;
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