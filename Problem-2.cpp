// https://leetcode.com/problems/snakes-and-ladders/description/

// Time Complexity: O(n*n)
// Space Complexity: O(n*n)

class Solution {
    public:
        int snakesAndLadders(vector<vector<int>>& board) {
            int n = board.size();
            vector<int> ans(n*n, 0);
            bool flag = true;
            int idx=0;
            int r=n-1; int c=0;
            while(idx < n*n){
                if(board[r][c] == -1){
                    ans[idx] = -1;
                }
                else{
                    ans[idx] = board[r][c]-1;
                }
                idx++;
                if(flag){
                    c++;
                    if(c == n){
                        r--;c--;flag = false;
                    }
                }
                else{
                    c--;
                    if(c == -1){
                        r--;c++;flag = true;;
                    }
                }   
            }
            queue<int> q;
            int moves=0;
            q.push(0);
            ans[0] = -2;
            while(!q.empty()){
                int s = q.size();
                for(int i=0; i<s; i++){
                    int curr = q.front(); q.pop();
                    if(curr == n*n-1) return moves;
                    for(int j=1; j<=6; j++){
                        int next = curr + j;
                        if(next > n*n-1) break;
                        if (ans[next] != -2) { 
                            if (ans[next] == -1) {
                                q.push(next);
                            } else {
                                q.push(ans[next]);
                            }
                            ans[next] = -2; 
                        }
                    }
                }
                moves++;
            }
            return -1;
        }
        
    };