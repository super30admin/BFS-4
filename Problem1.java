public class Minesweeper {
    int m, n;
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;

        if (board == null || m < 0)
            return board;
        dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if (count == 0) {
                for (int[] dir : dirs) {
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E') {
                        q.add(new int[] { nr, nc });
                        board[nr][nc] = 'B';
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char) (count + '0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int r, int c) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + r;
            int nc = dir[1] + c;
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M')
                count++;
        }
        return count;
    }
}