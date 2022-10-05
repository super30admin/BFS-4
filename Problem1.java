// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        int m = board.length; int n = board[0].length;
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], m, n);
            if(count == 0) {
                for(int[] dir: dirs) {
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
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
    
    private int countMines(char[][] board, int i, int j, int m, int n) {
        int count = 0;
        for(int[] dir: dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }
}