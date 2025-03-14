// Time Complexity : O(n*n)
// Space Complexity : O(n*n)

class Solution {
  public int snakesAndLadders(int[][] board) {
    // Edge Cases
    if(board == null || board.length == 0) return -1;
    // Declare and Initialise variables
    Queue<Integer> queue = new LinkedList<>();
    int n = board.length;
    int[] flattened = new int[n*n];
    int index = 0;
    int row = n-1;
    int col = 0;
    int even = 0;
    int result = 0;
    // Flatten the matrix
    while(index < n*n) {
      if(board[row][col] != -1) {
        flattened[index] = board[row][col] - 1;
      } else {
        flattened[index] = board[row][col];
      }
      if(even%2 == 0) {
        col++;
        if(col == n) {
          col = n-1;
          row--;
          even++;
        }
      } else {
        col--;
        if(col == -1) {
          col = 0;
          row--;
          even++;
        }
      }
      index++;
    }
    // Traverse flattened matrix
    queue.add(0);
    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0; i<size; i++) {
        int current = queue.poll();
        if (current == n*n - 1) return result;
        for(int j = 1; j<=6; j++) {
          int newIndex = current + j;
          if(newIndex > n*n - 1) continue;
          if(flattened[newIndex] == -1){
            queue.add(newIndex);
            flattened[newIndex] = -2;
          } else {
            if(flattened[newIndex] != -2) {
              queue.add(flattened[newIndex]);
              flattened[newIndex] = -2;
            }
          }
        }
      }
      result++;
    }
    return -1;
  }
}