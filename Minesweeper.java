//TC will be O(m * n)
//SC will be O(m * n)

import java.util.LinkedList;
import java.util.Queue;

class Minesweeper {
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }
        m = board.length;
        n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        dirs = new int[][] {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}}; //U D L R UL UR LL LR

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int countmines = countMines(board, curr);
            if(countmines == 0){
                for(int[] dir : dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E'){
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr, nc});
                    }

                }
            }
            else{
                board[curr[0]][curr[1]] = (char)(countmines + '0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int[] curr){
        int count = 0;
        for(int[] dir : dirs){
            int nr = curr[0] + dir[0];
            int nc = curr[1] + dir[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc]  == 'M'){
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args){
        Minesweeper obj = new Minesweeper();
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        int[] click = {3,0};
        obj.updateBoard(board,click);
    }
}
