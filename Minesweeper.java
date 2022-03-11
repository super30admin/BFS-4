package bfs4;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
	//BFS
	//Time Complexity : O(m*n), where m are the rows and n are the columns of board
	//Space Complexity : O(m*n)
	//Did this code successfully run on Leetcode : Yes
	//Any problem you faced while coding this : No
	public char[][] updateBoard(char[][] board, int[] click) {
        // null
        if(board == null || board.length == 0)
            return board;
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {click[0], click[1]});
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        board[click[0]][click[1]] = 'B';
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int mines = countMines(dirs, curr, board.length, board[0].length, board);
            if(mines > 0)
                board[curr[0]][curr[1]] = (char) (mines + '0');
            else {
                for(int[] dir: dirs) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'E') {
                        queue.offer(new int[] {r, c});
                        board[r][c] = 'B';
                    }
                }
            }
        }
        return board;
    }
    
    private int countMines(int[][] dirs, int[] curr, int m, int n, char[][] board) {
        int count = 0;
        for(int[] dir: dirs) {
            int r = dir[0] + curr[0];
            int c = dir[1] + curr[1];
            if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'M')
                count++;
        }
        
        return count;
    }
    
    //DFS
  	//Time Complexity : O(m*n), where m are the rows and n are the columns of board
  	//Space Complexity : O(m*n)
  	//Did this code successfully run on Leetcode : Yes
  	//Any problem you faced while coding this : No
    public char[][] updateBoard1(char[][] board, int[] click) {
        // null
        if(board == null || board.length == 0)
            return board;
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        dfs1(board, dirs, click[0], click[1]);
        return board;
    }
    
    private void dfs1(char[][] board, int[][] dirs, int i, int j) {
        // base
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'B')
            return;
        
        // logic
        board[i][j] = 'B';
        int mines = countMines(dirs, new int[] {i, j}, board.length, board[0].length, board);
        if(mines > 0)
            board[i][j] = (char) (mines + '0');
        else {
            for(int[] dir: dirs) {
                int r = dir[0] + i;
                int c = dir[1] + j;
                dfs1(board, dirs, r, c);
            }
        }
    }
}
