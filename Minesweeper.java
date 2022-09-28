// Time Complexity : O(mn) To cover all nodes
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

import java.util.*;

class Minesweeper {
    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        //null
        if(board==null || board.length==0)return board;
        int m = board.length;
        int n = board[0].length;
        Queue<int[]>q = new LinkedList<>();
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        q.add(click);
        board[click[0]][click[1]]='B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mines = countMines(board, curr[0],curr[1]);
            if(mines>0){
                board[curr[0]][curr[1]] = (char)(mines+'0');
            }
            else{
                for(int[] dir : dirs){
                    int nr = dir[0]+curr[0];
                    int nc = dir[1]+curr[1];
                    if(nr>=0 && nc>=0 && nr<m && nc<n && board[nr][nc]=='E'){
                        q.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    }
                }

            }
        }
        return board;
    }
    private int countMines(char[][]board, int i, int j){
        int count = 0;
        for(int[] dir : dirs){
            int nr = dir[0]+i;
            int nc = dir[1]+j;
            if(nr>=0 && nc>=0 && nr<board.length && nc<board[0].length && board[nr][nc]=='M'){
                count++;
            }
        }

        return count;
    }
}