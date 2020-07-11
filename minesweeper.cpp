// BFS
// Time Complexity : O(mn); visit each of them multiple times but not like n^2
// Space Complexity : O(mn); in the worst case, consider no mines and clicked on center, so almost all cells are added
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. If clicked cell is a mine, make it 'X' and return
// 2. Add clicked cell to queue, count number of neighboring mines and update cell value
// 3. If no mines, then make it 'B' and add its neighbors to queue  

// char to int is using c+'0'; int to char is c-'0'

class Solution {
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        if(board.size()==0)
            return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X'; 
            return board;
        }
        int m = board.size(), n = board[0].size();
        int mines = 0;
        queue<vector<int>> q;
        q.push(click);
        vector<vector<int>> dirs = {{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,1},{1,-1},{-1,-1}};
        while(!q.empty()){
            auto curr = q.front(); q.pop();
            mines = 0;
            for(auto dir:dirs){
                int r = curr[0]+dir[0];
                int c = curr[1]+dir[1];
                if(r>=0 && r<m && c>=0 && c<n && board[r][c]=='M')
                    mines++;
            }
            // mines found
            if(mines>0)
                board[curr[0]][curr[1]] = mines + '0';
            // its a blank cell, add unrevealed neighbors to queue
            else{
                board[curr[0]][curr[1]] = 'B';                   
                for(auto dir:dirs){
                    int r = curr[0]+dir[0];
                    int c = curr[1]+dir[1];
                    if(r>=0 && r<m && c>=0 && c<n && board[r][c]=='E'){
                        q.push({r,c});
                        board[r][c] = 'B'; // make it B, to avoid adding it multiple times; it'll be checked while popping if its really blank
                    }
                } 
            }
        }
        return board;
    }
};

// DFS
// Time Complexity : O(mn); visit each of them multiple times but not like n^2
// Space Complexity : O(mn); in the worst case, consider no mines, so all cells added to stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. Call dfs on clicked cell
// 2. If there is a neighboring mine, update value; If blank cell then call dfs on all neighbors
// 3. Base case is when it is out of bounds or when it is not 'E'

class Solution {
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        if(board.size()==0)
            return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X'; 
            return board;
        }
        int m = board.size(), n = board[0].size();
        int mines = 0;
        dfs(board, click[0], click[1]);
        return board;
    }
    
    void dfs(vector<vector<char>>& board, int i, int j){
        int m = board.size(), n = board[0].size();
        // base
        if(i<0 || i>=m || j<0 || j>=n || board[i][j]!='E')
            return;
        
        // logic
        vector<vector<int>> dirs = {{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,1},{1,-1},{-1,-1}};
        // calc no. of mines
        int mines = 0;
        for(auto dir:dirs){
            int r = i+dir[0];
            int c = j+dir[1];
            if(r>=0 && r<m && c>=0 && c<n && board[r][c]=='M')
                mines++;
        }
        if(mines>0)
            board[i][j] = mines+'0';
        else{ // call dfs on neighbors only on blank cells
            board[i][j] = 'B';
            for(auto  dir: dirs){
                int r = i+dir[0];
                int c = j+dir[1];
                dfs(board,r,c);
            }
        }
    }
};

