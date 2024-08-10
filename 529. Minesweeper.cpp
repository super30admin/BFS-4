/*
Time : O(n)
Space: O(1)
*/


class Solution {
public:
    int rows, cols;
    vector<vector<char>> board;
    vector<vector<int>> dir = {{-1,0},{1,0},{0,-1},{0,1},
                        {-1,-1},{-1,1},{1,-1},{1,1}};
    
    int get(int r, int c){
        if(r >= rows || r < 0 || c >= cols || c < 0)  return 0;
        return board[r][c] == 'M';
    }

    int get_all(int r, int c){
        int adj = 0;
        for(auto &d: dir){
            adj += get(r+d[0], c+d[1]);
        }
        return adj;
    }

    void dfs(int r, int c){
        if(r >= rows || r < 0 || c >= cols || c < 0)  return;
        
        //only reveal Empty squares
        if(board[r][c] != 'E')  return;

        int adj_mines = get_all(r,c);
        
        //if adj_mines > 0, change it to that count and dont reveal neighbors
        if(adj_mines > 0){
            board[r][c] = ('0' + adj_mines);
            return;
        }

        //if adjacent mines == 0, change it to 'B' and recursively reveal neighbors
        board[r][c] = 'B';
        for(auto &d: dir){
            dfs(r+d[0], c+d[1]);
        }
    }

    vector<vector<char>> updateBoard(vector<vector<char>>& b, vector<int>& click) {
        board = b;
        rows = board.size(), cols = board[0].size();
        int r = click[0], c = click[1];   

        //edge case
        if(board[r][c] == 'M'){
            board[r][c] = 'X';
            return board;
        }

        dfs(r, c);
        return board;
    }
};
