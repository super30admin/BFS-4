// Time Complexity : O(6n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class Main {

    public static int snakesAndLadders(int[][] board) {
        // null case
        if (board == null || board.length == 0)
            return 0;
        // first convert matrix into the array
        int n = board.length;
        int[] arr = new int[n * n];
        int idx = 0; // for traversing in array
        int flag = 0; // flag for odd even row in matrix
        int c = 0; // col of starting point
        int r = n - 1; // row of starting point
        int sides = 6; // number of sides of the dices

        while (idx < n * n) {
            // first check if board[r][c] > 0 then put
            // board[r][c] - 1 in array
            if (board[r][c] > 0) {
                arr[idx] = board[r][c] - 1;
            } else {
                arr[idx] = board[r][c];
            }
            // increase the index
            idx++;
            // if flag is even I will go forward
            // else I will go backward
            if (flag % 2 == 0) {
                c++;
                if (c == n) {
                    c--;
                    r--;
                    flag = 1;
                }
            } else {
                c--;
                if (c == -1) {
                    r--;
                    c++;
                    flag = 0;
                }
            }
        }
        // queue for BFS traversal
        Queue<Integer> q = new LinkedList<>();
        // count
        int count = 0;
        // add index 0 in the queue
        q.add(0);
        // mark it as visited
        arr[0] = -2;
        // BFS traversal
        while (!q.isEmpty()) {
            // to traverse in the level
            int size = q.size();
            for (int i = 0; i < size; i++) {
                // get current index from the queue
                int curr = q.poll();
                // at each index we have 6 option to traverse as number of side of
                // the dice is 6
                for (int j = 1; j <= sides; j++) {
                    // child
                    int child = curr + j;
                    // check if already visited or not
                    if (arr[child] != -2) {

                        // if arr[child] is -1 then go to jth index
                        // from current index
                        if (arr[child] == -1) {
                            // here we also check we are at our desired position than return the count
                            if (child >= n * n - 1)
                                return count + 1;
                            q.add(child);
                        } else {
                            if (arr[child] == n * n - 1)
                                return count + 1;
                            // else go to ladder's or snake's index
                            q.add(arr[child]);
                        }
                        // mark at is visited
                        arr[child] = -2;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 }, { -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 },
                { -1, 15, -1, -1, -1, -1 } };

        System.out.println(snakesAndLadders(board));
    }
}