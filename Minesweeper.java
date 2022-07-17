// Time Complexity :O(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, -1 } };
        Queue<int[]> q = new LinkedList<>();
        int n = board.length;
        int m = board[0].length;
        // if clicked on mine, game over
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int num = findMines(board, click[0], click[1]);
        if (num != 0) {
            board[click[0]][click[1]] = (char) (num + '0');
            return board;
        }
        q.add(click);
        board[click[0]][click[1]] = 'B';
        // BFS
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            num = findMines(board, r, c);
            // if neighbour mine is there, dont process that location further and write no
            // of mines
            if (num > 0) {
                board[r][c] = (char) (num + '0');
            } else {// if no neighbour mine, do normal bfs
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < n && nc < m && board[nr][nc] == 'E') {
                        board[nr][nc] = 'B';
                        q.add(new int[] { nr, nc });
                    }

                }
            }
        }
        return board;
    }

    public int findMines(char[][] board, int i, int j) {
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { 1, -1 } };
        int n = board.length;
        int m = board[0].length;
        int result = 0;
        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row >= 0 && row < n && col >= 0 && col < m && board[row][col] == 'M') {
                result++;
            }
        }
        return result;
    }
}