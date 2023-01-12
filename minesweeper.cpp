// Time Complexity : O(m*n) 
// Space Complexity : O(m*n) 
// Did this code successfully run on Leetcode : Yes 

/*
BFS
1. if the click is on a bomb, return 
2. If its on a black - ignore 
3. if its on an E
    a. get the total mines around it and don't explore further 
    b. If no mines, change to B and explore all 8 directions 

*/

class Solution {
public:
    vector<vector<int>> dirs;
    int m,n;
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        if(board.size() == 0) return board;
        m = board.size();
        n = board[0].size();
        dirs = {{-1,0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        if(board[click[0]][click[1]] == 'M'){
                board[click[0]][click[1]] = 'X';
                return board;
            }

        queue<vector<int>> q;
        q.push(click);
        board[click[0]][click[1]] = 'B';

        while(!q.empty()) {
            int row = q.front()[0];
            int col = q.front()[1];
            q.pop();
            int mines = getMines(board, row, col);
            if(mines > 0) 
                board[row][col] = (char)(mines + '0');
            else {
                for(auto dir:dirs){
                    int nr = row + dir[0];
                    int nc = col + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E'){
                        board[nr][nc] = 'B';
                        q.push({nr, nc});
                    }
                }
            }
            
        }
        return board;
    }

    int getMines (vector<vector<char>>& board, int row, int col) {
        int mines = 0;
        for(auto dir:dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1]; 
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                mines++;
            }
        }
        return mines;
    }
};



// DFS

class Solution {
public:
    vector<vector<int>> dirs;
    int m,n;
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        if(board.size() == 0) return board;
        m = board.size();
        n = board[0].size();
        dirs = {{-1,0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        if(board[click[0]][click[1]] == 'M'){
                board[click[0]][click[1]] = 'X';
                return board;
            }
        
        dfs(board, click[0], click[1]);
        return board;
    }

    void dfs(vector<vector<char>>& board, int row, int col) {
        // base 
        if(row < 0 || row >= m || col < 0 || col >= n || board[row][col] == 'B' )
            return;

        // logic 
        board[row][col] = 'B';
        int mines = getMines(board, row, col);
        if(mines > 0) 
            board[row][col] = (char)(mines + '0');
        else {
            for(auto dir:dirs){
                int nr = row + dir[0];
                int nc = col + dir[1];
                dfs(board, nr, nc);
            }
        }


    }

    int getMines (vector<vector<char>>& board, int row, int col) {
        int mines = 0;
        for(auto dir:dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1]; 
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                mines++;
            }
        }
        return mines;
    }
};