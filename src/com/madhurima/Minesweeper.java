//Time Complexity = O(m*n) {worst case: going over all cells in the grid}
//Space Complexity = O(m*n) {queue in case of bfs and recursive stack in case of dfs}

package com.madhurima;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
}

class MinesweeperBFS {

    int m, n;
    int[][] dirs;

    //bfs
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }

        m = board.length;
        n = board[0].length;
        dirs = new int[][]{{-1,0}, {0,-1}, {1,0}, {0,1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        Queue<int[]> q = new LinkedList<>();

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        //start bfs
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int countMines = countMines(board, curr[0], curr[1]);
            if(countMines != 0){//don't explore neighbors
                board[curr[0]][curr[1]] = (char)(countMines + '0');
            }else{
                // board[curr[0]][curr[1]] = 'B';
                for(int[] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E'){
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return board;

    }

    private int countMines(char[][] board, int row, int col){
        int total = 0;
        for(int[] dir: dirs){
            int nr = row + dir[0];
            int nc = col + dir[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                total++;
            }
        }
        return total;
    }
}

class MinesweeperDFS {

    int m, n;
    int[][] dirs;

    //dfs
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }

        m = board.length;
        n = board[0].length;
        dirs = new int[][]{{-1,0}, {0,-1}, {1,0}, {0,1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dfs(board, click);

        return board;

    }

    private void dfs(char[][] board, int[] click){
        //base
        if(click[0] < 0 || click[0] == m || click[1] < 0 || click[1] == n || board[click[0]][click[1]] != 'E'){
            return;
        }

        //logic
        board[click[0]][click[1]] = 'B';
        int countMines = countMines(board, click[0], click[1]);

        if(countMines != 0){//don't explore neighbors
            board[click[0]][click[1]] = (char)(countMines + '0');
        }else{
            for(int[] dir: dirs){
                int nr = click[0] + dir[0];
                int nc = click[1] + dir[1];
                dfs(board, new int[]{nr, nc});
            }
        }

    }

    private int countMines(char[][] board, int row, int col){
        int total = 0;
        for(int[] dir: dirs){
            int nr = row + dir[0];
            int nc = col + dir[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                total++;
            }
        }
        return total;
    }
}
