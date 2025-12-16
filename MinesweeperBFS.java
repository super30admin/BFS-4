/*
 * Class: MinesweeperBFS
 * Problem: LeetCode 529 - Minesweeper
 * Link: https://leetcode.com/problems/minesweeper/
 *
 * Algorithm / Approach:
 * - Use BFS starting from the clicked cell.
 * - If the clicked cell is a mine ('M') mark it as 'X' and return.
 * - Otherwise mark the start as 'B' and enqueue it. For each dequeued cell:
 *   - Count adjacent mines. If count == 0, expand (BFS) to all unrevealed neighbors ('E') and mark them 'B'.
 *   - If count > 0, set the cell to the digit character representing the count.
 * - Use the board itself to mark visited cells (change from 'E') so cells are processed at most once.
 *
 * Time Complexity: O(m * n) in the worst case (m = rows, n = cols). Each cell is processed at most once, and checking 8 neighbors is O(1).
 * Space Complexity: O(m * n) in the worst case for the BFS queue.
 */

import java.util.LinkedList;
import java.util.Queue;

public class MinesweeperBFS {

    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        board[click[0]][click[1]] = 'B';
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int mCount = getMinesCount(board, r, c);
            if (mCount == 0) {
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'E') {
                        queue.add( new int[]{nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            } else {
                board[r][c] = (char) (mCount + '0');
            }
        }

        return board;

    }

    private int getMinesCount(char[][] board, int r, int c) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }
}
