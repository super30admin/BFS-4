//Time Complexity- O(n*m)
//Space Complexity- O(n*m)

class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        
        if(board.size()==0 || board[0].size()==0){
            return 0;
        }
        
        int n=board.size();
        vector<int> arr(n*n,0);
        int row=n-1;
        int col=0;
        int idx=0;
        int even=0;
        
        while(idx<n*n){
            if(board[row][col]>=1){
                arr[idx]=board[row][col]-1;
            }
            else{
                arr[idx]=board[row][col];
            }
            idx++;
            if(even%2==0){
                col++;
                if(col==n){
                    row--;
                    col--;
                    even++;
                }
            }
            else{
                col--;
                if(col==-1){
                    row--;
                    col++;
                    even--;
                }
            }
        }
        
        queue<int> q;
        q.push(0);
        arr[0]=-2;
        int count=0;
        
        while(!q.empty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int curr=q.front();
                q.pop();
                if(curr==n*n-1){
                    return count;
                }
                for(int j=1;j<=6;j++){
                    int ncurr=curr+j;
                    if(ncurr>n*n-1) break;
                    if(arr[ncurr]!=-2){
                        if(arr[ncurr]==-1){
                            q.push(ncurr);
                        }
                        else{
                            q.push(arr[ncurr]);
                        }
                        arr[ncurr]=-2;
                    }
                }
            }
            count++;
        }
        return -1;
    }
};