// TC: O(m * n)
// SC: O(m * n)
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<int[]> bfs = new LinkedList<>();
        int[][] dirs = new int[][] { { 1, 1 }, { 0, 1 }, { -1, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { 1, -1 },
                { -1, -1 } };

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        bfs.add(click);
        board[click[0]][click[1]] = 'B';

        while (!bfs.isEmpty()) {
            int[] curr = bfs.poll();
            int minesNearBy = countMines(board, curr[0], curr[1], dirs);

            if (minesNearBy == 0) {
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];

                    if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == 'E') {
                        bfs.add(new int[] { x, y });
                        board[x][y] = 'B';
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char) (minesNearBy + '0');
            }

        }
        return board;
    }

    private int countMines(char[][] board, int i, int j, int[][] dirs) {
        int mines = 0;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}