// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Missed marking some nodes as visited while adding to queue

// Your code here along with comments explaining your approach
// Flatten matrix to 1D array
// Do BFS on flattened array, mark visited nodes as -2, keep track of size of queue to count no of moves

class Solution {
    public int snakesAndLadders(int[][] board) {
        boolean flag = true; int n = board.length;
        int[] arr = new int[n*n];
        Queue<Integer> q = new LinkedList<>();
        int i=n-1, j=0, idx=0;
        while(idx<n*n){
            if(board[i][j]==-1) arr[idx]=board[i][j];
            else arr[idx] = board[i][j]-1;
            if(flag){
                if(j==n-1){i--; flag=false;}
                else j++;
            }
            else{
                if(j==0){i--; flag=true;}
                else j--;
            }
            idx++;
        }
        q.add(0); arr[0]=-2; int moves=0;
        while(!q.isEmpty()){
            int size = q.size(); moves++;
            for(int k=0;k<size;k++){
                int curr = q.poll();
                for(int l=1;l<=6;l++){
                    int ahead = curr+l;
                    if(ahead>=n*n) continue;
                    if(ahead==n*n-1 || arr[ahead]==n*n-1) return moves;
                    if(arr[ahead]==-1){ q.add(ahead); arr[ahead]=-2;}
                    else {if(arr[ahead]!=-2){q.add(arr[ahead]);arr[ahead]=-2;}}
            }
        }
        }
         return -1;
    }
}