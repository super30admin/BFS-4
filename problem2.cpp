// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// We need to find the shortest path from 1 to n*n
// We can flatten the board to a 1D array and then run BFS
// If cell is -1, its a normal cell
// If cell is > -1, its a snake or ladder (put the destination in the queue)
// We use -2 to mark visited cells

class Solution {
public:
  int snakesAndLadders(vector<vector<int>> &board) {
    int n = board.size();
    vector<int> flattened(n * n);
    bool dir = true; // true = left to right, false = right to left
    int r = n - 1;
    int c = 0;
    for (int i = 0; i < n * n; i++) {
      if (board[r][c] == -1) {
        flattened[i] = -1;
      } else {
        flattened[i] = board[r][c] - 1;
      }
      if (dir) {
        c++;
        if (c == n) {
          r--;
          c--;
          dir = false;
        }
      } else {
        c--;
        if (c == -1) {
          r--;
          c++;
          dir = true;
        }
      }
    }

    queue<int> q;
    flattened[0] = -2;
    q.push(0);
    int moves = 0;
    while (!q.empty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int currIdx = q.front();
        q.pop();
        if (currIdx == n * n - 1)
          return moves;
        for (int j = 1; j <= 6; j++) {
          int newIdx = currIdx + j;
          if (newIdx >= n * n)
            break;
          if (flattened[newIdx] != -2) {
            if (flattened[newIdx] == -1) {
              q.push(newIdx);
            } else {
              q.push(flattened[newIdx]);
            }
            flattened[newIdx] = -2;
          }
        }
      }
      moves++;
    }
    return -1;
  }
};