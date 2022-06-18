import java.util.LinkedList;
import java.util.Queue;

public class Problem1 {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        // BFS Solution
        // TC : O(M*N)
        // SC : O (M*N)
        if (board == null || board.length == 0) return board;

        m = board.length;
        n = board[0].length;

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dirs = new int[][]{{-1, 0}, {-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while (!que.isEmpty()) {
            int[] curr = que.poll();
            int currMines = countMines(board, curr[0], curr[1]);
            if (currMines > 0) {
                board[curr[0]][curr[1]] = (char) (currMines + '0');
            } else {
                //board[curr[0]][curr[1]] = 'B';
                for (int[] dir : dirs) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E') {
                        que.add(new int[]{r, c});
                        board[r][c] = 'B';
                    }
                }
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j) {
        int count = 0;

        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') {
                count++;
            }
        }
        return count;
    }
}
