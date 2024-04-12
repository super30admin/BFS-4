// t-c:
// s-c: 
class Solution {
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int m = board.size(), n = board[0].size();
        vector<vector<int>> dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        queue<int> q;
        q.push(click[0]);
        q.push(click[1]);
        int nr, nc;
        while(!q.empty()){
            int r = q.front();
            q.pop();
            int c = q.front();
            q.pop();
            if(board[r][c] == 'E'){
                for(vector<int>dir: dirs){
                    nr = r + dir[0];
                    nc = c + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M'){
                        if(board[r][c] == 'E'){
                            board[r][c] = '1';
                        }
                        else{
                            board[r][c] = (char)(board[r][c] + 1);
                        }
                    }
                }
                if(board[r][c] == 'E'){
                    board[r][c] = 'B';
                    for(vector<int>dir: dirs){
                        nr = r + dir[0];
                        nc = c + dir[1];
                        if(nr >= 0 && nr < m && nc >= 0 && nc < n){
                            q.push(nr);
                            q.push(nc);
                        }
                    }
                }
            } 
        }
        return board;
    }
};
