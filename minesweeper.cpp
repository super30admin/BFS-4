/*Intuition: We mark visited nodes as 'B'. If mines > 0, we dont push its neighbours. Else we push its neighbours
Time: O(N^2)
Space: O(N^2)
*/

//BFS

class Solution {
public:
    vector<vector<int>> dirs;
    int rows, cols;
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        rows = board.size();
        cols = board[0].size();
        dirs = {{1,0}, {0,1}, {-1,0}, { 0,-1}, { 1,1}, {-1,1}, {1,-1}, {-1,-1}};
        
        if ( board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        queue<pair<int,int>>queue;
        queue.push({click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        while ( queue.size() != 0){
        
            auto currIJ = queue.front();
            queue.pop();
            
            int mines = getMines(board, currIJ.first, currIJ.second);
            
            if ( mines == 0){   
                for ( auto dir: dirs){
                    int newI = dir[0] + currIJ.first;
                    int newJ = dir[1] + currIJ.second;    
                    if ( newI >= 0 and newI < rows and newJ >=0 and newJ < cols and board[newI][newJ] == 'E'){ 
                        board[newI][newJ] = 'B';
                        queue.push({newI,newJ});           
                    }            
                }     
            }
            else{              
                board[currIJ.first][currIJ.second] = (char)('0' + mines);
            }   
        }
        return board;  
    }
    
    int getMines(vector<vector<char>>& board, int i, int j){
        int count = 0;
        for ( auto dir: dirs){
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if ( newI >= 0 and newI < rows and newJ >=0 and newJ < cols and board[newI][newJ] == 'M'){ 
                count++;
            }
        }
        return count;
    
    }
};

//DFS
class Solution {
public:
    vector<vector<int>> dirs;
    int rows, cols;
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        rows = board.size();
        cols = board[0].size();
        dirs = {{1,0}, {0,1}, {-1,0}, { 0,-1}, { 1,1}, {-1,1}, {1,-1}, {-1,-1}};
        
        if ( board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
                
        dfs(board, click[0], click[1]);
        
        return board;  
    }
    
    void dfs(vector<vector<char>>&board, int i, int j){
        if ( i < 0 or i == rows or j < 0 or j == cols or board[i][j] != 'E'){ 
            return;
        }
        
        board[i][j] = 'B';
        int mines = getMines(board, i, j);
        if ( mines == 0){
            for ( auto dir: dirs){
                int newI = i + dir[0];
                int newJ = j + dir[1];
                dfs(board, newI, newJ);
            }
        
        }
        else{
            board[i][j] = (char)('0' + mines);
        
        }

    }
    
    int getMines(vector<vector<char>>& board, int i, int j){
        int count = 0;
        for ( auto dir: dirs){
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if ( newI >= 0 and newI < rows and newJ >=0 and newJ < cols and board[newI][newJ] == 'M'){ 
                count++;
            }
        }
        return count;
    
    }
};