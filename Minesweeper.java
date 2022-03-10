// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {

    //bfs solution
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        //base cases
        if(board == null || board.length == 0) return board;

        //if initial click is only mine, change to 'X' and return
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        m = board.length; n = board[0].length;

        //bfs
        Queue<int []> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B'; //mark processed
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0],curr[1]);
            if(count == 0){
                //process the neighbors
                for(int[] dir: dirs){
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    //bound check + not visited
                    if( r >=0 && c >=0 && r < m && c < n
                            && board[r][c] == 'E'){
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }else{
                //mines found in neighborhood
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir: dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            //bound check and if cell
            if(r >= 0 && c >=0 && r < m && c < n
                    && board[r][c] == 'M')
                count++;
        }
        return count;
    }
}
