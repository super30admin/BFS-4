/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(n*n)
    n = no of rows or cols
* 
* Space Complexity: O(n*n)
* 
*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesandLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        int[] newBoard = new int[n * n + 1];

        boolean reverse = false;

        int num = 1, index = 1, row = n - 1, col = 0;

        while (row >= 0) {
            if (board[row][col] == -1) {
                newBoard[index] = num;
            } else {
                newBoard[index] = board[row][col];
            }
            if (reverse) {
                col--;
                if (col == -1) {
                    reverse = false;
                    row--;
                    col = 0;
                }
            } else {
                col++;
                if (col == n) {
                    reverse = true;
                    row--;
                    col = n - 1;
                }
            }
            index++;
            num++;
        }

        int target = n * n;

        Queue<Integer> queue = new LinkedList<>();

        HashSet<Integer> hset = new HashSet<>();

        queue.add(1);

        hset.add(1);

        int levels = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int ele = 0; ele < size; ele++) {
                int curr = queue.poll();

                for (int move = curr + 1; move <= Math.min(curr + 6, target); move++) {
                    if (newBoard[move] >= target) {
                        return levels + 1;
                    }
                    if (!hset.contains(newBoard[move])) {
                        hset.add(newBoard[move]);
                        queue.add(newBoard[move]);
                    }
                }
            }

            levels++;
        }

        return -1;
    }
}
