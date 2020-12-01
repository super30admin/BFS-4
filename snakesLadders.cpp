//time complexity:O(n^2)
//space complexity:O(n^2)
//executed on leetcode: yes
//approach:using bfs
//any issues faced? yes, its tricky

class Solution {
public:
    vector<int> calc(int row, int nextstep)
    {
        int x=(nextstep-1)/row;
        int y=(nextstep-1)%row;
        if(x%2==1)
            y=row-1-y;
        x=row-1-x;
        return {x,y};
    }
    int snakesAndLadders(vector<vector<int>>& board) {
        int r=board.size();
        queue<int>q;
        q.push(1);
        int step=0;
        while(!q.empty())
        {
            int n=q.size();
            for(int i=0;i<n;i++)
            {
                int t=q.front();
                q.pop();
                if(t==r*r)
                    return step;
                for(int i=1;i<=6;i++)
                {
                    int nextstep=t+i;
                    if(nextstep>r*r)
                        break;
                    auto v=calc(r,nextstep);
                    int row=v[0];
                    int col=v[1];
                    if(board[row][col]!=-1)
                    {
                        nextstep=board[row][col];
                    }
                    if(board[row][col]!=r*r+1)
                    {
                        q.push(nextstep);
                        board[row][col]=r*r+1;
                    }
                }
            }
            step++;
        }
        return -1;
    }
};