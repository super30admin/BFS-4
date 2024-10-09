// Problem 909. Snakes and Ladders
// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int idx = 0; // idx on 1d array
        int r = n-1; int c = 0;
        boolean flag = true;
        // Arrays.fill(moves, -1);
        while(idx < arr.length){
            if(board[r][c] == -1){
                arr[idx] = -1;
            }else{
                arr[idx] = board[r][c]-1;
            }
            idx++;
            // new r, c
            if(flag){
                c++;
                if(c == n){
                    flag = false;
                    r--;
                    c--;
                }
            }else{
                c--;
                if(c < 0){
                    flag = true;
                    r--;
                    c++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        q.add(0); set.add(0);
        int count = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == n*n-1) return count;
                for(int j = 1; j <= 6; j++){
                    int baby = curr + j;
                    if(baby >= arr.length){
                        break;
                    }
                    if(arr[baby] == -1){
                        if(!set.contains(baby)){
                            q.add(baby); 
                            set.add(baby); 
                        }
                    }else{
                        if(!set.contains(arr[baby])){
                            q.add(arr[baby]); 
                            set.add(arr[baby]); 
                        }
                    }
                }
            }
            count++;
        }
        return -1;
    }
}
