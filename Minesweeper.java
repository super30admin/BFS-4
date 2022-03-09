import java.util.LinkedList;
import java.util.Queue;

// TC O(M*N)
// SC O(M*N)
public class Minesweeper {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) {
            return board;
        }
        dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 } };
        m = board.length;
        n = board[0].length;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { click[0], click[1] });
        board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int mines = getMines(board, curr[0], curr[1]);
            if (mines == 0) {
                for (int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if (r < m && r >= 0 && c < n && c >= 0 && board[r][c] == 'E') {
                        q.add(new int[] { r, c });
                        board[r][c] = 'B';
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char) (mines + '0');
            }
        }
        return board;
    }

    private int getMines(char[][] board, int i, int j) {
        int mines = 0;
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r < m && r >= 0 && c < n && c >= 0 && board[r][c] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}
