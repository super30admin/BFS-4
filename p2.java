// Time Complexity : O(n*n)
// Space Complexity :O(n*n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {
        //flattening the board
        int i=0;
        int n = board.length;
        int j = n-1;
        int k = 0;
        int[] arr = new int[n*n];
        boolean flag = true;
        while(i<n*n){
            if(board[j][k] == -1) arr[i++] = board[j][k];
            else arr[i++] = board[j][k]-1;
            if(flag){
                k++;
            }
            else{
                k--;
            }
            if(k == n){
                k = n-1;
                j--;
                flag = false;
            }
            if(k == -1){
                k=0;
                j--;
                flag=true;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int rolls = 0;
//BFS
        while(!q.isEmpty()){
            int size = q.size();
            rolls++;
            for(int l=0; l<size; l++){
                i = q.poll();
                for(j=1; j<=6; j++){
                    i++;
                    if(i == (n*n)-1) return rolls;
                    if(arr[i] != -2){
                        if(arr[i] == -1){
                            arr[i] = -2;
                            q.add(i);
                        } else{
                            if(arr[i] == (n*n)-1) return rolls;
                            if(arr[arr[i]] != -2){
                                q.add(arr[i]);
                                arr[i] = -2;
                            } 
                        }
                    }
                }
            }
        }
        return -1;
    }
}