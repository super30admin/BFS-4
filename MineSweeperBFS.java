// Time Complexity : O(m*n)  -> 8 times O(m*n) (for all 8 directions)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes

import java.util.LinkedList;
import java.util.Queue;

public class MineSweeperBFS {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
        m = board.length;
        n = board[0].length;
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if(count != 0)
            {
                board[curr[0]][curr[1]] = (char)(count+'0');
            }
            else
            {
                for(int[] dir: dirs){
                    int r = curr[0]+ dir[0];
                    int c = curr[1] + dir[1];
                    if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'E'){
                        board[r][c] = 'B';
                        q.add(new int[]{r,c});
                    }
                }
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j){
        int count =0;
        for(int[] dir: dirs){
            int r = i + dir[0];
            int c = j + dir[1];
            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}

