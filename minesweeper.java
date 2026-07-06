// Time Complexity : O(m * n) because each cell can be visited at most once
// Space Complexity : O(m * n) for the queue in the worst case
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use BFS starting from the clicked cell to reveal connected empty cells.
// If a cell has adjacent mines, we mark it with the mine count and stop expanding from it.
// If a cell has no adjacent mines, we mark it as blank and continue exploring its unrevealed neighbors.

class Solution {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs = new int[][]{{-1,0},{-1,-1},{1,0},{0,-1},{0,1},{1,-1},{1,1},{-1,1}};
        this.m = board.length;
        this.n = board[0].length;

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int count = countMines(board, curr[0], curr[1]);

            if(count == 0) {
                for(int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];

                    if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'E') {
                        q.add(new int[]{r, c});
                        board[r][c] = 'B';
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }

        return board;
    }

    private int countMines(char[][] board, int i, int j) {
        int count = 0;

        for(int[] dir : dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;

            if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'M') {
                count++;
            }
        }

        return count;
    }
}