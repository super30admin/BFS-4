import java.util.*;

// problem link : https://leetcode.com/problems/minesweeper/description/
// time complexity  : O(n*n) quadratic
// space complexity : O(1)

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        int[][] dirs = {
            {0,1},{0,-1},{1,0},{-1,0},
            {1,1},{-1,-1},{1,-1},{-1,1}
        };

        int m = board.length;
        int n = board[0].length;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            if(isMine(curr, board)){
                board[curr[0]][curr[1]] = 'X';
                continue;
            }
            int numMines = numMinesSurrounded(curr, board, dirs);
            if(numMines == 0){
                board[curr[0]][curr[1]] = 'B';
                for(int[] dir : dirs){
                    int row = curr[0] + dir[0];
                    int col = curr[1] + dir[1];
                    if(isValid(row, col , m, n) && board[row][col] == 'E'){
                        board[row][col] = 'V';
                        q.add(new int[] {row, col});
                    }
                }
            }
            else {
                char num = (char)(numMines + '0');
                board[curr[0]][curr[1]] = num;
            }
        }
        return board;
    }

    private boolean isMine(int[] curr, char[][] board){
        return board[curr[0]][curr[1]] == 'M' || board[curr[0]][curr[1]] == 'X';
    }

    private int numMinesSurrounded(int[] curr, char[][] board, int[][] dirs){
        int numMines = 0;
        int m = board.length;
        int n = board[0].length;
        for(int[] dir : dirs){
                    int row = curr[0] + dir[0];
                    int col = curr[1] + dir[1];
                    if(isValid(row, col , m, n) && (board[row][col] =='M'
                || board[row][col] =='X'))
                       numMines++;  
                }
        return numMines;
    }

    private boolean isValid(int row, int col, int m, int n ){
        return row>=0 && row<m && col>=0 && col<n;
    }
}
