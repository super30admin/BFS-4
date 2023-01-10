import java.util.*;

//tc is O(mxn)
//sc is O(mxn)
class Solution {
    int m, n;
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;

        m = board.length;
        n = board[0].length;
        dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } };

        Queue<int[]> q = new LinkedList<>();

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        q.add(new int[] { click[0], click[1] });
        board[click[0]][click[1]] = 'B';

        // start bfs
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int countMines = countMines(board, curr[0], curr[1]);

            if (countMines != 0) {

                board[curr[0]][curr[1]] = (char) (countMines + '0');
            } else {
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E') {
                        board[nr][nc] = 'B';
                        q.add(new int[] { nr, nc });
                    }

                }
            }

        }

        return board;

    }

    private int countMines(char[][] board, int r, int c) {
        int total = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M') {
                total++;
            }
        }
        return total;
    }
}