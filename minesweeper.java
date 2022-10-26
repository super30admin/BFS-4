// Time Complexity : O(n*n) 
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach


class Solution {
    int [][] dirs;
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        // null
        dirs = new int [][] {{0,1}, {0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        if(board == null || board.length == 0) return board;
        m  = board.length; n = board[0].length;
        //case1
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int mines = countMines(board, curr[0], curr[1]); //board,r,c
            if(mines > 0){
                board[curr[0]][curr[1]] = (char)(mines + '0');
            } else {
                //process the babies
                for(int [] dir: dirs){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc <n && board[nr][nc] == 'E'){
                        q.add(new int [] {nr,nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
        }
        return board;
    }
    private int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int [] dir: dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >= 0 && nr < m && nc >= 0 && nc <n && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}