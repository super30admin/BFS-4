// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Nos


class Solution {
    int dirs[][];
    int row, col;
    public char[][] updateBoard(char[][] board, int[] click) {
        
        Queue<int[] > q = new LinkedList<>();

        row = board.length;
        col = board[0].length;
        dirs = new int [][] {{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}}; // U, D, L, R, UL, UR, DL, DR

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add(new int[] {click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        //BFS
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int mines = countMines(board, curr);
            if(mines==0){
                for(int[] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if(nr>=0 && nr < row && nc < col && nc >= 0 && board[nr][nc] =='E'){
                        q.add(new int [] {nr,nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else{
                board[curr[0]][curr[1]] = (char)(mines + '0');
            }
        }
        return board;
    }

    private int countMines(char [][]board, int [] curr){
        int count = 0;
        for(int[] dir: dirs){
            int nr = curr[0] + dir[0];
            int nc = curr[1] + dir[1];
            if(nr>=0 && nr< row && nc < col && nc >= 0 && board[nr][nc] =='M'){
                count++;
            }
        }
        return count;
    }
}