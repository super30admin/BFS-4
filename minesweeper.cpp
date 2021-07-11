// Time Complexity :O(MN) where MN is the size of the board
// Space Complexity : O(MN)   
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    int dirs[8][2] = {{0,1},{1,0},{1,1},{-1,-1},{0,-1},{-1,0},{-1,1},{1,-1}};
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        queue<pair<int,int>> q;
        q.push({click[0],click[1]});
        board[click[0]][click[1]] = 'B';
        
        while(!q.empty()){
            pair<int,int> curr = q.front();
            q.pop();
            int mines = getMines(board,curr.first,curr.second);
            if(mines == 0){
                for(auto dir : dirs){
                    int r = curr.first + dir[0];
                    int c = curr.second + dir[1];
                    if(r >=0 && c >=0 && r < board.size() && c < board[0].size() && board[r][c] == 'E'){
                        q.push({r,c});
                        board[r][c] = 'B';
                    }
                }
            }
            else{
                board[curr.first][curr.second] = mines + '0';
            }
        }
        return board;
    }
    int getMines(vector<vector<char>>& board, int row, int col){
        int mines = 0;
        for(auto dir : dirs){
            int r = row + dir[0];
            int c = col + dir[1];
            if(r >=0 && c >=0 && r < board.size() && c < board[0].size() && board[r][c] == 'M'){
                mines++;
            }
        }
        return mines;
    }
};