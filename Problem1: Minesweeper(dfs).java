//O(MxN) time and space complexity

//dfs solution
class Solution {
    int dirs[][];
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length; int n = board[0].length;
        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board,click[0], click[1] , m, n);
        return board;
    }
    private void dfs(char[][] board, int i , int j, int m, int n){
        //base
        if (i < 0 || i==m || j < 0 || j==n || board[i][j] != 'E')
            return;
        //logic
        board[i][j] = 'B';
        int count = countMines(board, i, j, m, n);
        if (count == 0){
            for(int[] dir : dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            dfs(board, nr, nc, m, n);
        }
    }else{
            board[i][j] = (char)(count + '0');
        }
    }
    private int countMines(char[][] board, int i, int j, int m, int n){
        int count = 0;
        for(int[] dir : dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if (nr >=0 && nr < m && nc >=0 && nc <n && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}


