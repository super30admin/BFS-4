// https://leetcode.com/problems/minesweeper/description/

// Time Complexity: O(m*n)
// Space Complexity: O(m*n)


// DFS

class Solution {
    public:
        int count(int x, int y, vector<vector<char>>& board, vector<pair<int,int>> &dirs){
            int ans=0;
            for(auto [dx,dy] : dirs){
                int nr = x+dx;
                int nc = y+dy;
                if(nr >= 0 && nr < board.size() && nc >=0 && nc < board[0].size() && board[nr][nc] == 'M'){
                    ans++;
                }
            }
            return ans;
        }
        void dfs(int x, int y, vector<vector<char>>& board,vector<pair<int,int>> &dirs){
            if(x < 0 || x >= board.size() || y < 0 || y >= board[0].size() || board[x][y] != 'E') return;
            int cnt = count(x,y,board,dirs);
            if(cnt == 0){
                board[x][y] = 'B';
                for(auto [dx,dy] : dirs){
                    int nr = x+dx;
                    int nc = y+dy;
                    dfs(nr, nc, board, dirs);
                }
            }
            else{
                board[x][y] = cnt + '0';
            }
        }
        vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
            vector<pair<int,int>> dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
            if(board[click[0]][click[1]] == 'M') {
                board[click[0]][click[1]] = 'X';
                return board;
            }
            int x = click[0]; int y = click[1];
            dfs(x, y, board, dirs);
            return board;
        }
    };

// BFS

class Solution {
    public:
        int count(int x, int y, vector<vector<char>>& board, vector<pair<int,int>> &dirs){
            int ans=0;
            for(auto [dx,dy] : dirs){
                int nr = x+dx;
                int nc = y+dy;
                if(nr >= 0 && nr < board.size() && nc >=0 && nc < board[0].size() && board[nr][nc] == 'M'){
                    ans++;
                }
            }
            return ans;
        }
        vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
            queue<pair<int,int>> q;
            if(board[click[0]][click[1]] == 'M') {
                board[click[0]][click[1]] = 'X';
                return board;
            }
            int x = click[0]; int y = click[1];
            q.push({x,y});
            vector<pair<int,int>> dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
            while(!q.empty()){
                auto [x,y] = q.front(); q.pop();
                if(board[x][y] != 'E') continue;
                int cnt = count(x,y,board,dirs);
                if(cnt == 0){
                    board[x][y] = 'B';
                    for(auto [dx,dy] : dirs){
                        int nr = x+dx;
                        int nc = y+dy;
                        if(nr >= 0 && nr < board.size() && nc >=0 && nc < board[0].size() && board[nr][nc] == 'E'){
                            q.push({nr,nc});
    
                        }
                    }
                }
                else{
                    board[x][y] = cnt + '0';
                }
            }
            return board;
        }
    };