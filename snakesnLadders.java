import java.util.*;

class Solution {
    // Tc: O(n^2) Sc: O(n^2)
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean visited[][] = new boolean[n][n];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[n - 1][0] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = size; i > 0; i--) {
                int cur = q.poll();
                if (cur == n * n)
                    return ans;
                for (int dice = 1; dice <= 6; dice++) {
                    if (dice + cur > n * n)
                        continue;
                    int pos[] = findCord(dice + cur, n);
                    int row = pos[0];
                    int col = pos[1];
                    if (visited[row][col] == true)
                        continue;

                    visited[row][col] = true;

                    if (board[row][col] == -1)
                        q.add(dice + cur);
                    else
                        q.add(board[row][col]);
                }
            }
            ans++;
        }
        return -1;
    }

    private int[] findCord(int cur, int n) {
        int r = n - (cur - 1) / n - 1;
        int c = (cur - 1) % n;

        if (r % 2 == n % 2)
            return new int[] { r, n - 1 - c };

        else
            return new int[] { r, c };
    }
}