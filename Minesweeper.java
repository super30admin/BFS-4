import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper{
    //bfs - o(mn) o(mn)
    //dfs - o(mn) o(mn)
    int dirs[][] = {{-1,-1},{-1,1},{-1,0},{1,1},{1,-1},{1,0},{0,1},{0,-1}};
    int n, m;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.n = board.length;
        this.m = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board; 
        }
        dfs(board, click[0], click[1]);


        return board;

        //bfs version below- 
        // q.offer(click);
        // board[click[0]][click[1]] = 'B';
        // while(!q.isEmpty()){
        //     int curr[] = q.poll();
        //     int count = countMines(board,curr[0], curr[1]);
        //     if(count != 0){
        //         //we have mines around 
        //         board[curr[0]][curr[1]] = (char)(count + '0');
        //     }else{
        //         for(int d[]: dirs){
        //             int r = curr[0] + d[0];
        //             int c = curr[1] + d[1];
        //             if(r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'E'){
        //                 board[r][c] = 'B';
        //                 q.offer(new int[]{r,c});
        //             }
        //         }
        //     }
        // }
        // return board;
    }

    private void dfs(char[][] board, int i, int j){
        board[i][j] = 'B';
        int count = countMines(board, i,j);
        if(count != 0){
            board[i][j] = (char)(count+'0');
        }else{
            for(int d[]: dirs){
                int r = i + d[0];
                int c = j + d[1];
                if(r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'E'){
                    dfs(board, r,c);
                }
            }
        }
    }


    public int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int d[]: dirs){
            int r = i + d[0];
            int c = j + d[1];
            if(r >= 0 && c >= 0 && r < n && c < m && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}