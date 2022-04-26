// Time Complexity : O(m*n) the dimension of the board
// Space Complexity : O(m*n) numboard
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// We will use queue and get the values at all the directons
// We will store the minecount in numboard
// We will update the values in the board and return it.
class Solution {
    int[][] directions = {
        {0,-1},{0, 1},
        {-1, 0},{1, 0},
        {-1, -1},{-1, 1},
        {1, -1},{1, 1}
    };
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        char[][] numBoard = new char[m][n];
        char[][] res = new char[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int minecount = 0;
                numBoard[i][j] = board[i][j];
                for(int[] dir: directions){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if(x < m && x>=0 && y >=0 && y < n)
                    if(board[x][y] == 'X' || board[x][y] == 'M')
                        minecount++;
                }
                if(minecount != 0){
                    minecount += 48;
                    numBoard[i][j] = (char)minecount;
                }
            }
        }
        int x = click[0];
        int y = click[1];
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }
        if(board[x][y] == 'E' && numBoard[x][y] != 'E'){
            board[x][y] = numBoard[x][y];
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        if(board[x][y] == 'E'){
            q.add(click);
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int i = cur[0];
                int j = cur[1];
                board[i][j] = 'B';
                for(int[] dir: directions){
                    int x1 = i + dir[0];
                    int y1 = j + dir[1];
                    if(x1 < m && x1>=0 && y1 >=0 && y1 < n){
                    if(board[x1][y1] == 'E' && numBoard[x1][y1] != 'E')
                        board[x1][y1] = numBoard[x1][y1];
                    if(board[x1][y1] == 'E' && numBoard[x1][y1] == 'E'){
                        board[x1][y1] = 'B';
                        q.add(new int[]{x1, y1});
                        }
                }
                }
            }
        }
       return board; 
    }
}