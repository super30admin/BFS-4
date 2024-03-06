class Solution {
    //TC O(m*n)
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        int m = board.size();
        int n = board[0].size();
        vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

       queue<vector<int>> q;
        q.push(click);
        board[click[0]][click[1]] = 'B';

        while (!q.empty()) {
            std::vector<int> curr = q.front();
            q.pop();

            int count = countMines(board, curr[0], curr[1], dirs);

            if (count != 0) {
                board[curr[0]][curr[1]] = static_cast<char>(count + '0');
            } else {
                // Process the neighbors
                for (const auto& dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
                        q.push({nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
        }

        return board;
    }

private:
    int countMines(const vector<vector<char>>& board, int r, int c, const vector<vector<int>>& dirs) {
        int count = 0;

        for (const auto& dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < board.size() && nc >= 0 && nc < board[0].size() && board[nr][nc] == 'M') {
                count++;
            }
        }

        return count;
    }
};