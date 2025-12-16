/*
 * Class: SnakesAndLadders
 * Problem: LeetCode 909 - Snakes and Ladders
 * Link: https://leetcode.com/problems/snakes-and-ladders/
 *
 * Algorithm / Approach:
 * - Flatten the 2D board into a 1D array following the Boustrophedon (zig-zag) order used by the problem.
 * - Use BFS on the flattened board: each BFS level represents one dice roll (1..6).
 * - From a current square index, try moves +1..+6. For each reachable square:
 *   - If it contains -1, the move lands on that square.
 *   - If it contains a number, it represents a snake/ladder; jump to that target index (stored as 0-based).
 * - Mark visited squares in the array (e.g., set to -2) to avoid revisiting.
 * - Return the number of dice rolls when the last square is reached, otherwise -1 if unreachable.
 *
 * Time Complexity: O(n^2) where the board is n x n; each square is visited at most once and each visit checks up to 6 moves.
 * Space Complexity: O(n^2) for the flattened array and BFS queue.
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] array = new int[n * n];

        int r = n - 1;
        int c = 0;
        boolean flag = true;
        for (int i = 0; i < n * n; i++) {
            if (board[r][c] == -1) {
                array[i] = -1;
            } else {
                array[i] = board[r][c] - 1;
            }
            if (flag) {
                c++;
                if (c == n) {
                    r--;
                    c--;
                    flag = false;
                }
            } else {
                c--;
                if (c < 0) {
                    r--;
                    c++;
                    flag = true;
                }
            }
        }

        System.out.println(Arrays.toString(array));
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        array[0] = -2;
        int diceRolls = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer idxObj = queue.poll();
                if (idxObj == null) continue;
                int idx = idxObj;
                for (int j = 1; j <= 6; j++) {
                    int newIdx = idx + j;
                    // bounds check: if newIdx is beyond last square, further dice values will also be out of bounds
                    if (newIdx >= n * n) break;
                    if (newIdx == n * n - 1 || array[newIdx] == n * n - 1) return diceRolls + 1;
                    if (array[newIdx] < 0) {
                        if (array[newIdx] == -1) {
                            queue.add(newIdx);
                        }
                    } else {
                        queue.add(array[newIdx]);
                    }
                    array[newIdx] = -2;
                }
            }
            diceRolls++;
        }

        return diceRolls;
    }
}
