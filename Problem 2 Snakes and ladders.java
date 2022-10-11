//O(NxN) time and space complexity
// Converting Board matrix in 1D array and then BFS

class Solution {
    public int snakesAndLadders(int[][] board) {
      //flatten the matrix
      
        boolean flag = false;
        int n = board.length;
        int i = n-1; //last index in board 
        int j = 0; //first index of board
       // because board[n-1][0] is the starting point of this game
        int[] arr = new int[n*n]; //because we are converting 2D board in 1D board
        int idx = 0; //index for flat array arr
        
        while(idx<arr.length){
            if(board[i][j] == -1){
                arr[idx] = -1;
            }else{
                arr[idx] = board[i][j] - 1;
            }
            idx++;
            if(flag){
             // if flag is true that means I'm moving from right to left(negative direction)
                j--;
                if (j==-1){
                    i--;
                    j++;
                    flag = false;
                }
            }else{
            //  if flag is false that means I'm moving from left to right(positive direction)
                j++;
                if (j==n){
                    i--; //move to row above
                    j--; //reduce j bcs its gone out of bounds
                    flag = true; //now we wil be iterating in negative direction
                }
            }
        }
        int moves = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0); //index of flattened array
        arr[0] = -2; //marking as visited
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0;k<size;k++){
                int curr = q.poll();
                for(int d=1;d<7;d++){
                    int child = curr +d;
                    if (arr[child] != -2){
                        if (arr[child] >= 0) {
                            if (arr[child] == n*n-1)
                                return moves+1;
                        q.add(arr[child]);
                    }else {
                            if (child == n*n-1)
                                return moves+1;
                             q.add(child);
                        }
                        arr[child] = -2; //marking it as visited
                    }
                }
            }
        moves++;
        }
    return -1;
    }
}
