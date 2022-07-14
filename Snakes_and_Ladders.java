import java.util.LinkedList;
import java.util.Queue;

//Time Complexity : O(n^2)
//Space Complexity : O(n^2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        //null
        if(board.length == 0 || board == null) return 0;
        int n = board.length;
        int [] arr = new int[n*n];
        int even = 0;
        int i = n-1; int j = 0;
        int idx = 0; // index of arr
        while(idx < arr.length){
            if(board[i][j] == -1){
                arr[idx] = board[i][j];
            }
            else{
                arr[idx] = board[i][j] -1;
            }
            
            idx++;
            //if even row move from left to right else move form right to left
            if(even % 2 == 0){
                j++;
                //out of bounds move to upper row and increase by 1
                if(j == n){
                    i--; j--;
                    even++;
                }
            }
            //odd row move form right to left
            else{
                j--;
                //out of bounds move to upper row and increase by 1
                if(j < 0){
                    i--;
                    j++;
                    even++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        //mark visited
        arr[0] = -2;
        int min = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                int curr = q.poll();
                // reached end return min
                if(curr == n* n -1) return min;
                //iterate for 6 
                for(int m = 1; m < 7; m++){
                    int newidx = curr + m;
                    //check if not already visited put inside q
                    if( newidx < n*n && arr[newidx] != -2){
                        if(arr[newidx] > -1){
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