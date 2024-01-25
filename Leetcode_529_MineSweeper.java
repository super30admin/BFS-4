//DFS APPROACH

/*
approach: 
1. base case - check if the click itself is mine, mark it as X and return. 
2.have a dirs array defined for all the neighbors 
3.perform a dfs on click. 
4. dfs logic
    a. mark the index as visited = 'B'
    b. get the number of mines near to that curr position, go through all direction
    c. if count of mines are zero, we're free to explore babies of the curr position, 
    d. traverse through the firs array and perform recursive dfs only if it's in bound and also that position is not explored == 'E'
    e. If count of mines is greater than Zero! We can'rtexplore the further babies, and just set the current position as count (Char conversion)
    f. return!
    g. the dfs will not need any base case, as once we run out of babies, we';ll automatically pop the function calls from stack and at last return.
    
    TC: O(m*n)
    SC: O(m*n) - stack space!
*/

class Solution {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        // base case
        if (board == null || click == null)
            return board;
        // Up DOWN Right Left left-upD Rt-dn-d r-up

        // if click index == mine, print x and return board
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        this.dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { 1, 1 }, { -1, 1 },
                { 1, -1 } };

        this.m = board.length;
        this.n = board[0].length;

        dfs(board, click);
        return board;
    }

    private void dfs(char[][] board, int[] curr) {
        // no need of base case
        // logic
        board[curr[0]][curr[1]] = 'B'; // mark it as visited.
        int count = countMines(board, curr);

        if (count > 0) {
            board[curr[0]][curr[1]] = (char) (count + '0');
        } else {
            // explore the neighbor
            for (int[] dir : dirs) {
                int r = dir[0] + curr[0];
                int c = dir[1] + curr[1];

                if (r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'E') // if not visited
                {
                    dfs(board, new int[] { r, c });
                }
            }
        }

    }

    private int countMines(char[][] board, int[] click) {
        int count = 0;
        for (int[] dir : dirs) {
            int r = dir[0] + click[0];
            int c = dir[1] + click[1];

            // check bounds and M
            if (r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'M')
                count++;
        }
        return count;
    }
}