//TC : O(m*n)
//SC : O(m*n)

class Solution {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;
        m = board.length;
        n = board[0].length;
        dirs = new int[][] { { -1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { -1, -1 }, { 1, -1 }, { 1, 1 } };
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { click[0], click[1] });
        board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int countMines = countMines(board, curr[0], curr[1]);
            if (countMines > 0) {
                board[curr[0]][curr[1]] = (char) (countMines + '0');
            } else {
                for (int[] dir : dirs) {
                    int row = curr[0] + dir[0];
                    int col = curr[1] + dir[1];
                    if (row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'E') {
                        board[row][col] = 'B';
                        q.add(new int[] { row, col });
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