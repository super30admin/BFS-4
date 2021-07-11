// Time Complexity :O(m*n) where mn is the board
// Space Complexity : O(m*n) space for flattened board and queue  
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        vector<int> newBoard = getFlatBoard(board);
        int r = board.size();
        queue<int> q;
        q.push(0);
        int moves = 0;
        newBoard[0] = -2;
        while(!q.empty()){
            int size = q.size();
            for(int j = 0;j < size;j++){
                int curr = q.front();
                q.pop();
                if(curr == r*r-1) return moves;
                for(int i =1;i<=6;i++){
                    int child = curr +i;
                    if(child >= r*r) break;
                    if(newBoard[child] != -2){
                        if(newBoard[child] == -1){
                            q.push(child);
                        }
                        else{
                            q.push(newBoard[child]);
                        }
                        newBoard[child] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
    vector<int> getFlatBoard(vector<vector<int>>& board){
        int r = board.size();
        vector<int> ans(r*r,0);
        int i = r-1;
        int j = 0;
        int even =0;
        int idx=0;
        while (idx < r*r){
            if(board[i][j] != -1) board[i][j]--;
            if(even%2 == 0){
                ans[idx] = board[i][j];
                j++;
                if(j == r){
                    j--;
                    i--;
                    even++;
                }
            }
            else {
                ans[idx] = board[i][j];
                j--;
                if(j < 0){
                    j++;
                    i--;
                    even++;
                }
            }
            idx++;
        }
        return ans;
    }
};