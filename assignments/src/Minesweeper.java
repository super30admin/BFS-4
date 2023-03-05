// Approach: BFS. DFS also possible
// Time: O(m*n)
// Space: O(m*n)

import java.util.*;

class Minesweeper {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) return board;
        int m = board.length, n = board[0].length;
        dirs = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(click[0]);
        q.add(click[1]);
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int currRow = q.poll();
            int currCol = q.poll();
            int mines = countMines(board, currRow, currCol, m, n);

            if (mines == 0) {
                // process its babies
                for (int[] dir : dirs) {
                    int nr = currRow + dir[0];
                    int nc = currCol + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                        q.add(nr);
                        q.add(nc);
                        board[nr][nc] = 'B';
                    }
                }
            }
            else {
                board[currRow][currCol] = (char)(mines + '0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j, int m, int n) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }
}