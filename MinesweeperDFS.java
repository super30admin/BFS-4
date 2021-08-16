//Time Complexity - O(m*n)
//Space Complexity - O(m*n)
//DFS solution ( connected components)

class Solution {
    int[][] dirs = new int[][] {{0,-1},{0,1},{-1,0},{1,0},{1,1},{-1,-1},{-1,1},
                                {1,-1}};
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
      //edge case
      if(board == null || board.length == 0) return board;
      if(board[click[0]][click[1]] == 'M') {
         //if click cell contains mine return baord 
         board[click[0]][click[1]] = 'X';
         return board;
      }
      m = board.length; 
      n = board[0].length;
      dfs(board,click[0],click[1]);
      return board;
 }
   public void dfs(char[][] board, int i, int j) {
    //base case
    if(i < 0 || i == m || j < 0 || j == n || board[i][j] !='E') {
            return;
    }
    
    //logic case
    board[i][j] = 'B';
    int mines = getMines(board,i,j);
    if(mines != 0) {
      board[i][j] = (char)(mines + '0');
    } else {
       for(int[] dir : dirs) {
         int nr = i + dir[0];
         int nc = j + dir[1];
         dfs(board,nr,nc);
       }
     }
   
  }
  
  public int getMines(char[][] board, int i, int j) {
     //checking mines exist in neighbor cell
     int count = 0;
     for(int[] dir : dirs) {
        int nr = i + dir[0];
        int nc = j + dir[1];
        if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
          count ++;
        }
     }
     return count;
  }
}