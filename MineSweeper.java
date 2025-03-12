import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * BFS approach
 */

public class MineSweeper {
    int m, n;
    int dirs[][];

    // ----------------------------- DFS Approach ---------------------------
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) {
            return board;
        }

        m = board.length;
        n = board[0].length;
        dirs = new int[][] {
            {-1, 0}, // U
            {1, 0}, // D
            {0, -1}, // L
            {0, 1}, // R
            {-1, -1}, // UL
            {1, - 1}, // DL
            {-1, 1}, // UR
            {1, 1} // DR
        };

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click);
        return board;
    }

    private void dfs(char[][] board, int[] click) {
        int row = click[0];
        int col = click[1];
        // base
        if (!isValidCell(row, col)|| board[row][col] != 'E'){
            return;
        }
        // logic
        int countMines = countMines(board, click);
        if (countMines == 0) {
            board[row][col] = 'B';
            for (int[] dir: dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];
                dfs (board, new int[]{nr, nc});
            }
        } else {
            board[row][col] = (char)(countMines + '0');
        }
    }

    private int countMines(char[][] board, int[] click) {
        int count = 0;
        for (int[] dir: dirs) {
            int nr = click[0] + dir[0];
            int nc = click[1] + dir[1];
            if (isValidCell(nr, nc) && board[nr][nc] == 'M') {
                count++;
            }
        }

        return count;
    }


    // ---------------------------- BFS approach ----------------------------

    public char[][] updateBoard2(char[][] board, int[] click) {
        if (board == null || board.length == 0) {
            return board;
        }

        m = board.length;
        n = board[0].length;
        dirs = new int[][] {
            {-1, 0}, // U
            {1, 0}, // D
            {0, -1}, // L
            {0, 1}, // R
            {-1, -1}, // UL
            {1, - 1}, // DL
            {-1, 1}, // UR
            {1, 1} // DR
        };

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        bfs(board, click);
        return board;
    }

    private void bfs(char[][] board, int[] click) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];
            int countMines = countMines(board, curr);
            for (int[] dir: dirs) {
                if (countMines == 0) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];
                    if (isValidCell(nr, nc) && board[nr][nc] == 'E') {
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr, nc});
                    }
                } else {
                    board[row][col] = (char)(countMines + '0');
                }
            }
        }
    }

    private boolean isValidCell(int nr, int nc) {
        return nr >= 0 && nr < m && nc >= 0 && nc < n;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'E','E','E','E','E'},
            {'E','E','M','E','E'},
            {'E','E','E','E','E'},
            {'E','E','E','E','E'}
        };

        int[] click = {3, 0};
        // Your code here

        MineSweeper obj = new MineSweeper();
        char[][] result = obj.updateBoard(board, click);
        System.out.println("DFS: ");
        for (char[] r: result)
            System.out.println(Arrays.toString(r));

        result = obj.updateBoard2(board, click);
        System.out.println("BFS: ");
        for (char[] r: result)
            System.out.println(Arrays.toString(r));
    }
}