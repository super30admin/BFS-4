//Time Complexity O(N*M)
//Space Complexity O(N*M)
//Leetcode tested

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        dirs = new int[][]{
                {0,1},
                {0,-1},
                {1,0},
                {-1,0},
                {1,1},
                {1,-1},
                {-1,1},
                {-1,-1}
        };
        m = board.length;
        n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        board[click[0]][click[1]] = 'B';
        while (!queue.isEmpty()){
            int[] curr= queue.poll();
            int count = countMines(board,curr[0],curr[1]);
            if(count == 0){
                for (int[] dir:dirs) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r >= 0 && c>=0 && r < m && c < n && board[r][c] == 'E'){
                        queue.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }else{
                board[curr[0]][curr[1]] = (char)count;
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i,int j){
        int count=0;

        for (int[] dir:dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r >= 0 && c>=0 && r < m && c < n && board[r][c] == 'M') count++;
        }
        return count;
    }
}
