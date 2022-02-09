// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// we use bfs to solve this
// we add the intial click to the queue
// We then poll the q and add the unvisited children in all dirs and proceed with the process
class Solution {
    int m, n;
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;

        m = board.length;
        n = board[0].length;

        dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { click[0], click[1] });
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if (count > 0) {
                board[curr[0]][curr[1]] = (char) (count + '0');
            } else {
                for (int[] dir : dirs) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E') {
                        board[r][c] = 'B';
                        q.add(new int[] { r, c });
                    }
                }
            }
        }
        return board;
    }

    private int countMines(char[][] board, int r, int c) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }
}

// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// we use dfs to solve this
// we start the dfs at the intial click
// We check unvisited children in all dirs and proceed with the dfs
class Solution {
    int m, n;
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;

        m = board.length;
        n = board[0].length;

        dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }

    private int countMines(char[][] board, int r, int c) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r == m || c < 0 || c == n || board[r][c] != 'E')
            return;
        board[r][c] = 'B';
        int count = countMines(board, r, c);
        if (count > 0) {
            board[r][c] = (char) (count + '0');
        } else {
            for (int[] dir : dirs) {
                int nr = dir[0] + r;
                int nc = c + dir[1];
                dfs(board, nr, nc);
            }
        }

    }
}