// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Forming the 1D array from board


// Your code here along with comments explaining your approach
class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int idx = 0;
        int r = n-1;
        int c = 0;
        int even =0;
        
        while(idx < n*n){
            if(board[r][c]==-1){
                arr[idx] = -1;
            }
            else{
                arr[idx] = board[r][c] - 1;
            }

            if(even % 2==0){
                c++;
                if(c == n){
                    r--;
                    c--;
                     even++;
                }
            }
            else{
                c--;
                if(c==-1){
                    r--;
                    c++;
                     even++;
                }
               
            }
            idx++;
        }
        Queue<Integer> q = new LinkedList();
        q.add(0);
        arr[0]=-2;
        int moves = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int curr = q.poll();
                if(curr == n*n -1)
                    return moves;
                for(int k=1;k<7;k++){
                    int baby = curr + k;
                    if(baby >= n*n){
                        break;
                    }
                    if(arr[baby]!=-2){
                        if(arr[baby]==-1){
                            q.add(baby);
                        }
                        else{
                            q.add(arr[baby]);
                        }
                       
                    }
                    arr[baby]=-2;
                }
                 
            }
            moves++;
        }
        return -1;
    }
}