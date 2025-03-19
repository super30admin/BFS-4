// Time Complexity : O(N^2)
// Space Complexity : O(N^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int snakesAndLadders(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] flat = new int[m * n];

        int l = 0;
        boolean leftToRight = true;
        for (int i = m - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < n; j++) {
                    flat[l++] = (board[i][j] == -1) ? -1 : board[i][j] - 1;
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    flat[l++] = (board[i][j] == -1) ? -1 : board[i][j] - 1;
                }
            }
            leftToRight = !leftToRight;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        flat[0] = -2;
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int num = q.poll();
                for (int k = 1; k <= 6; k++) {
                    int baby = num + k;
                    if (baby >= m * n)
                        break;

                    if (flat[baby] != -2) {
                        int nextPos = (flat[baby] == -1) ? baby : flat[baby];

                        if (nextPos == (m * n) - 1)
                            return moves + 1;

                        q.add(nextPos);
                        flat[baby] = -2;
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}
