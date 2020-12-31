//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : None

package com.s30.satish;

import java.util.LinkedList;
import java.util.Queue;

class Minesweeper {
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return board;
        dirs = new int[][] {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        Queue<int[]> q = new LinkedList<>();
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int minesCount = 0;
        m = board.length;
        n = board[0].length;
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty())
        {
            int size = q.size();
            int[] currLocation = q.poll();
            minesCount = getMinesCount(board, currLocation[0], currLocation[1]);
            if(minesCount == 0)
            {
                for(int[] dir : dirs)
                {
                    int r = currLocation[0] + dir[0];
                    int c = currLocation[1] + dir[1];
                    if(r < m && c < n && r >= 0 && c >= 0 && board[r][c] == 'E')
                    {
                        board[r][c] = 'B';
                        q.add(new int[] {r,c});
                    }
                }
            }
            else
            {
                board[currLocation[0]][currLocation[1]] = (char)(minesCount + '0');
            }
        }
        return board;
    }
    private int getMinesCount(char[][] board, int i, int j)
    {
        int count = 0;
        for(int[] dir : dirs)
        {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r < m && c < n && r >= 0 && c >= 0 && board[r][c] == 'M')
                count++;
        }
        return count;
    }
    
    
//    // DFS Appraoch
//    int[][] dirs;
//    int m, n;
//    public char[][] updateBoard(char[][] board, int[] click) {
//        if(board == null || board.length == 0)
//            return board;
//        dirs = new int[][] {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
//        if(board[click[0]][click[1]] == 'M')
//        {
//            board[click[0]][click[1]] = 'X';
//            return board;
//        }
//        m = board.length;
//        n = board[0].length;
//        dfs(board, click[0], click[1]);
//        return board;
//    }
//    private void dfs(char[][] board, int i, int j)
//    {
//        // Base case
//        if(i == m || j == n || i < 0 || j < 0 || board[i][j] != 'E')
//            return;
//        // Main logic
//        board[i][j] = 'B';
//        int minesCount = getMinesCount(board, i, j);
//        if(minesCount == 0)
//        {
//            for(int[] dir : dirs)
//            {
//                int r = dir[0] + i;
//                int c = dir[1] + j;
//                dfs(board, r, c);
//            }
//        }
//        else
//        {
//            board[i][j] = (char)(minesCount + '0');
//        }
//    }
//    private int getMinesCount(char[][] board, int i, int j)
//    {
//        int count = 0;
//        for(int[] dir : dirs)
//        {
//            int r = dir[0] + i;
//            int c = dir[1] + j;
//            if(r < m && c < n && r >= 0 && c >= 0 && board[r][c] == 'M')
//                count++;
//        }
//        return count;
//    }
}
