// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        boolean dir = true;
        int i = n-1;
        int j = 0;
        int idx = 0;
        while(idx < n*n){
            if(board[i][j] == -1)
                arr[idx] = -1;
            else
                arr[idx] = board[i][j] - 1;
            idx++;
            if(dir == true){
                j++;
                 if(j == n){
                    i--;
                    j--;
                    dir = false;
                }
            } else {
                j--;
                if(j < 0){
                    i--;
                    j++;
                    dir = true;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2; //visited
        int moves = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(i = 0; i < size; i++){
                int currIdx = q.poll();
                for(j = 1; j <= 6; j++){
                    int newIdx = currIdx + j;
                    if(newIdx == n*n-1)
                        return moves + 1;
                    if(arr[newIdx] != -2){
                        if(arr[newIdx] == -1){
                            q.add(newIdx);
                        } else {
                            if(arr[newIdx] == n*n-1)
                                return moves + 1;
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}