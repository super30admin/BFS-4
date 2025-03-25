// TC:O(m*n)
// SC:O(m*n)

class Solution {
    int[][] dirs = {
       {0, 1},   // Right
       {0, -1},  // Left
       {1, 0},   // Down
       {-1, 0},  // Up
       {1, 1},   // Bottom-right
       {1, -1},  // Bottom-left
       {-1, 1},  // Top-right
       {-1, -1}  // Top-left
       };
   public char[][] updateBoard(char[][] board, int[] click) {
       int m = board.length;
       int n= board[0].length;
       if(board[click[0]][click[1]]=='M'){
           board[click[0]][click[1]]='X';
           return board;
       }    
       dfs(board,click);
       return board;
   }

   private void dfs(char[][] board, int[] curr){
       // base
       int m = board.length;
       int n= board[0].length;
       if(curr[0]<0 || curr[0]>=m || curr[1]<0 || curr[1]>=n || board[curr[0]][curr[1]]!='E') return;
       // logic
       board[curr[0]][curr[1]]='B';
       int count = countMines(board,curr,dirs);
       if(count==0){
           for(int[] dir:dirs){
               int nr = curr[0]+dir[0];
               int nc = curr[1]+dir[1];
               dfs(board, new int[]{nr,nc});
           }
       }else{
           board[curr[0]][curr[1]] = (char)(count+'0');
       }
   }

   private int countMines(char[][] board, int[] curr, int[][] dirs){
       int count=0;
       int m = board.length;
       int n= board[0].length;
       for(int []dir:dirs){
           int nr = curr[0]+dir[0];
           int nc = curr[1]+dir[1];
           if(nr>=0 &&nr<m&&nc>=0&&nc<n&&board[nr][nc]=='M') count++;
       }
       return count;
   }
}