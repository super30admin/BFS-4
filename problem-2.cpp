class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int minMoves = 0;
        int n = board.size();
        vector<int> result(n*n);
        bool direction = true;
        int i = n - 1, k = 0;
        while(i >= 0){
            if(direction){
                for(int j = 0; j < n; j++){
                    if(board[i][j] != -1){
                        result[k] = board[i][j] - 1;
                    }
                    else{
                        result[k] = -1;
                    }
                    k++;
                }
                direction = false;
            }
            else{
                for(int j = n-1; j >= 0; j--){
                    if(board[i][j] != -1){
                        result[k] = board[i][j] - 1;
                    }
                    else{
                        result[k] = -1;
                    }
                    k++;
                }
                direction = true;
            }
            i--;
        }
        for(int i = 0; i < n*n; i++){
            cout<<result[i]<<" ";
        }
        queue<int> q;
        q.push(0);
        result[0] = -2;
        while(!q.empty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int temp = q.front();
                q.pop();
                for(int j = 1; j <= 6 && ((temp + j) < (n*n)); j++){

                    if(temp + j == (n*n - 1) || result[temp + j] == n*n - 1) return minMoves + 1;
                    if(result[temp + j] != -2){
                        if(result[temp + j] != -1){
                            q.push(result[temp + j]);
                        }else{
                            q.push(temp + j);
                        }
                        result[temp + j] = -2;
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
};
