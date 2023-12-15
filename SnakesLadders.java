// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class SnakesLadders {
    class Solution {
        public int snakesAndLadders(int[][] board) {
            int n = board.length;
            int arr[] = new int[n*n];
            int idx = 0;
            int even = 0;

            int row = n-1;
            int col = 0;
            while(idx < n*n){
                if(board[row][col] != -1)
                    arr[idx] = board[row][col] - 1;
                else
                    arr[idx] = board[row][col];
                idx++;

                if(even%2 == 0){
                    //even row
                    col++;
                    if(col == n){
                        row--;
                        col--;
                        even++;
                    }
                }
                else{
                    //odd row
                    col--;
                    if(col == -1){
                        row--;
                        col++;
                        even++;
                    }
                }
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            arr[0] = -2;
            int result = 0;

            while(!q.isEmpty()){
                int size = q.size();
                for(int i = 0; i < size; i++){
                    int curr = q.poll();
                    if(curr == n*n - 1)
                        return result;

                    for(int j = 1; j <= 6; j++){
                        int child = curr + j;

                        if(child > n*n-1)
                            break;

                        if(arr[child] != -2){
                            if(arr[child] == -1)
                                q.add(child);
                            else
                                q.add(arr[child]);

                            arr[child] = -2;
                        }
                    }
                }
                result++;
            }
            return -1;
        }
    }
}
