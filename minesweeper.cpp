//Time - O(mxn)
//Space = O(mxn) 

class Solution {
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        int r = board.size();int c = board[0].size();
        if(r == 0) return vector<vector<char>>();
        
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        queue<pair<int,int>> q;
        q.push(make_pair(click[0],click[1]));
        board[click[0]][click[1]]  = 'B';
        vector<vector<int>> dir {{0,1},{1,0},{-1,0},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
        
        while(!q.empty()){
            pair<int,int> p = q.front();q.pop();
            int cnt = countMines(board, p);
            if(cnt == 0){
                for(auto d:dir){
                    int x = p.first + d[0];
                    int y = p.second + d[1];
                    if(x>=0 && x<r && y>=0 && y<c && board[x][y] == 'E'){
                        q.push(make_pair(x,y));
                        board[x][y] = 'B';
                    }
                }
            }else{
                board[p.first][p.second] = '0'+cnt;
            }
        }
        return board;
        
    }
    
    int countMines(vector<vector<char>>& board, pair<int,int> p){
        int r = board.size();int c = board[0].size();
         vector<vector<int>> dir {{0,1},{1,0},{-1,0},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
        int cnt = 0;
        for(auto d:dir){
            int x = p.first + d[0];
            int y = p.second + d[1];
            if(x>=0 && x<r && y>=0 && y<c && board[x][y] == 'M'){
               cnt++;
            }
        }
        return cnt;
    }
};