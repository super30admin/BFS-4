/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(m*n)
    m = rows n = cols
* 
* Space Complexity: O(1)
* 
*/
import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
    int[][] dirs = new int[][] {
            { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 }
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.add(click[0]);
        queue.add(click[1]);

        board[click[0]][click[1]] = 'B';

        int m = board.length, n = board[0].length;

        while (!queue.isEmpty()) {
            int currRow = queue.poll();
            int currCol = queue.poll();

            int minesCount = getMinesCount(board, currRow, currCol, m, n);

            if (minesCount != 0) {
                board[currRow][currCol] = (char) (minesCount + '0');
            } else {
                for (int[] dir : dirs) {
                    int nr = currRow + dir[0];
                    int nc = currCol + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                        queue.add(nr);
                        queue.add(nc);

                        board[nr][nc] = 'B';
                    }
                }
            }
        }

        return board;
    }

    private int getMinesCount(char[][] board, int row, int col, int m, int n) {
        int count = 0;

        for (int[] dir : dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1];

            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }

        return count;
    }
}