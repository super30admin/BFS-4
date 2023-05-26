import java.util.*;
public class Minesweeper {
    //TC = O(m*n) SC = O(m*n)
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        dirs = new int[][]{{0,-1},{-1,0},{0,1},{1,0},{1,-1},{-1,1},{1,1},{-1,-1}};
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board,curr[0],curr[1],m,n);
            if(count==0){
                for(int[] dir:dirs){
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc] == 'E'){
                        q.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    }
                }

            }else{
                board[curr[0]][curr[1]] = (char)(count+'0');
            }

        }
        return board;
    }


    private int countMines(char[][] board,int row,int col,int m,int n){
        int count = 0;
        for(int[] dir:dirs){
            int nr = dir[0]+row;
            int nc = dir[1]+col;
            if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}
