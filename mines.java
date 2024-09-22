// Time Complexity : 0(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class Solution {
    int m;
    int n;
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.m = board.length;
        this.n = board[0].length;
        this.dirs = new int[][]{{-1,-1},{-1,1},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{1,0}};

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        board[click[0]][click[1]] = 'B';
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int result = countMines(board, curr[0], curr[1]);
            if(result != 0) {
                board[curr[0]][curr[1]] = (char)(result + '0');
            } else {
                for(int[] dir : dirs) {
                int nr = dir[0] + curr[0];
                int nc = dir[1] + curr[1];
                    if( nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E') {
                        board[nr][nc] = 'B';
                        queue.add(new int[]{nr,nc});
                    }
                }
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j) {
        int result  = 0;
        for(int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            if( nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M') {
                result++;
            }
        }
        return result;
    }
}