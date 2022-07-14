import java.util.LinkedList;
import java.util.Queue;

//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
class Solution {
    int [][] dirs;
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length == 0 || board == null) return board;
        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        m = board.length; n = board[0].length;
        //if mine is at click
        if(board[click[0]][click[1]] =='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] ='B';
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int mines = countMines(board, curr[0], curr[1]);
            //if there more mines assign the number of mines
            if(mines > 0){
                board[curr[0]][curr[1]] = (char)(mines + '0');
            }
            //add children and make it B
           else{
                for(int [] dir : dirs){
                int nr = curr[0]+ dir[0];
                int nc = curr[1]+ dir[1];
                if(nr >= 0 && nr < m && nc >= 0  && nc < n && board[nr][nc] == 'E'){
                    board[nr][nc] = 'B';
                    q.add(new int[]{nr,nc});
                }
             }
           }
        }
        return board;
    }
    //counting number of mines around the cell
    private int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int [] dir : dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'M') count++;
        }
        return count;
    }
}
