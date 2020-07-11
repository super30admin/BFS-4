// BFS
// Time Complexity : O(n^2); visit each of them multiple times but not like n^2
// Space Complexity : Not sure
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. Convert the 2D matrix to a 1D array and do a level-order traversal starting from 1
// 2. Children of a node are the next 6 elements from the array if they are not visited
// 3. If we add the last element to the queue, return the number of moves 

class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        if(board.size() == 0)
            return 0;
        int n = board.size();
        int N = n*n;
        vector<int> arr (N+1,0);
        int count=1, row = -1;
        for(int i=n-1; i>=0; i--){
            row++;
            // odd rows
            if(row%2 == 1){
                for(int j=n-1;j>=0;j--){
                    if(board[i][j] == -1)
                        arr[count] = count;
                    else
                        arr[count] = board[i][j];
                    count++;
                }
            }
            else{ // even rows
                for(int j=0;j<n;j++){
                    if(board[i][j] == -1)
                        arr[count] = count;
                    else
                        arr[count] = board[i][j];
                    count++;
                }                
            }
        }
        for(auto el: arr)
            cout<<el<<" ";

        cout<<"\nstarted bfs\n";
        // start bfs
        queue<int> q;
        q.push(1); 
        arr[1] = -1;
        int moves = 0, size=0;
        while(!q.empty()){
            size = q.size();
            cout<<"q size "<<size<<endl; 
            moves++;
            cout<<"level: "<<moves<<endl;
            for(int k=0;k<size;k++){
                int idx = q.front(); q.pop();
                cout<<"popped: "<<idx<<" and adding ";
                for(int i=1;i<7;i++){
                    if(idx+i <= N+1 && arr[idx+i] != -1){
                        cout<<arr[idx+i]<<" ";
                        if(arr[idx+i] == N)
                            return moves;   
                        q.push(arr[idx+i]);
                        // arr[arr[idx+i]] = -1;
                        arr[idx+i] = -1;
                    }
                }
                cout<<endl;
            }
        }
        return -1;
    }
};