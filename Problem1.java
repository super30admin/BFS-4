// https://leetcode.com/problems/minesweeper/
// Time complexity : O(MN)
// Space complexity : O(MN)

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int mines = getMines(board, click[0], click[1]);
        if (mines == 0) {
            board[click[0]][click[1]] = 'B';
            for (int dir[] : new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { 1, 1 }, { 1, -1 },
                    { -1, 1 } }) {
                int i = click[0] + dir[0];
                int j = click[1] + dir[1];
                if (i >= 0 && j >= 0 && i < board.length && j < board[i].length && board[i][j] == 'E') {
                    updateBoard(board, new int[] { i, j });
                }
            }
        } else {
            board[click[0]][click[1]] = (char) (mines + '0');
        }

        return board;
    }

    public int getMines(char[][] board, int x, int y) {
        int mines = 0;
        for (int dir[] : new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { -1, -1 }, { 1, 1 }, { 1, -1 },
                { -1, 1 } }) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i >= 0 && j >= 0 && i < board.length && j < board[i].length && board[i][j] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}
