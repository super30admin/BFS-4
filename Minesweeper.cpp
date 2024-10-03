// Time Complexity -> O(m*n)
// Space Complexity -> O(m*n) 
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