/*
 * Class: MinesweeperDFS
 * Problem: LeetCode 529 - Minesweeper
 * Link: https://leetcode.com/problems/minesweeper/
 *
 * Algorithm / Approach:
 * - Use DFS (recursive) starting from the clicked cell.
 * - If the clicked cell is a mine ('M') mark it as 'X' and return.
 * - Otherwise perform DFS: mark the cell as 'B', count adjacent mines.
 *   - If adjacent mine count is 0, recursively reveal all unrevealed neighbors ('E').
 *   - If count > 0, set the cell to the digit character representing the count.
 * - Use the board itself to mark visited cells (change from 'E') so cells are processed at most once.
 *
 * Time Complexity: O(m * n) in the worst case (m = rows, n = cols). Each cell is visited at most once.
 * Space Complexity: O(m * n) recursion stack in the worst case (could be O(m*n) for a large empty board).
 */

public class MinesweeperDFS {

    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }

    private void dfs(char[][] board, int r, int c) {
        //base
        if (r < 0 || c < 0 || r == board.length || c == board[0].length || board[r][c] != 'E') {
            return;
        }

        //logic
        board[r][c] = 'B';
        int mCount = getMinesCount(board, r, c);
        if (mCount == 0) {
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                dfs(board, nr, nc);
            }
        } else {
            board[r][c] = (char) (mCount + '0');
        }
    }

    private int getMinesCount(char[][] board, int r, int c) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }
}
