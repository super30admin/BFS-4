// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
/*
 * Run BFS approach,
 * create an array that stores the board in 1D array.  
 * At every new index if position is not visited we add it to  queue if it is -2 else we move to the 
 * given position and mark visited. every level we increase the move;
*/
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        int i = n - 1;
        int j = 0;

        boolean dir = true;
        int idx = 0;
        while (idx < n * n) {
            if (board[i][j] == -1) {
                arr[idx] = -1;
            } else {
                arr[idx] = board[i][j] - 1;
            }
            idx++;
            if (dir) {
                j++;
                if (j == n) {
                    i--;
                    dir = false;
                    j--;
                }
            } else {
                j--;
                if (j < 0) {
                    i--;
                    dir = true;
                    j++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int moves = 0;
        q.add(0);
        arr[0] = -2;

        while (!q.isEmpty()) {
            int size = q.size();
            for (i = 0; i < size; i++) {
                int currIdx = q.poll();
                for (j = 1; j <= 6; j++) {
                    int newIdx = currIdx + j;
                    if (newIdx == n * n - 1)
                        return moves + 1;
                    if (arr[newIdx] != -2) {
                        if (arr[newIdx] == -1) {
                            q.add(newIdx);
                        } else {
                            if (arr[newIdx] == n * n - 1)
                                return moves + 1;
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