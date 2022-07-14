TC: O(n*n) - n is the no.of rows or columns in the given 2D array
SC : O(n*n) - to flatten the array

Flattened the given 2D array and used BFS to go to all the possible ways at each and evry point. avaoided rechaecking
my making the cell as visisted with -2


class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int [] arr = new int [n*n];
        int i=n-1,j=0,even=0,idx=0;
        while(i>=0 && j>=0 && i<n && j<n){ //flattens the 2D array
            if(even%2 == 0)//rightwards
            {
                if(board[i][j] < 0){
                    arr[idx] = board[i][j];
                }
                else{
                     arr[idx] = board[i][j] - 1;
                }
               
                idx++;
                j++;
                if(j == n){
                    i--;
                    j--;
                    even ++;
                }
            }
            else{//leftwards
                if(board[i][j] < 0){
                    arr[idx] = board[i][j];
                }
                else{
                     arr[idx] = board[i][j] - 1;
                }
                idx++;
                j--;
                if(j < 0){
                    i--;
                    j++;
                    even++;
                }
                
            }
        }
        //System.out.println(Arrays.toString(arr));
        //calc the min jumps using BFS
        Queue<Integer>q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int min = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0;k<size;k++){
                int cur = q.poll();
                if(cur == n*n -1) return min;
                for(int m=1;m<7;m++){
                    int newidx = cur + m;
                    if(newidx < n*n && arr[newidx]!=-2){
                        if(arr[newidx] >= 0){
                            q.add(arr[newidx]);
                        }
                        else{
                            q.add(newidx);
                        }
                        arr[newidx] = -2;
                    }
                    
                }
            }
            min++;
        }
        return -1;
          
    }
}