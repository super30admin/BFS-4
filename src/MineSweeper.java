import java.util.Arrays;
//time complexity- O(m*n)
//space complexity- O(m*n)
public class MineSweeper {
    static int[][] dirs = new int[][]{{-1,0},{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    static int m;
    static int n;
    static char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }

    static void dfs(char[][] board, int r, int c){
        if(r < 0 || c < 0 || r == m || c == n || board[r][c] != 'E') {
            return;
        }
        board[r][c] = 'B';

        int count = countMines(board, r, c);
        if(count == 0){
            for(int[] dir : dirs){
                int cr = dir[0] + r;
                int cc = dir[1] + c;
                dfs(board, cr, cc);
            }
        }else{
            board[r][c] = (char)(count + '0');
        }
    }
    static int countMines(char[][] board, int r, int c){
        int count = 0;
        for(int[] dir: dirs){
            int cr = dir[0] + r;
            int cc = dir[1] + c;
            if(cr>=0 && cc>=0 && cr<m && cc<n && board[cr][cc] == 'M'){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        char [][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        int[] click = new int[] {3,0};
        char[][] res = updateBoard(board, click);
        System.out.println("Board:");
        for (char[] b: board) {
            System.out.println(Arrays.toString(b));
        }
        System.out.println();
        System.out.println("Result:");
        for (char[] r: res) {
            System.out.println(Arrays.toString(r));
        }
    }
}
