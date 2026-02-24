// Time Complexity : O(8mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// use DFS to process the neighbours if they are unvisited
// count the number of mines surrounding the cell , process neighbous if its 0
// else update the cell with count
// mark the cell as visited before adding to the queue or calling dfs

class Solution {
    int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 } };

    public char[][] updateBoardDFS(char[][] board, int[] click) {

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int m = board.length;
        int n = board[0].length;
        board[click[0]][click[1]] = 'B';
        dfs(board, click, m, n);

        return board;
    }

    private void dfs(char[][] board, int[] curr, int m, int n) {

        int count = countMines(board, curr, m, n);
        if (count == 0) {
            for (int[] dir : dirs) {
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                    board[nr][nc] = 'B';
                    dfs(board, new int[] { nr, nc }, m, n);
                }
            }
        } else
            board[curr[0]][curr[1]] = (char) (count + '0');

    }

    public char[][] updateBoardBFS(char[][] board, int[] click) {

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int m = board.length;
        int n = board[0].length;
        Queue<int[]> q = new LinkedList();
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int[] curr = q.poll();
                int count = countMines(board, curr, m, n);
                if (count == 0) {
                    for (int[] dir : dirs) {
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];
                        if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                            q.add(new int[] { nr, nc });
                            board[nr][nc] = 'B';
                        }
                    }
                } else
                    board[curr[0]][curr[1]] = (char) (count + '0');
            }
        }

        return board;
    }

    private int countMines(char[][] board, int[] curr, int m, int n) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = curr[0] + dir[0];
            int nc = curr[1] + dir[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                if (board[nr][nc] == 'M')
                    count++;
            }
        }
        return count;
    }
}