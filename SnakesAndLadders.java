// Time Complexity :O(n*n) where n is no of rows
// Space Complexity :O(n*n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :no
class Solution {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board[0].length == 0)
            return -1;
        int n = board.length;
        int[] arr = new int[n * n];
        Queue<Integer> q = new LinkedList<>();
        // flatten the list
        int i = n - 1;
        int j = 0;
        int idx = 0;
        int even = 0;
        while (idx < n * n) {
            if (board[i][j] > -1) {
                arr[idx] = board[i][j] - 1;
            } else {
                arr[idx] = board[i][j];
            }

            // even i case
            if (even % 2 == 0) {
                j++;
                if (j == n) {
                    i--;
                    j--;
                    even++;
                }
            }
            // odd case
            else if (even % 2 != 0) {
                j--;
                if (j < 0) {
                    i--;
                    j++;
                    even++;
                }
            }

            // System.out.println("i and j is"+i+" and "+j);
            idx++;
        }
        System.out.println("list" + Arrays.toString(arr));
        q.add(0);
        arr[0] = -2;
        int size = 0;
        int moves = 0;
        // doing bfs on board
        while (!q.isEmpty()) {
            size = q.size();

            for (int p = 0; p < size; p++) {
                int curr = q.poll();
                if (curr == n * n - 1) {
                    return moves;
                }
                // make moves from 1 to 7
                for (int k = 1; k < 7; k++) {
                    int nc = curr + k;
                    // if we reached the destination

                    // if already visited
                    if (nc < n * n && arr[nc] != -2) {
                        if (arr[nc] > -1) {
                            q.add(arr[nc]);
                        } else {
                            q.add(nc);
                        }
                        arr[nc] = -2;
                    }
                }
            }
            // increment if we are done with the level
            moves++;
        }
        return -1;
    }
}