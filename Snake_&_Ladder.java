// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Nos


class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int row = n-1;
        int col = 0;
        int[] nums = new int[n * n]; 
        int idx = 0;
        int even = 0;
        
        Queue<Integer> q = new LinkedList<>();
        int moves =0;

        // flatten board according to snake and ladder index
        while(idx < n*n){
            if(board[row][col] != -1){
                nums[idx] = board[row][col] -1;
            }
            else{
                nums[idx] = -1;
            }
            idx++;
            if(even % 2 == 0){
                col++;
                if(col == n){
                    col = n-1;
                    row--;
                    even++;
                }
            }
            else{
                col--;
                if(col == -1){
                    col = 0;
                    row--;
                    even++;
                }
            }
        }

        //BFS
        q.add(0);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i =0 ; i< size; i++){
                int curr = q.poll();

                if(curr == n*n -1){
                    return moves;
                }

                for(int j = 1; j<=6; j++){
                    int index = curr + j;
                    if(index >= n*n){
                        continue;
                    }

                    if(nums[index] == -1){
                        q.add(index);
                        nums[index] = -2;
                    }
                    else{
                        if(nums[index] != -2){
                            q.add(nums[index]);
                            nums[index] = -2;
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}