// Time Complexity : O(n^2)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadder {

    public int snakesAndLadders(int[][] board) {

        if (board == null || board.length == 0) return 0;
        int r = board.length;
        int[] moves = new int[r * r];
        int i = r - 1, j = 0;
        boolean flag = true; //true when moviing from lfet to right i.e j++
        int idx = 0;

        //populate 1D array
        while (idx < r * r) {
            if (board[i][j] == -1) {
                moves[idx] = -1;
            } else {
                moves[idx] = board[i][j] - 1;
            }
            idx++;
            if (flag) {
                j++; //move to right
                if (j == r) { //reached right end
                    flag = false; //reverse direction
                    i--; // go up by 1 row
                    j--; //
                }
            } else {
                j--; //move to left
                if (j == -1) { //reached left end
                    flag = true; //reverse direction
                    i--; // go up by 1 row
                    j++; //
                }
            }
        }

        //bfs
        Queue<Integer> q = new LinkedList<>();
        q.add(0); //1st index of moves[]
        moves[0] = -2; //mark as visited
        int minMoves = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int curr = q.poll();
                if (curr == r * r - 1) return minMoves;

                //add children
                for (int d = 1; d <= 6; d++) { //rolling dice 6 times
                    int child = curr + d;

                    //bound check
                    if (child >= r * r) break;

                    //add to queue if not visited
                    if (moves[child] != -2) {
                        //normal traversal - add index
                        if (moves[child] == -1) {
                            q.add(child);
                        } else { //if there is a ladder
                            q.add(moves[child]);
                        }
                        moves[child] = -2; //mark visited
                    }
                }
            }
            minMoves++;
        }

        return -1; //return -1 when there no valid solution
    }
}
