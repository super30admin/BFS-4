//Time Complexity - O(m*n)
//Space Complexity - O(m*n)
//BFS solution ( connected components)
/** Start with click position and initially mark the click position 'B'
 ** If  cell does not have adjacent mine then add all the neighbors to queue and mark
 ** it as 'B' and continue search level by level.
 ** If adjacent cell contain mine mark the cell as '1' and do not add its neighbors    to queue
 **/

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
      //bfs
      Queue<Integer> r = new LinkedList<>();
      Queue<Integer> c = new LinkedList<>();
      
      //update the click position
      r.add(click[0]);
      c.add(click[1]);
      board[click[0]][click[1]] = 'B';
      
      while(!r.isEmpty()) {
        int i = r.poll();
        int j = c.poll();
        int mines = getMines(board,i,j);
        if(mines != 0) {
          board[i][j] = (char)(mines+'0');
        } else {
          //traverse neighbors
          for(int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E'){
              //if its unrevelaed empty square then mark revealed blank square
              board[nr][nc] = 'B';
              r.add(nr);
              c.add(nc);
            }
          }
        }
      }
      return board;
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