// Time Complexity : O(mn)
// Space Complexity : O(mn)

class Solution {
  public char[][] updateBoard(char[][] board, int[] click) {
    int m = board.length;
    int n = board[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int[][] dirs = {{0,1}, {1,0}, {1,1}, {0,-1}, {-1,0}, {-1,-1}, {1,-1}, {-1,1}};
    queue.add(click);

    while(!queue.isEmpty()) {
      int[] current = queue.poll();
      int row = current[0];
      int col = current[1];

      if(board[row][col] == 'M') {
        board[row][col] = 'X';
        return board;
      }

      int count = 0;
      for(int[] dir: dirs) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        if(newRow < m && newRow >= 0 && newCol < n && newCol >= 0 && board[newRow][newCol] == 'M') {
          count++;
        }
      }

      if(count > 0) board[row][col] = (char) ('0'+ count);
      else {
        board[row][col] = 'B';
        for (int[] dir : dirs) {
          int newRow = row + dir[0], newCol = col + dir[1];
          if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && board[newRow][newCol] == 'E') {
            board[newRow][newCol] = 'B';
            queue.add(new int[]{newRow, newCol});
          }
        }
      }
    }
    return board;
  }
}