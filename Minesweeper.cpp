// Approach 1 - DFS
// Time Complexity -> O(m*n)
// Space Complexity -> O(m*n) 
// Problems Faced - No!
// It runs on Leetcode!
class Solution {
    vector<vector<int>> dirs;
    private:
    int countMines(vector<vector<char>>& board, int r, int c, int m, int n){
        int count = 0;
        for(vector<int> dir : dirs){
            int nr = dir[0] + r;
            int nc = dir[1] + c;
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M')
                count++;
        }
        return count;
    }
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        int m = board.size();
        int n = board[0].size();
        dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
            
        queue<vector<int>> q;
        q.push(click);
        board[click[0]][click[1]] = 'B';
        
        while(!q.empty()){
            vector<int> curr = q.front(); q.pop();
            int nMines = countMines(board, curr[0], curr[1], m, n);
            if(nMines > 0)
                board[curr[0]][curr[1]] = nMines + '0';
            else{
                for(vector<int> dir : dirs){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E'){
                        
                        q.push({nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
        }
        return board;
    }
};

// Approach 2 - BFS
class Solution {
    vector<vector<int>> dirs;
    private:
    int countMines(vector<vector<char>>& board, int r, int c, int m, int n){
        int count = 0;
        for(vector<int> dir : dirs){
            int nr = dir[0] + r;
            int nc = dir[1] + c;
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M')
                count++;
        }
        return count;
    }
    void dfs(vector<vector<char>>& board, int r, int c, int m, int n){
        // base
        if(r < 0 || r == m || c < 0 || c == n || board[r][c] != 'E')
            return;
        // logic
        board[r][c] = 'B';
        int mines = countMines(board, r, c, m, n);
        if(mines > 0)
            board[r][c] = '0' + mines;
        else{
            for(vector<int> dir : dirs){
                int nr = dir[0] + r;
                int nc = dir[1] + c;
                dfs(board, nr, nc, m, n);
            }
        }
    }
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int m = board.size();
        int n = board[0].size();
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click[0], click[1], m, n);
        return board;
    }
};
