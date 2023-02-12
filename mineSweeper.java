// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
/*
 * Run BFS approach,we move through the board from the click position. add the click values to the queue.
 * if there are  mines around it count the number and replace that value.
 * else move in all 8 directions and add the children. 
 Mark the visisted ones as B, 
 * return the board
*/
class Solution {
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;
        dirs = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
        int m = board.length;
        int n = board[0].length;

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(click[0]);
        q.add(click[1]);
        board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()) {
            int cr = q.poll();
            int cc = q.poll();

            int countMine = countMines(board, cr, cc, m, n);
            if (countMine == 0) {
                // pprocess the children
                for (int[] dir : dirs) {
                    int nr = cr + dir[0];
                    int nc = cc + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                        q.add(nr);
                        q.add(nc);
                        board[nr][nc] = 'B';
                    }
                }
            } else {
                board[cr][cc] = (char) (countMine + '0');
            }

        }

        return board;
    }

    private int countMines(char[][] board, int i, int j, int m, int n) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M')
                count++;
        }

        return count;
    }
}

// DFS

// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
/*
 * Run DFS approach,we move through the board from the click position.
 * if there are mines around it count the number and replace that value.
 * else move in all 8 directions and add the children.
 * Mark the visited ones as B,
 * return the board
 */
class Solution {
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return board;
        dirs = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
        int m = board.length;
        int n = board[0].length;

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1], m, n);

        return board;
    }

    private void dfs(char[][] board, int i, int j, int m, int n) {
        // base
        if (i < 0 || i == m || j < 0 || j == n || board[i][j] != 'E')
            return;
        // logic
        board[i][j] = 'B';
        int count = countMines(board, i, j, m, n);
        if (count == 0) {
            for (int[] dir : dirs) {
                int nr = i + dir[0];
                int nc = j + dir[1];
                dfs(board, nr, nc, m, n);
            }
        } else {
            board[i][j] = (char) (count + '0');
        }
    }

    private int countMines(char[][] board, int i, int j, int m, int n) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M')
                count++;
        }

        return count;
    }
}