/**
 * Leetcode Execution: TLE
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * Approach: BFS
 */



class Solution {
  int[][] dirs = {{-1,0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
  int row;
  int col;
  
  public char[][] updateBoard(char[][] board, int[] click) {
              
      if (board[click[0]][click[1]] == 'M') {
          board[click[0]][click[1]] = 'X';
          return board;
      } 
      
      this.row = board.length;
      this.col = board[0].length;
      
      Queue<Integer[]> q = new LinkedList<>();
      
      q.add(new Integer[]{click[0], click[1]});
      
      helper(board, q);
      return board;
          
  }
  
  private void helper(char[][] board, Queue<Integer[]> q) {
      
      while (q.size() > 0) {
          
          Integer[] point = q.remove();
          
          int count = 0;
          for (int[] dir : dirs) {
              int r = point[0] + dir[0];
              int c = point[1] + dir[1];
              
              if (r>=0 && r<row && c>= 0 && c< col && board[r][c] == 'M') count++;
          }
          
          // Advance if count = 0, need to explore more cells
          if (count > 0) {
              board[point[0]][point[1]] = (char)(count + '0');
          } else {
              board[point[0]][point[1]] = 'B';
          
              for (int[] dir : dirs) {
                  int r = point[0] + dir[0];
                  int c = point[1] + dir[1];

                  if (r>=0 && r<row && c>= 0 && c< col && board[r][c] == 'E') q.add(new Integer[]{r,c});
              }
          }

      }
  }
}