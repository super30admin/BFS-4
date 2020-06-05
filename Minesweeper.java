// Time Complexity : O(m*n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {

    // DFS Solution
    public char[][] updateBoardDFS(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];

        if(board[i][j] == 'M'){
            board[i][j] = 'X';
            return board;
        }

        dfs(board, i, j);

        return board;
    }

    int[] dirX = {-1, 1, -1, 1, 0, 0, 1, -1};
    int[] dirY = {-1, 1, 1, -1, 1, -1, 0, 0};

    private void dfs(char[][] board, int i, int j){
        //base
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'E'){
            return;
        }

        //logic
        int mines = getNumberOfMines(board,i,j);

        if(mines == 0){
            board[i][j] = 'B';
            for(int x = 0; x < 8; x++){
                dfs(board, i + dirX[x], j + dirY[x]);
            }
        } else {
            board[i][j] = (char) (mines + '0');
        }

    }

    private int getNumberOfMines(char[][] board, int i, int j){
        int mines = 0;
        for(int x = 0; x < 8; x++){
            int xCoordinate = i + dirX[x];
            int yCoordinate = j + dirY[x];

            if(xCoordinate < 0 || xCoordinate >= board.length ||yCoordinate < 0 || yCoordinate >= board[0].length){
                continue;
            }

            if(board[xCoordinate][yCoordinate] == 'M'){
                mines++;
            }
        }

        return mines;
    }

    // BFS Solution

    int m;
    int n;

    public char[][] updateBoardBFS(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }

        m = board.length;
        n = board[0].length;

        int i = click[0];
        int j = click[1];

        if(board[i][j] == 'M'){
            board[i][j] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        board[i][j] = 'B';
        q.add(click);

        while(!q.isEmpty()){

            int[] curr = q.poll();

            int adjMines = calculateMines(board, curr[0], curr[1]);

            if(adjMines == 0){

                for(int[] dir : dirs){

                    int x = curr[0] + dir[0];
                    int y =  curr[1] + dir[1];

                    if(x >= 0 && x < m && y >=0 && y < n && board[x][y] == 'E'){

                        q.add(new int[] {x,y});
                        board[x][y] = 'B';
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char) (adjMines + '0');
            }
        }

        return board;

    }


    int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0, 1}, {-1,-1}, {1,1}, {-1,1}, {1, -1}};

    private int calculateMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir : dirs){

            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >=0 && y < n && board[x][y] == 'M'){
                count++;
            }
        }

        return count;
    }
}
