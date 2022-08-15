// Time Complexity : O(n^2*6) 
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this: No


class Solution {
    public int snakesAndLadders(int[][] board) {
        //null
        if(board  == null || board.length == 0) return 0;
        int n = board.length;
        int [] arr = new int [n*n];
        int idx = 0; //idx on flattened arr
        int even = 0;; int r = n-1; int c = 0;
        while(idx < n*n){
            if(board[r][c] >= 1){
                arr[idx]= board[r][c] - 1;
            } else {
                arr[idx] = board[r][c];
            }
            idx++;
            //new r and c
            if(even%2 == 0){
                c++;
                if(c == n){ //if last in colomn than decrese row for upper row
                    r--; c--;
                    even++;
                }
            } else{
                c--;
                if(c == -1){
                    r--; c++;
                    even++;
                }
            }
        }
        //flatterened arr
        Queue<Integer> q = new LinkedList<>();
        q.add(0); arr[0] = -2;
        int count = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i <size; i++){
                int curr = q.poll();
                if(curr == n*n - 1) return count;
                //
                for(int j = 1;j <= 6; j++){
                    int child = curr + j;
                    //check
                    if(child == n*n - 1) return count + 1;
                    if(child > n*n - 1) break;
                    if(arr[child] != -2){
                        if(arr[child] == -1){
                            q.add(child);
                        } else {
                            if(arr[child] == n*n -1) return count+1;
                            q.add(arr[child]);
                        }
                        arr[child] = -2;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}