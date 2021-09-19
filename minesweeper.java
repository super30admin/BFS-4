//Time: O(MN)
//Space: O(MN)

//This is the bfs approach

import java.util.*;

class Solution {
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return board;
        
        m = board.length;
        n = board[0].length;
        dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {1, 1}, {-1,-1}, {1, -1}, {-1, 1}};
        
        //If the given click is a mine, then we change it to X and the game is over so we return
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        board[click[0]][click[1]] = 'B';
        
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            //get the number of mines
            int mines = getMines(board, curr[0], curr[1]);
            //tile is replaced with the number of mines surrounding it
            if(mines != 0){
                board[curr[0]][curr[1]] = (char) (mines + '0'); 
            }
            //there are 0 surrounding mines so all  tiles on  edges need to be revealed so we add them to  queue
            else{
                for(int[] dir: dirs){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E'){
                        queue.add(new int[]{nr, nc});
                        board[nr][nc] = 'B';
                    }
                
                }
            }
        }
        return board;
    }
    
    //This method checks all 8 edges to count the number of mines
    private int getMines(char[][] board, int i , int j){
        int mines = 0;
        for(int[] dir: dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M'){
                mines++;
            }
        }
        
        return mines;
    }
}