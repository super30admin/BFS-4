//Time - O(NxN)
//Space - O(NxN)
class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        vector<int> flat (n*n,-1);
        int i = n-1,j = 0, dir = 1,idx = 0;
        
        while(i>=0){
            if(board[i][j] != -1){
                flat[idx] = board[i][j]-1;
            }
            idx++;
            j = j+dir;
            if(j>=n){
                dir = -1;
                j = n-1;
                i--;
            }else if(j<0){
                dir = 1;
                j = 0;
                i--;
            }
        }
        
 
        int moves = 0;
        queue<int> q;
        q.push(0);
        flat[0] = -2;
        
        while(!q.empty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int idx = q.front();q.pop();
                if(idx == n*n-1) return moves;
                for(int j=1;j<=6;j++){
                    if(idx+j<n*n){
                        if(flat[idx+j] == -1){
                            q.push(idx+j);
                        }else if(flat[idx+j] != -2){
                            q.push(flat[idx+j]);
                        }
                        flat[idx+j] = -2;
                    }
                } 
            }
            moves++;
        }
        
        return -1;
        
    }
};