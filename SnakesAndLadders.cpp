// Time Complexity -> O(n*n)
// Space Complexity -> O(n*n)
class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        vector<int> vec(n*n);
        int idx = 0;
        int even = 0;
        int r = n - 1;
        int c = 0;
        while(idx < n*n){
            if(board[r][c] >= 1){
                vec[idx] = board[r][c] - 1;    
            }else
                vec[idx] = board[r][c];
            idx++;
            if(even%2 == 0){
                c++;
                if(c == n){
                    r--; c--;
                    even++;
                }
            }else{
                c--;
                if(c == -1){
                    r--; c++;
                    even++;
                }
            }
        }
        
        queue<int> q;
        q.push(0); vec[0] = -2;
        
        int count = 0;
        while(!q.empty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.front(); q.pop();
                for(int j = 1; j < 7; j++){
                    int child = curr + j;
                    if(vec[child] != -2){
                        if(vec[child] == -1)
                            {
                            if(child == n*n -1)
                                return count + 1;
                            q.push(child);
                            }
                        else
                            {
                            if(vec[child] == n*n -1)
                                return count + 1;
                            q.push(vec[child]);}
                        vec[child] = -2;
                    }
                }
            }
            count++;
        }
        return -1;
    }
};