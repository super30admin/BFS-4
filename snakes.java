// Time Complexity : 0(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int row = n - 1;
        int col = 0;
        int even = 0;
        int index = 0;
        int[] nums = new int[n * n];
        while (index < n * n) {
            if (board[row][col] != -1) {
                nums[index] = board[row][col] - 1;
            } else {
                nums[index] = -1;
            }
            index++;
            if (even % 2 == 0) {
                col++;
                if (col == n) {
                    row--;
                    even++;
                    col = n - 1;
                }
            } else {
                col--;
                if (col == -1) {
                    row--;
                    even++;
                    col = 0;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        int moves = 0;
        q.add(0);
        // start bfs
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == n * n - 1) {
                    return moves;
                }
                for (int j = 1; j <= 6; j++) {
                    int child = Math.min(curr + j, n * n - 1);
                    if (nums[child] == -1) {
                        q.add(child);
                        nums[child] = -2;
                    } else {
                        if (nums[child] != -2) {
                            q.add(nums[child]);
                            nums[child] = -2;
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }

}