/*Time Complexity: O(M*N)

Space Complexity: O(M*N)

Did this code successfully run on Leetcode : Yes

Approach: BFS - Create a queue to keep track of the neighbors being processed and convert to 'B'. If 
there is a mine in the sorrounding change it to 1 to indicate the number of mines.

Prob: 529. Minesweeper
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int [][] dirs = new int [][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], dirs);
            if(count != 0){
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
            else{
                for(int [] dir : dirs){
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];
                if(nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length 
                && board[nr][nc] == 'E'){
                    q.add(new int[]{nr, nc});
                    board[nr][nc] = 'B';
                    }
                }
            }
        }
        return board;
    }
    private int countMines(char[][] board, int r, int c, int [][] dirs){
        int count = 0;
        for(int [] dir : dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >=0 && nr < board.length && nc >= 0 && nc < board[0].length 
            && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}