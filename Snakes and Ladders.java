class Solution {
  public int snakesAndLadders(int[][] board) {
    int len = board.length;
    int end = len * len;
    int start = 1;
    int minMove = 0;

    Deque<Integer> queue = new LinkedList<>();
    boolean[][] isVisited = new boolean[len][len];
    queue.offer(start);
    isVisited[len - 1][0] = true;

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int curr = queue.poll();
        if (curr == end) {
          return minMove;
        }
        for (int step = 1; step <= 6; step++) {
          if (curr + step > end) {
            break;
          }
          int[] nextStep = location(curr + step, len);
          int nextRow = nextStep[0];
          int nextCol = nextStep[1];
          if (isVisited[nextRow][nextCol]) {
            continue;
          }
          isVisited[nextRow][nextCol] = true;
          if (board[nextRow][nextCol] == -1) {
            queue.offer(curr + step);
          } else {
            queue.offer(board[nextRow][nextCol]);
          }
        }
      }
      minMove++;
    }
    return -1;
  }

  private int[] location(int location, int n) {
    int r = n - (location - 1) / n - 1;
    int c = (location - 1) % n;
    if (r % 2 == n % 2) {
      c = n - c - 1;
    }
    return new int[] { r, c };
  }
}