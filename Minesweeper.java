import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, 1}, {1, 1}, {-1, -1}, {1, -1}};
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        return updateBoardBFS(board, click);
    }

    // TC: (8 * M * N) where M is number of rows and N is number of cols
    // SC: O(Max(M, N)) where M is number of rows and N is number of cols
    private char[][] updateBoardDFS(char[][] board, int[] click) {
        dfs(board, click[0], click[1]);
        return board;
    }

    private void dfs(char[][] board, int i, int j) {
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return;
        }
        int minesCount = getNeighborMinesCount(board, new int[]{i, j});
        if (minesCount == 0) {
            board[i][j] = 'B';
            for (int[] d : dirs) {
                int nextX = i + d[0];
                int nextY = j + d[1];
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'E') {
                    board[nextX][nextY] = 'B';
                    dfs(board, nextX, nextY);
                }
            }
        } else {
            board[i][j] = (char)(minesCount + '0');
        }
    }

    // TC: (8 * M * N) where M is number of rows and N is number of cols
    // SC: O(M * N) where M is number of rows and N is number of cols
    private char[][] updateBoardBFS(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (board[curr[0]][curr[1]] == 'M') {
                board[curr[0]][curr[1]] = 'X';
                return board;
            }
            int minesCount = getNeighborMinesCount(board, curr);
            if (minesCount == 0) {
                board[curr[0]][curr[1]] = 'B';
                for (int[] d : dirs) {
                    int nextX = curr[0] + d[0];
                    int nextY = curr[1] + d[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'E') {
                        board[nextX][nextY] = 'B';
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char)(minesCount + '0');
            }
        }
        return board;
    }

    private int getNeighborMinesCount(char[][] board, int[] curr) {
        int count = 0;
        for (int[] d : dirs) {
            int nextX = curr[0] + d[0];
            int nextY = curr[1] + d[1];
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'M') {
                count++;
            }
        }
        return count;
    }
}
