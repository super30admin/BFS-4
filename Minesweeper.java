//TC: O(M*N), M*N-> dimensions of the board
//SC: O(M*N), M*N-> dimensions of the board
// Did it run successfully on Leetcode? : Yes
class Solution {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0)
            return null;
        dirs = new int[][]{{-1,0},{0,-1},{-1,-1},{1,1},{1,0},{0,1},{-1,1},{1,-1}};
        int m = board.length;
        int n = board[0].length;
        if ( board[click[0]][click[1]]  == 'M'){
            board[click[0]][click[1]] = 'X';
             return board;
        }
        return BFS(board, click, m, n);
    }
    private char[][] BFS(char[][] board, int[] click, int m, int n)
    { 
        Queue<int[]> q = new LinkedList();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()){
            int[] curr = q.poll();
            int mines = getMines(board, curr[0], curr[1], m, n);
            if (mines == 0){
            for (int[] dir: dirs){
                int i = dir[0] + curr[0];
                int j = dir[1] + curr[1];
                if (i <= m-1 && i >=0 && j <= n-1 && j >=0 && board[i][j] == 'E'){
                        q.add(new int[]{i,j});
                        board[i][j] = 'B';
                    }
                }
            }
            else{
                 board[curr[0]][curr[1]] = (char)(mines + '0');
            }
        }
        return board;
    }
    private int getMines(char[][] board, int i, int j, int m, int n){
        int result = 0;
        for (int[] dir: dirs){
            int r = i + dir[0];
            int c = j + dir[1];
            if (r <= m-1 && r >=0 && c <= n-1 && c >=0 && board[r][c] == 'M')
                result++;
        }
        return result;
    }
}
