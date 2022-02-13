// Time Complexity = O(m*n)
// Space Complexity = O(m*n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// BFS solution
class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) return board;
        m = board.length;
        n = board[0].length;

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dirs = new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if (count > 0) {
                board[curr[0]][curr[1]] = (char)(count+'0');
            }
            else {
                for (int[] dir: dirs) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if (r>=0 && r<m && c>=0 && c<n && board[r][c] == 'E') {
                        board[r][c] = 'B';
                        q.add(new int[] {r, c});
                    }
                }
            }
        }

        return board;
    }

    private int countMines(char[][] board, int r, int c) {
        int count=0;

        for (int[] dir:dirs) {
            int row = r+dir[0];
            int col = c+dir[1];
            if (row>=0 && row<m && col>=0 && col<n && board[row][col] == 'M') {
                count++;
            }
        }

        return count;
    }
}

// ---------------------------------------------------------------------------------------------------------
// DFS solution
class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) return board;
        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dfs(board, click[0], click[1]);

        return board;
    }

    private void dfs(char[][] board, int r, int c) {
        //base
        if (r<0 || r == m || c<0 || c == n || board[r][c] != 'E') return;

        //logic
        board[r][c] = 'B';
        int count = countMines(board, r, c);
        if (count > 0) {
            board[r][c] = (char)(count+'0');
        }
        else {
            for (int[] dir: dirs) {
                int row = dir[0] + r;
                int col = dir[1] + c;
                dfs(board, row, col);
            }
        }
    }

    private int countMines(char[][] board, int r, int c) {
        int count=0;

        for (int[] dir:dirs) {
            int row = r+dir[0];
            int col = c+dir[1];
            if (row>=0 && row<m && col>=0 && col<n && board[row][col] == 'M') {
                count++;
            }
        }

        return count;
    }
}