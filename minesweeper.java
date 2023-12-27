// Time Complexity : O(m*n), where m and n are dimensions of the board
// Space Complexity : O(m*n), where m and n are dimensions of the board
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


// Your code here along with comments explaining your approach

class Solution {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return board;
        int m = board.length;
        int n = board[0].length;
        dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(click[0]);
        q.add(click[1]);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int cr = q.poll();
            int cc = q.poll();
            int countMines = countMines(board, cr, cc, m, n);
            if(countMines == 0){
                // process its babies
                for(int[] dir: dirs){
                    int nr = cr + dir[0];
                    int nc = cc + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E'){
                        q.add(nr);
                        q.add(nc);
                        board[nr][nc] = 'B';
                    }
                }
            } else {
                board[cr][cc] = (char)(countMines + '0');
            }
        }
        return board;
    }
    private int countMines(char[][] board, int i, int j, int m, int n){
        int count = 0;
        for(int[] dir: dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}