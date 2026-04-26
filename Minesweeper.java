// Time Complexity : O(m * n)
// Space Complexity : O(m * n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We do a BFS traversal using queue by checking all the 8 directions and count the number of adjacent mines if
present. If there, we update that cell value with that count, if not, we traverse further in the 8 directions
and update the cells with 'B' value. If Initial click position is a mine, we update the cell value to 'X'
and return the board.
 */
class Solution {
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.m = board.length;
        this.n = board[0].length;
        this.dirs = new int[][] {{0, 1} , {1, 0} , {0, -1}, {-1, 0}, {-1, -1}, {1, 1}, {-1,1}, {1, -1}};
        int x = click[0];
        int y = click[1];
        if(board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        board[x][y] = 'B';

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if(count == 0) {
                for(int[] dir : dirs) {
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E') {
                        q.add(new int[] {nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else {
                board[curr[0]][curr[1]] = (char)(count + '0');
            }

        }
        return board;
    }

    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        for(int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;

            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M')
                count++;
        }
        return count;
    }
}