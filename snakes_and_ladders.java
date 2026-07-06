// Time Complexity : O(n²) because we flatten the board and visit each cell at most once in BFS
// Space Complexity : O(n²) for the flattened array and queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We first convert the zigzag board into a 1D array so each square maps to an index.
// Then we use BFS where each level represents one dice throw.
// For every square, we try moves from 1 to 6 and use ladders or snakes when present.

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];

        int r = n - 1, c = 0;
        int i = 0;
        boolean flag = true;

        // flatten board in zigzag order
        while(i < n * n) {
            if(board[r][c] == -1) {
                arr[i] = board[r][c];
            } else {
                arr[i] = board[r][c] - 1;
            }

            i++;

            if(flag) {
                c++;
                if(c == n) {
                    r--;
                    c--;
                    flag = false;
                }
            } else {
                c--;
                if(c == -1) {
                    r--;
                    c++;
                    flag = true;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        int level = 0;

        while(!q.isEmpty()) {
            int size = q.size();

            for(int j = 0; j < size; j++) {
                int currIdx = q.poll();

                // try all dice values
                for(int k = 1; k <= 6; k++) {
                    int newIdx = currIdx + k;

                    if(newIdx == n * n - 1 || arr[newIdx] == n * n - 1) {
                        return level + 1;
                    }

                    if(arr[newIdx] != -2) {
                        if(arr[newIdx] == -1) {
                            q.add(newIdx);
                        } else {
                            q.add(arr[newIdx]);
                        }

                        arr[newIdx] = -2;
                    }
                }
            }

            level++;
        }

        return -1;
    }
}