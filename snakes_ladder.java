// Time Complexity : O(nxn)
// Space Complexity : O(nxn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0)
            return 0;

        int n = board.length;
        // flattening board to 1D array
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        int[] nums = new int[n * n];
        int idx = 0;
        int r = n - 1;
        int c = 0, even = 0;
        while (r >= 0 && c >= 0) {
            if (board[r][c] == -1) {
                nums[idx] = board[r][c];
            } else {
                nums[idx] = board[r][c] - 1;
            }
            idx++;
            if (even % 2 == 0) {
                c++;
                if (c == n) {
                    r--;
                    c--;
                    even++;
                }
            } else {
                c--;
                if (c == -1) {
                    r--;
                    c++;
                    even++;
                }
            }
        }
        // BFS
        q.add(0);
        nums[0] = -2;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == n * n - 1)
                    return count;
                for (int j = 1; j <= 6; j++) {
                    int child = curr + j;
                    if (child >= n * n)
                        continue;
                    if (nums[child] != -2) {
                        if (nums[child] == -1) {
                            nums[child] = -2;
                            q.add(child);
                        } else {
                            q.add(nums[child]);
                            nums[child] = -2;
                        }
                    }
                }
            }
            count++;
        }
        return -1;

    }
}