// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int snakesAndLadders(int[][] board) {
        
        if(board == null || board.length == 0) return 0;
        
        Queue<Integer> q = new LinkedList<>();
        int n = board.length;
        
        // flatten the matrix
        boolean flag = false;
        int i = n - 1;
        int j = 0;
        int[] arr = new int[n*n];
        int idx = 0; // flat arr index
        
        while(idx < arr.length) {
            if(board[i][j] == -1) {
                arr[idx] = -1;
            }
            else {
                arr[idx] = board[i][j] - 1;
            }
            idx++;
            if(flag) {
                j--;
                if(j == -1) {
                    i--;
                    j++;
                    flag = false;
                }
            }
            else {
                j++;
                if(j == n) {
                    i--;
                    j--; // j has gone out of bounds too
                    flag = true;
                }
            }
        }
        
        int moves = 0;
        q.add(0);
        arr[0] = -2; // mark an index as visited
        while(!q.isEmpty()) {
            int size = q.size();
            for(int k = 0; k < size; k++) {
                int curr = q.poll();
                for(int d = 1; d < 7; d++) {
                    int child = curr + d;                   
                    if(arr[child] != -2) {
                        if(arr[child] >= 0) {
                            if(arr[child] == n*n - 1) return moves + 1;
                            q.add(arr[child]);
                        }
                        else {
                            if(child == n*n - 1) {
                        return moves + 1;
                    }
                            q.add(child);
                        }
                        arr[child] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}