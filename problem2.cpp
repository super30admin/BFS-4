// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

// we need to find minimum no. of moves required. so we use a BFS approach
// for traversing through board, we need to properly know the [r,c] of that value;
// and do a standard BFS approach.

class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        unordered_set<int>vis;
        queue<int>q;
        q.push(1);
        vis.insert(1);
        int moves = 0;
        
        while(!q.empty())
        {
            int sz = q.size();
            while(sz--)
            {
                int curr = q.front();
                q.pop();
                if(curr == n*n) return moves;
                for(int i = 1;i<=6;i++)
                {
                    int val = curr + i;
                    if(val>n*n || vis.count(val)>0) continue;
                    auto [r,c] = helper(val,n);
                    if(board[r][c] == -1){
                        q.push(curr+i);
                    }
                    else{
                        q.push(board[r][c]);
                    }
                    vis.insert(val);
                }
            }
            moves++;
        }
        return -1;
        
    }
    pair<int,int> helper(int x,int n)
    {
        int r = (x-1)/n;
        int c = (x-1)%n;
        if(r&1) c = n-1-c;
        r = n-1-r;
        return {r,c};
    }
};