import java.util.LinkedList;
import java.util.Queue;


//Time complexity: O(MXN)
//Space Complexity: O(MXN) size of the queue for BFS and call stack for DFS
class Minesweeper {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        dirs = new int[][]{{0,1},{1,1}, {1,0}, {1,-1}, {0,-1},{-1,-1},{-1,0},{-1,1}};
        //start exploring from the click
        // if the neighbours contains mines -> count the mines and place it in the current index
        // if no mines -> explore neighbours


        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        //this is the DFS implementation
        dfs(board, click[0], click[1]);

        //this is the BFS implementation
        Queue<Integer> queue = new LinkedList<>();
         int mines = countMines(click[0], click[1], board);
         if(mines != 0){
             board[click[0]][click[1]] = (char)(mines + '0');
             return board;
         }
         board[click[0]][click[1]] = 'B';

         queue.offer(click[0]);
         queue.offer(click[1]);

         while(!queue.isEmpty()){
             int r = queue.poll();
             int c = queue.poll();

             // board[r][c] = 'B';
             for(int[] dir: dirs){
                 int nr = r + dir[0];
                 int nc = c + dir[1];
                 if(nr >=0 && nr < board.length && nc >=0 && nc < board[0].length && board[nr][nc] == 'E'){
                     mines = countMines(nr,nc, board);
                     if(mines != 0){
                         board[nr][nc] = (char)(mines + '0');
                     }
                     else{
                         board[nr][nc] = 'B';
                         queue.offer(nr);
                         queue.offer(nc);
                     }

                 }
             }
         }

        return board;

    }

    private void dfs(char[][] board, int r, int c){
        //base
        if(r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'E') return;


        int mines = countMines(r,c,board);
        if(mines != 0){
            board[r][c] = (char)(mines + '0');
            return;
        }
        board[r][c] = 'B';

        //logic
        for(int[] dir: dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];

            dfs(board, nr, nc);
        }

    }
    private int countMines(int i, int j, char[][]board){
        int count = 0;

        for(int[] dir: dirs){
            int nr = i + dir[0];
            int nc = j + dir[1];
            if(nr >=0 && nr < board.length && nc >=0 && nc < board[0].length && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;

    }
}