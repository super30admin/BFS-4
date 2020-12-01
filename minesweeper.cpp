//time complexity:O(n^2)
//space complexity:O(n^2)
//executed on leetcode: yes
//approach:using dfs
//any issues faced? yes, its tricky

class Solution {
public:
    vector<pair<int,int>>dirs={{-1,-1},{-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    int check(vector<vector<char>>& b, int x, int y)
    {
        int mine=0;
        for(pair<int,int>dir:dirs)
        {
            int addx=dir.first;
            int addy=dir.second;
            if(x+addx>=0 && x+addx<b.size() && y+addy>=0 && y+addy<b[0].size())
            {
                if(b[x+addx][y+addy]=='M')
                    mine++;
            }
        }
        return mine;
    }
    void dfs(vector<vector<char>>& b, int x, int y)
    {
        if(x<0 || y<0 || x>=b.size() || y>=b[0].size())
            return;
        if(b[x][y]=='E')
        {
            int neighbor=check(b,x,y);
            if(neighbor==0)
            {
                b[x][y]='B';
                for(pair<int,int>dir:dirs)
                {
                    int addx=dir.first;
                    int addy=dir.second;
                    dfs(b,x+addx,y+addy);
                }
            }
            else
                b[x][y]=neighbor+'0';
        }
    }
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        int x=click[0];
        int y=click[1];
        if(board[x][y]=='M')
            board[x][y]='X';
        else
            dfs(board,x,y);
        return board;
    }
};