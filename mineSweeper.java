// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class Main {
    // approch 1 using BFS
    public static char[][] updateBoard(char[][] board, int[] click) {
        // null case
        if (board == null || board.length == 0)
            return board;
        // if click index contains mine
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int m = board.length;
        int n = board[0].length;
        // direcion matrix
        int[][] dirs = new int[][] { { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 0 },
                { -1, 1 } };
        // Queue for BFS
        Queue<int[]> q = new LinkedList<>();
        // add click index inside the queue
        // mark click index as revealed
        q.add(click);
        board[click[0]][click[1]] = 'B';
        // BFS traversal
        while (!q.isEmpty()) {
            // get current index
            int[] curr = q.poll();
            // count number of mines nearby
            int mines = countMines(board, dirs, curr[0], curr[1], m, n);
            // if number of mines is greater than 1 we are not going to explore it because
            // it may be possible that there are mines in vicinity
            if (mines > 0) {

                // if there is mines update it with number of mines
                board[curr[0]][curr[1]] = (char) (mines + '0');
            } else {
                // else we are checking all of its childrens
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    // check bound and check if neighbour is already revelead or not
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E') {
                        // mark as revelaed
                        board[nr][nc] = 'B';
                        // add inside the queue;
                        q.add(new int[] { nr, nc });
                    }
                }
            }
        }
        return board;

    }

    private static int countMines(char[][] board, int[][] dirs, int i, int j, int m, int n) {
        int count = 0;
        // go over each direction and check if there is mine increase the count by one
        for (int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            // check bound and check if mine is there
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }

    private static int[][] dirs;

    // approch 2 using DFS
    public static char[][] updateBoard2(char[][] board, int[] click) {
        // null case
        if (board == null || board.length == 0)
            return board;
        // if click index contains mine
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int m = board.length;
        int n = board[0].length;
        // direcion matrix
        dirs = new int[][] { { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

        // dfs
        dfs(board, click[0], click[1], m, n);

        return board;

    }

    private static void dfs(char[][] board, int i, int j, int m, int n) {

        // base case
        if (i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'E')
            return;

        // main logic
        // first count number of mines nearby
        int mines = countMines(board, i, j, m, n);
        // if mines is greater than 1
        if (mines > 0) {
            board[i][j] = (char) (mines + '0');
        } else {
            // mark as revealed
            board[i][j] = 'B';
            // get in all direction
            for (int[] dir : dirs) {
                int nr = i + dir[0];
                int nc = j + dir[1];
                dfs(board, nr, nc, m, n);
            }
        }
    }

    private static int countMines(char[][] board, int i, int j, int m, int n) {
        int count = 0;
        // go over each direction and check if there is mine increase the count by one
        for (int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            // check bound and check if mine is there
            if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' },
                { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' } };
        int[] click = new int[] { 3, 0 };
    }
}