// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

// Approach BFS 

class Solution {
public:
    vector<vector<int>>dirs;
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        int m = board.size();
        int n = board[0].size();
        
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        dirs = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{-1,1},{1,1}};// L R U D UL UR DL DR
        
        queue<pair<int,int>>q;
        q.push({click[0],click[1]});
        board[click[0]][click[1]]='B';
        
        while(!q.empty())
        {
            auto [r,c] = q.front();q.pop();
            int count = findCount(r,c,m,n,board);
            if(count == 0)
            {
                 for(auto& dir: dirs)
                {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='E')
                    {
                        q.push({nr,nc});
                        board[nr][nc] = 'B';
                    }
                 }
            }
            else
            {
                board[r][c] = (char)(count+'0');
            }
        }
        return board;
        
    }
    int findCount(int r,int c,int m,int n, vector<vector<char>>& board)
    {
        int a = 0;
        for(auto& dir: dirs)
        {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='M')
            {
                a++;
            }
        }
        return a;
    }
};


// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

// Approach DFS 

class Solution {
public:
    vector<vector<int>>dirs;
    int m = 0;
    int n = 0;
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        m = board.size();
        n = board[0].size();
        
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        dirs = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{1,-1},{-1,1},{1,1}};// L R U D UL UR DL DR
        
        board[click[0]][click[1]] = 'B';
        dfs(board,click[0],click[1]);
        return board;
        
    }
    void dfs(vector<vector<char>>& board, int r, int c)
    {
        int count  = findCount(r,c,board);
        if(count == 0)
        {
            for(auto& dir: dirs)
            {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='E')
                {
                    board[nr][nc] = 'B';
                    dfs(board,nr,nc);
                }
            }           
        }
        else 
        {
            board[r][c] = (char)(count+'0');
        }      
    }
    int findCount(int r,int c,vector<vector<char>>& board)
    {
        int a = 0;
        for(auto& dir: dirs)
        {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='M')
            {
                a++;
            }
        }
        return a;
    }
};