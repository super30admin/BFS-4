// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// This is a BFS problem, we need to update the board based on the click
// If the click is on a mine, update it to X and return
// Otherwise, update the board based on the number of mines around the clicked
// cell If the number of mines is 0, update it to B and add all the neighbors to
// the queue If the number of mines is greater than 0, update it to the number
// of mines and return

class Solution {
private:
  char countMines(int r, int c, int m, int n, vector<vector<char>> &board) {
    int ans = 0;
    for (auto &dir : dirs) {
      int nr = r + dir[0];
      int nc = c + dir[1];
      if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
        ans++;
      }
    }
    return '0' + ans;
  }

public:
  vector<vector<int>> dirs = {{0, 1},  {1, 0},   {-1, 0}, {0, -1},
                              {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
  vector<vector<char>> updateBoard(vector<vector<char>> &board,
                                   vector<int> &click) {
    int m = board.size();
    int n = board[0].size();
    if (board[click[0]][click[1]] == 'M') {
      board[click[0]][click[1]] = 'X';
      return board;
    }
    queue<vector<int>> q;
    q.push(click);
    char mines = countMines(click[0], click[1], m, n, board);
    if (mines != '0') {
      board[click[0]][click[1]] = mines;
      return board;
    } else {
      board[click[0]][click[1]] = 'B';
    }
    while (!q.empty()) {
      vector<int> top = q.front();
      int r = top[0];
      int c = top[1];
      q.pop();
      char numMines = countMines(r, c, m, n, board);
      if (numMines != '0') {
        board[r][c] = numMines;
        continue;
      }
      for (auto &dir : dirs) {
        int nr = r + dir[0];
        int nc = c + dir[1];
        if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
          board[nr][nc] = 'B';
          q.push({nr, nc});
        }
      }
    }

    return board;
  }
};