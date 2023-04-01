529. Minesweeper


TC O(MN)
SC O(M+N)

// DFS APPROACH (very simple and straightforward) : --------
class Solution {
public:
    
    bool inboard(const vector<vector<char>>& board, int x, int y){
        return ( x>=0 && x<board.size() && y>=0 && y<board[0].size() );
    }
    
    void dfs(vector<vector<char>>& board, int x, int y)
    {
        // boundary condition..
        if(!inboard(board,x,y)) return;
        if(board[x][y] == 'B') return;
        
        // if it is 'E' then go forward recursively 
        // check 8 adjacent squares & count all mines adjacent to board[x][y], and place count to the current 
        // position of board[x][y]
        int count = 0;
        if(inboard(board,x-1,y-1) && board[x-1][y-1] == 'M') count++;
        if(inboard(board,x-1,y  ) && board[x-1][y  ] == 'M') count++;
        if(inboard(board,x-1,y+1) && board[x-1][y+1] == 'M') count++;
        if(inboard(board,x  ,y-1) && board[x  ][y-1] == 'M') count++;
        if(inboard(board,x  ,y+1) && board[x  ][y+1] == 'M') count++;
        if(inboard(board,x+1,y-1) && board[x+1][y-1] == 'M') count++;
        if(inboard(board,x+1,y  ) && board[x+1][y  ] == 'M') count++;
        if(inboard(board,x+1,y+1) && board[x+1][y+1] == 'M') count++;
        
        // set board with different values either 'count' or 'B' 
        if(count>0)
            board[x][y] = '0'+count;
        else
        {
            board[x][y] = 'B';
            // search recursively in 8 directions
            dfs(board,x-1,y-1);
            dfs(board,x-1,y  );
            dfs(board,x-1,y+1);
            dfs(board,x  ,y-1);
            dfs(board,x  ,y+1);
            dfs(board,x+1,y-1);
            dfs(board,x+1,y  );
            dfs(board,x+1,y+1);
        }
    }
    
    // Return the board after revealing this position according to the following rules
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        
        // m X n character matrix
        // first click start from ----> (r, c)
        int r = click[0];
        int c = click[1];
        if(board[r][c] == 'M'){
            board[r][c]= 'X';
            return board;
        }
        
        dfs(board, r, c);
        return board;
    }
};


class Solution {
public:
    
    //   Trick to write 8 - Directions fastly:                     ___ ___ ___
    //   -1 ----> Previous row cells => {-1,-1},{-1,0},{-1,1}     |_*_|_*_|_*_| 
    //    0 ----> Current row cells  => {0,-1},{0,1}              |_*_|_X_|_*_|
    //    1 ----> Next row cells     => {1,-1},{1,0},{1,1}        |_*_|_*_|_*_|
   
    vector<vector<int>>dirs = {{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
    
    bool inboard(const vector<vector<char>>& board, int x, int y){
        return ( x>=0 && x<board.size() && y>=0 && y<board[0].size() );
    }
    
    void dfs(vector<vector<char>>& board, int x, int y)
    {
        // boundary condition..
        if(!inboard(board,x,y)) return;
        if(board[x][y] == 'B') return;
        
        // if it is 'E' then go forward recursively 
        // check 8 adjacent squares & count all mines adjacent to board[x][y], and place count to the current 
        // position of board[x][y]
        int count = 0;
        for(auto d : dirs)
            if(inboard(board, x+d[0], y+d[1]) && board[x + d[0]][y + d[1]] == 'M') 
                count++;
        
        
        // set board with different values either 'count' or 'B' 
        if(count>0)
            board[x][y] = '0'+count;
        else
        {
            board[x][y] = 'B';
            // search recursively in 8 directions
            for(auto d : dirs)
                dfs(board, x + d[0], y + d[1]);
            
        }
    }
    
    // Return the board after revealing this position according to the following rules
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        
        // m X n character matrix
        // first click start from ----> (r, c)
        int r = click[0];
        int c = click[1];
        if(board[r][c] == 'M'){
            board[r][c]= 'X';
            return board;
        }
        
        dfs(board, r, c);
        return board;
    }
};


// BFS APPROACH : --------
class Solution {
public:
    
    bool inboard(const vector<vector<char>>& board, int x, int y){
        return (x>=0 and x<board.size() and y>=0 and y<board[0].size());
    }
    
    void bfs(vector<vector<char>>& board, int sr, int sc)
    {
        // 8 directions
       //   Trick to write 8 - Directions fastly:                  ___ ___ ___
    //   -1 ----> Previous row cells => {-1,-1},{-1,0},{-1,1}     |_*_|_*_|_*_| 
    //    0 ----> Current row cells  => {0,-1},{0,1}              |_*_|_X_|_*_|
    //    1 ----> Next row cells     => {1,-1},{1,0},{1,1}        |_*_|_*_|_*_|
        vector<vector<int>>dirs = {{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
        
        queue<pair<int, int>>q;
        q.push({sr, sc});
            
        while(!q.empty())
        {
            auto cur = q.front(); q.pop();   
            int x = cur.first;
            int y = cur.second;
            
            // Search 8 directions for mines
            // Rule 2 : if there is atleast one mine present then place their count. (i.e board[x][y] = count)
            // Rule 3 : if there is no mine in all 8 directions then mark current as Blank 'B', and then push 
            // their 8 adjacent coordinates into the queue.
            int count = 0;
            for(auto d : dirs)
            {
                int nx = x + d[0];
                int ny = y + d[1];        
                if(inboard(board, nx, ny) and board[nx][ny] == 'M')
                    count++;
            }
            
            // Rule 2 : implementation - atleast one mine present
            if(count > 0){
                board[x][y] = count + '0';
            }
            else // Rule 3 : implementation - No mine present
            { 
                board[x][y] = 'B'; // mark current as blank
                for(auto d : dirs){
                    
                    int nx = x + d[0];
                    int ny = y + d[1];
                    // push only empty squares, traversal only possible through empty squares
                    if(inboard(board, nx, ny) and board[nx][ny] == 'E') {
                        q.push({nx, ny});
                        board[nx][ny] = 'B';
                    }
                        
                }
            }
        }
    }
    
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        
        // m X n character matrix
        // first click start from ----> (r, c)
        int r = click[0];
        int c = click[1];
        // Rule 1: If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
        if(board[r][c] == 'M'){
            board[r][c]= 'X';
            return board;
        }
        
        bfs(board, r, c);
        return board;
    }
};

