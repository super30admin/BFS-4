// Time Complexity : O(m * n) --> where m & n are the lengths of input matrix
// Space Complexity : O(m * n)
// Did this code successfully run on Leetcode (529): Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class Solution {
    int dirs[][];
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        // edge case
        if (board == null || board.length == 0) return board;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dirs = new int[][] {{0,-1}, {0,1}, {-1,0}, {1,0}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
        m = board.length; n = board[0].length; 
        
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        
        while (!q.isEmpty()) {
            int curr[] = q.poll();
            int r = curr[0]; int c = curr[1];
            int mines = getMines(board, r, c);
            if (mines == 0) { // then only process the childrens
                for (int dir[] : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < m && nr >= 0 && nc < n && nc >= 0 && board[nr][nc] == 'E') {
                        q.add(new int[] {nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else board[r][c] = (char) (mines + '0');
        }
        return board;
    }
    
    private int getMines(char[][] board, int i, int j) {
        int count = 0;
        for (int dir[] : dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if (r < m && r >= 0 && c < n && c >= 0 && board[r][c] == 'M') count++;
        }
        return count;
    }
}